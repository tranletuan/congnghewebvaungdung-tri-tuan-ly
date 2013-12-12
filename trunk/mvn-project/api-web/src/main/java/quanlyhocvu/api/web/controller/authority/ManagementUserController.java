/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.authority;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HuuTri
 */
@Controller
@RequestMapping(value = "management/user")
public class ManagementUserController {

    @Autowired

    @RequestMapping(value = "index")
    public @ResponseBody
    ModelAndView index() {
        Map<String, Object> model = new HashMap<String, Object>();
        
        return new ModelAndView("authority/management/user/index", model);
    }
}
