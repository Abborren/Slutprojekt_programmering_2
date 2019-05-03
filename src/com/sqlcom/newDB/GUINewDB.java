package com.sqlcom.newDB;

import com.sqlcom.MainGui.Controller;
import com.sqlcom.databases.Database;
import com.sqlcom.databases.DatabaseMYSQL;
import com.sqlcom.utilities.FileUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.sql.SQLException;
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

        dbTypeComboBox.addItem(Controller.formatDbClassName(DatabaseMYSQL.class));

        JFrame frame = new JFrame("Database hantering");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 200);

        frame.setContentPane(jPanel);
        frame.setVisible(true);
        okButton.addActionListener(e -> {

            runAction();
            frame.dispose();
        });
        cancelButton.addActionListener(e -> frame.dispose());
    }

    /**
     * @return this casts the input combobox to a database type (full database class name).
     */
    private String getDbType() {
        String s = dbTypeComboBox.getSelectedItem().toString();
        switch (s) {
            case "Mysql":
                return "DatabaseMYSQL";
            case "SQLLITE":
                return "DatabaseMYSQL";
            default:
                return "null";
        }
    }

    /**
     * This is the action that is run on the okay click button.
     * Creates a database from the inputed data.
     */
    private void runAction() {
        String[] array = {
                "TYPE=" + getDbType(),
                "NAME=" + dbName.getText(),
                "HOST=" + dbHost.getText(),
                "PORT=" + dbPort.getText(),
                "USERNAME=" + dbUsername.getText(),
                "PASSWORD=" + dbPassword.getText(),
        };
        try {
            Database database = FileUtil.createDatabaseFromStrings(array, getDbType(), getRandomString(20));
            if (database != null) {
                controller.getGui().getDbComboBox().addItem(database.toString());

                DefaultTableModel model = (DefaultTableModel) controller.getGui().getDatabaseTable().getModel();
                model.addRow(database.toRow());

                controller.getDatabaseList().add(database);
                FileUtil.saveDatabase(controller.getDatabaseList());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
