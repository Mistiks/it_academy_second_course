package dao.api;

import java.util.List;

/**
 * Database access interface. Used by servlets for preparing requests
 * and receiving answers from flights table.
 *
 * @param <T> Generic parameter
 */
public interface IFlightAccess<T> {

    /**
     * Taking all lists from database that satisfy prepared in concrete
     * realisation SELECT request
     *
     * @param parameters parameters array. Could be null depending on realisation
     * @return answer from database
     */
    List<T> getList(String[] parameters);

    /**
     * Receives count of rows that satisfy prepared in concrete realisation SELECT request
     *
     * @param parameters parameters array. Could be null depending on realisation
     * @return answer from database
     */
    int getRowCount(String[] parameters);
}