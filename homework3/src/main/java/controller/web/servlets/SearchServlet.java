package controller.web.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.FlightDAO;
import dao.api.ITableAccess;
import model.Flight;
import java.io.IOException;
import java.util.List;

/**
 * Flights searching data class. Receives departure and arrival point
 * with departure time and uses it for database request. Sends database
 * answer for showing to JSP page
 *
 * @author Vadim Rataiko
 * @since 1.0
 */
@WebServlet(urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    /** Instance of ITableAccess interface */
    private final ITableAccess<Flight> tableAccess;

    /** Variable that stores amount of records per page */
    private final int rowsOnPage = 25;

    /** Default constructor which defines ITableAccess interface */
    public SearchServlet() {
        this.tableAccess = FlightDAO.getInstance();
    }

    /**
     * Handles GET request. Sends data needed for database request, receives answer
     * and saves it for showing on JSP page
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = request.getSession().getServletContext();
        int page;

        if (request.getParameter("currentPage") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("currentPage"));
        }

        String departure = request.getParameter("Departure");
        String arrival = request.getParameter("Arrival");
        String departureDate = request.getParameter("timeDeparture");

        if (departure == null || arrival == null  || departureDate == null) {
            departure = (String) context.getAttribute("Departure");
            arrival = (String) context.getAttribute("Arrival");
            departureDate = (String) context.getAttribute("timeDeparture");
            context.removeAttribute("Departure");
            context.removeAttribute("Arrival");
            context.removeAttribute("timeDeparture");
        }

        String[] parameters = {departureDate, departure, arrival};
        System.out.println(departureDate);

        int numberOfRows = tableAccess.getRowCount(parameters);
        int maxPage = (int) Math.ceil((double)numberOfRows / this.rowsOnPage);

        parameters = new String[]{departureDate, departure, arrival,
                String.valueOf(this.rowsOnPage), String.valueOf(page)};
        List<Flight> flightsList = this.tableAccess.getList(parameters);

        request.setAttribute("flightsList", flightsList);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("Departure", departure);
        request.setAttribute("Arrival", arrival);
        request.setAttribute("timeDeparture", departureDate);
        request.setAttribute("currentPage", page);
        System.out.println(departureDate);
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }
}