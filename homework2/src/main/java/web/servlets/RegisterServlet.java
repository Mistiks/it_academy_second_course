package web.servlets;

import model.entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which implements register functionality
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/signUp")
public class RegisterServlet extends HttpServlet {

    /** Constant attribute name for user storage */
    private static final String DATA = "userList";

    /** Constant attribute name for "SIGN_UP_FAIL" flag */
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    /**
     * GET request processing method. Redirects to signUp page
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }

    /**
     * POST request processing method. Register new user if all field are filled with data (username must be unique).
     * Redirects to sign up page with error message if register process fails.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        List userList;
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
            dateOfBirth = null;
        }

        if (first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() ||
                password.isEmpty() || patronymic.isEmpty() || dateOfBirth == null) {
            session.setAttribute(SIGN_UP_FAIL, "true");
            response.sendRedirect(contextPath + "/signUp");
            return;
        }

        userList = (ArrayList) context.getAttribute(DATA);

        if (userList == null) {
            userList = new ArrayList<User>();
        } else {
            for (Object object : userList) {
                User user = (User) object;

                if (user.getUsername().equals(username)) {
                    session.setAttribute(SIGN_UP_FAIL, "true");
                    response.sendRedirect(contextPath + "/signUp");
                    return;
                }
            }

        }

        userList.add(new User(first_name, last_name, patronymic, username, password, dateOfBirth));
        context.setAttribute(DATA, userList);
        session.setAttribute(SIGN_UP_FAIL, "false");
        response.sendRedirect(contextPath + "/signIn");
    }
}