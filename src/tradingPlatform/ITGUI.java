package tradingPlatform;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ITGUI extends JFrame implements ActionListener, Runnable {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private JTabbedPane tabbedPane;
    private JTextField asset, organisation, userName, firstName, lastName, credits, userPassword;
    private JList assetList, organisationList;//, userList;
    private JTable userList;
    private JMenuBar menuBar;
    private JMenu options;
    private JMenuItem changePassword, close, logout;
    private JButton userNewButton, userEditButton, userSaveButton, userDeleteButton;
    private DefaultTableModel model;

    TradingPlatformData data;

    public ITGUI(String title) throws HeadlessException {
        super(String.valueOf(title));
    }

    private void createGUI() {
        JFrame ITFrame = new JFrame("Electronic Asset Trading Platform");
        ITFrame.setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ITFrame.setLayout(new BorderLayout());
        ITFrame.setResizable(false);
        //Panel related code will go here
        //ITFrame.getContentPane().setBackground(Color.decode("#0b2862"));

        addTabbedPane();
        addMenuItems();
        ITFrame.add(addMenu());
        ITFrame.add(tabbedPane);

        add(menuBar);
        ITFrame.setJMenuBar(menuBar);
        ITFrame.setVisible(true);
    }

    @Override
    public void run() {
        createGUI();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */

    public void actionPerformed(ActionEvent e) {


    }

/*    /**
     * Checks size of data/model and enables/disables the delete button

    private void checkListSize() {
        userDeleteButton.setEnabled(data.getUserSize() != 0);
    }*/

    private void addMenuItems() {
        changePassword = new JMenuItem("Change Password");
        logout = new JMenuItem("Logout");
        close = new JMenuItem("Close");
        changePassword.addActionListener(this);
        logout.addActionListener(this);
        close.addActionListener(this);
    }

    private JMenuBar addMenu() {
        menuBar = new JMenuBar();
        options = new JMenu("Options");
        options.add(changePassword);
        options.add(logout);
        options.add(close);
        menuBar.add(options);
        return menuBar;
    }

    /**
     * USER SECTION
     */
    private void userPaneUI(JTabbedPane tabbedPane) {
        Container userPane = new Container();
        tabbedPane.addTab("User", userPane);
        userPane.setBackground(Color.decode("#0b2862"));
        userPane.setLayout(new BoxLayout(userPane, BoxLayout.X_AXIS));
        userPane.add(Box.createHorizontalStrut(15));
        userPane.add(makeUserListPane());
        userPane.add(Box.createHorizontalStrut(15));
        userPane.add(makeUserAddPanel());
        userPane.add(Box.createHorizontalStrut(15));
    }

    private JPanel makeUserAddPanel() {
        JPanel userAddPanel = new JPanel();
        userAddPanel.setBackground(Color.decode("#0b2862"));
        userAddPanel.setLayout(new BoxLayout(userAddPanel, BoxLayout.Y_AXIS));
        userAddPanel.add(Box.createVerticalStrut(10));
        userAddPanel.add(makeUserFieldPanel());
        userAddPanel.add(makeUserButtonPanel());
        userAddPanel.add(Box.createVerticalStrut(100));
        userAddPanel.setMaximumSize(new Dimension(500, 300));
        return userAddPanel;
    }

    private JPanel makeUserFieldPanel() {
        JPanel userFieldPanel = new JPanel();
        GroupLayout layout = new GroupLayout(userFieldPanel);
        userFieldPanel.setLayout(layout);

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        String[] testList = {" ", "Telstra", "Optus", "Belong"};

        JLabel userNameFieldLabel = new JLabel("Username: ");
        JLabel firstNameFieldLabel = new JLabel("First Name: ");
        JLabel lastNameFieldLabel = new JLabel("Last Name: ");
        JLabel userPasswordLabel = new JLabel("Password: ");
        JLabel userITLabel = new JLabel("IT user:");
        JLabel userOrganisationLabel = new JLabel("Organisation: ");
        userNameFieldLabel.setForeground(Color.white);
        firstNameFieldLabel.setForeground(Color.white);
        lastNameFieldLabel.setForeground(Color.white);
        userPasswordLabel.setForeground(Color.white);
        userITLabel.setForeground(Color.white);
        userOrganisationLabel.setForeground(Color.white);
        userName = new JTextField(20);
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        userPassword = new JPasswordField(20);
        JCheckBox userITCheck = new JCheckBox();
        userITCheck.setBackground(Color.decode("#0b2862"));
        JComboBox userOrganisationList = new JComboBox(testList);

        GroupLayout.SequentialGroup hUserGroup = layout.createSequentialGroup();
        hUserGroup.addGroup(layout.createParallelGroup()
                .addComponent(userNameFieldLabel)
                .addComponent(firstNameFieldLabel)
                .addComponent(lastNameFieldLabel)
                .addComponent(userPasswordLabel)
                .addComponent(userITLabel)
                .addComponent(userOrganisationLabel));
        hUserGroup.addGroup(layout.createParallelGroup()
                .addComponent(userName)
                .addComponent(firstName)
                .addComponent(lastName)
                .addComponent(userPassword)
                .addComponent(userITCheck)
                .addComponent(userOrganisationList));
        layout.setHorizontalGroup(hUserGroup);

        GroupLayout.SequentialGroup vUserGroup = layout.createSequentialGroup();
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(userNameFieldLabel)
                .addComponent(userName));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(firstNameFieldLabel)
                .addComponent(firstName));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lastNameFieldLabel)
                .addComponent(lastName));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(userPasswordLabel)
                .addComponent(userPassword));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(userITLabel)
                .addComponent(userITCheck));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(userOrganisationLabel)
                .addComponent(userOrganisationList));
        layout.setVerticalGroup(vUserGroup);

        userFieldPanel.setMaximumSize(new Dimension(350, 75));
        userFieldPanel.setBackground(Color.decode("#0b2862"));
        return userFieldPanel;
    }

    private JPanel makeUserButtonPanel() {
        JPanel userButtonPanel = new JPanel();
        userButtonPanel.setLayout(new BoxLayout(userButtonPanel, BoxLayout.X_AXIS));
        userButtonPanel.setBackground(Color.decode("#0b2862"));
        userNewButton = new JButton("New");
        userEditButton = new JButton("Edit");
        userSaveButton = new JButton("Save");
        userSaveButton.setEnabled(false);
        userDeleteButton = new JButton("Delete");
        userButtonPanel.add(Box.createHorizontalStrut(5));
        userButtonPanel.add(userNewButton);
        userButtonPanel.add(Box.createHorizontalStrut(15));
        userButtonPanel.add(userEditButton);
        userButtonPanel.add(Box.createHorizontalStrut(15));
        userButtonPanel.add(userSaveButton);
        userButtonPanel.add(Box.createHorizontalStrut(15));
        userButtonPanel.add(userDeleteButton);
        userButtonPanel.add(Box.createHorizontalStrut(15));
        userButtonPanel.setMinimumSize(new Dimension(300, 50));
        userButtonPanel.setPreferredSize(new Dimension(300, 50));
        userButtonPanel.setMaximumSize(new Dimension(300, 50));
        return userButtonPanel;
    }

    private JScrollPane makeUserListPane() {
        String userData[][] = {
                {"101", "AB123", "Amit", "Baheer", "Optus"},
                {"102", "Jai25", "Jai", "Briggs", "Telstra"},
                {"101", "SFoster91", "Sachin", "Foster", "Belong"}};
        String userColumn[] = {"ID", "Username", "First Name", "Last Name", "Organisation"};
        model = new DefaultTableModel(userData, userColumn);
        userList = new JTable(model);
        //userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane userScroller = new JScrollPane(userList);
        userScroller.setViewportView(userList);
        userScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userScroller.setMinimumSize(new Dimension(400, 150));
        userScroller.setPreferredSize(new Dimension(450, 150));
        userScroller.setMaximumSize(new Dimension(500, 200));
        return userScroller;
    }

    /**
     * ORGANISATION SECTION
     */
    private void organisationPaneUI(JTabbedPane tabbedPane) {
        Container organisationPane = new Container();
        tabbedPane.addTab("Organisation", organisationPane);
        organisationPane.setBackground(Color.decode("#0b2862"));
        organisationPane.setLayout(new BoxLayout(organisationPane, BoxLayout.X_AXIS));
        organisationPane.add(Box.createHorizontalStrut(100));
        organisationPane.add(makeOrganisationListPane());
        organisationPane.add(Box.createHorizontalStrut(15));
        organisationPane.add(makeOrganisationAddPanel());
        organisationPane.add(Box.createHorizontalStrut(15));
    }

    private JPanel makeOrganisationAddPanel() {
        JPanel organisationAddPanel = new JPanel();
        organisationAddPanel.setBackground(Color.decode("#0b2862"));
        organisationAddPanel.setLayout(new BoxLayout(organisationAddPanel, BoxLayout.Y_AXIS));
        organisationAddPanel.add(Box.createVerticalStrut(50));
        organisationAddPanel.add(makeOrganisationFieldPanel());
        organisationAddPanel.add(makeOrganisationButtonPanel());
        organisationAddPanel.setMaximumSize(new Dimension(350, 300));
        return organisationAddPanel;
    }

    private JPanel makeOrganisationFieldPanel() {
        JPanel organisationFieldPanel = new JPanel();
        GroupLayout layout = new GroupLayout(organisationFieldPanel);
        organisationFieldPanel.setLayout(layout);

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        JLabel organisationFieldLabel = new JLabel("Organisation: ");
        JLabel organisationCreditsFieldLabel = new JLabel("Credits: ");
        organisationFieldLabel.setForeground(Color.white);
        organisationCreditsFieldLabel.setForeground(Color.white);
        organisation = new JTextField(20);
        credits = new JTextField(20);

        GroupLayout.SequentialGroup hOrgGroup = layout.createSequentialGroup();
        hOrgGroup.addGroup(layout.createParallelGroup()
                .addComponent(organisationFieldLabel)
                .addComponent(organisationCreditsFieldLabel));
        hOrgGroup.addGroup(layout.createParallelGroup()
                .addComponent(organisation)
                .addComponent(credits));
        layout.setHorizontalGroup(hOrgGroup);

        GroupLayout.SequentialGroup vOrgGroup = layout.createSequentialGroup();
        vOrgGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(organisationFieldLabel)
                .addComponent(organisation));
        vOrgGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(organisationCreditsFieldLabel)
                .addComponent(credits));
        layout.setVerticalGroup(vOrgGroup);


        organisationFieldPanel.setMaximumSize(new Dimension(350, 75));
        organisationFieldPanel.setBackground(Color.decode("#0b2862"));
        return organisationFieldPanel;
    }

    private JPanel makeOrganisationButtonPanel() {
        JPanel organisationButtonPanel = new JPanel();
        organisationButtonPanel.setLayout(new BoxLayout(organisationButtonPanel, BoxLayout.X_AXIS));
        organisationButtonPanel.setBackground(Color.decode("#0b2862"));
        JButton organisationNewButton = new JButton("New");
        JButton organisationEditButton = new JButton("Edit");
        JButton organisationSaveButton = new JButton("Save");
        organisationSaveButton.setEnabled(false);
        JButton organisationDeleteButton = new JButton("Delete");
        organisationButtonPanel.add(Box.createHorizontalStrut(5));
        organisationButtonPanel.add(organisationNewButton);
        organisationButtonPanel.add(Box.createHorizontalStrut(15));
        organisationButtonPanel.add(organisationEditButton);
        organisationButtonPanel.add(Box.createHorizontalStrut(15));
        organisationButtonPanel.add(organisationSaveButton);
        organisationButtonPanel.add(Box.createHorizontalStrut(15));
        organisationButtonPanel.add(organisationDeleteButton);
        organisationButtonPanel.add(Box.createHorizontalStrut(15));
        organisationButtonPanel.setMinimumSize(new Dimension(300, 50));
        organisationButtonPanel.setPreferredSize(new Dimension(300, 50));
        organisationButtonPanel.setMaximumSize(new Dimension(300, 50));
        return organisationButtonPanel;
    }

    private JScrollPane makeOrganisationListPane() {
        String[] organisationDataList = {"Telstra", "Optus", "Belong"};
        organisationList = new JList(organisationDataList);
        organisationList.setFixedCellWidth(200);
        JScrollPane organisationScroller = new JScrollPane(organisationList);
        organisationScroller.setViewportView(organisationList);
        organisationScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        organisationScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        organisationScroller.setMinimumSize(new Dimension(200, 150));
        organisationScroller.setPreferredSize(new Dimension(250, 150));
        organisationScroller.setMaximumSize(new Dimension(250, 200));
        return organisationScroller;
    }

    /**
     * ASSET SECTION
     */
    private void assetPaneUI(JTabbedPane tabbedPane) {
        Container assetPane = new Container();
        assetPane.setBackground(Color.decode("#0b2862"));
        assetPane.setLayout(new BoxLayout(assetPane, BoxLayout.X_AXIS));
        assetPane.add(Box.createHorizontalStrut(15));
        assetPane.add(makeAssetListPane());
        assetPane.add(Box.createHorizontalStrut(15));
        assetPane.add(makeAssetAddPanel());
        assetPane.add(Box.createHorizontalStrut(15));
        tabbedPane.addTab("Asset", assetPane);
    }

    private JPanel makeAssetAddPanel() {
        JPanel assetAddPanel = new JPanel();
        assetAddPanel.setBackground(Color.decode("#0b2862"));
        assetAddPanel.setLayout(new BoxLayout(assetAddPanel, BoxLayout.Y_AXIS));
        assetAddPanel.add(Box.createVerticalStrut(100));
        assetAddPanel.add(makeAssetFieldPanel());
        assetAddPanel.add(makeAssetButtonPanel());
        assetAddPanel.add(Box.createVerticalStrut(100));
        assetAddPanel.setMaximumSize(new Dimension(500, 300));
        return assetAddPanel;
    }

    private JPanel makeAssetFieldPanel() {
        JPanel assetFieldPanel = new JPanel();
        GroupLayout layout = new GroupLayout(assetFieldPanel);
        JLabel assetFieldLabel = new JLabel("Asset: ");
        assetFieldLabel.setForeground(Color.white);
        asset = new JTextField(20);
        setFieldsEditable(false);
        GroupLayout.SequentialGroup hAssetGroup = layout.createSequentialGroup();
        hAssetGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(assetFieldLabel)
                .addComponent(asset));
        assetFieldPanel.setMaximumSize(new Dimension(300, 75));
        assetFieldPanel.setBackground(Color.decode("#0b2862"));
        return assetFieldPanel;
    }

    private JPanel makeAssetButtonPanel() {
        JPanel assetButtonPanel = new JPanel();
        assetButtonPanel.setLayout(new BoxLayout(assetButtonPanel, BoxLayout.X_AXIS));
        assetButtonPanel.setBackground(Color.decode("#0b2862"));
        JButton assetNewButton = new JButton("New");
        JButton assetEditButton = new JButton("Edit");
        JButton assetSaveButton = new JButton("Save");
        assetSaveButton.setEnabled(false);
        JButton assetDeleteButton = new JButton("Delete");
        assetButtonPanel.add(Box.createHorizontalStrut(5));
        assetButtonPanel.add(assetNewButton);
        assetButtonPanel.add(Box.createHorizontalStrut(15));
        assetButtonPanel.add(assetEditButton);
        assetButtonPanel.add(Box.createHorizontalStrut(15));
        assetButtonPanel.add(assetSaveButton);
        assetButtonPanel.add(Box.createHorizontalStrut(15));
        assetButtonPanel.add(assetDeleteButton);
        assetButtonPanel.add(Box.createHorizontalStrut(15));
        assetButtonPanel.setMinimumSize(new Dimension(300, 50));
        assetButtonPanel.setPreferredSize(new Dimension(300, 50));
        assetButtonPanel.setMaximumSize(new Dimension(300, 50));
        return assetButtonPanel;
    }

    private JScrollPane makeAssetListPane() {
        String[] assetDataList = {"CPU Hours", "Widgets", "AutoCAD Licence"};
        assetList = new JList(assetDataList);
        assetList.setFixedCellWidth(200);
        JScrollPane assetScroller = new JScrollPane(assetList);
        assetScroller.setViewportView(assetList);
        assetScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        assetScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        assetScroller.setMinimumSize(new Dimension(200, 150));
        assetScroller.setPreferredSize(new Dimension(250, 150));
        assetScroller.setMaximumSize(new Dimension(250, 200));
        return assetScroller;
    }

    private void setFieldsEditable(boolean editable) {
        asset.setEditable(editable);
        organisation.setEditable(editable);
        credits.setEditable(editable);
        userName.setEditable(editable);
        firstName.setEditable(editable);
        lastName.setEditable(editable);
        userPassword.setEditable(editable);
    }

    private void addTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.decode("#8a8a8a"));
        userPaneUI(tabbedPane);
        organisationPaneUI(tabbedPane);
        assetPaneUI(tabbedPane);
    }

    /**
     * Adds a listener to the new, save and delete buttons
     */
    private void addButtonListeners(ActionListener listener) {
        userNewButton.addActionListener(listener);
        userSaveButton.addActionListener(listener);
        userEditButton.addActionListener(listener);
        userDeleteButton.addActionListener(listener);
    }

    /**
     * Adds a listener to the name list
     */
    private void addUserListListener(ListSelectionListener listener) {
//        userList.addListSelectionListener(listener);
    }

    /**
     * Adds a listener to the JFrame
     */
    private void addClosingListener(WindowListener listener) {
        addWindowListener(listener);
    }

    /**
     * Sets the text in the address text fields to the empty string.
     */
    private void clearFields() {
        userName.setText("");
        firstName.setText("");
        lastName.setText("");
        userPassword.setText("");
    }



    /**
     * Displays the details of a Person in the address fields.
     *
     * @param user The User to display.
     */
    private void display(User user) {
        if (user != null) {
            firstName.setText(user.getFirstname());
            lastName.setText(user.getLastname());
            userName.setText(user.getUsername());
            userPassword.setText(user.getPassword());
        }
    }

    private class ButtonListener implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */

        public void actionPerformed(ActionEvent e) {
            int size = data.getUserSize();

            JButton source = (JButton) e.getSource();
            if (source == userNewButton) {
                newPressed();
            } else if (source == userSaveButton) {
                savePressed();
            } else if (source == userDeleteButton) {
                deleteUserPressed();
            } else if (source == userEditButton) {

            }
        }

        /**
         * When the new button is pressed, clear the field display, make them
         * editable and enable the save button.
         */
        private void newPressed() {
            clearFields();
            setFieldsEditable(true);
            userSaveButton.setEnabled(true);
        }

        /**
         * When the save button is pressed, check that the name field contains
         * something. If it does, create a new Person object and attempt to add it
         * to the data model. Change the fields back to not editable and make the
         * save button inactive.
         * <p>
         * Check the list size to see if the delete button should be enabled.
         */
        private void savePressed() {
            if (userName.getText() != null && !userName.getText().equals("")) {
                User u = new User(userName.getText(), firstName.getText(), lastName
                        .getText(), userPassword.getText());
                data.addUser(u);
            }
            setFieldsEditable(false);
            userSaveButton.setEnabled(false);
            //checkListSize();
        }

        /**
         * When the delete button is pressed remove the selected name from the
         * data model.
         * <p>
         * Clear the fields that were displayed and check to see if the delete
         * button should be displayed.
         * <p>
         * The index here handles cases where the first element of the list is
         * deleted.
         */
        private void deleteUserPressed() {
            int index = userList.getSelectedRow();
            //data.deleteUser(userList.getSelectedValue());
            clearFields();
            if (index != -1) {
                model.removeRow(index);
                //checkListSize();
            }
        }
    }

    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different name is selected from the list.
     */
//    private class NameListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
//        public void valueChanged(ListSelectionEvent e) {
////            int getRow = userList.getSelectedRow();
//                if (userList.getSelectedValue() != null
//                    && !userList.getSelectedValue().equals("")) {
//                display(data.getUser(userList.getSelectedValue()));
//            }
//        }
//    }

    /**
     * Implements the windowClosing method from WindowAdapter/WindowListener to
     * persist the contents of the data/model.
     */
    private class ClosingListener extends WindowAdapter {

        /**
         * @see WindowAdapter#windowClosing(WindowEvent)
         */
        public void windowClosing(WindowEvent e) {
            data.persist();
            System.exit(0);
        }
    }

}
