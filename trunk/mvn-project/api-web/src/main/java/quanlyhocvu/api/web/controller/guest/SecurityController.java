package quanlyhocvu.api.web.controller.guest;

import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.jms.ClientJMS;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.util.Tools;

@Controller
public class SecurityController {

    Logger logger = Logger.getLogger(getClass());

    @Autowired
    MongoService mongoService;

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

    @RequestMapping(value = "check")
    public @ResponseBody
    ModelAndView check() {
        return new ModelAndView("forward:/authority/check");
    }

    @RequestMapping(value = "logout")
    public @ResponseBody
    ModelAndView logout() throws JMSException {
        String username = Tools.getCurrentUser();
        if (username != null) {
            UserDTO dto = mongoService.getUserByUserName(username);
            if (dto != null) {
                ClientJMS client = new ClientJMS((dto.getId()));
                client.sendStatusOffline();
            }
        }
        return new ModelAndView("forward:/authority/logout");
    }
}
