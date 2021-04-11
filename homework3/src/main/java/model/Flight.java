package model;

/**
 * Class for data about flight
 *
 * @author Vadim Rataiko
 * @since 1.0
 */
public class Flight {

    /** Flight code */
    private String flight;

    /** Departure time */
    private String departure;

    /** Arrival time */
    private String arrival;

    /** Departure airport */
    private String departureAirport;

    /** Arrival airport */
    private String arrivalAirport;

    /** Flight status */
    private String status;

    /** Aircraft model */
    private String aircraft;

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
}