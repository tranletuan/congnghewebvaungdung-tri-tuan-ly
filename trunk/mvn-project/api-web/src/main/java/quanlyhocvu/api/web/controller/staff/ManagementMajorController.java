/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.staff;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
@RequestMapping(value = "management/major/")
public class ManagementMajorController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value = "list")
    public @ResponseBody
    ModelAndView major(HttpServletRequest request) {
        
        
        return new ModelAndView("controller/management/major/list");
        
    }
    
}
