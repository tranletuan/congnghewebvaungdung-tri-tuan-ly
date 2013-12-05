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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import sun.rmi.runtime.Log;

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
        java.util.Date ex = new java.util.Date(request.getParameter("ngaySinh"));
        HocSinhDTO obj = new HocSinhDTO(
                request.getParameter("hoTen"),
                Integer.parseInt(request.getParameter("gioiTinh")),
                new java.util.Date(request.getParameter("ngaySinh")),
                request.getParameter("diaChi"),
                request.getParameter("maHocSinh"),
                new java.util.Date(request.getParameter("ngayNhapHoc"))
                );
        boolean res = mongoService.insertStudent(obj);        
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
        System.out.println("StudentId: "+ studentId);
        HocSinhDTO obj = mongoService.getStudentByMaHS(studentId); 
        //update information for student
        obj.setdiaChi(request.getParameter("diaChi"));
        obj.sethoTen(request.getParameter("hoTen"));
        
        obj.setgioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));
        obj.setmaHocSinh(request.getParameter("maHocSinh"));
        String ngaySinh = request.getParameter("ngaySinh");
        if (ngaySinh != "") {
            obj.setngaySinh(new java.util.Date(formatStringDate(ngaySinh)));
        }         
        String ngayNghiHoc = request.getParameter("ngayNghiHoc");
        if (ngayNghiHoc != "") {
            obj.setngayNghiHoc(new java.util.Date(formatStringDate(ngayNghiHoc)));
        }  
        String ngayNhapHoc = request.getParameter("ngayNhapHoc");
        if (ngayNhapHoc != "") {
            obj.setngayNhapHoc(new java.util.Date(formatStringDate(ngayNhapHoc)));
        }        
                
        //update to database
        boolean res = mongoService.updateStudent(obj);        
        map.put("message", "Đã chỉnh sửa thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index", map);
    }
    
    @RequestMapping(value="delete/{studentId}")
    public @ResponseBody
    ModelAndView deleteStudent(@PathVariable("studentId") String studentId, HttpServletRequest request){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAa");
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("Enter function");
        HocSinhDTO obj = mongoService.getStudentByMaHS(studentId);
        System.out.println(obj);
        boolean res = mongoService.deleteStudent(obj);
        map.put("message", "Đã xóa thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index",map);
    }      
     
    public String formatStringDate(String date){
        String res = "";
        String[] arr = date.split("/");
        res += arr[1] + "/" + arr[0]+ "/" + arr[2];
        return res;
        
    }
    
}
