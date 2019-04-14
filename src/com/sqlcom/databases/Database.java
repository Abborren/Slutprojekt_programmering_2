package com.sqlcom.databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This abstract Database file is for a generic connection databases.
 */
public abstract class Database {

    /**
     * The Database name.
     */
    private String dbName;

    /**
     * The database host url.
     */
    private String dbHost;

    /**
     * The database url.
     */
    private String dbPort;

    /**
     * The database username.
     */
    private String dbUsername;

    /**
     * The database password.
     */
    private String dbPassword;

    /**
     * The SQL database connection.
     */
    private Connection connection;

    /**
     * This stores the different variables needed to connect to a database.
     *
     * @param dbName     The Database name
     * @param dbHost     The database host url
     * @param dbPort     The database url
     * @param dbUsername The database username
     * @param dbPassword The database password
     */
    public Database(final String dbName, final String dbHost, final String dbPort, final String dbUsername, final String dbPassword) {
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    /**
     * This method will update the database connection incase any variables are changed.
     *
     * @throws ClassNotFoundException This will get thrown if the mysql driver is missing.
     * @throws SQLException           this will get thrown if anything with connecting to the database goes wrong.
     */
    public abstract void updateDbConnection() throws SQLException, ClassNotFoundException;

    /**
     * Tries to update DbConnection.
     */
    private void tryUpdateDbConnection() {
        try {
            updateDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This connects to the database.
     *
     * @return returns true if the connection is successful
     */
    public boolean connect() {
        throw new UnsupportedOperationException();
    }

    /**
     * This disconnects the database connection.
     *
     * @return true if connection is closed successfully
     */
    public boolean disconnect() {
        try {
            connection.close();
            return connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * This returns the current databases connection status.
     *
     * @return true if the connection is valid else false
     */
    public boolean status() {
        try {
            return connection.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * This fetches the result of a stmt as a HashMap array.
     *
     * @param stmt the statement (Might be changed to a ResultSet)
     * @return returns a hashmap array of strings with the result values
     */
    public HashMap<String, String>[] fetch(final String stmt) {
        throw new UnsupportedOperationException();
    }

    /**
     * This will execute a string sql query.
     *
     * @param stmt the query
     */
    public void execute(final String stmt) {

    }

    /* Setter and Getter section */

    /**
     * @return dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName new dbName
     */
    public void setDbName(final String dbName) {
        this.dbName = dbName;
        tryUpdateDbConnection();
    }

    /**
     * @return dbName host.
     */
    public String getDbHost() {
        return dbHost;
    }

    /**
     * @param dbHost the new dbName host.
     */
    public void setDbHost(final String dbHost) {
        this.dbHost = dbHost;
        tryUpdateDbConnection();
    }

    /**
     * @return dbName port.
     */
    public String getDbPort() {
        return dbPort;
    }

    /**
     * @param dbPort the new dbName port
     */
    public void setDbPort(final String dbPort) {
        this.dbPort = dbPort;
        tryUpdateDbConnection();
    }

    /**
     * @return dbName username.
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * @param dbUsername new dbName username.
     */
    public void setDbUsername(final String dbUsername) {
        this.dbUsername = dbUsername;
        tryUpdateDbConnection();
    }

    /**
     * @return dbName password.
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param dbPassword new dbName password.
     */
    public void setDbPassword(final String dbPassword) {
        this.dbPassword = dbPassword;
        tryUpdateDbConnection();
    }

    /**
     * @param connection new dbName connection.
     */
    public void setConnection(final Connection connection) {
        this.connection = connection;
        tryUpdateDbConnection();
    }
}
