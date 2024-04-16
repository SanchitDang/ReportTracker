package org.sanapplications.Config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sanapplications.Model.AdminModel;
import org.sanapplications.Model.EmployeeModel;
import org.sanapplications.Model.ReportModel;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(AdminModel.class)
                .addAnnotatedClass(EmployeeModel.class)
                .addAnnotatedClass(ReportModel.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (factoryConfig == null)
                ? factoryConfig =  new SessionFactoryConfig()
                : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
