package quanlyhocvu.api.web.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "report_add", method = RequestMethod.GET)
    public String getReportAddPage() {
        return "report_add";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
            ModelMap model) {
        logger.debug("Received request to show login page");

        // Add an error message to the model if login is unsuccessful
        // The 'error' parameter is set to true based on the when the authentication has failed. 
        // We declared this under the authentication-failure-url attribute inside the spring-security.xml
		/* See below:
         <form-login 
         login-page="/krams/auth/login" 
         authentication-failure-url="/krams/auth/login?error=true" 
         default-target-url="/krams/main/common"/>
         */
        if (error == true) {
            // Assign an error message
            model.put("error", "You have entered an invalid username or password!");
        } else {
            model.put("error", "");
        }
        // This will resolve to /WEB-INF/jsp/loginpage.jsp
        return "login";
    }

    @RequestMapping(value = "denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        logger.debug("Received request to show denied page");

        // This will resolve to /WEB-INF/jsp/deniedpage.jsp
        return "denied";
    }

    @RequestMapping(value = "invalidSession", method = RequestMethod.GET)
    public String invalidSession() {
        logger.debug("Received request to show invalid session");

        // This will resolve to /WEB-INF/jsp/deniedpage.jsp
        return "invalidSession";
    }

    @RequestMapping(value = "noAccess", method = RequestMethod.GET)
    public String noAccess() {
        logger.debug("Received request to show no access");

        // This will resolve to /WEB-INF/jsp/deniedpage.jsp
        return "noAccess";
    }
}
