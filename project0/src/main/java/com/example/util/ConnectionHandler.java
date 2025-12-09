package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHandler {
    private static Connection connection;

    static {
        if (connection == null) {
            Properties properties = new Properties();

            try (InputStream input = ConnectionHandler.class.getClassLoader().getResourceAsStream("database.properties")){

                if(input == null){
                    throw new Exception("Database properties file not found in resources.");
                } else {
                    properties.load(input);
                }

                // Load JDBC driver
                Class.forName(properties.getProperty("db.driver"));

                connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
                );

            } catch (IOException | ClassNotFoundException e){
                throw new RuntimeException("Unable to load database properties file.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection() throws RuntimeException{
        if (connection == null) {
            throw new RuntimeException("Connection is not established.");
        }
        return connection;
    }
}
