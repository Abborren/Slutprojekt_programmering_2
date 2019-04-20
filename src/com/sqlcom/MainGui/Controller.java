package com.sqlcom.MainGui;

import com.sqlcom.MainGui.listeners.*;
import com.sqlcom.databases.Database;
import com.sqlcom.utilities.FileUtil;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * This is the main controller for handling the GUI.
 */
public class Controller {
    /**
     * The GUI.
     */
    private Gui gui;

    /**
     * The array list of all the database connections.
     */
    private ArrayList<Database> databaseList;

    /**
     * This is the active database connection.
     */
    private Database activeDBConn;

    /**
     * Constructor for making the controller object.
     *
     * @param gui The gui.
     */
    public Controller(final Gui gui) {
        this.gui = gui;
        createEventListeners();

        databaseList = FileUtil.loadDatabases();
        activeDBConn = databaseList.get(0);

        // Sets JTables column names
        DefaultTableModel model = (DefaultTableModel) gui.getDatabaseTable().getModel();
        model.setColumnIdentifiers(new Object[] {"Type", "Host", "Db Name", "Username", "Password"});

        for (Database db : databaseList) {
            // Adds dbs to comboBox.
            gui.getDbComboBox().addItem(db);
            // Adds dbs to JTable.
            model.addRow(db.toRow());
        }
    }

    /**
     * This creates all the Event listeners for the different components of the gui.
     */
    private void createEventListeners() {

        /* IO Tab */
        gui.getRun().addActionListener(new RunEventListener(this));
        gui.getClearOutputButton().addActionListener(new ClearOutputEventListener(this));

        /* Database Tab */
        gui.getSyncRadioButton().addActionListener(new SyncEventListener(this));
        gui.getStopRadioButton().addActionListener(new StopEventListener(this));

        gui.getNewDb().addActionListener(new NewDBEventListener(this));

        gui.getDbComboBox().addActionListener(new DbComboBoxEventListener(this));

        //TODO create a event listener for the table that updates the Database list if a change is detected on a table row.
    }

    /**
     * @return returns the database arrayList.
     */
    public ArrayList<Database> getDatabaseList() {
        return databaseList;
    }

    /**
     * @return The gui.
     */
    public Gui getGui() {
        return gui;
    }

    /**
     * @return the active database connection.
     */
    public Database getActiveDBConn() {
        return activeDBConn;
    }

    /**
     * @param activeDBConn the new active database connection.
     */
    public void setActiveDBConn(final Database activeDBConn) {
        this.activeDBConn = activeDBConn;
    }
}
