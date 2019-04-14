package com.sqlcom.MainGui;

import com.sqlcom.FileUtil;
import com.sqlcom.MainGui.listeners.*;
import com.sqlcom.databases.Database;

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
     * Constructor for making the controller object.
     *
     * @param gui The gui.
     */
    public Controller(final Gui gui) {
        this.gui = gui;
        createEventListeners();
        new SyncEventListener(this);
        databaseList = FileUtil.loadDatabases();
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

        gui.getNewDB().addActionListener(new NewDBEventListener(this));

        gui.getdBComboBox().addActionListener(new DbComboBoxEventListener(this));
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
}
