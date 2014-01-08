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
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
@RequestMapping(value = "management/major/")
public class ManagementMajorController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView major(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();  
        List<PhanCongDTO> listPhanCong = mongoService.getAllPhanCong();  
        //loa bo cai nao co chi tiet phan cong = null
        for (PhanCongDTO phanCong : listPhanCong){
            if (phanCong == null || phanCong.getChiTietChuyenMon() == null || phanCong.getClass() == null){
                listPhanCong.remove(phanCong);
            }
        }
        map.put("listPhanCong", listPhanCong);
        
        return new ModelAndView("staff/management/major/list", map);
        
    }
    
    @RequestMapping(value = "new")
    public @ResponseBody
    ModelAndView newMajor(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();  
        List<ChiTietChuyenMonDTO> listChiTietChuyenMon = mongoService.getAllChiTietChuyenMon();
        List<LopHocDTO> listLopHoc = mongoService.getAllLopHoc();                
        List<MonHocDTO> listMonHoc = mongoService.getAllMonHoc();
        List<GiaoVienDTO> listGiaoVien = mongoService.getAllgiaoVien();
        map.put("listChiTietChuyenMon", listChiTietChuyenMon);
        map.put("listLopHoc", listLopHoc);  
        map.put("listMonHoc", listMonHoc);        
        map.put("listGiaoVien", listGiaoVien);
        
        
        return new ModelAndView("staff/management/major/new", map);
        
    }
    
    
    @RequestMapping(value = "loadChiTietMonHoc")
    public @ResponseBody
    ModelAndView loadChiTietMonHoc(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();    
        String lopHocId = request.getParameter("lopHocId");
        LopHocDTO lopHoc = mongoService.getLopHocById(lopHocId);
        List<ChiTietMonHocDTO> listChiTietMonHoc = mongoService.getByKhoiLopId(lopHoc.getkhoiLop().getid());
        map.put("listChiTietMonHoc", listChiTietMonHoc);        
        return new ModelAndView("staff/management/major/select_monhoc", map);        
    }
    
    @RequestMapping(value = "loadGiaoVien")
    public @ResponseBody
    ModelAndView loadGiaoVien(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("loadGiaoVien");
        String chiTietMonHocId = request.getParameter("chiTietMonHocId");        
        List<ChiTietChuyenMonDTO> listChiTietChuyenMon = mongoService.getListByChiTietMonHoc(chiTietMonHocId);
        
        map.put("listChiTietChuyenMon", listChiTietChuyenMon);         
        return new ModelAndView("staff/management/major/select_giaovien", map);        
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView save(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("Here");
        int hocKy = Integer.parseInt(request.getParameter("hocKy"));
        String lopHocId = request.getParameter("lopHoc");        
        LopHocDTO lopHoc = mongoService.getLopHocById(lopHocId);
        String chiTietChuyenMonId = request.getParameter("chiTietChuyenMon");
        ChiTietChuyenMonDTO chiTietChuyenMon = mongoService.getChiTietChuyenMonById(chiTietChuyenMonId);
        PhanCongDTO phanCong = new PhanCongDTO();
        phanCong.setChiTietChuyenMon(chiTietChuyenMon);
        phanCong.setLopHoc(lopHoc);
        phanCong.setHocKy(hocKy);
        mongoService.insertPhanCong(phanCong);        
        return new ModelAndView("redirect:/staff/management/major/index", map);
    }
    
    
}
