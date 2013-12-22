package quanlyhocvu.api.web.controller.guest;

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
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CoverImageDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsCounterDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.util.Tools;

@Controller
public class HomeController {

     @Autowired
     MongoService mongoService;

     @RequestMapping(value = {"", "/", "home", "index"})
     public @ResponseBody
     ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          String username = Tools.getCurrentUser();
          if (username != null) {
               UserDTO dto = mongoService.getUserByUserName(username);
               model.put("user", dto);
          }
          List<CatalogNewsDTO> list = mongoService.getAllCatalog();
          model.put("listCatalogs", list);
          List<CoverImageDTO> listcover = mongoService.getAllCovers();
          model.put("covers", listcover);
          return new ModelAndView("guest/home", model);
     }

     @RequestMapping(value = "home_list_news/{catalogId}/{page}")
     public @ResponseBody
     ModelAndView homeListNews(
             @PathVariable(value = "catalogId") String catalogId,
             @PathVariable(value = "page") String page,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          int limit = 6;
          int offset = 0;
          try {
               offset = limit * (Integer.parseInt(page) - 1);
          } catch (NumberFormatException ex) {
               model.put("message", "Xảy ra lỗi tải trang");
               return new ModelAndView("guest/home_list_news", model);
          }
          List<NewsDTO> listNews;
          if (!"0".equals(catalogId)) {
               listNews = mongoService.getNewsByCatalogIdAndPage(catalogId, limit, offset);
          } else {
               listNews = mongoService.getAllNewsByPage(limit, offset);
               if (listNews.isEmpty()) {
                    listNews = mongoService.getAllNewsByPage(limit, 0);
               }
          }
          model.put("listNews", listNews);
          return new ModelAndView("guest/home_list_news", model);
     }

     @RequestMapping(value = "news/{newsId}")
     @ResponseBody
     public ModelAndView news_id(
             @PathVariable(value = "newsId") String newsId,
             HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          mongoService.increaseConterNews(newsId);
          List<NewsCounterDTO> listcounter = mongoService.getHotNews(5);
          List<CoverImageDTO> listcover = mongoService.getAllCovers();
          model.put("covers", listcover);
          String content = mongoService.getNewsContentByNewsId(newsId);
          model.put("content", content);
          model.put("listhotnews", listcounter);
          return new ModelAndView("guest/home", model);
     }
}
