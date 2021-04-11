package web.servlets;

import model.Message;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import storage.ChatStorage;
import storage.api.IChatStorage;

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

    /** Instance of class that implements IChatStorage interface */
    private final IChatStorage chatStorage;

    /** Default constructor which connect IChatStorage interface to servlet */
    public ChatServlet() {
        this.chatStorage = ChatStorage.getInstance();
    }

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
        String currentUser = (String) session.getAttribute(CURRENT_USER);

        List<Message> messages = this.chatStorage.get(currentUser);
        session.setAttribute(CHATS, messages);
    }
}