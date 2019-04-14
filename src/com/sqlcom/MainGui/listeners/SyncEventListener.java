package com.sqlcom.MainGui.listeners;


import com.sqlcom.MainGui.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * TODO disable comboBox if a connection is active.
     *
     * @param e the action
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        throw new UnsupportedOperationException();
    }
}
