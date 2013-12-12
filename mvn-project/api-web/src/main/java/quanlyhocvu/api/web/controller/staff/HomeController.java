/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.staff;

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
public class HomeController {
    @RequestMapping(value="home") 
    public @ResponseBody
    ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("staff/home");
    }
}
