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

        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "invalidSession", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView invalidSession() {
        logger.debug("Received request to show invalid session");
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("message", "Bạn không được truy cập chức năng này");

        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "noAccess", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView noAccess() {
        logger.debug("Received request to show no access");
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("message", "Vui lòng kiểm tra thông tin đăng nhập");
        return new ModelAndView("login", model);
    }
}
