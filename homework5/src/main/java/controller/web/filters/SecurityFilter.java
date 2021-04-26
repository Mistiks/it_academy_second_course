package controller.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Class with filter that prevents user from entering search page
 * without search data
 *
 * @author Vadim Rataiko
 */
@WebFilter(urlPatterns = {"/search"})
public class SecurityFilter implements Filter {

    /**
     * Redirects to search page if application doesn't have parameters
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
        ServletContext context = req.getSession().getServletContext();
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();

        String departure = request.getParameter("Departure");
        String arrival = request.getParameter("Arrival");
        String departureDate = request.getParameter("timeDeparture");

        if (departure == null || arrival == null  || departureDate == null) {
            departure = (String) context.getAttribute("Departure");
            arrival = (String) context.getAttribute("Arrival");
            departureDate = (String) context.getAttribute("timeDeparture");
        }

        if (departure != null && arrival != null || departureDate != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(contextPath + "/flights");
        }
    }
}