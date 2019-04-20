package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a actionListener for the ComboBox that holds the active db connection.
 * TODO maybe repurpose this event listener for the table otherwise delete since its not needed / used.
 */
public class DbComboBoxEventListener implements ActionListener {

    /**
     * The parent controller.
     */
    private Controller controller;

    /**
     * Constructor for creating this event listener.
     *
     * @param controller the parent controller.
     */
    public DbComboBoxEventListener(final Controller controller) {
        this.controller = controller;
    }

    /**
     * The action performed listener.
     *
     * @param e the action
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        //throw new UnsupportedOperationException();
    }

}
