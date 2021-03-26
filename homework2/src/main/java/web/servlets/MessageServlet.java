package web.servlets;

import core.entity.Message;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MessageServlet", urlPatterns = "/messageServlet")
public class MessageServlet extends HttpServlet {
    private static final String MESSAGE = "messageList";
    private static final String CURRENT_USER = "currentUser";
    private static final String EMPTY_FIELD = "emptyField";
    private static final String MESSAGE_SENT = "messageSent";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        List messageList;
        String recipient = request.getParameter("recipient");
        String text = request.getParameter("text");
        String currentUser = (String)session.getAttribute(CURRENT_USER);
        String contextPath = request.getContextPath();


        if (recipient.isEmpty() || text.isEmpty()) {
            response.sendRedirect(contextPath + "/message");
            session.setAttribute(EMPTY_FIELD, "true");
            return;
        }

        messageList = (ArrayList) context.getAttribute(MESSAGE);

        if (messageList == null) {
            messageList = new ArrayList<Message>();
        }

        messageList.add(new Message(currentUser, recipient, text, LocalDateTime.now()));
        context.setAttribute(MESSAGE, messageList);
        session.setAttribute(MESSAGE_SENT, "true");
        response.sendRedirect(contextPath + "/message");
    }
}