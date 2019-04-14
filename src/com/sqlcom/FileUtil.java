package com.sqlcom;

import com.sqlcom.databases.Database;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a utility file Parsing database credentials from and to files for storage between program restart.
 */
public final class FileUtil {

    /**
     * Ignored.
     */
    private FileUtil() {
    }

    /**
     * This loads databas credentials from the local storage and returns them as a ArrayList of Database objects.
     *
     * @return ArrayList with database objects.
     */
    public static ArrayList<Database> loadDatabases() {
        return new ArrayList<>(Arrays.asList(parseFromFile()));
    }

    /**
     * This saves a requested database to a file.
     * @param database the list of database entries to be saved
     */
    public static void saveDatabase(final Database[] database) {
        throw new UnsupportedOperationException();
    }

    /**
     * This parses a database to a file for reading later.
     *
     * @param db the database in question.
     */
    private static void parseTofile(final Database db) {
        throw new UnsupportedOperationException();
    }

    /**
     * This reads dataBase entries from files and returns a Database array.
     *
     * @return the loaded database entries.
     */
    private static Database[] parseFromFile() {
        throw new UnsupportedOperationException();
    }
}
