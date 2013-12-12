/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import quanlyhocvu.api.mongodb.DTO.staff.KhoiLopDTO;
import quanlyhocvu.api.mongodb.DTO.staff.LopHocDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NamHocDTO;
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
        List<NamHocDTO> listNamHoc = mongoService.getAllnamHoc();        
        List<KhoiLopDTO> listKhoiLop = mongoService.getAllkhoiLop();
        NamHocDTO namHocDuocChon = null;
        KhoiLopDTO khoiLopDuocChon = null;                        
        String namHienTai =  FunctionService.namHocHienTai();
        namHocDuocChon = mongoService.getnamHocByName(namHienTai);          
        khoiLopDuocChon = mongoService.getKhoiLopByName("6");            
        map.put("namHienTai", namHocDuocChon);
        map.put("listNamHoc", listNamHoc);
        map.put("khoiLopMacDinh", khoiLopDuocChon);
        map.put("listKhoiLop", listKhoiLop);               
        
        return new ModelAndView("staff/management/students/index", map);        
        
    }
    
    @RequestMapping(value="load")
    public @ResponseBody
    ModelAndView loadStudents(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();         
        String namHocId = request.getParameter("namHocId");
        String khoiLopId = request.getParameter("khoiLopId");
        List<LopHocDTO> listLopHoc = mongoService.getLopHocTheoNamHocKhoiLop(namHocId, khoiLopId);
        List<HocSinhDTO> listHocSinh = mongoService.getHocSinhChuaXepLopTheoKhoiLop(khoiLopId);  
        LopHocDTO lopHocDuocChon = null;
        String laNamHienTai = "1";
        NamHocDTO namHoc = mongoService.getnamHocById(namHocId);
        if (namHoc.gettenNamHoc().equals(FunctionService.namHocHienTai())) {                
                laNamHienTai = null;                
            }
        map.put("laNamHienTai", laNamHienTai);
        map.put("listLopHoc", listLopHoc);
        map.put("listHocSinh", listHocSinh);
        map.put("namHocId", namHocId);
        map.put("khoiLopId", khoiLopId);
        map.put("lopHocDuocChon", lopHocDuocChon);
        return new ModelAndView("staff/management/students/subindex", map);
    }
    
    @RequestMapping(value = "request_data")
    public @ResponseBody
    ModelAndView requestDataStudents(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>(); 
        List<HocSinhDTO> listHocSinh = new ArrayList<HocSinhDTO>();        
        List<LopHocDTO> listLopHoc = new ArrayList<LopHocDTO>(); ; 
        String namHocId;
        String khoiLopId;
        String lopHocId;
        LopHocDTO lopHocDuocChon = null;
        if(request.getParameter("requestElement").equals("namHoc") || request.getParameter("requestElement").equals("khoiLop")){                                    
            namHocId = request.getParameter("namHocId");
            NamHocDTO namHocDuocChon = mongoService.getnamHocById(namHocId);
            khoiLopId = request.getParameter("khoiLopId");
            listLopHoc = mongoService.getLopHocTheoNamHocKhoiLop(namHocId, khoiLopId);
            String laNamHienTai = "1";//Bien nay dung de kiem tra neu la nam hien tai thi khong hien Hoc Sinh Chua Xep Lop           
            //Neu lam nam hoc hien tai thi se lay danh sach hoc sinh chua xep lop
            //Nguoc lai se lay danh sach hoc sinh cua lop dau tien trong khoi, nam hoc do
            if (namHocDuocChon.gettenNamHoc().equals(FunctionService.namHocHienTai())) {                
                laNamHienTai = null;
                lopHocDuocChon = null;
                listHocSinh = mongoService.getHocSinhChuaXepLopTheoKhoiLop(khoiLopId);
            } else {
                //Neu co danh sach lop hoc thi lay lop dau tien
                //Nguoi lai thi tra ve null
                if (listLopHoc.size() > 0 ) {
                    lopHocDuocChon = listLopHoc.get(0);
                    listHocSinh = mongoService.getStudentsByLopHoc(lopHocDuocChon.getid());
                }else{
                    lopHocDuocChon = null;
                    listHocSinh = null;  
                } 
            }                         
            map.put("laNamHienTai", laNamHienTai);
            map.put("listHocSinh", listHocSinh);        
            map.put("listLopHoc", listLopHoc);
            map.put("namHocId", namHocId);
            map.put("khoiLopId", khoiLopId);
            map.put("lopHocDuocChon", lopHocDuocChon);                           
            return new ModelAndView("staff/management/students/subindex", map); 
            
        }else if(request.getParameter("requestElement").equals("lopHoc")){            
            lopHocId = request.getParameter("lopHocId");
            namHocId = request.getParameter("namHocId");
            khoiLopId = request.getParameter("khoiLopId");
            if (lopHocId.equals("0")) {
                //Lay danh sach hoc sinh theo khoi Lop
                listHocSinh = mongoService.getHocSinhChuaXepLopTheoKhoiLop(khoiLopId); 
            }else{
                listHocSinh = mongoService.getStudentsByLopHoc(lopHocId);
            }            
            listLopHoc = mongoService.getLopHocTheoNamHocKhoiLop(namHocId, khoiLopId);
            map.put("listHocSinh", listHocSinh);
            map.put("listLopHoc", listLopHoc);
            return new ModelAndView("management/students/sub_table_student", map); 
        }  
        return new ModelAndView("management/students/subindex", map); 
    }

    @RequestMapping(value = "new")
    public @ResponseBody
    ModelAndView newStudent(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<KhoiLopDTO> listkhoiLop = mongoService.getAllkhoiLop();
        KhoiLopDTO khoiLop6 = mongoService.getKhoiLopByName("6");
        map.put("listkhoiLop", listkhoiLop);
        map.put("khoiLop6", khoiLop6);
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
        KhoiLopDTO khoiLopHienTai = mongoService.getKhoiLopByid(request.getParameter("IDKhoiLop"));
        obj.setKhoiLopHienTai(khoiLopHienTai);
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
    
    @RequestMapping(value="xeplop")
    public @ResponseBody
    ModelAndView xeplopHocSinh(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();    
        if (request.getParameter("radio_value").equals("6")){            
            xepHocSinhVaoLopHoc("6");
        }else{            
            xepHocSinhVaoLopHoc("6");
            xepHocSinhVaoLopHoc("7");
            xepHocSinhVaoLopHoc("8");
            xepHocSinhVaoLopHoc("9");
            
        };        
        map.put("message", "Đã xếp lớp thành công cho tất cả học sinh mới");
        return new ModelAndView("redirect:/staff/management/students/index",map);
    }
    
    @RequestMapping(value="xeplop_hocsinh")
    public @ResponseBody
    ModelAndView xepLopHocSinh(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();            
        String hocSinhId = request.getParameter("hocSinhId");
        String lopHocId = request.getParameter("lopHocId");
        HocSinhDTO hocSinh = mongoService.getHocSinhById(hocSinhId);
        hocSinh.setMaLopHoc(lopHocId);
        mongoService.updateStudent(hocSinh);
        mongoService.addStudent(hocSinh, lopHocId);
        return new ModelAndView("redirect:/staff/management/students/index",map);
    }        
    
    public void xepHocSinhVaoLopHoc(String tenKhoiLop){
        //Xep hoc sinh vao theo khoi lop
        KhoiLopDTO khoiLop = mongoService.getKhoiLopByName(tenKhoiLop);
        List<HocSinhDTO> listHocSinh = mongoService.getHocSinhChuaXepLopTheoKhoiLop(khoiLop.getid());        
        List<LopHocDTO> listLopHoc = mongoService.getLopHocTheoKhoiLop(khoiLop.getid());                          
        HocSinhDTO hocSinh;
        LopHocDTO lopHoc;
        int count = 0;        
        if (listLopHoc.size() > 0) {
            for (int i = 0; i < listHocSinh.size(); i++) {                
                if (count >= listLopHoc.size()) {
                    count = 0;
                }
                if (listLopHoc.get(count).getlistHocSinh().size() < FunctionService.SoHocSinhToiDaMotLop) {                    
                    hocSinh = listHocSinh.get(i);
                    lopHoc = listLopHoc.get(count);
                    hocSinh.setMaLopHoc(lopHoc.getid());
                    mongoService.updateStudent(hocSinh);
                    mongoService.addStudent(hocSinh, lopHoc.getid());
                }else{
                    listLopHoc.remove(listLopHoc.get(count));
                }            
                count++;
            }
        }
        
    }
            
}
