package controller.web.servlets;

import jakarta.servlet.RequestDispatcher;
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
 * Class for getting all data about airports from database.
 * Sends it to jsp page.
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@WebServlet(urlPatterns = "/airports")
public class ListServlet extends HttpServlet{

    /** Instance of ITableAccess interface */
    private final ITableAccess<Airport> tableAccess;

    /** Default constructor which defines ITableAccess interface */
    public ListServlet() {
        this.tableAccess = AirportDao.getInstance();
    }

    /**
     * Handles GET request. Take data from database and sends it to jsp page.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] language = {request.getParameter("language")};

        if (language[0] == null) {
            language[0] = "ru";
        }

        List<Airport> listAirport = this.tableAccess.getList(language);
        request.setAttribute("listAirport", listAirport);
        request.setAttribute("language", language[0]);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/airports.jsp");
        dispatcher.forward(request, response);
    }
}