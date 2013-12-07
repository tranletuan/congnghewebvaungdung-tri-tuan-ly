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
public class AdminController {
     @Autowired
     MongoService mongoService;
     
     @RequestMapping(value="home")
     public @ResponseBody
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          
          return new ModelAndView("home", model);
     }
}
