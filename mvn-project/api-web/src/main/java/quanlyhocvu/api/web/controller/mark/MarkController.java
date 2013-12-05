/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlyhocvu.api.web.controller.mark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tuan
 */
@Controller
public class MarkController {
    
    @RequestMapping(value="teacher/mark")
    public @ResponseBody
    ModelAndView mark () {
        return new ModelAndView("mark");
    }
}
