package com.sqlcom.MainGui.listeners;


import com.sqlcom.MainGui.Controller;
import com.sqlcom.MainGui.Gui;
import com.sqlcom.databases.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * This is a actionListener for the sync button to open a active database connection.
 */
public class SyncEventListener implements ActionListener {
    /**
     * The parent controller.
     */
    private Controller controller;

    /**
     * Constructor for creating this event listener.
     *
     * @param controller the parent controller.
     */
    public SyncEventListener(final Controller controller) {
        this.controller = controller;
    }

    /**
     * The action performed listener.
     *
     * @param e the action
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        Gui gui = controller.getGui();
        gui.getRun().setEnabled(true);
        controller.setActiveDBConn((Database) gui.getDbComboBox().getSelectedItem());
        try {
            controller.getActiveDBConn().updateDbConnection();
        } catch (SQLException | ClassNotFoundException e1) {
            controller.appendOutputTextPane(e1.getMessage());
        }
        gui.getDbComboBox().setEnabled(false);
    }
}
