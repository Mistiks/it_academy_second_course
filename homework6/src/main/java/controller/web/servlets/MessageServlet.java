package controller.web.servlets;

import model.Message;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IMessageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


/**
 * Class which implements sending messages functionality
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
@Controller
@RequestMapping(value = "/message")
public class MessageServlet {

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for "EMPTY_FIELD" flag */
    private static final String EMPTY_FIELD = "emptyField";

    /** Constant attribute name for "MESSAGE_SENT" flag */
    private static final String MESSAGE_SENT = "messageSent";

    /** Instance of class that implements IMessageService interface */
    private final IMessageService messageService;


    /**
     * Default constructor which connect IMessageService interface to servlet
     *
     * @param messageService instance of IMessageService interface
     */
    public MessageServlet(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * GET request processing method. Redirects to message creation page
     *
     * @return URL of page
     */
    @GetMapping
    public String doGet() {
        return "/views/message.jsp";
    }

    /**
     * POST request processing method. Redirects to sending messages page with success message if message correct and
     * saves message. Redirects to sending messages page with error message if some fields are empty.
     *
     * @param request HttpServletRequest object
     */
    @PostMapping
    protected String doPost(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String recipient = request.getParameter("recipient");
        String text = request.getParameter("text");
        User currentUser = (User) session.getAttribute(CURRENT_USER);

        Message message = new Message(currentUser.getUsername(), recipient, text, LocalDateTime.now());

        try {
            this.messageService.addMessage(message);
            model.addAttribute(MESSAGE_SENT, "true");
            return "/views/message.jsp";
        } catch (IllegalArgumentException e) {
            model.addAttribute(EMPTY_FIELD, "true");
            return "/views/message.jsp";
        }
    }
}