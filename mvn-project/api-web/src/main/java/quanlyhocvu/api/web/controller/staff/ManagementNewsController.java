/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CoverImageDTO;
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
          String imageUrl = request.getParameter("imageUrl");
          if ("".equals(title) || "".equals(author) || "".equals(content)) {
               model.put("message", "Tạo tin tức không thành công");
               return new ModelAndView("redirect:/guest/home", model);
          }

          NewsDTO news = new NewsDTO(title, author, date,
                  content, url,
                  mongoService.getCatalogsFromIds(catalogIds));
          news.setImageUrl(imageUrl);
          mongoService.insertNews(news);
          model.put("message", "Tạo tin tức thành công");
          return new ModelAndView("redirect:/guest/home", model);

     }

     @RequestMapping(value = "delete/{newsId}")
     public ModelAndView delete(
             @PathVariable String newsId,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          mongoService.deleteNewsById(newsId);
          model.put("message", "Xóa thành công một bản tin");
          return new ModelAndView("redirect:/guest/home", model);
     }

     @RequestMapping(value = "edit/{newsId}")
     public ModelAndView edit(
             @PathVariable String newsId,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          model.put("catalogs", mongoService.getAllCatalog());
          NewsDTO news = mongoService.getNewsById(newsId);
          if (news != null) {
               model.put("news", news);
               return new ModelAndView("staff/management/news/edit", model);
          }
          model.put("message", "Không thể tìm kiếm thông tin bài tin tức được chọn");
          return new ModelAndView("redirect:/guest/home", model);
     }

     @RequestMapping(value = "update")
     public ModelAndView update(
             HttpServletRequest request) {

          Map<String, Object> model = new HashMap<>();
          NewsDTO news = mongoService.getNewsById(request.getParameter("newsId"));
          try {
               if (news != null) {
                    news.setContent(request.getParameter("content"));
                    news.setTitle(request.getParameter("title"));
                    news.setImageUrl(request.getParameter("imageUrl"));
                    news.setCatalogs(mongoService.getCatalogsFromIds(request.getParameterValues("catalogs")));
                    model.put("message", "Cập nhật thông tin bản tin thành công");
                    mongoService.updateNews(news);
                    return new ModelAndView("redirect:/guest/home", model);
               }
          } catch (Exception ex) {
               model.put("message", "Cập nhật thông tin bản tin thất bại");
          }
          return new ModelAndView("redirect:/guest/home", model);
     }


     @RequestMapping(value = "cover/add")
     public ModelAndView corver_add(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          List<CoverImageDTO> list = mongoService.getAllCovers();
          model.put("covers", list);
          int count;
          if (list == null) {
               count = 1;
          } else {
               count = list.size();
          }
          model.put("count", count);
          return new ModelAndView("staff/management/news/cover_add", model);
     }

     @RequestMapping(value = "cover/save")
     public ModelAndView cover_save(HttpServletRequest request) {
          int count = 1;
          String id = "";
          String link = null;
          int size = Integer.parseInt(request.getParameter("count"));
          mongoService.removeAllCover();
          while (count <= size) {
               id = "field";
               if (count > 0) {
                    id = id + String.valueOf(count);
               }

               link = request.getParameter(id);
               if (!(link == null || link.equals(""))) {
                    mongoService.insertCover(link, count);
               }

               count++;
          }
          return new ModelAndView("redirect:/guest/home");
     }
}
