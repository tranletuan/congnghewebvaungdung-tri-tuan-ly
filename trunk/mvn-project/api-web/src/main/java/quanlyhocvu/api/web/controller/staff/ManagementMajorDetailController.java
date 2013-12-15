/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietChuyenMonDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.ChiTietMonHocDTO;
import quanlyhocvu.api.mongodb.DTO.Teacher.PhanCongDTO;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/major_detail/")
public class ManagementMajorDetailController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView majorDetailList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();  
        List<ChiTietChuyenMonDTO> listChiTietChuyenMon = mongoService.getAllChiTietChuyenMon();
        map.put("listChiTietChuyenMon", listChiTietChuyenMon);
        return new ModelAndView("staff/management/major_detail/index", map);
        
    }
    
    @RequestMapping(value="new")
    public @ResponseBody
    ModelAndView newMajorDetail(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();  
        List<ChiTietMonHocDTO> listChiTietMonHoc = mongoService.getAllChiTietMonHoc();
        List<GiaoVienDTO> listGiaoVien = mongoService.getAllgiaoVien();
        map.put("listChiTietMonHoc", listChiTietMonHoc);
        map.put("listGiaoVien", listGiaoVien);
        return new ModelAndView("staff/management/major_detail/new", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView saveMajorDetail(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();  
        String chiTietMonHocId = request.getParameter("chiTietMonHoc");
        ChiTietMonHocDTO chiTietMonHoc = mongoService.getChiTietMonHocById(chiTietMonHocId);
        String giaoVienId = request.getParameter("giaoVien");
        GiaoVienDTO giaoVien = mongoService.getTeacherByid(giaoVienId);
        ChiTietChuyenMonDTO chiTietChuyenMon = new ChiTietChuyenMonDTO();
        chiTietChuyenMon.setChiTietMonHoc(chiTietMonHoc);
        chiTietChuyenMon.setGiaoVien(giaoVien);
        mongoService.insertChiTietChuyenMon(chiTietChuyenMon);
        return new ModelAndView("redirect:/staff/management/major_detail/index",map);
    }
}
