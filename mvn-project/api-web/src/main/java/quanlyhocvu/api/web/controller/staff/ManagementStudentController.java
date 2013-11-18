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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
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
        java.util.Date ex = new java.util.Date(request.getParameter("NgaySinh"));
//        java.sql.Date ngaySinhS = Date.valueOf(request.getParameter("NgaySinh"));
//        java.util.Date ngaySinh = new java.util.Date(ngaySinhS.getYear(), ngaySinhS.getMonth(), ngaySinhS.getDay());
//        java.sql.Date NgayNhapHocS = Date.valueOf(request.getParameter("NgayNhapHoc"));
//        java.util.Date ngayNhapHoc = new java.util.Date(NgayNhapHocS.getYear(), NgayNhapHocS.getMonth(), NgayNhapHocS.getDay());
//        java.sql.Date ngayNghiHocS = Date.valueOf(request.getParameter("NgayNghiHoc"));
//        java.util.Date ngayNghiHoc = new java.util.Date(ngayNghiHocS.getYear(), ngayNghiHocS.getMonth(), ngayNghiHocS.getDay());
        HocSinhDTO obj = new HocSinhDTO(
                request.getParameter("HoTen"),
                Integer.parseInt(request.getParameter("GioiTinh")),
                new java.util.Date(request.getParameter("NgaySinh")),
                request.getParameter("DiaChi"),
                request.getParameter("MaHocSinh"),
                new java.util.Date(request.getParameter("NgayNhapHoc")),
                new java.util.Date(request.getParameter("NgayNghiHoc"))
                );
        boolean res = mongoService.insertStudent(obj);        
        map.put("message", "Đã thêm thành công 1 học sinh");
        return new ModelAndView("redirect:/staff/management/students/index", map);
    }
}
