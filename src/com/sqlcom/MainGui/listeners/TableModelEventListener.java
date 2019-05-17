package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;
import com.sqlcom.databases.Database;
import com.sqlcom.utilities.FileUtil;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableModelEventListener implements TableModelListener {

    private Controller controller;

    public TableModelEventListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * This triggers editing of the database lists.
     * @param e the model event.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        //type 0 seems to be edited type.
        if (e.getType() == 0) {
            int column = e.getColumn();
            int row = e.getFirstRow();
            //gets the vector containing the table row data.
            Vector rowData = (Vector) ((DefaultTableModel) e.getSource()).getDataVector().get(row);
            //Gets the old database entry that is going to be edited.
            Database oldDatabase = getDatabase((String) rowData.get(5), true);
            //the data of the edited column.
            String columnData = (String) rowData.get(column);
            //updates the column then re-adds the updated database entry to the array list.
            controller.getDatabaseList().add(setColumn(oldDatabase, column, columnData));
            //Saves the old selected items unique id.
            String oldSelectedUId = ((Database) controller.getGui().getDbComboBox().getSelectedItem()).getUniqueId();
            //Removes all old database entries from combobox.
            controller.getGui().getDbComboBox().removeAllItems();
            //Re-adds all the databases to the combobox.
            for (Database db : controller.getDatabaseList()) {
                controller.getGui().getDbComboBox().addItem(db);
            }

            //Sets the old database that was selected to current selected.
            controller.getGui().getDbComboBox().setSelectedItem(getDatabase(oldSelectedUId, false));
            //sets stop button active to refresh the active connection if active was edited.
            controller.getGui().getDbComboBox().setEnabled(true);
            if (!controller.getGui().getStopRadioButton().isSelected()) {
                controller.getGui().getStopRadioButton().setSelected(true);
            }
            FileUtil.saveDatabase(controller.getDatabaseList());
        }
    }

    /**
     * This Finds a database entry from the controllers database list by unique id.
     * @param uniqueId The lookup unique id.
     * @param deleteOld If true it will delete the old entry from the controllers array list.
     * @return A database entry with the unique id equals to the unique id supplied. null if no match is found.
     */
    private Database getDatabase(String uniqueId, boolean deleteOld) {
        for (Database database : controller.getDatabaseList()) {
            if (database.getUniqueId().equals(uniqueId)) {
                if (deleteOld) {
                    controller.getDatabaseList().remove(database);
                }
                return database;
            }
        }
        return null;
    }

    /**
     * This will change the databases data by column value corresponding to the index of the database table.
     *
     * @param database The specified database.
     * @param column   The index column.
     * @param value    The new value to be edited
     * @return Returns the edited database.
     */
    private Database setColumn(Database database, int column, String value) {
        switch (column) {
            case 0:
                //TODO Allow changing of database type.
                break;
            case 1:
                database.setDbHost(value);
                break;
            case 2:
                database.setDbName(value);
                break;
            case 3:
                database.setDbUsername(value);
                break;
            case 4:
                database.setDbPassword(value);
            default:
                break;
        }
        return database;
    }
}
