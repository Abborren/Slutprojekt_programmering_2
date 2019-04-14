package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a actionListener for the clear output button.
 */
public class ClearOutputEventListener implements ActionListener {

    /**
     * The parent controller.
     */
    private Controller controller;

    /**
     * Constructor for creating this event listener.
     *
     * @param controller the parent controller.
     */
    public ClearOutputEventListener(final Controller controller) {
        this.controller = controller;
    }

    /**
     * The action performed listener.
     *
     * @param e the action
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        throw new UnsupportedOperationException();
    }

}
