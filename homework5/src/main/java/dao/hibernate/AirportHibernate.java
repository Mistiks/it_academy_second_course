package dao.hibernate;

import dao.api.ITableAccess;
import dao.connectors.HibernateCreator;
import model.dto.Airport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class-realisation of ITableAccess interface for airports_data table in database
 * using Hibernate framework
 *
 * @author Vadim Rataiko
 * @version  1.0
 */
public class AirportHibernate implements ITableAccess<Airport> {

    /** Instance of AirportHibernate object */
    private final static AirportHibernate instance = new AirportHibernate();

    /**
     * Taking all records from database that satisfy prepared in concrete
     * realisation SELECT request. Sorts resulting list if russian language is required,
     * because result in this case incorrectly sorted by database.
     *
     * @param parameters parameters array. Could be null depending on realisation.
     *                   parameters[0] - language
     * @return answer from database sorted by city
     */
    @Override
    public List<Airport> getList(String[] parameters) {
        Session session = HibernateCreator.getInstance().openSession();
        Query query = session.createQuery("FROM Airport ORDER BY city");
        List<Airport> listAirport = query.list();

        for (Airport airport : listAirport) {
            airport.setNameStoredLanguage(airport.getName().get(parameters[0]));
            airport.setCityStoredLanguage(airport.getCity().get(parameters[0]));
        }

        if (parameters[0].equals("ru")) {
            Collections.sort(listAirport);
        }

        session.close();
        return listAirport;
    }

    /**
     * Taking data from fields with airport name
     *
     * @param language search language
     * @return list with all airport names sorted by city
     */
    @Override
    public List<String> getField(String language) {
        List<Airport> listAirport = getList(new String[]{language});
        List<String> result = new ArrayList<>();
        Collections.sort(listAirport);

        for (Airport airport : listAirport) {
            result.add(airport.getNameStoredLanguage());
        }

        return result;
    }

    /**
     * Receives count of rows in airports_data table. Written to maintain the interface.
     * Not used in homework at the moment. Will be needed when pagination will be added.
     *
     * @param parameters parameters array. Could be null depending on realisation
     *                   parameters[0] - language
     * @return answer from database
     */
    @Override
    public int getRowCount(String[] parameters) {
        List<Airport> listAirport = getList(parameters);
        return listAirport.size();
    }

    /**
     * Receives instance of AirportHibernate class
     *
     * @return AirportHibernate class
     */
    public static AirportHibernate getInstance() {
        return instance;
    }
}