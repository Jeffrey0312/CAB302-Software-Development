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
        JPanel panel1 = new JPanel();

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
        JButton b=new JButton("Computational Resource");
        panel1.add(b);
        JButton c=new JButton("Hardware Resource");
        panel1.add(c);
        JButton d=new JButton("Software Resource");
//        b.setBounds(20,40,95,95);
//        buttonPanel.add(d);
        panel1.add(d);


//        c= getContentPane();
//        card=new CardLayout(40,30);
////create CardLayout object with 40 hor space and 30 ver space
//        c.setLayout(card);
//
//        b1=new JButton("Apple");
//        b2=new JButton("Boy");
//        b3=new JButton("Cat");
//
//        c.add(b1);
//        c.add(b2);
//        c.add(b3);
//
//        panel1.add(c);



    //table
        JPanel tablePanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(tablePanel, BoxLayout.Y_AXIS);
        tablePanel.setLayout(boxLayout);

        String data[][] = {
                {"101", "Amit", "$670000","BUY"},
                {"102", "Jai", "$780000", "BUY"},
                {"101", "Sachin", "$700000", "BUY"}};
        String column[] = {"NAME", "ORGANIZATION", "PRICE","Button"};
        JTable jt = new JTable(data, column);
        jt.getColumn("Button");

        JScrollPane scroll = new JScrollPane(jt);
        jt.setBounds(30, 40, 700, 400);
        JScrollPane sp = new JScrollPane(jt);
        tablePanel.add(sp);
        panel1.add(tablePanel);



        //start of panel 2
        JPanel panel2 = new JPanel();

        //start of panel 3
        JPanel panel3 = new JPanel();


        pane.add("RESOURCE", panel1);
        pane.add("OFFERS", panel2);
        pane.add("SELL", panel3);


        frame.getContentPane().add(BorderLayout.CENTER, pane);

        frame.setVisible(true);
    }


}
