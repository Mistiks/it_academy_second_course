package dao.hibernate;

import dao.api.IFlightAccess;
import dao.connectors.HibernateCreator;
import model.dto.Airport;
import model.dto.Flight;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class-realisation of ITableAccess interface for flights table in database
 * using Hibernate framework
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
public class FlightHibernate implements IFlightAccess<Flight> {

    /** Instance of FlightHibernate object */
    private final static FlightHibernate instance = new FlightHibernate();

    /**
     * Taking all lists from database that satisfy prepared in concrete
     * realisation SELECT request
     *
     * @param parameters parameters array. Could be null depending on realisation
     *                   parameters[0] - date of flight, parameters[1] - departure airport
     *                   parameters[2] - arrival airport, parameters[3] - amount of rows per page
     *                   parameters[4] - page
     * @return answer from database
     */
    @Override
    public List<Flight> getList(String[] parameters) {
        Session session = HibernateCreator.getInstance().openSession();
        String hibernateDateTime = parameters[0].substring(6,10) + "-" + parameters[0].substring(0,2) + "-" +
                parameters[0].substring(3,5) + " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(hibernateDateTime, formatter);
        Airport departureAirport;
        Airport arrivalAirport;
        List<Airport> airportsList = AirportHibernate.getInstance().getList(new String[]{"ru"});

        departureAirport = getAirportFromList(airportsList, parameters[1]);
        arrivalAirport = getAirportFromList(airportsList, parameters[2]);

        Query query = session.createQuery("FROM Flight WHERE scheduledDeparture BETWEEN :start AND :end AND " +
                "airportDeparture = :departure AND airportArrival = :arrival ORDER BY scheduledDeparture");
        query.setParameter("start", dateTime);
        query.setParameter("end", dateTime.plusDays(1));
        query.setParameter("departure", departureAirport);
        query.setParameter("arrival", arrivalAirport);
        query.setMaxResults(Integer.parseInt(parameters[3]));
        query.setFirstResult((Integer.parseInt(parameters[4]) - 1) * Integer.parseInt(parameters[3]));
        List<Flight> result = query.list();

        for (Flight element: result) {
            element.convertHibernateToString();
        }

        session.close();
        return result;
    }

    /**
     * Receives count of rows that satisfy prepared in concrete realisation SELECT request
     *
     * @param parameters parameters array. Could be null depending on realisation
     *                   parameters[0] - date of flight, parameters[1] - departure airport
     *                   parameters[2] - arrival airport
     * @return answer from database
     */
    @Override
    public int getRowCount(String[] parameters) {
        Session session = HibernateCreator.getInstance().openSession();
        String hibernateDateTime = parameters[0].substring(6,10) + "-" + parameters[0].substring(0,2) + "-" +
                parameters[0].substring(3,5) + " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(hibernateDateTime, formatter);
        Airport departureAirport;
        Airport arrivalAirport;
        List<Airport> airportsList = AirportHibernate.getInstance().getList(new String[]{"ru"});

        departureAirport = getAirportFromList(airportsList, parameters[1]);
        arrivalAirport = getAirportFromList(airportsList, parameters[2]);

        Query query = session.createQuery("FROM Flight WHERE scheduledDeparture BETWEEN :start AND :end AND " +
                "airportDeparture = :departure AND airportArrival = :arrival");
        query.setParameter("start", dateTime);
        query.setParameter("end", dateTime.plusDays(1));
        query.setParameter("departure", departureAirport);
        query.setParameter("arrival", arrivalAirport);
        int count = query.getResultList().size();

        session.close();
        return count;
    }

    /**
     * Search Airport object with provided airport name
     *
     * @param list list for search
     * @param name name of airport for search
     * @return Airport object with provided airport name, or null if Airport don't exist
     */
    private Airport getAirportFromList(List<Airport> list, String name) {

        for (Airport airport : list) {
            if (airport.getNameStoredLanguage().equals(name)) {
                return airport;
            }
        }

        return null;
    }

    /**
     * Receives instance of FlightHibernate class
     *
     * @return FlightHibernate class
     */
    public static FlightHibernate getInstance() {
        return instance;
    }
}