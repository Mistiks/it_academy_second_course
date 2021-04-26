package model.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Class which stores all data about aircraft.
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@Entity
@Table(name = "aircrafts_data")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Aircraft implements Serializable {

    /** Aircraft code */
    @Id
    @Column(name = "aircraft_code")
    private String code;

    /**
     * Model name HashMap for Hibernate
     * Key - language, value - name of model in that language
     */
    @Type(type = "jsonb")
    @Column(name = "model", columnDefinition = "jsonb")
    private HashMap<String, String> model = new HashMap<>();

    /** Aircraft range */
    @Column
    private int range;

    /** Aircraft model in the required language. */
    @Transient
    private String modelStoredLanguage;

    /** Default constructor without parameters */
    public Aircraft() {}

    /**
     * Retrieves aircraft code
     *
     * @return String with code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets aircraft code
     *
     * @param code aircraft code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves model name in all stored languages
     *
     * @return HashMap with model name in different languages
     */
    public HashMap<String, String> getModel() {
        return model;
    }

    /**
     * Sets new HashMap in city field
     *
     * @param model HashMap with new values for aircraft model
     * @since 1.1
     */
    public void setModel(HashMap<String, String> model) {
        this.model = model;
    }

    /**
     * Retrieves aircraft range
     *
     * @return aircraft range
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets new aircraft range
     *
     * @param range new value for range field
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Retrieves model name in stored language
     *
     * @return String with model name
     */
    public String getModelStoredLanguage() {
        return modelStoredLanguage;
    }

    /**
     * Sets new model name
     *
     * @param modelStoredLanguage new value
     */
    public void setModelStoredLanguage(String modelStoredLanguage) {
        this.modelStoredLanguage = modelStoredLanguage;
    }

    /**
     * Printing all data from object
     *
     * @return String with object
     */
    @Override
    public String toString() {
        return "Aircraft{" +
                "code='" + code + '\'' +
                ", model=" + model +
                ", range=" + range +
                ", modelStoredLanguage='" + modelStoredLanguage + '\'' +
                '}';
    }
}