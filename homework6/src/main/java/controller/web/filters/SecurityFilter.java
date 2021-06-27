package controller.web.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Class which implements filter functionality
 *
 * @author Vadim Rataiko
 * @version 1.2
 */
@Component
public class SecurityFilter implements HandlerInterceptor {

    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined an appropriate handler object,
     * but before HandlerAdapter invokes the handler.
     *
     * @param httpServletRequest current HTTP request
     * @param httpServletResponse current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return true if the execution chain should proceed with the next interceptor or the handler itself.
     * Else, DispatcherServlet assumes that this interceptor has already dealt with the response itself.
     * @throws Exception – in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object handler) throws Exception {
        String contextPath = httpServletRequest.getContextPath();
        HttpSession session = httpServletRequest.getSession();

        if ((session != null) && (session.getAttribute("currentUser") != null)) {
            return true;
        } else {
            httpServletResponse.sendRedirect(contextPath + "/signIn");
            return false;
        }
    }
}