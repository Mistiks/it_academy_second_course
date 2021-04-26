package dao.connectors;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class for creating Hibernate connection to database
 *
 * @author Ilya Shadryn
 * @version 1.0
 */
public class HibernateCreator {

    /** Instance of SessionFactory object */
    private static SessionFactory sessionFactory = buildFactory();

    /**
     * Creates SessionFactory object which produces Hibernate sessions for
     * database accessing and performing CRUD-based operations.
     *
     * @return SessionFactory object
     */
    private static SessionFactory buildFactory() {

        try {
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(standardServiceRegistry)
                    .getMetadataBuilder()
                    .build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    /**
     * Receives instance of SessionFactory object
     *
     * @return SessionFactory object
     */
    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
