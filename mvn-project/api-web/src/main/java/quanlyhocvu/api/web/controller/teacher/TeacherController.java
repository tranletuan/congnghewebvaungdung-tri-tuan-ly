/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value="teacher")
public class TeacherController {
    @RequestMapping(value="home")
    public @ResponseBody
    ModelAndView home () {
        return new ModelAndView("teacher/home");
    }
    
    @RequestMapping(value="logout") 
    public @ResponseBody 
    ModelAndView logout () {
        return new ModelAndView("forward:/authority/logout");
    }
    
    @RequestMapping(value="information") 
    public @ResponseBody
    ModelAndView information () {
        return new ModelAndView("teacher/information");
    }
    
}
