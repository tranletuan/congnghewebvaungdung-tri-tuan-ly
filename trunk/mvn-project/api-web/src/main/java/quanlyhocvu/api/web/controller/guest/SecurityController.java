package quanlyhocvu.api.web.controller.guest;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getLoginPage() {
        logger.debug("Received request to show login page");

        Map<String, Object> model = new HashMap<String, Object>();

        return new ModelAndView("guest/login", model);
    }

    @RequestMapping(value = "invalidSession", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView invalidSession() {
        logger.debug("Received request to show invalid session");
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("message", "Bạn không được truy cập chức năng này");

        return new ModelAndView("redirect:/guest/home", model);
    }
    
    @RequestMapping(value="check") 
    public @ResponseBody
    ModelAndView check() {
        return new ModelAndView("forward:/authority/check");
    }
    
     @RequestMapping(value="logout") 
    public @ResponseBody
    ModelAndView logout() {
        return new ModelAndView("forward:/authority/logout");
    }
}
