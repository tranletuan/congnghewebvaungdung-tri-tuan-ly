/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.admin;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller 
@RequestMapping(value="management/ueser")
public class ManagementUserController {
     @Autowired
     private MongoService mongoService;
     
     @RequestMapping(value="generate")
     public @ResponseBody
     ModelAndView generate(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          mongoService.generateAllUser();
          model.put("users", mongoService.getAllUser());
          return new ModelAndView("redirect:/admin/management/user/list", model);
     }
}
