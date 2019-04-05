import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private JTabbedPane tabbedPane1;

    private JButton run;

    private JTextArea textArea1;

    private JButton newDB;

    private JComboBox comboBox1;

    private JRadioButton syncRadioButton;

    private JRadioButton stopRadioButton;

    private JTable table1;

    private JButton clearOutputButton;

    private JTextArea textArea2;

    public Gui() {
        /* IO TAB */
        run.addActionListener(e -> {
            print();
        });
        clearOutputButton.addActionListener(e -> {
            print();
        });

        /* DATABASE TAB */

        //TODO Create button group for the radio buttons
        syncRadioButton.addActionListener(e -> {
            print();
        });
        stopRadioButton.addActionListener(e -> {
            print();
        });


        newDB.addActionListener(e -> {
            print();
        });
    }
    private void print() {
        System.out.println("this works!");
    }
    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }
}
