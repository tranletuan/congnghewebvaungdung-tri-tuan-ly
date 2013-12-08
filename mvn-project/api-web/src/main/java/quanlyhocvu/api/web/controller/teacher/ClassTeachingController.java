/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietChuyenMonDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
public class ClassTeachingController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value = "classteaching")
    public @ResponseBody
    ModelAndView classTeaching(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ChiTietChuyenMonDTO> listChuyenMon = mongoService.getListChiTietChuyenMonByIdGiaoVien("52a0aa8d44aeb4910f530db8");
        List<PhanCongDTO> listPhanCong = new ArrayList<PhanCongDTO>();
        
        for(int i = 0; i < listChuyenMon.size(); i++) {
            List<PhanCongDTO> listTemp = mongoService.getListPhanCongBy(listChuyenMon.get(i).getid());
            listPhanCong.addAll(listTemp);
        }
        
        map.put("listPhanCong", listPhanCong);
        
        return new ModelAndView("classteaching", map);
    }
    
    @RequestMapping(value = "classteaching/{classId}")
    public @ResponseBody
    ModelAndView grading(@PathVariable String classId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LopHocDTO lop = mongoService.getLopHocById(classId);
        List<HocSinhDTO> listHocSinh = lop.getlistHocSinh();
        map.put("lopHoc", lop);
        map.put("listHocSinh", listHocSinh);
        return new ModelAndView("class", map);
    }
}
