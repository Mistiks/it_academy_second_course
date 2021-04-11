package model;

/**
 * Class for data about airport
 *
 * @author Vadim Rataiko
 * @since 1.0
 */
public class Airport {

    /** Airport code */
    private String code;

    /** Airport name */
    private String name;

    /** Airport city */
    private String city;

    /** Airport coordinates */
    private String coordinates;

    /** Airport timezone */
    private String timezone;

    /**
     * Default constructor which defines Airport object
     *
     * @param code Airport code
     * @param name Airport name
     * @param city Airport city
     * @param coordinates Airport coordinates
     * @param timezone Airport timezone
     */
    public Airport(String code, String name, String city, String coordinates, String timezone) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.coordinates = coordinates;
        this.timezone = timezone;
    }

    /**
     * Receives code field
     *
     * @return airport code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code field
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Receives name field
     *
     * @return airport name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Receives city field
     *
     * @return airport city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city field
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Receives coordinates field
     *
     * @return airport coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates field
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Receives timezone field
     *
     * @return airport timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets timezone field
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}