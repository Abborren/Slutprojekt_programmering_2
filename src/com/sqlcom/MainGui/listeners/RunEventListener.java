package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a actionListener for the sql run button.
 */
public class RunEventListener implements ActionListener {

    /**
     * The parent controller.
     */
    private Controller controller;

    /**
     * Constructor for creating this event listener.
     *
     * @param controller the parent controller.
     */
    public RunEventListener(final Controller controller) {
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

    /**
     * This will collect the sql command from the input text area and process it.
     */
    private void runSql() {
        throw new UnsupportedOperationException();
    }

    /**
     * This will print out the results in the output text area.
     */
    private void printResult() {
        throw new UnsupportedOperationException();
    }

}
