package controller.web.servlets;

import model.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.IMessageService;
import java.util.List;

/**
 * Class which gets all messages for current user
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/chats")
public class ChatServlet {

    /** Constant attribute name for current user storage */
    private static final String CURRENT_USER = "currentUser";

    /** Constant attribute name for user's messages storage */
    private static final String CHATS = "userChats";

    /** Instance of class that implements IMessageService interface */
    private final IMessageService messageService;

    /**
     * Default constructor which defines implementation of IMessageService
     * interface in servlet
     *
     * @param messageService instance of IMessageService interface
     */
    public ChatServlet(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * GET request processing method. Creates ArrayList of objects "Message" where field "recipient" equals current
     * user's username and saves it in current session attribute. If user doesn't have messages, result will be null
     *
     * @param request request information for HTTP servlets
     * @return URL of page to show
     */
    @GetMapping
    public String doGet(HttpServletRequest request, Model model)  {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(CURRENT_USER);

        List<Message> messages = this.messageService.get(currentUser);
        model.addAttribute(CHATS, messages);
        return "/views/chats.jsp";
    }
}