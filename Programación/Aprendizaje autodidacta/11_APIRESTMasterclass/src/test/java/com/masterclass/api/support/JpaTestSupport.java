package com.masterclass.api.support;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Soporte de tests JPA AISLADO: construye un EntityManagerFactory propio por
 * cada conjunto de entidades, con H2 en memoria y create-drop. Cada llamada usa
 * una BD distinta, así un ejercicio sin resolver no contamina a los demás.
 */
public final class JpaTestSupport {

    private JpaTestSupport() {
    }

    public static EntityManagerFactory emf(Class<?>... annotated) {
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "org.h2.Driver");
        settings.put("hibernate.connection.url",
                "jdbc:h2:mem:jpa_" + UUID.randomUUID().toString().replace("-", "")
                        + ";DB_CLOSE_DELAY=-1");
        settings.put("hibernate.connection.username", "sa");
        settings.put("hibernate.connection.password", "");
        settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.hbm2ddl.auto", "create-drop");
        settings.put("hibernate.show_sql", "false");

        var registry = new StandardServiceRegistryBuilder().applySettings(settings).build();
        var sources = new MetadataSources(registry);
        for (Class<?> c : annotated) {
            sources.addAnnotatedClass(c);
        }
        return sources.buildMetadata().buildSessionFactory();
    }
}
