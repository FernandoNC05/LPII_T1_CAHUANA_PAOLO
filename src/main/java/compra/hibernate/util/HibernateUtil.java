package compra.hibernate.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emf = build();

    private static EntityManagerFactory build() {
        try {
            return Persistence.createEntityManagerFactory("compraPU");
        } catch (Exception e) {
            throw new RuntimeException("Error creando EntityManagerFactory: " + e.getMessage(), e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void close() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}