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
          model.put("currRoleName", "STAFF");

          List<RoleDTO> roles = mongoService.getAllRole();
          model.put("roles", roles);

          return new ModelAndView("management/user/index", model);
     }

     @RequestMapping(value = "index_table/{rolename}")
     public @ResponseBody
     ModelAndView index_table(@PathVariable String rolename, HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();

          List<UserDTO> users = mongoService.getUsersByRoleName(rolename);
          model.put("users", users);
          
          return new ModelAndView("management/user/index_table", model);
     }
}
