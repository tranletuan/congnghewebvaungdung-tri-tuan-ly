/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.ArrayList;
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
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/class")
public class ManagementClassController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView classList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();        
        List<LopHocDTO> listLopHoc = new ArrayList<LopHocDTO>();
        listLopHoc = mongoService.getAllLopHoc();
        map.put("listLopHoc", listLopHoc);
        return new ModelAndView("management/class/index", map);
    }

    @RequestMapping(value="new")
    public @ResponseBody
    ModelAndView newClass(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        
        return new ModelAndView("management/class/new", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView saveClass(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LopHocDTO obj = new LopHocDTO(
                    request.getParameter("TenLopHoc"),
                    request.getParameter("IDNamHoc"),
                    request.getParameter("IDKhoiLop"),
                    request.getParameter("IDGiaoVien")
                );
        boolean res = mongoService.insertLopHoc(obj);
        map.put("message", "Đã thêm thành công 1 lớp học");
        return new ModelAndView("redirect:/staff/management/class/index", map);
    }
}