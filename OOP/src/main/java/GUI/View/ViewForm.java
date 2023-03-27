package GUI.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ViewForm extends JFrame{
    private final JButton viewB1;
    private final JRadioButton viewRB1;
    private final JRadioButton viewRB2;
    private final JTable table;

    private final JButton viewB2;
    private final ButtonGroup viewGroup;

    public ViewForm(){
        setTitle("Skin Consultation Manger");
        setSize(680,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Font
        Font vfl = new Font("Helvetica", Font.PLAIN, 16);

        //JButton - sort
        viewB1 = new JButton("Sort");
        viewB1.setBounds(500,300,128,40);
        viewB1.setBackground(new Color(241, 242, 246));
        viewB1.setFont(new Font("Arial", Font.PLAIN, 15));

        //JButton - refresh
        viewB2 = new JButton("Refresh");
        viewB2.setBackground(new Color(241, 242, 246));
        viewB2.setFont(new Font("Arial", Font.PLAIN, 15));
        viewB2.setBounds(500,36,128,40);

        //JRadio Buttons
        viewRB1 = new JRadioButton ("Name");
        viewRB2 = new JRadioButton ("Surname");

        viewGroup = new ButtonGroup();
        viewGroup.add(viewRB1);
        viewGroup.add(viewRB2);

        viewRB1.setBounds(50, 40, 150, 25);
        viewRB2.setBounds(150, 40, 150, 25);
        viewRB1.setFont(vfl);
        viewRB2.setFont(vfl);

        //JTable
        table = new JTable();
        String[] colNames = {"Name", "Surname", "Date of Birth","Mobile","License","Specialisation"};
        Object[][] data = new Object[][]{};
        TableModel model = new DefaultTableModel(data,colNames);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);

        JPanel tablePanel = new JPanel();
        scrollPane.add(tablePanel);
        scrollPane.setBounds(30,100,620,180);

        //Add Components
        add(scrollPane);
        add(viewB2);
        add(viewRB2);
        add(viewRB1);
        add(viewB1);
        setResizable(false);
        setVisible(true);
    }

    public JButton getViewB1() {
        return viewB1;
    }

    public JRadioButton getViewRB1() {
        return viewRB1;
    }

    public JRadioButton getViewRB2() {
        return viewRB2;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getViewB2() {
        return viewB2;
    }

    public ButtonGroup getViewGroup() {
        return viewGroup;
    }
}

