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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.AbstractStaffDTO;
import quanlyhocvu.api.mongodb.DTO.staff.StaffDTO;
import quanlyhocvu.api.mongodb.service.FunctionService;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value="management/staff")
public class ManagementStaffController {
     @Autowired
     MongoService mongoService;
     
     @RequestMapping(value="index")
     public @ResponseBody 
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          List<StaffDTO> staffs  = mongoService.getAllStaff();
          model.put("staffs", staffs);
          return new ModelAndView("management/staff/index", model);
     }
     
     @RequestMapping(value="add")
     public @ResponseBody
     ModelAndView add(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          
          return new ModelAndView("management/staff/add", model);
     }
     
     @RequestMapping(value="save")
     public @ResponseBody
     ModelAndView save(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          
          StaffDTO staff = new StaffDTO();
          staff.setdiaChi(request.getParameter("diachi"));
          staff.setgioiTinh(Integer.parseInt(request.getParameter("gioitinh")));
          staff.sethoTen(request.getParameter("hoten"));
          staff.setmoTa(request.getParameter("mota"));
          staff.setngayNghiViec(new java.util.Date(FunctionService.formatStringDate(request.getParameter("ngaynghiviec"))));
          staff.setngaySinh(new java.util.Date(FunctionService.formatStringDate(request.getParameter("ngaysinh"))));
          staff.setngayVaoLam(new java.util.Date(FunctionService.formatStringDate(request.getParameter("ngayvaolam"))));
          
          mongoService.insertStaff(staff);
          return new ModelAndView("redirect:/management/staff/index", model);
     }
}
