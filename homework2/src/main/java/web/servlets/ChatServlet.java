package web.servlets;

import core.entity.Message;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which gets all messages for current user
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@WebServlet(urlPatterns = "/chatServlet")
public class ChatServlet extends HttpServlet {

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for messages storage */
    private static final String MESSAGE = "messageList";

    /** Constant attribute name for user's messages storage */
    private static final String CHATS = "userChats";

    /**
     * GET request processing method. Creates ArrayList of objects "Message" where field "recipient" equals current
     * user's username and saves it in current session attribute. If user doesn't have messages, result will be null
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        List messageList;
        List chatList = new ArrayList<Message>();
        String currentUser = (String)session.getAttribute(CURRENT_USER);

        messageList = (ArrayList) context.getAttribute(MESSAGE);

        if (messageList == null) {
            return; // предотвращаем NPE в цикле при отсутствии сообщений
        }

        for (Object object : messageList) {
            Message message = (Message) object;

            if (message.getRecipient().equals(currentUser)) {
                chatList.add(message);
            }
        }

        session.setAttribute(CHATS, chatList);
    }
}