package controller.web.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.AirportDao;
import dao.api.ITableAccess;
import model.Airport;
import java.io.IOException;
import java.util.List;

/**
 * Class-servlet that takes airport names from database and sends it to jsp.
 * When user fills form, redirects data in another servlet for search in database
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@WebServlet(urlPatterns = "/flights")
public class FlightServlet extends HttpServlet{

    /** Parameter with a name for INPUT_FAIL attribute */
    private static final String INPUT_FAIL = "input_fail";

    /** Instance of ITableAccess interface */
    private final ITableAccess<Airport> tableAccess;

    /** Default constructor which defines ITableAccess interface */
    public FlightServlet() {
        this.tableAccess = AirportDao.getInstance();
    }

    /**
     * Handles GET request. Sends list with airports names to JSP
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> listAirport = this.tableAccess.getField("airport_name");
        request.setAttribute("listAirportNames", listAirport);

        request.getRequestDispatcher("/flights.jsp").forward(request, response);
    }

    /**
     * Handles POST request. Checks and saves data for search in database.
     * Will not proceed if some data misses.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getSession().getServletContext();
        String departure = request.getParameter("Departure");
        String arrival = request.getParameter("Arrival");
        String departureDate = request.getParameter("timeDeparture");
        String contextPath = request.getContextPath();

        if (departure.isEmpty() || arrival.isEmpty()  || departureDate.isEmpty()) {
            request.getSession().setAttribute(INPUT_FAIL, "true");
            response.sendRedirect(contextPath + "/flights");
            return;
        }

        context.setAttribute("Departure", departure);
        context.setAttribute("Arrival", arrival);
        context.setAttribute("timeDeparture", departureDate);
        response.sendRedirect(contextPath + "/search");
    }
}