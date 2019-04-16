package com.sqlcom.MainGui.listeners;

import com.sqlcom.MainGui.Controller;
import com.sqlcom.databases.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
        String input = controller.getGui().getInputArea().getText();
        if (input.length() != 0) {
            runSql(input);
        }
    }

    /**
     * Executes a given SQL statement.
     *
     * @param sql an SQL statement to be sent to the database, typically a
     *            static SQL <code>SELECT</code> statement
     */
    private void runSql(final String sql) {
        String[] stmts = sql.replaceAll("\n", "").split(";");
        for (String stmt : stmts) {

            String stmtKind = stmt.substring(0, stmt.indexOf(" ")).toLowerCase();
            Database database = controller.getActiveDBConn();
            if (stmtKind.startsWith("select") || stmtKind.startsWith("show")) {
                try {
                    ResultSet result = database.execute(stmt);
                    if (result != null) {
                        printResult(result);
                    }
                } catch (SQLException e) {
                    appendOutputTextPane("<p>" + e.getMessage() + "</p>");
                }
            } else {
                try {
                    int status = database.executeUpdate(stmt);
                    appendOutputTextPane("<p>Förfrågan slutfördes som förväntat. " + status + " rad(er) påverkade.</p>");
                } catch (SQLException e) {
                    appendOutputTextPane("<p>" + e.getMessage() + "</p>");

                }
            }
        }
    }

    /**
     * This will print out the results in the output text area.
     *
     * @param result this is the query result from the database.
     * @throws SQLException gets thrown if something with the resultset goes wrong.
     */
    private void printResult(final ResultSet result) throws SQLException {
        StringBuilder s = new StringBuilder("<table><tr>\n");
        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        for (int i = 1; i < columnCount + 1; i++) {
            s.append("<th>").append(metadata.getColumnLabel(i)).append("</th>\n");
        }
        s.append("</tr>\n");
        while (result.next()) {
            metadata = result.getMetaData();
            columnCount = metadata.getColumnCount();
            s.append("<tr>\n");
            for (int i = 1; i < columnCount + 1; i++) {
                s.append("<td>").append(result.getString(metadata.getColumnLabel(i))).append("</td\n");
            }
            s.append("</tr>\n");
        }
        s.append("</table>");
        appendOutputTextPane(s.toString());
    }

    /**
     * This appends html content to the output pane.
     *
     * @param text the text to append.
     */
    private void appendOutputTextPane(final String text) {
        JTextPane jTextPane = controller.getGui().getOutputTextPane();
        jTextPane.setContentType("text/html");

        String content = jTextPane.getText();
        content = content.substring(content.indexOf("<body>") + 6, content.indexOf("</body>"));
        jTextPane.setText("<html><head></head><body>" + content + text + "</body></html>");
    }
}
