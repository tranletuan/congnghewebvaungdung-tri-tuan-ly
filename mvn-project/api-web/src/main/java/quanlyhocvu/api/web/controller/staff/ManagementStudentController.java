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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.service.FunctionService;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author linhly
 */
@Controller
@RequestMapping(value = "management/students")
public class ManagementStudentController {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @Autowired
    MongoService mongoService;

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView listStudents(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();        
        List<HocSinhDTO> listHocSinh = new ArrayList<HocSinhDTO>();
        listHocSinh = mongoService.getAllStudents();
        map.put("listHocSinh", listHocSinh);
        map.put("stt", 1);
        return new ModelAndView("management/students/index", map);
    }

    @RequestMapping(value = "new")
    public @ResponseBody
    ModelAndView newStudent(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        return new ModelAndView("management/students/new", map);
    }

    @RequestMapping(value = "save")
    public @ResponseBody
    ModelAndView saveStudent(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //add student to class -> just Demo        
        //LopHocDTO lopHoc = mongoService.getAllLopHoc().get(2);//Demo to add Student to Class        
        java.util.Date ex = new java.util.Date(request.getParameter("ngaySinh"));        
        HocSinhDTO obj = new HocSinhDTO();
        obj.sethoTen(request.getParameter("hoTen"));
        obj.setgioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));  
        obj.setdiaChi(request.getParameter("diaChi"));
        obj.setmaHocSinh(request.getParameter("maHocSinh"));
        String ngaySinh = request.getParameter("ngaySinh");
        if (ngaySinh != "") {
            obj.setngaySinh(new java.util.Date(FunctionService.formatStringDate(ngaySinh)));            
        }
        String ngayNhapHoc = request.getParameter("ngayNhapHoc");
        if (ngayNhapHoc != "") {
            obj.setngayNhapHoc(new java.util.Date(FunctionService.formatStringDate(ngayNhapHoc)));            
        }    
//        obj.setLopHoc(lopHoc);
        //obj.setMaLopHoc(lopHoc.getid());//Demo to add Student to Class
        boolean res = mongoService.insertStudent(obj);   
        //mongoService.addStudent(obj, lopHoc.getid());
        map.put("message", "Đã thêm thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index", map);
    }
    
    
    
    @RequestMapping(value = "edit/{studentId}")
    public @ResponseBody
    ModelAndView editStudent(@PathVariable String studentId,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HocSinhDTO obj = mongoService.getStudentByMaHS(studentId);        
        map.put("hocSinh", obj);
        return new ModelAndView("management/students/edit", map);
    }
    
    @RequestMapping(value = "update/{studentId}")
    public @ResponseBody
    ModelAndView updateStudent(@PathVariable String studentId,HttpServletRequest request) {        
        Map<String, Object> map = new HashMap<String, Object>();
        //get the student by studentId
        HocSinhDTO obj = mongoService.getStudentByMaHS(studentId); 
        //update information for student
        obj.setdiaChi(request.getParameter("diaChi"));
        obj.sethoTen(request.getParameter("hoTen"));
        
        obj.setgioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));
        obj.setmaHocSinh(request.getParameter("maHocSinh"));
        String ngaySinh = request.getParameter("ngaySinh");
        if (ngaySinh != "") {
            obj.setngaySinh(new java.util.Date(FunctionService.formatStringDate(ngaySinh)));
        }         
        String ngayNghiHoc = request.getParameter("ngayNghiHoc");
        if (ngayNghiHoc != "") {
            obj.setngayNghiHoc(new java.util.Date(FunctionService.formatStringDate(ngayNghiHoc)));
        }  
        String ngayNhapHoc = request.getParameter("ngayNhapHoc");
        if (ngayNhapHoc != "") {
            obj.setngayNhapHoc(new java.util.Date(FunctionService.formatStringDate(ngayNhapHoc)));
        }        
                
        //update to database
        boolean res = mongoService.updateStudent(obj);        
        map.put("message", "Đã chỉnh sửa thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index", map);
    }
    
    @RequestMapping(value="delete/{studentId}")
    public @ResponseBody
    ModelAndView deleteStudent(@PathVariable("studentId") String studentId, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        HocSinhDTO obj = mongoService.getStudentByMaHS(studentId);
        boolean res = mongoService.deleteStudent(obj);
        map.put("message", "Đã xóa thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index",map);
    }      
    
}
