package web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Class which implements filter functionality
 *
 * @author Vadim Rataiko
 */
@WebFilter(urlPatterns = {"/chats", "/message", "/profile"})
public class SecurityFilter implements Filter {

    /**
     * Filtering method. Redirects to login page if user didn't sign in
     *
     * @param request ServletRequest object
     * @param response ServletResponse object
     * @param chain FilterChain object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        if ((session != null) && (session.getAttribute("currentUser") != null)) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(contextPath + "/signIn");
        }
    }
}