package view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.Person;
import view.api.ISaveShow;

/**
 * Class which contains all methods for work with attributes
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
public class AttributeWorker implements ISaveShow {

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
            value = (String) session.getAttribute(ATTRIBUTE);

            if (value == null) {
                throw new IllegalArgumentException("No value in session");
            }
        }

        return value;
    }

    /**
     * Retrieves value from parameters or cookies and saves value in cookie
     *
     * @param name parameter name
     * @param req HttpServletRequest object
     * @param resp HttpServletResponse object
     * @return String with parameter value
     * @since 1.1
     */
    @Override
    public String workWithParameter(String name, HttpServletRequest req, HttpServletResponse resp) {
        String result = getValueParamOrAttribute(req, req.getSession(), name);
        req.getSession().setAttribute(ATTRIBUTE, result);
        return result;
    }
}