package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;
import com.sqlcom.newDB.GUINewDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a actionListener for the new db button to open a new gui.
 */
public class NewDBEventListener implements ActionListener {

    /**
     * The parent controller.
     */
    private Controller controller;

    /**
     * Constructor for creating this event listener.
     *
     * @param controller the parent controller.
     */
    public NewDBEventListener(final Controller controller) {
        this.controller = controller;
    }

    /**
     * The action performed listener.
     *
     * @param e the action
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        new GUINewDB(controller);
    }

}
