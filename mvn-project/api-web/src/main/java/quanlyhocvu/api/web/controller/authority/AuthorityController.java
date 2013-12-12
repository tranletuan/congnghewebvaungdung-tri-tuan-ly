/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.web.controller.authority;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HuuTri
 */
@Controller
public class AuthorityController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "check")
    public @ResponseBody
    ModelAndView check() {
        return new ModelAndView("forward:/authority/j_spring_security_check");
    }

    @RequestMapping(value = "check_role")
    public @ResponseBody
    ModelAndView check_role(HttpServletRequest request) {
        return new ModelAndView("redirect:/guest/home");
    }

    @RequestMapping(value = "noAccess", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView noAccess() {
        logger.debug("Received request to show no access");
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("message", "Vui lòng kiểm tra thông tin đăng nhập");
        return new ModelAndView("redirect:/guest/login", model);
    }
}
