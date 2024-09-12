package com.codeforall.online.javabank.persistence;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the connection for the application and makes sure it closes when the application finishes
 */
@Component
public class ConnectionManager implements DisposableBean {

    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASS = "";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_DB = "javabank";
    private static final String DEFAULT_PORT = "5432";

    private static final String CONNECTOR = "jdbc:postgresql:";

    private String dbUrl;
    private String user;
    private String pass;
    private Connection connection;

    /**
     * Initialises the connection manager with customised credentials
     * @param user the database user
     * @param pass the password of the user
     * @param host the host of the database
     * @param port the port number
     * @param database the name of the database
     */
    public ConnectionManager(String user, String pass, String host, String port, String database) {
        this.user = user;
        this.pass = pass;
        this.dbUrl = CONNECTOR + "//" + host + ":" + port + "/" + database;
    }

    /**
     * Initialises the connection manager with default credentials
     */
    public ConnectionManager() {
        this(DEFAULT_USER, DEFAULT_PASS, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DB);
    }

    /**
     * Gets the connection to the database through the Driver Manager
     * @return a connection
     */
    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    /**
     * Closes a connection
     */
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }

    /**
     * Closes the connection to the database when the application is shutdown
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
