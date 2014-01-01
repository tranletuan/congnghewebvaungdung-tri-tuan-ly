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
import quanlyhocvu.api.mongodb.DTO.staff.MonHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/subject")
public class ManagementSubjectController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView subjectList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();        
        List<MonHocDTO> listMonHoc = new ArrayList<MonHocDTO>();
        listMonHoc = mongoService.getAllMonHoc();
        map.put("listMonHoc", listMonHoc);
        return new ModelAndView("staff/management/subject/index", map);
    }

    @RequestMapping(value="new")
    public @ResponseBody
    ModelAndView newSubject(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        
        return new ModelAndView("staff/management/subject/new", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView saveSubject(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        MonHocDTO obj = new MonHocDTO(
                    request.getParameter("tenMonHoc"),
                    request.getParameter("moTa")                    
                );
        boolean res = mongoService.insertMonHoc(obj);
        map.put("message", "Đã thêm thành công 1 môn học");
        return new ModelAndView("redirect:/staff/management/subject/index", map);
    }
    
    public List<MonHocDTO> getListMonHoc(){
        System.out.println(mongoService.getAllMonHoc());
        return mongoService.getAllMonHoc();
    }
}
