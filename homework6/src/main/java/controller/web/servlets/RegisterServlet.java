package controller.web.servlets;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class which implements register functionality
 *
 * @author Vadim Rataiko
 * @version 1.2.1
 */
@Controller
@RequestMapping(value = "/signUp")
public class RegisterServlet {

    /** Constant attribute name for "SIGN_UP_FAIL" flag */
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    /** Instance of class that implements IUserService interface */
    private final IUserService userService;

    /** Default constructor which connect IUserService interface to servlet
     *
     * @param userService instance of IUserService interface
     */
    public RegisterServlet(IUserService userService) {
        this.userService = userService;
    }

    /**
     * GET request processing method. Redirects to signUp page
     *
     * @return URL of registration page
     */
    @GetMapping
    public String doGet() {
        return "/views/signUp.jsp";
    }

    /**
     * POST request processing method. Register new user if all field are filled with data (username must be unique).
     * Redirects to sign up page with error message if register process fails.
     *
     * @param request request information for HTTP servlets
     * @return destination URL
     */
    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {

        String first_name = request.getParameter("firstName");
        String last_name = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");

        try {
            User user = new User(first_name, last_name, patronymic, username, password, dateOfBirth);
            this.userService.signUp(user);
            return "redirect:/signIn";
        } catch (IllegalArgumentException e){
            model.addAttribute(SIGN_UP_FAIL, "true");
            return "/views/signUp.jsp";
        }
    }
}