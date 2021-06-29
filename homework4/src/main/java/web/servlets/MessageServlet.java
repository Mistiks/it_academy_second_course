package web.servlets;

import model.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.MessageService;
import view.api.IMessageService;
import java.io.IOException;;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Class which implements sending messages functionality
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {

    /** Constant attribute name for messages storage */
    private static final String MESSAGE = "messageList";

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for "EMPTY_FIELD" flag */
    private static final String EMPTY_FIELD = "emptyField";

    /** Constant attribute name for "MESSAGE_SENT" flag */
    private static final String MESSAGE_SENT = "messageSent";

    /** Instance of class that implements IMessageService interface */
    private final IMessageService messageService;

    /** Default constructor which connect IMessageService interface to servlet */
    public MessageServlet() {
        this.messageService = MessageService.getInstance();
    }

    /**
     * GET request processing method. Redirects to sending messages page
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }

    /**
     * POST request processing method. Redirects to sending messages page with success message if message correct and
     * saves message. Redirects to sending messages page with error message if some fields are empty.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String recipient = request.getParameter("recipient");
        String text = request.getParameter("text");
        String currentUser = (String) session.getAttribute(CURRENT_USER);
        String contextPath = request.getContextPath();

        if (recipient.isEmpty() || text.isEmpty()) {
            response.sendRedirect(contextPath + "/message");
            session.setAttribute(EMPTY_FIELD, "true");
            return;
        }

        Message message = new Message(currentUser, recipient, text, LocalDateTime.now());

        try {
            this.messageService.addMessage(recipient, message);
            session.setAttribute(MESSAGE_SENT, "true");
            response.sendRedirect(contextPath + "/message");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(contextPath + "/message");
            session.setAttribute(EMPTY_FIELD, "true");
        }
    }
}