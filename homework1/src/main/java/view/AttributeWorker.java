package view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dto.Person;

/**
 * Class which contains all methods for work with attributes
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class AttributeWorker {

    /**
     * Parameter name for person's first name
     */
    private static final String FIRST_NAME_PARAM = "firstName";

    /**
     * Parameter name for person's last name
     */
    private static final String LAST_NAME_PARAM = "lastName";

    /**
     * Parameter name for person's age
     */
    private static final String AGE_PARAM = "age";

    /**
     * Parameter name for Person object that ready for get or set operation
     */
    private static final String ATTRIBUTE = "User";

    /**
     * Method which search parameter value from query string or attribute. Can throw IllegalArgument Exception
     * if parameter does not exist or no value stored in session and request
     *
     * @param req     ServletRequest object
     * @param session current Http session
     * @param name    parameter name
     * @return String with parameter value
     */
    public String getValueParamOrAttribute(HttpServletRequest req, HttpSession session, String name) {
        String value = req.getParameter(name);

        if (value == null) {
            Person person = (Person) session.getAttribute(ATTRIBUTE);

            try {
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
                        throw new IllegalArgumentException("Wrong parameter name");
                }
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("No value in session");
            }
        }

        return value;
    }
}