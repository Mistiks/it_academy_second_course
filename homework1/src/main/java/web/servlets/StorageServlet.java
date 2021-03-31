package web.servlets;

import core.dto.Person;
import web.servlets.api.Storage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Class that provides homework main functionality
 *
 * @version 1.0
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

        if (header == null) {
            throw new IllegalArgumentException("Header doesn't exist.");
        }

        if (header.equalsIgnoreCase(String.valueOf(Storage.COOKIES))) {
            firstName = getValueParamOrCookie(req, FIRST_NAME_PARAM);
            lastName = getValueParamOrCookie(req, LAST_NAME_PARAM);
            age = Integer.parseInt(getValueParamOrCookie(req, AGE_PARAM));

            person = new Person(firstName, lastName, age);
            saveCookies(resp, req, FIRST_NAME_PARAM, person.getFirstName());
            saveCookies(resp, req, LAST_NAME_PARAM, person.getLastName());
            saveCookies(resp, req, AGE_PARAM, String.valueOf(person.getAge()));

            person.setFirstName(getValueFromCookie(req, FIRST_NAME_PARAM));
            person.setLastName(getValueFromCookie(req, LAST_NAME_PARAM));
            person.setAge(Integer.parseInt(getValueFromCookie(req, AGE_PARAM)));
       } else if (header.equalsIgnoreCase(String.valueOf(Storage.SESSION))) {
            firstName = getValueParamOrAttribute(req, session, FIRST_NAME_PARAM);
            lastName = getValueParamOrAttribute(req, session, LAST_NAME_PARAM);
            age = Integer.parseInt(getValueParamOrAttribute(req, session, AGE_PARAM));

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
    }

    /**
     * Method which search parameter value from query string or cookies
     *
     * @param req ServletRequest object
     * @param name parameter name
     * @return String with parameter value
     */
    public String getValueParamOrCookie(HttpServletRequest req, String name) {
        String value = req.getParameter(name);

        if (value == null) {
            value = getValueFromCookie(req, name);
        }

        return value;
    }

    /**
     * Method which search parameter value from query string or attribute. Can throw IllegalArgument Exception
     * if parameter does not exist
     *
     * @param req ServletRequest object
     * @param session current Http session
     * @param name parameter name
     * @return String with parameter value
     */
    public String getValueParamOrAttribute(HttpServletRequest req, HttpSession session, String name) {
        String value = req.getParameter(name);

        if (value == null) {
            Person person = (Person) session.getAttribute(ATTRIBUTE);

            switch (name) {
                case FIRST_NAME_PARAM:
                    value = person.getFirstName();
                    break;
                case LAST_NAME_PARAM:
                    value = person.getLastName();
                    break;
                case AGE_PARAM:
                    value = String.valueOf(person.getAge());
                    break;
                default:
                    throw new IllegalArgumentException("No parameter in attribute");
            }
        }
        return value;
    }

    /**
     * Method which search parameter value from cookie. Can throw IllegalArgument Exception
     * if parameter does not exist
     *
     * @param req ServletRequest object
     * @param name parameter name
     * @return String with parameter value
     */
    public String getValueFromCookie(HttpServletRequest req, String name) {
        String value = null;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            value = Arrays.stream(cookies)
                    .filter(c -> name.equalsIgnoreCase(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (value == null) {
            throw new IllegalArgumentException("No value in cookies");
        }

        return value;
    }

    /**
     * Method which search Cookie object with certain name
     *
     * @param req ServletRequest object
     * @param name cookie name
     * @return String with Cookie value or null if cookie does not exist
     */
    public Cookie getCookie(HttpServletRequest req, String name) {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    /**
     * Method which creates or updates existing cookie
     *
     * @param resp ServletResponse object
     * @param req ServletRequest object
     * @param name name of Cookie object
     * @param value value of Cookie object
     */
    public void saveCookies(HttpServletResponse resp, HttpServletRequest req, String name, String value) {
        Cookie cookie = getCookie(req, name);

        if (cookie != null) {
            cookie.setValue(value);
        } else {
            cookie = new Cookie(name, value);
            cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        }
        resp.addCookie(cookie);
    }
}