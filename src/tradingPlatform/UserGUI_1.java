package tradingPlatform;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class UserGUI_1 extends JFrame implements ActionListener, Runnable {

    public static final int Width = 900;
    public static final int Height = 600;

    private JTabbedPane tabbedPane;
    private JTable offerTable;
    private JList resourceList;
    private JMenu account;
    private JMenuBar menuBar;
    private JMenuItem changePassword, logout;
    private JTextField resourceName, creditPerResource, resourceAmount;
    private JButton newButton, sellButton, deleteButton, editButton, buyButton;

    TradingPlatformData data;

    public UserGUI_1(String electronic_asset_trading_platform) {

    }

    private void createGUI() {
        JFrame UserFrame = new JFrame("Electronic Asset Trading Platform");
        UserFrame.setSize(Width, Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addTabbedPane();
        addMenuItems();
        UserFrame.add(menuBar());
        UserFrame.add(tabbedPane);

        add(menuBar);
        UserFrame.setJMenuBar(menuBar);

        UserFrame.setVisible(true);
    }

    private void addMenuItems() {
        changePassword = new JMenuItem("Change Password");
        logout = new JMenuItem("Logout");

        changePassword.addActionListener(this);
        logout.addActionListener(this);

    }

    private JMenuBar menuBar() {
        menuBar = new JMenuBar();
        account = new JMenu("Account");
        account.add(changePassword);
        account.add(logout);
        menuBar.add(account);
        return menuBar;
    }

    private void addTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.decode("#8a8a8a"));
        HomePane(tabbedPane);
//        OfferPane(tabbedPane);
    }

    // start of the home page
    private void HomePane(JTabbedPane tabbedPane) {
        Container resource = new Container();
        tabbedPane.addTab("Resource", resource);
        resource.setBackground(Color.decode("#c8ddf2"));
        resource.setLayout(new BoxLayout(resource, BoxLayout.Y_AXIS));

        resource.add(Box.createVerticalStrut(20));
        resource.add(DetailsPanel());
        resource.add(Box.createVerticalStrut(20));
        resource.add(makeButtonsPanel());
        resource.add(Box.createVerticalStrut(20));

    }

    /**
     * Makes a JPanel consisiting of (1) the list of resources and (2) the amount
     * fields in a box layout with horizontal alignment and puts a 20 pixel gap
     * between the components and the left and right edges of the panel.
     *
     * @return the detail panel.
     */
    private JPanel DetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(resourceListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeResourceFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        return detailsPanel;
    }

    /**
     * Makes a JScrollPane that holds a JList for the list of resources in the
     * table.
     *
     * @return the scrolling resource list panel
     */
    private JScrollPane resourceListPane() {
//        resourceList = new JList(data.getModel());
//        resourceList.setFixedCellWidth(200);

        JScrollPane scroller = new JScrollPane(resourceList);
        scroller.setViewportView(resourceList);
        scroller
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));

        return scroller;
    }

    /**
     * make a text file for the resource field
     *
     * @return a panel containing the address fields
     */
    private JPanel makeResourceFieldsPanel() {
        JPanel ResourceFieldsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(ResourceFieldsPanel);
        ResourceFieldsPanel.setLayout(layout);


        layout.setAutoCreateGaps(true);


        layout.setAutoCreateContainerGaps(true);

        JLabel resourceNameLabel = new JLabel("Resource Name");
        JLabel creditPerResourceLabel = new JLabel("Credit per Resource");
        JLabel resourceAmountLabel = new JLabel("Resource Amount");


        resourceName = new JTextField(20);
        creditPerResource = new JTextField(20);
        resourceAmount = new JTextField(20);
//        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();


        hGroup.addGroup(layout.createParallelGroup().addComponent(resourceNameLabel)
                .addComponent(creditPerResourceLabel).addComponent(resourceAmountLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(resourceName)
                .addComponent(creditPerResource).addComponent(resourceAmount));
        layout.setHorizontalGroup(hGroup);


        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();


        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceNameLabel).addComponent(resourceName));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(creditPerResourceLabel).addComponent(creditPerResource));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceAmountLabel).addComponent(resourceAmount));

        layout.setVerticalGroup(vGroup);

        return ResourceFieldsPanel;
    }

    /**
     * Adds the New, Save and Delete buttons to a panel
     */
    private JPanel makeButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        newButton = new JButton("New");
        sellButton = new JButton("SELL");
        sellButton.setEnabled(false);
        deleteButton = new JButton("Delete");
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(newButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(sellButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        return buttonPanel;
    }

//    private void addButtonListeners(ActionListener listener) {
//        newButton.addActionListener(listener);
//        sellButton.addActionListener(listener);
//        deleteButton.addActionListener(listener);
//    }
//
//    private void addResourceListener(ListSelectionListener listener) {
//        resourceList.addListSelectionListener(listener);
//    }
//
//
//    private void addClosingListener(WindowListener listener) {
//        addWindowListener(listener);
//    }
//
//
//    /**
//     * Sets the text in the address text fields to the empty string.
//     */
//    private void clearFields() {
//        resourceName.setText("");
//        creditPerResource.setText("");
//        resourceAmount.setText("");
//
//    }
//
//    private void setFieldsEditable(boolean editable) {
//        resourceName.setEditable(editable);
//        creditPerResource.setEditable(editable);
//        resourceAmount.setEditable(editable);
//
//    }
//
//    private void checkListSize() {
//        deleteButton.setEnabled(data.getUserSize() != 0);
//    }
//
//    private class ButtonListener implements ActionListener {
//
//
//        public void actionPerformed(ActionEvent e) {
//            int size = data.getUserSize();
//
//            JButton source = (JButton) e.getSource();
//            if (source == newButton) {
//                newPressed();
//            } else if (source == sellButton) {
//                sellPressed();
//            } else if (source == deleteButton) {
//                deletePressed();
//            }
//        }
//
//        private void newPressed() {
//            clearFields();
//            setFieldsEditable(true);
//            sellButton.setEnabled(true);
//        }
//
//        private void sellPressed() {
//            if (resourceName.getText() != null && !resourceName.getText().equals("")) {
//                Resource resource = new Resource(resourceName.getText(), creditPerResource.getText(), resourceAmount.getText());
//                data.add(resource);
//            }
//            setFieldsEditable(false);
//            sellButton.setEnabled(false);
//            checkListSize();
//        }
//
//        private void deletePressed() {
//            int index = resourceList.getSelectedIndex();
//            data.remove(resourceList.getSelectedValue());
//            clearFields();
//            index--;
//            if (index == -1) {
//                if (data.getUserSize() != 0) {
//                    index = 0;
//                }
//            }
//            resourceList.setSelectedIndex(index);
//            checkListSize();
//        }
//    }
//
//
//    private class NameListListener implements ListSelectionListener {
//
//
//        public void valueChanged(ListSelectionEvent e) {
////            if (resourceList.getSelectedValue() != null
////                    && !resourceList.getSelectedValue().equals("")) {
////                display(data.get(resourceList.getSelectedValue()));
////            }
//        }
//    }
//
//    private class ClosingListener extends WindowAdapter {
//
//        public void windowClosing(WindowEvent e) {
//            data.persist();
//            System.exit(0);
//        }
//    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        createGUI();
    }
}
