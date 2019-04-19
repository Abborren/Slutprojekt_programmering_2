package com.sqlcom.databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
     * The databases unique id for saving to file.
     */
    private String uniqueId;
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
     * @param uniqueId   The databases unique id
     */
    public Database(final String dbName, final String dbHost, final String dbPort, final String dbUsername, final String dbPassword, final String uniqueId) {
        this.dbName = dbName;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.uniqueId = uniqueId;
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
     * This will execute a string sql query.
     *
     * @param stmt an SQL statement to be sent to the database, typically a
     *             static SQL <code>SELECT</code> statement
     *             Javadoc borrowed from the JDBC api under the GPL license.
     * @return returns a hashmap array list with the results of the queries. null if it fails or there is no result.
     * if the query does not return any data it returns a hashmap array with one hashmap index containing a key set called "200"
     * @throws SQLException this will get thrown if anything with connecting to the database goes wrong.
     */
    public ResultSet execute(final String stmt) throws SQLException {
        return connection.createStatement().executeQuery(stmt);
    }

    /**
     * Executes the given SQL statement, which may be an <code>INSERT</code>,
     * <code>UPDATE</code>, or <code>DELETE</code> statement or an
     * SQL statement that returns nothing, such as an SQL DDL statement.
     * <p>
     * <strong>Note:</strong>This method cannot be called on a
     * <code>PreparedStatement</code> or <code>CallableStatement</code>.
     * Javadoc borrowed from the JDBC api under the GPL license.
     *
     * @param stmt SQL statement, such as <code>INSERT</code>, <code>UPDATE</code> or
     *             <code>DELETE</code>; or an SQL statement that returns nothing.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs,
     *                      this method is called on a closed <code>Statement</code>, the given
     *                      SQL statement produces a <code>ResultSet</code> object, the method is called on a
     *                      <code>PreparedStatement</code> or <code>CallableStatement</code>
     */
    public int executeUpdate(final String stmt) throws SQLException {
        return connection.createStatement().executeUpdate(stmt);
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
     * @return the connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection new db connection.
     */
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the unique id (filename).
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId new unique id.
     */
    public void setUniqueId(final String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Overrides <code>toString</code> method to enable use in comboBoxes.
     *
     * @return The db name and host url
     */
    @Override
    public String toString() {
        return getDbName() + ", " + getDbHost();
    }
}

