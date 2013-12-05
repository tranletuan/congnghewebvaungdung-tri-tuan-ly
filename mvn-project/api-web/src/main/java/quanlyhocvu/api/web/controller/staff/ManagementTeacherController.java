/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.GiaoVienDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value="management/teacher")
public class ManagementTeacherController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value="list")
    public @ResponseBody
    ModelAndView teacherList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
        int res = Integer.parseInt(request.getParameter("result"));
        System.out.println("asfasdfadfadsfasdfadsfs " + res);
        } catch (Exception ex) {
          
        }
        
        List<GiaoVienDTO> listgiaoVien = new ArrayList<GiaoVienDTO>();
        listgiaoVien = mongoService.getAllgiaoVien();
        map.put("listgiaoVien", listgiaoVien);
        return new ModelAndView("management/teacher/list", map);
    }
    
    @RequestMapping(value="add")
    public @ResponseBody
    ModelAndView teacherAdd(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        
        return new ModelAndView("management/teacher/add", map);
    }
    
    @RequestMapping(value="save")
    public @ResponseBody
    ModelAndView teacherSave(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        GiaoVienDTO obj = new GiaoVienDTO(
                    request.getParameter("magiaoVien"),
                    request.getParameter("hoTen"),
                    Integer.parseInt(request.getParameter("gioiTinh")),
                    Date.valueOf(request.getParameter("ngaySinh")),
                    request.getParameter("diaChi"),
                    Date.valueOf(request.getParameter("ngayVaoLam"))
                );
        boolean res = mongoService.insertgiaoVien(obj);
        map.put("message", "Đã thêm thành công 1 giáo viên");
        return new ModelAndView("redirect:/staff/management/teacher/list", map);
    }
    
    

}
