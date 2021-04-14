package web.servlets;

import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.UserService;
import view.api.IUserService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which implements register functionality
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {

    /** Constant attribute name for "SIGN_UP_FAIL" flag */
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    /** Instance of class that implements IUserService interface */
    private final IUserService userService;

    /** Default constructor which connect IUserService interface to servlet */
    public RegisterServlet() {
        this.userService = UserService.getInstance();
    }

    /**
     * GET request processing method. Register new user if all field are filled with data (username must be unique).
     * Redirects to sign up page with error message if register process fails.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String first_name = request.getParameter("firstName");
        String last_name = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LocalDate dateOfBirth;
        String contextPath = request.getContextPath();

        try {
            dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"), formatter);
        } catch (DateTimeParseException e) {
            session.setAttribute(SIGN_UP_FAIL, "true");
            response.sendRedirect(contextPath + "/signUp");
            return;
        }

        User user = new User(first_name, last_name, patronymic, username, password, dateOfBirth);

        try {
            this.userService.signUp(user);
            session.setAttribute(SIGN_UP_FAIL, "false");
            response.sendRedirect(contextPath + "/signIn");
        } catch (IllegalArgumentException e){
            session.setAttribute(SIGN_UP_FAIL, "true");
            response.sendRedirect(contextPath + "/signUp");
        }
    }
}