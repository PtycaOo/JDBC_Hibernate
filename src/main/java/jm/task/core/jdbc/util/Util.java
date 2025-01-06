package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Connection connection = null;

    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String name = "root";
    private final String password = "root";

    public Util() {
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            System.out.println("Подключение не установленно");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

