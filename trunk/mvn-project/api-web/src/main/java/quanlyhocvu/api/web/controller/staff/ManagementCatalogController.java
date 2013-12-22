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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsCounterDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/catalog")
public class ManagementCatalogController {

     @Autowired
     MongoService mongoService;

     @RequestMapping(value = "index")
     @ResponseBody
     public ModelAndView index(HttpServletRequest request) {
          Map<String, Object> model = new HashMap<>();
          model.put("catalogs", mongoService.getAllCatalog());          
          return new ModelAndView("staff/management/catalog/index", model);
     }
     
     @RequestMapping(value="delete/{id}")
     public ModelAndView delete_id(
             @PathVariable(value="id") String id,
             HttpServletRequest request) {
          mongoService.deleteCatalog(id);
          Map<String, Object> model = new HashMap<>();
          model.put("message", "Xóa thành công!");
          return new ModelAndView("redirect:/staff/management/catalog/index", model);
     }
     
     @RequestMapping(value="update/{id}")
     public ModelAndView update(
             @PathVariable(value="id") String catalogId,
             HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          CatalogNewsDTO catalog = mongoService.getCatalogById(catalogId);
          map.put("catalog",catalog);
          return new ModelAndView("staff/management/catalog/add", map);
     }
     
     @RequestMapping(value="update_done")
     public ModelAndView update_done(HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          String catalogId = request.getParameter("catalogId");
          String catalogName = request.getParameter("catalogName");
          String catalogInfo = request.getParameter("catalogInfo");
          
          CatalogNewsDTO catalog = mongoService.getCatalogById(catalogId);
          if (catalog != null) {
               catalog.setName(catalogName);
               catalog.setInfo(catalogInfo);
               mongoService.updateCatalog(catalog);
          }
          return new ModelAndView("redirect:/staff/management/catalog/index", map);
     }
     
     @RequestMapping(value="add") 
     public ModelAndView add(HttpServletRequest request) {
          return new ModelAndView ("staff/management/catalog/add");
     }
     
     @RequestMapping(value="save")
     public ModelAndView save(HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          String catalogName = request.getParameter("catalogName");
          String catalogInfo = request.getParameter("catalogInfo");
          if ("".equals(catalogName)) {
               map.put("message", "Vui lòng nhập thông tin tên catalog");
               return new ModelAndView("redirect:/staff/management/catalog/add", map);
          }
          CatalogNewsDTO catalog = new CatalogNewsDTO();
          catalog.setName(catalogName);
          catalog.setInfo(catalogInfo);
          mongoService.insertCatalogNews(catalog);
          map.put("message", "Thêm thành công catalog mới!");
          return new ModelAndView ("redirect:/staff/management/catalog/index", map);
     }
     
     @RequestMapping(value="{catalogId}")
     public ModelAndView catalog_catalogId(
             @PathVariable(value="catalogId") String catalogId, 
             HttpServletRequest request) {
          Map<String, Object> map = new HashMap<>();
          List<NewsCounterDTO> listNews = mongoService.getNewsByCatalogId(catalogId);
          map.put("listNews", listNews);
          return new ModelAndView("staff/management/catalog/catalogId", map);
     }
}  
