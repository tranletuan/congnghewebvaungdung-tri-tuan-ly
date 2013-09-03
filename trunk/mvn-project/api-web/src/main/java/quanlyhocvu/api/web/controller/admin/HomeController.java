package quanlyhocvu.api.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @RequestMapping(value = {"", "/","home","index"})
    public @ResponseBody
    ModelAndView index() {
        ModelAndView mv = new ModelAndView("home");
        System.out.println("Login app-web management");
        return mv;
    }
    
}
