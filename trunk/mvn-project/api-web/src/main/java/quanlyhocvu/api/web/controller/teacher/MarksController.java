/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.teacher;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.base.HocSinhDTO;
import quanlyhocvu.api.mongodb.service.MongoService;

/**
 *
 * @author Tuan
 */
@Controller
@RequestMapping(value = "classteaching/{classId}")
public class MarksController {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    MongoService mongoService;
    
    @RequestMapping(value = "{studentId}")
    public @ResponseBody
    ModelAndView marks(@PathVariable String studentId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        HocSinhDTO hocSinh = mongoService.getHocSinhById(studentId);
        
        map.put("hocSinh", hocSinh);
        return new ModelAndView("mark", map);
    }
    
}
