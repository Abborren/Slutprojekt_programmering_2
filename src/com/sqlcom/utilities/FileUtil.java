package com.sqlcom.utilities;

import com.sqlcom.databases.Database;
import com.sqlcom.databases.DatabaseMYSQL;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

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
        ArrayList<Database> list = parseFromFile();
        if (list == null) {
            return null;
        }
        return list;

    }

    /**
     * This saves a requested database to a file.
     *
     * @param databases the list of database entries to be saved
     */
    public static void saveDatabase(final ArrayList<Database> databases) {
        for (Database database : databases) {
            parseToFile(database);
        }
    }

    /**
     * This parses a database to a file for reading later.
     *
     * @param database the database in question.
     */
    private static void parseToFile(final Database database) {
        String dbFileName = database.getUniqueId();
        File[] dbcFiles = getFiles("/Databases");
        dbcFiles = getDBCFiles(dbcFiles);

        if (dbcFiles != null) {
            for (File dbc : dbcFiles) {
                if (dbc.getName().contains(dbFileName)) {
                    try {
                        writeToFile(dbc, database);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        String dbDirectory = getAppFolderDirectory() + "/Databases/";
        File file = new File(dbDirectory + dbFileName + ".dbc");
        try {
            file.createNewFile();
            writeToFile(file, database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This writes a database to a specified file.
     *
     * @param file     the specified file
     * @param database the database object to be saved
     * @throws IOException gets thrown if the
     */
    private static void writeToFile(final File file, final Database database) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("CLASS=" + database.getClass().getSimpleName() + "\n" +
                "NAME=" + database.getDbName() + "\n" +
                "HOST=" + database.getDbHost() + "\n" +
                "PORT=" + database.getDbPort() + "\n" +
                "USERNAME=" + database.getDbUsername() + "\n" +
                "PASSWORD=" + database.getDbPassword() + "\n");
        fw.close();
    }

    /**
     * This reads dataBase entries from files and returns a Database array.
     *
     * @return returns a array of database objects
     */
    private static ArrayList<Database> parseFromFile() {
        File[] files = getFiles("/Databases");
        if (files != null) {
            return createDatabasesFromFile(getDBCFiles(files));
        }
        return null;
    }

    /**
     * this creates a array of databases from a arraylist of dbc files.
     *
     * @param dbcFiles the list of dbcFiles
     * @return returns a array of database objects
     */
    private static ArrayList<Database> createDatabasesFromFile(final File[] dbcFiles) {
        ArrayList<Database> databases = new ArrayList<>();
        for (File file : dbcFiles) {
            try {
                String[] fileContent = loadFileContent(file);
                String classType = stringArrayLookup("CLASS", fileContent);

                if (classType != null) {
                    databases.add(createDatabaseFromStrings(fileContent, classType,
                            file.getName().substring(0, file.getName().indexOf("."))));
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return databases;
    }

    /**
     * returns a array of Files with the DBC file extension.
     *
     * @param files the input of unverified files
     * @return a array of dbc files returns null if there are no valid files or input was null
     */
    private static File[] getDBCFiles(final File[] files) {
        if (files != null) {
            ArrayList<File> correctFile = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".dbc")) {
                        correctFile.add(file);
                    }
                }
            }
            return correctFile.toArray(new File[0]);
        }
        return null;
    }

    /**
     * This will create a database from a data supplied from a string array.
     *
     * @param stringArray the supplied string array containing data from a file
     * @param classType   the class type looked up from the string array
     * @param uniqueId    the name of the class
     * @return a database from data supplied by array null if no matching classType
     * @throws ClassNotFoundException gets thrown if the database class is not found
     * @throws SQLException           gets thrown if credentials are incorrect
     */
    public static Database createDatabaseFromStrings(final String[] stringArray, final String classType, final String uniqueId)
            throws ClassNotFoundException, SQLException {
        if (classType.equalsIgnoreCase("DatabaseMYSQL")) {
            return new DatabaseMYSQL(
                    stringArrayLookup("NAME", stringArray),
                    stringArrayLookup("HOST", stringArray),
                    stringArrayLookup("PORT", stringArray),
                    stringArrayLookup("USERNAME", stringArray),
                    stringArrayLookup("PASSWORD", stringArray),
                    uniqueId
            );
        }
        //TODO Add further class types when other database types have been added.
        return null;
    }

    /**
     * This looks up a specified lookup in a array and returns the value after that lookups = sign.
     *
     * @param lookup the lookup value
     * @param array  the lookup array
     * @return returns the desired value if found else null
     */
    private static String stringArrayLookup(final String lookup, final String[] array) {
        for (String index : array) {
            if (index.startsWith(lookup)) {
                index = index.substring(index.indexOf("=") + 1);
                return index;
            }
        }
        return null;
    }

    /**
     * This read a specified file and returns a string array of its content.
     *
     * @param file the specified file
     * @return a string array
     * @throws IOException get thrown if something is invalid with the file
     */
    private static String[] loadFileContent(final File file) throws IOException {
        BufferedReader reader;

        ArrayList<String> strings = new ArrayList<>();
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            strings.add(line);
            // read next line
            line = reader.readLine();
        }
        reader.close();
        return strings.toArray(String[]::new);
    }

    /**
     * This function returns a File array with the files and directories in database folder under documents.
     *
     * @return File array with files and directories.
     */
    private static File[] getFiles() {
        return getFiles("");
    }

    /**
     * This function returns a File array with the files and directories in database folder under documents.
     *
     * @param subDir optional parameter if the files in a subdirectory are wanted
     * @return File array with files and directories
     */
    private static File[] getFiles(final String subDir) {
        String path = getAppFolderDirectory() + subDir;
        if (directoryExists(path, true)) {
            File directory = new File(path);
            return directory.listFiles();
        }
        return null;
    }

    /**
     * This checks if the directory in the specified <code>path</code> exists.
     * If <code>create</code> is set to true and the directory does not exists it will be created.
     *
     * @param path   the specified path for the directory
     * @param create if true and directory is missing it will create a new directory
     * @return true if directory exits else false even if just created
     */
    private static boolean directoryExists(final String path, final boolean create) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        if (create) {
            file.mkdir();
        }
        return false;
    }

    /**
     * @return This gets the my documents folder and the database handlers data subfolder.
     */
    private static String getAppFolderDirectory() {
        return new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/Database Handler";
    }
}
