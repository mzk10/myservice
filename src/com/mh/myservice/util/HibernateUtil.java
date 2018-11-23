package com.mh.myservice.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Configuration configuration;
    private static SessionFactory factory;

    private static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
            configuration.configure();
        }
        return configuration;
    }

    private static SessionFactory getFactory() {
        if (factory == null) {
            factory = getConfiguration().buildSessionFactory();
        }
        return factory;
    }

    public static Session openSession() {
        return getFactory().openSession();
    }

}
