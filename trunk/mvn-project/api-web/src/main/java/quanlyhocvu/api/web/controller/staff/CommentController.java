/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.CommentDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value="management/comment")
public class CommentController {
     @Autowired
     MongoService mongoService;
     
     @RequestMapping("index")
     public ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          List<CommentDTO> comments = mongoService.getAllComments();
          
          model.put("comments", comments);
          return new ModelAndView("staff/management/comment/index", model);
     }
}
