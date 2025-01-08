package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String name = "root";
    private final String password = "root";

    private Connection connection = null;

    private static SessionFactory sessionFactory = null;


    /* Соединение для JDBC*/
//    public Util() {
//        try {
//            connection = DriverManager.getConnection(url, name, password);
//        } catch (SQLException e) {
//            System.out.println("Подключение не установленно");
//        }
//    }

    /*Блок для инициализации интерфейса SessionFabric с настройками*/
    static {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "root");
            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.hbm2ddl.auto", "create");

            sessionFactory = new org.hibernate.cfg.Configuration().addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    /*Можно обойтись без этого метода, но с ним короче код в UserDaoHibernate*/
    public static Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }

    public Connection getConnection() {
        return connection;
    }
}

