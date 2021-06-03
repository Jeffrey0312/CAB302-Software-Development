package tradingPlatform;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ITGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private JTabbedPane tabbedPane;
    private JTextField asset, organisation, userName, firstName, lastName, credits, userPassword, userOrganisation;
    private JList assetList, organisationList, userList;
    //private JTable userList;
    private JCheckBox userITCheck;
    private JMenuBar menuBar;
    private JMenu options;
    private JMenuItem changePassword, close, logout;
    private JButton userNewButton, userEditButton, userSaveButton, userDeleteButton;
    private JButton organisationNewButton, organisationEditButton, organisationSaveButton, organisationDeleteButton;
    private JButton assetNewButton, assetEditButton, assetSaveButton, assetDeleteButton;
    private DefaultTableModel model;
    private JFrame ITFrame;


    TradingPlatformData data;

    /**
     * Constructor sets up user interface, adds listeners and displays.
     *
     * @param data The underlying data/model class the UI needs.
     */
    public ITGUI(TradingPlatformData data) {
        this.data = data;
        createGUI();
        //checkListSize();

        // add listeners to interactive components
        addButtonListeners(new ButtonListener());
        addNameListListener(new NameListListener());
        addClosingListener(new ClosingListener());
    }

    private void createGUI() {
        ITFrame = new JFrame("Electronic Asset Trading Platform");
        ITFrame.setSize(WIDTH, HEIGHT);
        ITFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ITFrame.setLayout(new BorderLayout());
        ITFrame.setResizable(false);
        //ITFrame.getContentPane().setBackground(Color.decode("#0b2862"));

        addTabbedPane();
        addMenuItems();
        ITFrame.add(addMenu());
        ITFrame.add(tabbedPane);

        add(menuBar);
        ITFrame.setJMenuBar(menuBar);
        ITFrame.setVisible(true);
    }

    private void addMenuItems(){
        changePassword = new JMenuItem("Change Password");
        logout = new JMenuItem("Logout");
        close = new JMenuItem("Close");
    }

    private JMenuBar addMenu(){
        menuBar = new JMenuBar();
        options = new JMenu("Options");
        options.add(changePassword); options.add(logout); options.add(close);
        menuBar.add(options);
        return menuBar;
    }

    /** USER SECTION */
    private void userPaneUI(JTabbedPane tabbedPane){
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

    private JPanel makeUserAddPanel(){
        JPanel userAddPanel = new JPanel();
        userAddPanel.setBackground(Color.decode("#0b2862"));
        userAddPanel.setLayout(new BoxLayout(userAddPanel, BoxLayout.Y_AXIS));
        userAddPanel.add(Box.createVerticalStrut(10));
        userAddPanel.add(makeUserFieldPanel());
        userAddPanel.add(makeUserButtonPanel());
        userAddPanel.add(Box.createVerticalStrut(100));
        userAddPanel.setMaximumSize(new Dimension(500,300));
        return userAddPanel;
    }

    private JPanel makeUserFieldPanel(){
        JPanel userFieldPanel = new JPanel();
        GroupLayout layout = new GroupLayout(userFieldPanel);
        userFieldPanel.setLayout(layout);

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //String[] orgList = new String[orgListModelTopic.getSize()];

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
        userITCheck = new JCheckBox();
        userITCheck.setBackground(Color.decode("#0b2862"));
        userOrganisation =  new JTextField(20);

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
                .addComponent(userOrganisation));
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
                .addComponent(userOrganisation));
        layout.setVerticalGroup(vUserGroup);

        userFieldPanel.setMaximumSize(new Dimension(350, 75));
        userFieldPanel.setBackground(Color.decode("#0b2862"));
        return userFieldPanel;
    }

    private JPanel makeUserButtonPanel(){
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
        //userList = new JTable(data.userListModel);
        //model = new DefaultTableModel(userData, userColumn);
        userList = new JList(data.userListModel);
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

    /** ORGANISATION SECTION */
    private void organisationPaneUI(JTabbedPane tabbedPane){
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

    private JPanel makeOrganisationAddPanel(){
        JPanel organisationAddPanel = new JPanel();
        organisationAddPanel.setBackground(Color.decode("#0b2862"));
        organisationAddPanel.setLayout(new BoxLayout(organisationAddPanel, BoxLayout.Y_AXIS));
        organisationAddPanel.add(Box.createVerticalStrut(50));
        organisationAddPanel.add(makeOrganisationFieldPanel());
        organisationAddPanel.add(makeOrganisationButtonPanel());
        organisationAddPanel.setMaximumSize(new Dimension(350,300));
        return organisationAddPanel;
    }

    private JPanel makeOrganisationFieldPanel(){
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

    private JPanel makeOrganisationButtonPanel(){
        JPanel organisationButtonPanel = new JPanel();
        organisationButtonPanel.setLayout(new BoxLayout(organisationButtonPanel, BoxLayout.X_AXIS));
        organisationButtonPanel.setBackground(Color.decode("#0b2862"));
        organisationNewButton = new JButton("New");
        organisationEditButton = new JButton("Edit");
        organisationSaveButton = new JButton("Save");
        organisationSaveButton.setEnabled(false);
        organisationDeleteButton = new JButton("Delete");
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
        //String[] organisationDataList = {"Telstra", "Optus", "Belong"};
        organisationList = new JList(data.organisationListModel);
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

    /** ASSET SECTION */
    private void assetPaneUI(JTabbedPane tabbedPane){
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

    private JPanel makeAssetAddPanel(){
        JPanel assetAddPanel = new JPanel();
        assetAddPanel.setBackground(Color.decode("#0b2862"));
        assetAddPanel.setLayout(new BoxLayout(assetAddPanel, BoxLayout.Y_AXIS));
        assetAddPanel.add(Box.createVerticalStrut(100));
        assetAddPanel.add(makeAssetFieldPanel());
        assetAddPanel.add(makeAssetButtonPanel());
        assetAddPanel.add(Box.createVerticalStrut(100));
        assetAddPanel.setMaximumSize(new Dimension(500,300));
        return assetAddPanel;
    }

    private JPanel makeAssetFieldPanel(){
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

    private JPanel makeAssetButtonPanel(){
        JPanel assetButtonPanel = new JPanel();
        assetButtonPanel.setLayout(new BoxLayout(assetButtonPanel, BoxLayout.X_AXIS));
        assetButtonPanel.setBackground(Color.decode("#0b2862"));
        assetNewButton = new JButton("New");
        assetEditButton = new JButton("Edit");
        assetSaveButton = new JButton("Save");
        assetSaveButton.setEnabled(false);
        assetDeleteButton = new JButton("Delete");
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
        //String[] assetDataList = {"CPU Hours", "Widgets", "AutoCAD Licence"};
        assetList = new JList(data.getOrganisationModel());
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

    private void setFieldsEditable(boolean editable){
        asset.setEditable(editable);
        organisation.setEditable(editable);
        credits.setEditable(editable);
        userName.setEditable(editable);
        firstName.setEditable(editable);
        lastName.setEditable(editable);
        userPassword.setEditable(editable);
        userOrganisation.setEditable(editable);
    }

    private void addTabbedPane(){
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
        organisationNewButton.addActionListener(listener);
        organisationSaveButton.addActionListener(listener);
        organisationEditButton.addActionListener(listener);
        organisationDeleteButton.addActionListener(listener);
        assetNewButton.addActionListener(listener);
        assetSaveButton.addActionListener(listener);
        assetEditButton.addActionListener(listener);
        assetDeleteButton.addActionListener(listener);
        changePassword.addActionListener(listener);
        logout.addActionListener(listener);
        close.addActionListener(listener);
    }

    /**
     * Adds a listener to the name list
     */
    private void addNameListListener(ListSelectionListener listener) {
        organisationList.addListSelectionListener(listener);
        userList.addListSelectionListener(listener);
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
        organisation.setText("");
        credits.setText("");
        asset.setText("");
        userOrganisation.setText("");
    }

    /**
     * Displays the details of a Person in the address fields.
     * @param user The User to display.
     */
    private void displayUser(User user) {
        if (user != null) {
            userName.setText(user.getUsername());
            firstName.setText(user.getFirstname());
            lastName.setText(user.getLastname());
            userPassword.setText(user.getPassword());
        }
    }

    /**
     * Displays the details of a Person in the address fields.
     * @param org The User to display.
     */
    private void displayOrganisation(OrganisationalUnit org) {
        if (org != null) {
            organisation.setText(org.getName());
            credits.setText(Integer.toString(org.getCredits()));
        }
    }

    private class ButtonListener implements ActionListener {
        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == userNewButton) {
                userNewPressed();
            } else if (source == userSaveButton) {
                saveUserPressed();
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == userDeleteButton) {
                deleteUserPressed();
            } else if (source == userEditButton) {
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == organisationNewButton) {
                organisationNewPressed();
            } else if (source == organisationSaveButton) {
                saveOrganisationPressed();
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == organisationDeleteButton) {
                deleteOrganisationPressed();
            } else if (source == organisationEditButton) {
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == assetNewButton) {
                assetNewPressed();
            } else if (source == assetSaveButton) {
                //saveAssetPressed();
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == assetDeleteButton) {
                //deleteAssetPressed();
            } else if (source == assetEditButton) {
                clearFields();
                setFieldsEditable(false);
                userSaveButton.setEnabled(false);
            } else if (source == changePassword) {
               new PasswordGUI(data);
            } else if (source == close) {
                data.persist();
                System.exit(0);
            } else if (source == logout) {
                new LoginGUI(data);
                ITFrame.setVisible(false);
            }
        }

        /**
         * When the new button is pressed, clear the field display, make them
         * editable and enable the save button.
         */
        private void userNewPressed() {
            clearFields();
            setFieldsEditable(true);
            userSaveButton.setEnabled(true);
        }

        /**
         * When the new button is pressed, clear the field display, make them
         * editable and enable the save button.
         */
        private void organisationNewPressed() {
            clearFields();
            setFieldsEditable(true);
            organisationSaveButton.setEnabled(true);
        }

        /**
         * When the new button is pressed, clear the field display, make them
         * editable and enable the save button.
         */
        private void assetNewPressed() {
            clearFields();
            setFieldsEditable(true);
            assetSaveButton.setEnabled(true);
        }

        /**
         * When the save button is pressed, check that the name field contains
         * something. If it does, create a new User object and attempt to add it
         * to the data model. Change the fields back to not editable and make the
         * save button inactive.
         * <p>
         * Check the list size to see if the delete button should be enabled.
         */
        private void saveUserPressed() {
            if (userName.getText() != null && !userName.getText().equals("")) {
                //if (userITCheck.isSelected()) {
                    User u = new User(userName.getText(), firstName.getText(), lastName
                            .getText(), userPassword.getText());
                    data.addUser(u);
/*                } else if (!userITCheck.isSelected()) {
                    User u = new ClientUser(userName.getText(), firstName.getText(), lastName
                            .getText(), userPassword.getText(), (OrganisationalUnit) userOrganisation.getText());
                    data.addUser(u);
                }*/
            }
        }

        /**
         * When the save button is pressed, check that the name field contains
         * something. If it does, create a new organisation object and attempt to add it
         * to the data model. Change the fields back to not editable and make the
         * save button inactive.
         * <p>
         * Check the list size to see if the delete button should be enabled.
         */
        private void saveOrganisationPressed() {
            if (organisation.getText() != null && !organisation.getText().equals("")) {
                OrganisationalUnit o = new OrganisationalUnit(organisation.getText(), Integer.parseInt(credits.getText()));
                data.addOrganisation(o);
                data.setOrganisationCredits(o, Integer.parseInt(credits.getText()));
            }
            setFieldsEditable(false);
            userSaveButton.setEnabled(false);
        }

/*        *//**
         * When the save button is pressed, check that the name field contains
         * something. If it does, create a new Person object and attempt to add it
         * to the data model. Change the fields back to not editable and make the
         * save button inactive.
         * <p>
         * Check the list size to see if the delete button should be enabled.
         */
        private void saveAssetPressed() {
            if (asset.getText() != null && !asset.getText().equals("")) {
                OrganisationalUnit o = new OrganisationalUnit(asset.getText(), Integer.parseInt(credits.getText()));
                data.addOrganisation(o);
            }
            setFieldsEditable(false);
            userSaveButton.setEnabled(false);
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
/*            //int index = userList.getSelectedRow();
            int index = userList.getSelectedIndex();
            data.deleteUser(userList.getSelectedValue());
            clearFields();
            if (index >= 0) {
                model.removeRow(index);
                JOptionPane.showMessageDialog(null, "Row Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Unable To Delete Row");
            }*/
            int index = userList.getSelectedIndex();
            data.deleteUser(userList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (data.getSize() != 0) {
                    index = 0;
                }
            }
            userList.setSelectedIndex(index);
            //checkListSize();
        }
    }

    /**
     * When the delete button is pressed remove the selected organisation from the
     * data model.
     * <p>
     * Clear the fields that were displayed and check to see if the delete
     * button should be displayed.
     * <p>
     * The index here handles cases where the first element of the list is
     * deleted.
     */
    private void deleteOrganisationPressed() {
        int index = organisationList.getSelectedIndex();
        data.deleteOrganisation(organisationList.getSelectedValue());
        clearFields();
        index--;
        if (index == -1) {
            if (data.getSize() != 0) {
                index = 0;
            }
        }
        organisationList.setSelectedIndex(index);
        //checkListSize();
    }


    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different name is selected from the list.
     */
    private class NameListListener implements ListSelectionListener {
        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (userList.getSelectedValue() != null
                    && !userList.getSelectedValue().equals("")) {
                displayUser(data.getUser(userList.getSelectedValue()));
            }
            if (organisationList.getSelectedValue() != null
                    && !organisationList.getSelectedValue().equals("")) {
                displayOrganisation(data.getOrganisation(organisationList.getSelectedValue()));
            }
        }
    }

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
