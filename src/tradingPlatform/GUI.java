package tradingPlatform;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellRenderer;


public class GUI extends JFrame {


    {
        JFrame frame = new JFrame("Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        //tabs
        JTabbedPane pane = new JTabbedPane();
        pane.setBackground(Color.BLUE);
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel("Type here");
        JTextField tf = new JTextField(65);
        JButton search = new JButton("Search");

        panel1.add(label);
        panel1.add(tf);
        panel1.add(search);

//        //button group
//        JPanel buttonPanel = new JPanel();
//        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
//        buttonPanel.setLayout(boxLayout);

//        buttonPanel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        JButton b = new JButton("Computational Resource");
        panel1.add(b);
        JButton c = new JButton("Hardware Resource");
        panel1.add(c);
        JButton d = new JButton("Software Resource");
        panel1.add(d);


        //table
        JPanel tablePanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(tablePanel, BoxLayout.Y_AXIS);
        tablePanel.setLayout(boxLayout);

        String data[][] = {
                {"101", "Amit123", "Amit", "Jack", "Sales", "$670000", "BUY"},
                {"102", "Jai007", "Jai", "Spring", "Human Resource", "$780000", "BUY"},
                {"101", "Sachin334", "Sachin", "Eystein", "Design", "$700000", "BUY"}};

        String column[] = {"ID", "USERNAME", "FIRST NAME", "LAST NAME", "ORGANIZATION", "PRICE", "Button"};
        JTable jt = new JTable(data, column);
        jt.getColumn("Button");

//      JScrollPane scroll = new JScrollPane(jt);
        jt.setSize(600, 700);
        JScrollPane sp = new JScrollPane(jt);
        tablePanel.add(sp);
        panel1.add(tablePanel);


        //start of panel 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        JTextField L8 = new JTextField(65);
        JButton sellButton = new JButton("Sell");
        JButton filterButton = new JButton("Filter");
        panel2.add(L8);
        panel2.add(sellButton);
        panel2.add(filterButton);

        //add table
        JPanel tablePanel_2 = new JPanel();
        BoxLayout boxLayout_3 = new BoxLayout(tablePanel_2, BoxLayout.Y_AXIS);
        tablePanel_2.setLayout(boxLayout_3);
        String column_1[] = {"Resources", "Organization", "Total", "Available", "Price", "Date", "Button","Button"};
        String data_1[][] = {
                {"R1", "Happy Planet", "10,000", "8,000", "$3,000", "27/04/2021", "Edit", "Remove"}};
        JTable jt_1 = new JTable(data_1, column_1);
        jt_1.getColumn("Button");

        JScrollPane sp_1 = new JScrollPane(jt_1);
        tablePanel_2.add(sp_1);
        panel2.add(tablePanel_2);


        //start of panel 3
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);

//        JPanel contentPanel = new JPanel();

        BoxLayout boxLayout_1 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxLayout_1);
        panel3.setSize(700,600);

        //add title
        JLabel L1 = new JLabel("Resource Title");
        JLabel L2 = new JLabel("(Description)");
        panel3.add(L1);
        panel3.add(L2);


        //add dropdown
        JLabel L3 = new JLabel("Available Resource");
        String[] sourceStrings = {"Hard Disk", "Software License", "RAM", "Computational Resource", "Others"};

        JComboBox sourceList = new JComboBox(sourceStrings);
        sourceList.setSelectedIndex(4);
//      sourceList.addActionListener((ActionListener) this);
        panel3.add(L3);
        panel3.add(sourceList);

//        String[] labels = {"Resource Type: ", "Sales Amount: ", "Credits per Resource: ", "Estimate Credit Income: "};
//        int numPairs = labels.length;
//
//        //Create and populate the panel.
//        JPanel p = new JPanel(new SpringLayout());
//        for (int i = 0; i < numPairs; i++) {
//            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//            p.add(l);
//            JTextField textField = new JTextField(10);
//            l.setLabelFor(textField);
//            p.add(textField);
//        }
//        contentPanel.add(p);
//        //Lay out the panel.
//        SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);


        //add text area
        JLabel L4 = new JLabel("Resource Type:");
        JTextField L4_1 = new JTextField(40);
        panel3.add(L4);
        panel3.add(L4_1);

        JLabel L5 = new JLabel("Sales Amount:");
        JTextField L5_1 = new JTextField(40);
        panel3.add(L5);
        panel3.add(L5_1);

        JLabel L6 = new JLabel("Credits per Resource:");
        JTextField L6_1 = new JTextField(40);
        panel3.add(L6);
        panel3.add(L6_1);

        JLabel L7 = new JLabel("Estimate Credit Income:");
        JTextField L7_1 = new JTextField(40);
        panel3.add(L7);
        panel3.add(L7_1);

//        panel3.add(contentPanel);


        //add pane
        pane.add("RESOURCE", panel1);
        pane.add("OFFERS", panel2);
        pane.add("SELL", panel3);
        frame.getContentPane().add(BorderLayout.CENTER, pane);
        frame.setVisible(true);
    }


}
