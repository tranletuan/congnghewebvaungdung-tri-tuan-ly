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
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/school_year")
public class ManagementSchoolYearController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView schoolYearList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();        
        List<NamHocDTO> listnamHoc = new ArrayList<NamHocDTO>();
        listnamHoc = mongoService.getAllnamHoc();
        map.put("listnamHoc", listnamHoc);
        return new ModelAndView("staff/management/school_year/index", map);
    }

    @RequestMapping(value="new")
    public @ResponseBody
    ModelAndView newSchoolYear(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        
        return new ModelAndView("staff/management/school_year/new", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView saveSchoolYear(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        NamHocDTO obj = new NamHocDTO(
                    formatSchoolYear(request.getParameter("tennamHoc")),
                    request.getParameter("moTa")                    
                );
        boolean res = mongoService.insertnamHoc(obj);
        map.put("message", "Đã thêm thành công 1 năm học");
        return new ModelAndView("redirect:/staff/management/school_year/index", map);
    }
    
    public String formatSchoolYear(String namHoc){        
        String[] res = namHoc.split("\\D");
        int startYear = Integer.parseInt(res[0]);
        int endYear = Integer.parseInt(res[1]);
        return startYear + "-" + endYear;
    }
}
