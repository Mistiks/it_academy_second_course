package view.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface with api for saving and showing information about person
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public interface ISaveShow {

    /**
     * Access to data storage. Saving data or extracting existing data.
     *
     * @param name parameter name
     * @param req HttpServletRequest object
     * @param resp HttpServletResponse object
     * @return String with parameter value or IllegalArgumentException if value does not exist
     */
    String workWithParameter(String name, HttpServletRequest req, HttpServletResponse resp);
}
