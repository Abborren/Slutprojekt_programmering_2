package com.sqlcom.MainGui;

import com.sqlcom.utilities.TextLineNumber;

import javax.swing.*;

/**
 * This is the object for the main GUI in the program.
 */
public class Gui {

    /**
     * The initial tabbed pane that has two tabs, IO and Databases (DB).
     */
    private JTabbedPane tabbedPane;
    /**
     * The IO Run button.
     */
    private JButton run;

    /**
     * The IO input area.
     */
    private JTextArea inputArea;

    /**
     * The DB button to create a new database.
     */
    private JButton newDb;

    /**
     * The DB ComboBox to select the active database connection.
     */
    private JComboBox dBComboBox;

    /**
     * The DB radio button to open a database connection.
     */
    private JRadioButton syncRadioButton;

    /**
     * The DB radio button to close a active database connection.
     */
    private JRadioButton stopRadioButton;

    /**
     * The table of available Databases.
     */
    private JTable databaseTable;
    /**
     * The IO button to clear the output text area.
     */
    private JButton clearOutputButton;

    /**
     * The output text pane.
     */
    private JTextPane outputTextPane;

    /**
     * The input scroll pane.
     */
    private JScrollPane inputScrollPane;

    /**
     * Constructor for making the Gui object.
     */
    public Gui() {
        JFrame frame = new JFrame("Database hantering");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 550);

        frame.setContentPane(tabbedPane);
        frame.setVisible(true);

        //creates as line number for each row written.
        inputScrollPane.setRowHeaderView(new TextLineNumber(inputArea));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(syncRadioButton);
        buttonGroup.add(stopRadioButton);
    }

    /* Getters */

    /**
     * @return The input text area component.
     */
    public JTextArea getInputArea() {
        return inputArea;
    }

    /**
     * @return The newDb Button component.
     */
    public JButton getNewDb() {
        return newDb;
    }

    /**
     * @return The dbComboBox component.
     */
    public JComboBox getDbComboBox() {
        return dBComboBox;
    }

    /**
     * @return The sync radio button component.
     */
    public JRadioButton getSyncRadioButton() {
        return syncRadioButton;
    }

    /**
     * @return The stop radio button component.
     */
    public JRadioButton getStopRadioButton() {
        return stopRadioButton;
    }

    /**
     * @return The Database Table JTable component.
     */
    public JTable getDatabaseTable() {
        return databaseTable;
    }

    /**
     * @return The clear output button component.
     */
    public JButton getClearOutputButton() {
        return clearOutputButton;
    }

    /**
     * @return The output JText area component.
     */
    public JTextPane getOutputTextPane() {
        return outputTextPane;
    }

    /**
     * @return The run button component.
     */
    public JButton getRun() {
        return run;
    }
}
