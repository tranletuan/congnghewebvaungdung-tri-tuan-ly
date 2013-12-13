/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.util.Tools;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/news")
public class ManagementNewsController {

     @Autowired
     MongoService mongoService;

     @RequestMapping(value = "add")
     public ModelAndView add(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          model.put("listCatalogs", mongoService.getAllCatalog());
          return new ModelAndView("staff/management/news/add", model);
     }

     @RequestMapping(value = "save")
     public ModelAndView save(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          //////////////////////
          String title = request.getParameter("title");
          String author = Tools.getCurrentUser();
          Date date = new Date();
          String content = request.getParameter("content");
          String catalogIds[] = request.getParameterValues("catalogs");
          String url = "";
          System.out.println(title + author + content);
          if ("".equals(title) || "".equals(author) || "".equals(content)) {
               model.put("message", "Tạo tin tức không thành công");
               return new ModelAndView("redirect:/guest/home", model);
          }
          
          NewsDTO news = new NewsDTO(title, author, date,
                  content, url,
                  mongoService.getCatalogsFromIds(catalogIds));
          mongoService.insertNews(news);
          model.put("message", "Tạo tin tức thành công");
          return new ModelAndView("redirect:/guest/home", model);

     }
     
     @RequestMapping(value = "delete")
     public ModelAndView delete(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String newsId = request.getParameter("newsId");
          mongoService.deleteNewsById(newsId);
          model.put("message", "Xóa thành công một bản tin");
          return new ModelAndView("redirect:/guest/home", model);
     }
}
