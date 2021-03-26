package web.servlets;

import core.entity.User;
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

@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final String DATA = "userList";
    private static final String SIGN_UP_FAIL = "fail_sign_up";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            userList.add(new User(first_name, last_name, patronymic, username, password, dateOfBirth));
            context.setAttribute(DATA, userList);
            session.setAttribute(SIGN_UP_FAIL, "false");
            response.sendRedirect(contextPath + "/signIn");
        } else {
            for (Object object : userList) {
                User user = (User) object;

                if (user.getUsername().equals(username)) {
                    session.setAttribute(SIGN_UP_FAIL, "true");
                    response.sendRedirect(contextPath + "/signUp");
                    return;
                }
            }

            userList.add(new User(first_name, last_name, patronymic, username, password, dateOfBirth));
            context.setAttribute(DATA, userList);
            session.setAttribute(SIGN_UP_FAIL, "false");
            response.sendRedirect(contextPath + "/signIn");
        }
    }
}