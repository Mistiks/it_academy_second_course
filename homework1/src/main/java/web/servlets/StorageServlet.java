package web.servlets;

import model.dto.Person;
import view.AttributeWorker;
import view.CookieWorker;
import web.servlets.api.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that provides homework main functionality
 *
 * @version 1.1
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

    /** Parameter name for Person object that ready for get or set operation */
    private static final String ATTRIBUTE = "User";

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
        HttpSession session = req.getSession();
        String header = req.getHeader(HEADER_PARAMETER);
        Person person;
        String firstName;
        String lastName;
        int age;
        CookieWorker cookieWorker = new CookieWorker();
        AttributeWorker attributeWorker = new AttributeWorker();

        if (header == null) {
            throw new IllegalArgumentException("Header doesn't exist.");
        }

        if (header.equalsIgnoreCase(String.valueOf(Storage.COOKIES))) {
            firstName = cookieWorker.getValueParamOrCookie(req, FIRST_NAME_PARAM);
            lastName = cookieWorker.getValueParamOrCookie(req, LAST_NAME_PARAM);
            age = Integer.parseInt(cookieWorker.getValueParamOrCookie(req, AGE_PARAM));

            person = new Person(firstName, lastName, age);
            cookieWorker.saveCookies(resp, req, FIRST_NAME_PARAM, person.getFirstName());
            cookieWorker.saveCookies(resp, req, LAST_NAME_PARAM, person.getLastName());
            cookieWorker.saveCookies(resp, req, AGE_PARAM, String.valueOf(person.getAge()));

            person.setFirstName(cookieWorker.getValueParamOrCookie(req, FIRST_NAME_PARAM));
            person.setLastName(cookieWorker.getValueParamOrCookie(req, LAST_NAME_PARAM));
            person.setAge(Integer.parseInt(cookieWorker.getValueParamOrCookie(req, AGE_PARAM)));
       } else if (header.equalsIgnoreCase(String.valueOf(Storage.SESSION))) {
            firstName = attributeWorker.getValueParamOrAttribute(req, session, FIRST_NAME_PARAM);
            lastName = attributeWorker.getValueParamOrAttribute(req, session, LAST_NAME_PARAM);
            age = Integer.parseInt(attributeWorker.getValueParamOrAttribute(req, session, AGE_PARAM));

            person = new Person(firstName, lastName, age);
            session.setAttribute(ATTRIBUTE, person);
            person = (Person) session.getAttribute(ATTRIBUTE);
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