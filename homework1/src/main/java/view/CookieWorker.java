package view;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Class which contains all methods for work with cookies
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class CookieWorker {

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