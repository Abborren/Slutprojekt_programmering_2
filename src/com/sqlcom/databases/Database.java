package com.sqlcom.databases;

import java.sql.*;
import java.util.ArrayList;
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
     * This loops through a input Resultset and convert it to a
     * HashMap array.
     *
     * @param rs a result of a query.
     * @return returns a hashmap array list of strings with the result values
     * @throws SQLException this will get thrown if anything with connecting to the database goes wrong.
     */
    private ArrayList<HashMap<String, String>> fetch(final ResultSet rs) throws SQLException {
        ArrayList<HashMap<String, String>> returnsArr = new ArrayList<>();
        while (rs.next()) {
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();

            HashMap<String, String> hashMap = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String name = metadata.getColumnName(i + 1);
                hashMap.put(name, rs.getString(name));
            }
            returnsArr.add(hashMap);
        }
        // This returns a hashmap arraylist with one index with a key of "200",
        // will get casted incase the database dosen't give a result.
        if (returnsArr.size() == 0) {
            HashMap<String, String> stringHashMap = new HashMap<>();
            stringHashMap.put("200", "The query did not return a result but query ran successfully");
            ArrayList<HashMap<String, String>> maps = new ArrayList<>();
            maps.add(stringHashMap);
            return maps;
        }
        return returnsArr;
    }

    /**
     * This will execute a string sql query.
     *
     * @param stmt an SQL statement to be sent to the database, typically a
     *             static SQL <code>SELECT</code> statement
     * @return returns a hashmap array list with the results of the queries. null if it fails or there is no result.
     * if the query does not return any data it returns a hashmap array with one hashmap index containing a key set called "200"
     */
    public ArrayList<HashMap<String, String>> execute(final String stmt) {
        try {
            Statement statement = connection.createStatement();
            return fetch(statement.executeQuery(stmt));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
