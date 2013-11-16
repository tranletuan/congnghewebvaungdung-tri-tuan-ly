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
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/grade")
public class ManagementGradeController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView gradeList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();        
        List<KhoiLopDTO> listKhoiLop = new ArrayList<KhoiLopDTO>();
        listKhoiLop = mongoService.getAllKhoiLop();
        map.put("listKhoiLop", listKhoiLop);
        return new ModelAndView("management/grade/index", map);
    }

    @RequestMapping(value="new")
    public @ResponseBody
    ModelAndView newGrade(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        
        return new ModelAndView("management/grade/new", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView saveGrade(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        KhoiLopDTO obj = new KhoiLopDTO(
                    request.getParameter("TenKhoiLop"),
                    request.getParameter("MoTa")                    
                );
        boolean res = mongoService.insertKhoiLop(obj);
        map.put("message", "Đã thêm thành công 1 khối lớp");
        return new ModelAndView("redirect:/staff/management/grade/index", map);
    }
}
