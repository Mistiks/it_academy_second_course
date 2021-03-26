package web.servlets;

import core.entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final String DATA = "userList";
    private static final String CURRENT_USER = "currentUser";
    private static final String SIGN_IN_FAIL = "fail_sign_in";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        session.removeAttribute(CURRENT_USER);
        List userList;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String contextPath = request.getContextPath();

        if (username.isEmpty() || password.isEmpty()) {
            session.setAttribute(SIGN_IN_FAIL, "true");
            response.sendRedirect(contextPath + "/signIn");
            return;
        }

        userList = (ArrayList) context.getAttribute(DATA);

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