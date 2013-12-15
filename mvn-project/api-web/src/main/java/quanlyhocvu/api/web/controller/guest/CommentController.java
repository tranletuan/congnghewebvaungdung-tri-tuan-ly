/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.guest;

import java.util.Date;
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
import quanlyhocvu.api.web.util.Tools;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController {

     @Autowired
     MongoService mongoService;

     @RequestMapping(value = "save")
     public ModelAndView save(HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          String username = Tools.getCurrentUser();
          if (username == null || "".equals(username)) {
               map.put("message", "Bạn không có quyền truy cập chức năng này!");
               return new ModelAndView("redirect:/guest/home", map);

          }
          CommentDTO comment = new CommentDTO();
          comment.setContent(request.getParameter("message"));
          comment.setDate(new Date());
          comment.setUser(mongoService.getUserByUserName(username));
          mongoService.insertComment(comment);
          map.put("message", "Kiến nghị đã được gửi");
          return new ModelAndView("redirect:/guest/home", map);
     }

     @RequestMapping(value = "index")
     public ModelAndView index(HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          String username = Tools.getCurrentUser();
          if (username == null || "".equals(username)) {
               map.put("message", "Bạn không có quyền truy cập chức năng này!");
               return new ModelAndView("redirect:/guest/home", map);

          }
          List<CommentDTO> comments = mongoService.getCommentsByUser(mongoService.getUserByUserName(username));
          map.put("comments", comments);
          return new ModelAndView("guest/comment/index", map);
     }
}
