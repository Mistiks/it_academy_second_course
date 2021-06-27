package controller.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class which handles profile page of application
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileServlet {

    /**
     * GET request processing method. Redirects to jsp profile page
     *
     * @return URL of jsp page
     */
    @GetMapping
    public String doGet() {
        return "/views/profile.jsp";
    }
}
