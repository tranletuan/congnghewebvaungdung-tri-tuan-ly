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
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/role")
public class ManagementRoleController {
     @Autowired
     MongoService mongoService;
     
     @RequestMapping(value = "index")
     public @ResponseBody 
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          List<UserDTO> users = mongoService.getAllUser();
          model.put("users", users);
          return new ModelAndView("management/role/index", model);
     }
}
