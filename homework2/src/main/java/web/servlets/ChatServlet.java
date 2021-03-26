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

@WebServlet(urlPatterns = "/chatServlet")
public class ChatServlet extends HttpServlet {
    private static final String CURRENT_USER = "currentUser";
    private static final String MESSAGE = "messageList";
    private static final String CHATS = "userChats";

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