package com.masterclass.api.support;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Soporte de tests JPA: construye un {@link EntityManagerFactory} aislado contra
 * una base H2 en memoria, registrando SOLO las entidades que cada test necesita.
 *
 * <p>Cada llamada usa una URL de base distinta, de modo que los tests no
 * comparten estado entre sí. La base se crea al abrir el EMF
 * ({@code hbm2ddl=create-drop}) y se destruye al cerrarlo.
 *
 * <p>Esto reproduce, en miniatura y sin contexto de Spring, lo que Spring Boot
 * hace por ti al arrancar: leer las entidades, generar el esquema y entregarte
 * un EntityManager listo.
 */
public final class JpaTestSupport {

    private static final AtomicInteger SEQ = new AtomicInteger();

    private JpaTestSupport() {
    }

    /**
     * @param entities clases {@code @Entity} a mapear en esta unidad de persistencia
     * @return un EntityManagerFactory H2 en memoria, aislado y desechable
     */
    public static EntityManagerFactory emf(Class<?>... entities) {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.url",
                "jdbc:h2:mem:b15test" + SEQ.incrementAndGet() + ";DB_CLOSE_DELAY=-1");
        cfg.setProperty("hibernate.connection.username", "sa");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        cfg.setProperty("hibernate.show_sql", "false");
        for (Class<?> e : entities) {
            cfg.addAnnotatedClass(e);
        }
        return cfg.buildSessionFactory();
    }
}
