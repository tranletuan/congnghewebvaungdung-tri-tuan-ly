/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;
import quanlyhocvu.api.mongodb.service.FunctionService;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/staff")
public class ManagementStaffController {

     @Autowired
     MongoService mongoService;

     @RequestMapping(value = "index")
     public @ResponseBody
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          List<StaffDTO> staffs = mongoService.getAllStaff();
          model.put("staffs", staffs);
          return new ModelAndView("management/staff/index", model);
     }

     @RequestMapping(value = "add")
     public @ResponseBody
     ModelAndView add(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();

          return new ModelAndView("management/staff/add", model);
     }

     @RequestMapping(value = "save")
     public @ResponseBody
     ModelAndView teacherSave(HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          StaffDTO obj = new StaffDTO(
                  request.getParameter("manhanvien"),
                  request.getParameter("hoTen"),
                  Integer.parseInt(request.getParameter("gioiTinh")),
                  new java.util.Date(FunctionService.formatStringDate(request.getParameter("ngaySinh"))),
                  request.getParameter("diaChi"),
                  new java.util.Date(FunctionService.formatStringDate(request.getParameter("ngayVaoLam")))
          );
          try {
               mongoService.insertStaff(obj);
               map.put("message", "Đã thêm thành công 1 nhân viên");
               return new ModelAndView("redirect:/admin/management/staff/index", map);
          } catch (Exception ex) {
               map.put("message", ex.getMessage());
               return new ModelAndView("management/staff/add", map);
          }
     }

     @RequestMapping(value = "edit/{manhanvien}")
     public @ResponseBody
     ModelAndView editStaff(@PathVariable String manhanvien, HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          StaffDTO obj = mongoService.getStaffById(manhanvien);
          map.put("staff", obj);
          return new ModelAndView("management/staff/edit", map);
     }

     @RequestMapping(value = "update/{manhanvien}")
     public @ResponseBody
     ModelAndView updateTeacher(@PathVariable String manhanvien, HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          StaffDTO obj = mongoService.getStaffById(manhanvien);
          obj.setManhanvien(request.getParameter("manhanvien"));
          obj.sethoTen(request.getParameter("hoTen"));
          obj.setdiaChi(request.getParameter("diaChi"));
          obj.setgioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));
          String ngaySinh = request.getParameter("ngaySinh");
          if (!"".equals(ngaySinh)) {
               obj.setngaySinh(new java.util.Date(FunctionService.formatStringDate(ngaySinh)));
          }

          String ngayVaoLam = request.getParameter("ngayVaoLam");
          if (!"".equals(ngayVaoLam)) {
               obj.setngayVaoLam(new java.util.Date(FunctionService.formatStringDate(ngayVaoLam)));
          }

          String ngayNghiViec = request.getParameter("ngayNghiViec");
          if (!"".equals(ngayNghiViec)) {
               obj.setngayNghiViec(new java.util.Date(FunctionService.formatStringDate(ngayNghiViec)));
          }

          try {
               mongoService.updateStaff(obj);
               return new ModelAndView("redirect:/admin/management/staff/index", map);
          } catch (Exception ex) {
               map.put("message", ex.getMessage());
               return new ModelAndView("management/staff/add", map);
          }
     }
}

