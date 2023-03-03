package pl.solvd.concerthall.utils;

import pl.solvd.concerthall.DAOReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static Connection connection;

    private ConnectionPool() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("ConcertHall/src/main/resources/mysqlcreds.properties"));
            final String dbUrl = properties.getProperty("url");
            final String dbUsername = properties.getProperty("login");
            final String dbPassword = properties.getProperty("password");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            DAOReader.LOG.info("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
            DAOReader.LOG.error("Connection Error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                try {
                    if (instance == null || instance.connection.isClosed()) {
                        instance = new ConnectionPool();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
