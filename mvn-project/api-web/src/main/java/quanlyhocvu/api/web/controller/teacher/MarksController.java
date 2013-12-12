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
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietDiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.DiemDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
public class MarksController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "class")
    public @ResponseBody
    ModelAndView Class(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ChiTietChuyenMonDTO> listChuyenMon = mongoService.getListChiTietChuyenMonByIdGiaoVien("52a0aa8d44aeb4910f530db8");
        List<PhanCongDTO> listPhanCong = new ArrayList<PhanCongDTO>();

        for (int i = 0; i < listChuyenMon.size(); i++) {
            List<PhanCongDTO> listTemp = mongoService.getListPhanCongBy(listChuyenMon.get(i).getid());
            listPhanCong.addAll(listTemp);
        }

        map.put("listPhanCong", listPhanCong);

        return new ModelAndView("teacher/class", map);
    }

    @RequestMapping(value = "class/{majorId}")
    public @ResponseBody
    ModelAndView List(@PathVariable String majorId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        PhanCongDTO phanCong = mongoService.getPhanCongById(majorId);

        LopHocDTO lopHoc = phanCong.getLopHoc();
        MonHocDTO monHoc = phanCong.getChiTietChuyenMon().getChiTietMonHoc().getMonHoc();

        List<HocSinhDTO> listHocSinh = lopHoc.getlistHocSinh();
        map.put("phanCong", phanCong);
        map.put("lopHoc", lopHoc);
        map.put("monHoc", monHoc);
        map.put("listHocSinh", listHocSinh);
        return new ModelAndView("teacher/list", map);
    }

    @RequestMapping(value = "class/{majorId}/{studentId}")
    public @ResponseBody
    ModelAndView marks(@PathVariable String majorId, @PathVariable String studentId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        PhanCongDTO phanCong = mongoService.getPhanCongById(majorId);
        LopHocDTO lopHoc = phanCong.getLopHoc();
        HocSinhDTO hocSinh = mongoService.getHocSinhLopHocById(lopHoc, studentId);
        DiemDTO diem = mongoService.getDiemByIdPhanCong(majorId);
        ChiTietDiemDTO chiTietDiem = mongoService.getChiTietDiemByIdDiem(diem);
        
        map.put("diem", diem);
        map.put("hocSinh", hocSinh);
        map.put("chiTietDiem", chiTietDiem);
        return new ModelAndView("teacher/mark", map);
    }

}
