package web.servlets;

import model.entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which implements login functionality
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    /** Constant attribute name for user storage */
    private static final String DATA = "userList";

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for "SIGN_IN_FAIL" flag */
    private static final String SIGN_IN_FAIL = "fail_sign_in";

    /**
     * GET request processing method. Redirects to profile page if user with entered username and password exists.
     * Redirects to sign in page with error message if login process fails.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        session.removeAttribute(CURRENT_USER);
        List userList;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String contextPath = request.getContextPath();
        userList = (ArrayList) context.getAttribute(DATA);

        if (username.isEmpty() || password.isEmpty() || userList == null) {
            session.setAttribute(SIGN_IN_FAIL, "true");
            response.sendRedirect(contextPath + "/signIn");
            return;
        }

            for (Object object : userList) {
                User user = (User) object;

                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    session.setAttribute(CURRENT_USER, username);
                    response.sendRedirect(contextPath + "/profile");
                    return;
                }
            }

        session.setAttribute(SIGN_IN_FAIL, "true");
        response.sendRedirect(contextPath + "/signIn");
    }
}