package com.sqlcom.databases;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This Database file is for Connecting to Mysql databases.
 */
public class DatabaseMYSQL extends Database {

    /**
     * This initiates a MySQL database object that extends Database class.
     *
     * @param dbName     The Database name
     * @param dbHost     The database host url
     * @param dbPort     The database url
     * @param dbUsername The database username
     * @param dbPassword The database password
     * @param dbUniqueId The database unique id
     */
    public DatabaseMYSQL(final String dbName, final String dbHost, final String dbPort,
                         final String dbUsername, final String dbPassword, final String dbUniqueId) {

        super(dbName, dbHost, dbPort, dbUsername, dbPassword, dbUniqueId);
    }

    /**
     * This updates database connection.
     *
     * @throws ClassNotFoundException This will get thrown if the mysql driver is missing.
     * @throws SQLException           this will get thrown if anything with connecting to the database goes wrong.
     */
    @Override
    public void updateDbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String defaultUrl = "jdbc:mysql://" + getDbHost() + "/" + getDbName() + "?serverTimezone=UTC";
        setConnection(DriverManager.getConnection(defaultUrl, super.getDbUsername(), super.getDbPassword()));
    }
}
