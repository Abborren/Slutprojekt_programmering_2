package com.sqlcom.newDB;

import com.sqlcom.MainGui.Controller;

import javax.swing.*;
import java.util.Random;

/**
 * This is a Gui class that is used to create new Database entries.
 */
public class GUINewDB {

    /**
     * Ok button to confirm.
     */
    private JButton okButton;

    /**
     * Cancel button to close gui.
     */
    private JButton cancelButton;

    /**
     * The database name.
     */
    private JTextField dbName;
    /**
     * The database port (default 3306).
     */
    private JTextField dbPort;
    /**
     * The ComboBox with the different types of databases.
     */
    private JComboBox dbTypeComboBox;

    /**
     * The database host url address.
     */
    private JTextField dbHost;

    /**
     * The database Username.
     */
    private JTextField dbUsername;
    /**
     * The database Password.
     */
    private JPasswordField dbPassword;

    /**
     * The jPanel.
     */
    private javax.swing.JPanel jPanel;

    /**
     * Parent controller.
     */
    private Controller controller;

    /**
     * This creates a ne GUI for creating Databases.
     *
     * @param controller the parent Controller that holds the arraylist of Databases.
     */
    public GUINewDB(final Controller controller) {

        this.controller = controller;

        JFrame frame = new JFrame("Database hantering");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 200);

        frame.setContentPane(jPanel);
        frame.setVisible(true);

        okButton.addActionListener(e -> {

        });
        cancelButton.addActionListener(e -> frame.dispose());
    }

    /**
     * This returns a random string with a set amount of characters.
     *
     * @param targetLength the specified length of the random string
     * @return the random string
     */
    private static String getRandomString(final int targetLength) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetLength);
        for (int i = 0; i < targetLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
