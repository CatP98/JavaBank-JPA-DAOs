package com.codeforall.online.javabank.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Manages database transactions
 */
@Component
public class TransactionManager {

    private ConnectionManager connectionManager;

    private boolean finished = false;

    /**
     * Gets the connection from the connection manager
     * @return
     */
    public Connection getConnection() {
        return connectionManager.getConnection();
    }

    /**
     * Start a transaction
     */
    public void startTransaction() {
        try {
            if (getConnection().getAutoCommit()) {
                getConnection().setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Commits a transaction
     */
    public void commit() {
        try {
            getConnection().commit();
            finished = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rollback a transaction
     */
    public void rollback() {
        try {
            getConnection().rollback();
            finished = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cleans up by changing the auto commit back to true
     */
    public void cleanUp() {
        try {
            if (!getConnection().getAutoCommit() && finished) {
                getConnection().setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the connection manager
     * @return connection manager
     */
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    /**
     * Set the connection manager to the given connection manager
     * @param connectionManager
     */
    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
