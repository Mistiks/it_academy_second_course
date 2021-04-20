package web.servlets;

import model.dto.Person;
import view.AttributeWorker;
import view.CookieWorker;
import view.api.ISaveShow;
import web.servlets.api.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that provides homework main functionality
 *
 * @version 1.2
 * @author Vadim Rataiko
 */
@WebServlet(name = "StorageServlet", urlPatterns = "/storage")
public class StorageServlet extends HttpServlet {

    /** Parameter name for person's first name */
    private static final String FIRST_NAME_PARAM = "firstName";

    /** Parameter name for person's last name */
    private static final String LAST_NAME_PARAM = "lastName";

    /** Parameter name for person's age */
    private static final String AGE_PARAM = "age";

    /** Parameter name for storage type */
    private static final String HEADER_PARAMETER = "storageType";

    /**
     * Method which processes GET request in homework
     *
     * @param req ServletRequest object
     * @param resp ServletResponse object
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     * @throws IllegalArgumentException if header doesn't exist or has unsupported value
     */
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader(HEADER_PARAMETER);
        Person person;
        String firstName;
        String lastName;
        int age;
        ISaveShow worker;

        if (header == null) {
            throw new IllegalArgumentException("Header doesn't exist.");
        }

        if (header.equalsIgnoreCase(String.valueOf(Storage.COOKIES))) {
            worker = new CookieWorker();
            firstName = worker.workWithParameter(FIRST_NAME_PARAM, req, resp);
            lastName = worker.workWithParameter(LAST_NAME_PARAM, req, resp);
            age = Integer.parseInt(worker.workWithParameter(AGE_PARAM, req, resp));

            person = new Person(firstName, lastName, age);
       } else if (header.equalsIgnoreCase(String.valueOf(Storage.SESSION))) {
            worker = new AttributeWorker();
            firstName = worker.workWithParameter(FIRST_NAME_PARAM, req, resp);
            lastName = worker.workWithParameter(LAST_NAME_PARAM, req, resp);
            age = Integer.parseInt(worker.workWithParameter(AGE_PARAM, req, resp));

            person = new Person(firstName, lastName, age);
        } else {
            throw new IllegalArgumentException("Invalid header");
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<p> First name: " + person.getFirstName() + "</p>");
        writer.write("<p> Last name: " + person.getLastName() + "</p>");
        writer.write("<p> Age: " + person.getAge() + "</p>");
        writer.close();
    }
}