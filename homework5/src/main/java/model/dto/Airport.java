package model.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Class which stores all data about airport. Implements Comparable interface for
 * correct sorting russian city names.
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
@Entity
@Table(name = "airports_data")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Airport implements Serializable, Comparable<Airport> {

    /** Airport code */
    @Id
    @Column (name = "airport_code")
    private String code;

    /** Airport name in the required language. */
    @Transient
    private String nameStoredLanguage;

    /**
     * Airport name HashMap for Hibernate
     * Key - language, value - name of airport in that language
     *
     * @since 1.1
     */
    @Type(type = "jsonb")
    @Column(name = "airport_name", columnDefinition = "jsonb")
    private HashMap<String, String> name = new HashMap<>();

    /** Airport city in the required language. */
    @Transient
    private String cityStoredLanguage;

    /** City HashMap for Hibernate
     * Key - language, value - name of city in that language
     *
     * @since 1.1
     */
    @Type(type = "jsonb")
    @Column(name = "city", columnDefinition = "jsonb")
    private HashMap<String, String> city = new HashMap<>();

    /** Airport coordinates */
    @Column
    private String coordinates;

    /** Airport timezone */
    @Column
    private String timezone;

    /** Default constructor without parameters */
    public Airport() {}

    /**
     * Default constructor with parameters which defines Airport object.
     * Used when database is accessed without Hibernate framework.
     *
     * @param code Airport code
     * @param nameStoredLanguage Airport name
     * @param cityStoredLanguage Airport city
     * @param coordinates Airport coordinates
     * @param timezone Airport timezone
     */
    public Airport(String code, String nameStoredLanguage, String cityStoredLanguage, String coordinates, String timezone) {
        this.code = code;
        this.nameStoredLanguage = nameStoredLanguage;
        this.cityStoredLanguage = cityStoredLanguage;
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
    public String getNameStoredLanguage() {
        return nameStoredLanguage;
    }

    /**
     * Sets name field
     */
    public void setNameStoredLanguage(String nameStoredLanguage) {
        this.nameStoredLanguage = nameStoredLanguage;
    }

    /**
     * Receives city field
     *
     * @return airport city
     */
    public String getCityStoredLanguage() {
        return cityStoredLanguage;
    }

    /**
     * Sets city field
     */
    public void setCityStoredLanguage(String cityStoredLanguage) {
        this.cityStoredLanguage = cityStoredLanguage;
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

    /**
     * Retrieves airport names in all stored languages
     *
     * @return HashMap with cities in different languages
     * @since 1.1
     */
    public HashMap<String, String> getName() {
        return name;
    }

    /**
     * Sets newHashMap in name field
     *
     * @param name HashMap with new values for airport names
     * @since 1.1
     */
    public void setName(HashMap<String, String> name) {
        this.name = name;
    }

    /**
     * Retrieves cities in all stored languages
     *
     * @return HashMap with cities in different languages
     * @since 1.1
     */
    public HashMap<String, String> getCity() {
        return city;
    }

    /**
     * Sets new HashMap in city field
     *
     * @param city HashMap with new values for city names
     * @since 1.1
     */
    public void setCity(HashMap<String, String> city) {
        this.city = city;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Airport o) {
        return this.cityStoredLanguage.compareTo(o.getCityStoredLanguage());
    }

    /**
     * Printing all data from object
     *
     * @return String with object
     */
    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", nameStoredLanguage='" + nameStoredLanguage + '\'' +
                ", name=" + name +
                ", cityStoredLanguage='" + cityStoredLanguage + '\'' +
                ", city=" + city +
                ", coordinates='" + coordinates + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}