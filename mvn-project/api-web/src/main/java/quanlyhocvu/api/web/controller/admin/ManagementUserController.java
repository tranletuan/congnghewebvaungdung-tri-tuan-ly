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
import quanlyhocvu.api.mongodb.DTO.Authority.RoleDTO;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.mongodb.utils.Authorities;
import quanlyhocvu.api.mongodb.utils.Data;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/user")
public class ManagementUserController {

     @Autowired
     private MongoService mongoService;

     @RequestMapping(value = "generate")
     public @ResponseBody
     ModelAndView generate(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          mongoService.generateAllUser();
          return new ModelAndView("redirect:/admin/management/user/index", model);
     }

     @RequestMapping(value = "index")
     public @ResponseBody
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String message = request.getParameter("message");
          model.put("currRoleName", "STAFF");
          model.put("message", message);

          List<RoleDTO> roles = mongoService.getAllRole();
          model.put("roles", roles);

          return new ModelAndView("admin/management/user/index", model);
     }

     @RequestMapping(value = "index_table/{rolename}")
     public @ResponseBody
     ModelAndView index_table(@PathVariable String rolename, HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();

          List<UserDTO> users = mongoService.getUsersByRoleName(rolename);
          model.put("users", users);

          return new ModelAndView("admin/management/user/index_table", model);
     }

     @RequestMapping(value = "update")
     public ModelAndView update(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String message = "";
          String username = request.getParameter("username");
          String rolename = request.getParameter("rolename");
          UserDTO user = mongoService.getUserByUserName(username);
          RoleDTO role = mongoService.getRoleByRoleName(rolename);
          System.out.println(user.getUsername() + " " + role.getRolename());
          user.setPassword(request.getParameter("password"));
          if (role != null) {
               user.getRoles().clear();
               user.getRoles().add(role);
          }
          try {
               mongoService.updateUser(user);
               model.put("message", "Cập nhật thành công thông tin của " + user.getUsername());
               return new ModelAndView("redirect:/admin/management/user/index", model);
          } catch (Exception ex) {
               message = "Không thể cập nhật thông tin người dùng";
          }
          model.put("message", message);
          return new ModelAndView("admin/management/user/edit/" + user.getUsername(), model);
     }

     @RequestMapping(value = "update/{username}/{active_enabled}/{active_nonlocked}")
     public ModelAndView enabled(
             @PathVariable(value = "username") String username,
             @PathVariable(value = "active_enabled") String active_enabled,
             @PathVariable(value = "active_nonlocked") String active_nonlocked,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          try {
               UserDTO user = mongoService.getUserByUserName(username);
               user.setEnabled((Integer.parseInt(active_enabled) == 1));
               user.setNonlocked((Integer.parseInt(active_nonlocked) == 0));

               mongoService.updateUser(user);
          } catch (NumberFormatException ex) {

          }
          return new ModelAndView("redirect:/admin/management/user/index", model);
     }

     @RequestMapping(value = "add")
     public @ResponseBody
     ModelAndView add(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String message = request.getParameter("message");
          List<RoleDTO> roles = mongoService.getAllRole();

          model.put("roles", roles);
          model.put("message", message);
          return new ModelAndView("admin/management/user/add", model);
     }

     @RequestMapping(value = "edit/{username}")
     public @ResponseBody
     ModelAndView edit(@PathVariable String username,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String message = request.getParameter("message");

          model.put("message", message);
          model.put("username", username);
          model.put("roles", mongoService.getAllRole());
          try {
               String rolename = mongoService.getUserByUserName(username).getRoles().get(0).getRolename();
               model.put("rolename", rolename);
          } catch (Exception ex) {

          }

          return new ModelAndView("admin/management/user/edit", model);
     }

     @RequestMapping(value = "save")
     public @ResponseBody
     ModelAndView save(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String password_confirm = request.getParameter("password_confirm");
          String role_name = request.getParameter("rolename");

          String message = Data.checkUserDTO(username, password, password_confirm);
          if ("".equals(message)) {

               try {
                    model.put("message", "Đã thêm thành công người dùng!");
                    mongoService.insertUser(username, password, role_name, "");
                    return new ModelAndView("redirect:/admin/management/user/index", model);
               } catch (Throwable ex) {
                    message = "Thêm người dùng thất bại!";
               }
          }
          model.put("message", message);
          return new ModelAndView("redirect:/admin/management/user/add", model);
     }
}
