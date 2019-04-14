package com.sqlcom;

import com.sqlcom.MainGui.Controller;
import com.sqlcom.MainGui.Gui;

import javax.swing.*;

/**
 * This Starts the Gui and sends it to the controller.
 */
public final class Main {

    /**
     * Ignored.
     */
    private Main() {
    }

    /**
     * The start method that creates the gui and passes it to the controller.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {
        Gui gui = new Gui();
        new Controller(gui);
        changeGuiLook();
    }

    /**
     * This changes the look of the gui to look more like a windows 10 window.
     */
    private static void changeGuiLook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
