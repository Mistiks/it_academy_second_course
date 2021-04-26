package model.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for data about flight
 *
 * @author Vadim Rataiko
 * @version 1.1
 */
@Entity
@Table(name = "flights")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Flight implements Serializable {

    /** Formatter for conversion LocalDateTime to String */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Flight id for Hibernate */
    @Id
    @Column (name = "flight_id")
    private int flightId;

    /** Flight code */
    @Column (name = "flight_no")
    private String flight;

    /** Departure time for Hibernate */
    @Column (name = "scheduled_departure")
    private LocalDateTime scheduledDeparture;

    /** Departure time */
    @Transient
    private String departure;

    /** Arrival time for Hibernate */
    @Column (name = "scheduled_arrival")
    private LocalDateTime scheduledArrival;

    /** Arrival time */
    @Transient
    private String arrival;

    /** Departure airport for Hibernate */
    @ManyToOne
    @JoinColumn(name="departure_airport")
    private Airport airportDeparture;

    /** Departure airport */
    @Transient
    private String departureAirport;

    /** Arrival airport for Hibernate */
    @ManyToOne
    @JoinColumn(name="arrival_airport")
    private Airport airportArrival;

    /** Arrival airport */
    @Transient
    private String arrivalAirport;

    /** Flight status */
    @Column (name = "status")
    private String status;

    /** Aircraft model for Hibernate */
    @ManyToOne
    @JoinColumn(name="aircraft_code")
    private Aircraft aircraftModel;

    /** Aircraft model */
    @Transient
    private String aircraft;

    /** Default constructor without parameters */
    public Flight() {
    }

    /**
     * Default constructor which defines Flight object
     *
     * @param flight Flight code
     * @param departure Departure time
     * @param arrival Arrival time
     * @param departureAirport Departure airport
     * @param arrivalAirport Arrival airport
     * @param status Flight status
     * @param aircraft Aircraft model
     */
    public Flight(String flight, String departure, String arrival, String departureAirport,
                  String arrivalAirport, String status, String aircraft) {
        this.flight = flight;
        this.departure = departure;
        this.arrival = arrival;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.status = status;
        this.aircraft = aircraft;
    }

    /**
     * Receives flight code
     *
     * @return flight code
     */
    public String getFlight() {
        return flight;
    }

    /**
     * Sets flight code
     */
    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * Receives departure time
     *
     * @return departure time
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * Sets departure time
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * Receives arrival time
     *
     * @return arrival time
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * Sets arrival time
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /**
     * Receives departure airport
     *
     * @return departure airport
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * Sets departure airport
     */
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * Receives arrival airport
     *
     * @return arrival airport
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * Sets arrival airport
     */
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     * Receives flight status
     *
     * @return flight status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets flight status
     *
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Receives aircraft model
     *
     * @return aircraft model
     */
    public String getAircraft() {
        return aircraft;
    }

    /**
     * Sets aircraft model
     */
    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    /**
     * Receives flight id (Hibernate only)
     *
     * @return flight id
     */
    public int getFlightId() {
        return flightId;
    }

    /**
     * Sets new flight id
     *
     * @param flightId new id
     */
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    /**
     * Receives departure time (Hibernate only)
     *
     * @return departure time
     */
    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    /**
     * Sets new scheduled departure (Hibernate only)
     *
     * @param scheduledDeparture new scheduled departure
     */
    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    /**
     * Receives arrival time (Hibernate only)
     *
     * @return arrival time
     */
    public LocalDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    /**
     * Sets new scheduled arrival (Hibernate only)
     *
     * @param scheduledArrival new scheduled arrival
     */
    public void setScheduledArrival(LocalDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    /**
     * Receives departure airport (Hibernate only)
     *
     * @return departure airport
     */
    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    /**
     * Sets new departure airport (Hibernate only)
     *
     * @param airportDeparture new departure airport
     */
    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    /**
     * Receives arrival airport (Hibernate only)
     *
     * @return arrival airport
     */
    public Airport getAirportArrival() {
        return airportArrival;
    }

    /**
     * Sets new arrival airport (Hibernate only)
     *
     * @param airportArrival new arrival airport
     */
    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
    }

    /**
     * Receives aircraft model (Hibernate only)
     *
     * @return aircraft model
     */
    public Aircraft getAircraftModel() {
        return aircraftModel;
    }

    /**
     * Sets new aircraft model (Hibernate only)
     *
     * @param aircraftModel new aircraft model
     */
    public void setAircraftModel(Aircraft aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    /**
     * Converts departure LocalDateTime value to String and saves it in departure field
     * (Hibernate only)
     */
    public void convertToDeparture() {
        departure = scheduledDeparture.format(formatter);
    }

    /**
     * Converts arrival LocalDateTime value to String and saves it in departure field
     * (Hibernate only)
     */
    public void convertToArrival() {
        arrival = scheduledArrival.format(formatter);
    }

    /** Converts model name from Airport object to String field (Hibernate only) */
    public void convertToAircraft() {
        aircraftModel.setModelStoredLanguage(aircraftModel.getModel().get("ru"));
        aircraft = aircraftModel.getModelStoredLanguage();
    }

    /** Convert name of departure Airport object to String field */
    public void convertToDepartureAirport() {
        airportDeparture.setNameStoredLanguage(airportDeparture.getName().get("ru"));
        departureAirport = airportDeparture.getNameStoredLanguage();
    }

    /** Convert name of arrival Airport object to String field */
    public void convertToArrivalAirport() {
        airportArrival.setNameStoredLanguage(airportArrival.getName().get("ru"));
        arrivalAirport = airportArrival.getNameStoredLanguage();
    }

    /** Fills String fields with data received from Hibernate */
    public void convertHibernateToString() {
        convertToDeparture();
        convertToArrival();
        convertToAircraft();
        convertToDepartureAirport();
        convertToArrivalAirport();
    }

    /**
     * Printing all data from object
     *
     * @return String with object
     */
    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flight='" + flight + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", departure='" + departure + '\'' +
                ", scheduledArrival=" + scheduledArrival +
                ", arrival='" + arrival + '\'' +
                ", airportDeparture=" + airportDeparture +
                ", departureAirport='" + departureAirport + '\'' +
                ", airportArrival=" + airportArrival +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", status='" + status + '\'' +
                ", aircraftModel=" + aircraftModel +
                ", aircraft='" + aircraft + '\'' +
                '}';
    }
}