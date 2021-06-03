package tradingPlatform;

import com.sun.jdi.Value;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class UserGUI extends JFrame {

    public static final int Width = 1000;
    public static final int Height = 600;

    private JTabbedPane tabbedPane;
    private JTable resourceTable, offerTable, historyTable;
    private JMenu account;
    private JMenuBar menuBar;
    private JMenuItem changePassword, logout;
    private JTextField resourceType, estimateCredits, salesAmount, credits, resourceAmount, resourceName, creditPerResource, buyResourceAmount;
    private JList resourceList, buyResourceList, historyList;
    private JButton editButton, sellButton, removeButton, buyButton, newButton;
    TradingPlatformData data;

/*    public UserGUI(String title) throws HeadlessException {
        super(title);
    }*/

    public UserGUI(TradingPlatformData data) {
        this.data = data;
        createGUI();

        addButtonListener(new UserGUI.ButtonListener());
        addResourceListListener(new UserGUI.ResourceListListener());
        addClosingListener(new UserGUI.ClosingListener());

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
        ResourcePane(tabbedPane);
        OfferPane(tabbedPane);
        SellPane(tabbedPane);
        BuyPane(tabbedPane);
//        HomePane(tabbedPane);
//        HistoryPane(tabbedPane);
    }

    // start of the resource page
    private void ResourcePane(JTabbedPane tabbedPane) {
        Container resource = new Container();
        tabbedPane.addTab("Resource", resource);
        resource.setBackground(Color.decode("#c8ddf2"));
        resource.setLayout(new BoxLayout(resource, BoxLayout.Y_AXIS));

        resource.add(searchFieldPanel());
        resource.add(buttonFieldPanel());
        resource.add(resourceScrollPane());
//        resource.add(buttonFieldPanel_3());
    }

    private JPanel searchFieldPanel() {
        JPanel searchField = new JPanel();
        searchField.setBackground(Color.decode("#0b2862"));
        JTextField tf = new JTextField(30);
        JButton search = new JButton("Search");
        searchField.add(tf);
        searchField.add(search);
        searchField.setMaximumSize(new Dimension(1000, 50));
        return searchField;
    }

    private JPanel buttonFieldPanel() {
        JPanel resourceButtonField = new JPanel();
        resourceButtonField.setBackground(Color.decode("#c8ddf2"));
        JButton button_1 = new JButton("Computational Resource");
        JButton button_2 = new JButton("Hardware Resource");
        JButton button_3 = new JButton("Software Resource");
        resourceButtonField.add(button_1);
        resourceButtonField.add(button_2);
        resourceButtonField.add(button_3);
        resourceButtonField.setMaximumSize(new Dimension(1000, 50));
        return resourceButtonField;
    }

    private JScrollPane resourceScrollPane() {
        String data[][] = {
//                {"101", "Amit123", "Amit", "Jack", "Sales", "$670000"},
//                {"102", "Jai007", "Jai", "Spring", "Human Resource", "$780000"},
//                {"101", "Sachin334", "Sachin", "Eystein", "Design", "$700000"}};
        };

        String column[] = {"ASSETS", "ORGANIZATION", "ASSETS AMOUNT", "PRICE"};
        resourceTable = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(resourceTable);

        scrollPane.setViewportView(resourceTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMinimumSize(new Dimension(400, 150));
        scrollPane.setPreferredSize(new Dimension(450, 150));
        scrollPane.setMaximumSize(new Dimension(700, 200));

        return scrollPane;

    }
//    private JPanel buttonFieldPanel_3() {
//        JPanel resourceBuyButtonField = new JPanel();
//        JButton button_5 = new JButton("BUY");
//        resourceBuyButtonField.add(button_5);
//        resourceBuyButtonField.setMaximumSize(new Dimension(700, 50));
//        return resourceBuyButtonField;
//    }


    //start of the offer page
    private void OfferPane(JTabbedPane tabbedPane) {
        Container offer = new Container();
        tabbedPane.addTab("Offer", offer);
        offer.setBackground(Color.decode("#0b2862"));
        offer.setLayout(new BoxLayout(offer, BoxLayout.Y_AXIS));

        offer.add(searchFieldPanel_2());
        offer.add(offersScrollPane());
        offer.add(offerButtonFieldPanel());

    }

    private JPanel searchFieldPanel_2() {
        JPanel searchField_2 = new JPanel();
        searchField_2.setBackground(Color.decode("#0b2862"));
        JTextField tf = new JTextField(30);
        JButton search = new JButton("Search");
        searchField_2.add(tf);
        searchField_2.add(search);

//        String[] sourceStrings = {"SELL", "BUY"};
//        JComboBox sourceList = new JComboBox(sourceStrings);
//        sourceList.setSelectedIndex(1);
//        searchField_2.add(sourceList);
        searchField_2.setMaximumSize(new Dimension(1000, 50));
        return searchField_2;
    }

    private JScrollPane offersScrollPane() {
        String column_1[] = {"Resources", "Order time", "Organisation", "Price", "Total", "Remaining"};
        String data_1[][] = {
//                {"R1", "RAM", "27/04/2021","Happy Planet","$3,000","10,000", "8,000",  "27/04/2021"}
        };
        offerTable = new JTable(data_1, column_1);
        JScrollPane scrollPane_2 = new JScrollPane(offerTable);
        scrollPane_2.setViewportView(offerTable);
        scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_2.setMinimumSize(new Dimension(400, 150));
        scrollPane_2.setPreferredSize(new Dimension(450, 150));
        scrollPane_2.setMaximumSize(new Dimension(700, 300));

        return scrollPane_2;
    }

    private JPanel offerButtonFieldPanel() {
        JPanel offerButtonField = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");

        offerButtonField.add(editButton);
        offerButtonField.add(removeButton);
        offerButtonField.setMaximumSize(new Dimension(700, 50));
        return offerButtonField;
    }


    //start of the sell page
    private void SellPane(JTabbedPane tabbedPane) {
        Container sell = new Container();
        tabbedPane.addTab("SELL", sell);
        sell.setBackground(Color.decode("#0b2862"));
        sell.setLayout(new BoxLayout(sell, BoxLayout.Y_AXIS));
        sell.add(titlePanel());
        sell.add(resourceListPane());
        sell.add(contentPanel());
        sell.add(makeButtonsPanel());
        sell.add(Box.createHorizontalStrut(20));
    }

    private JPanel titlePanel() {
        JPanel sellTitle = new JPanel();
        sellTitle.setBackground(Color.decode("#0b2862"));
        JLabel L1 = new JLabel("Resource List");
        L1.setFont(new Font("Verdana", Font.BOLD, 24));
        L1.setForeground(Color.WHITE);
        sellTitle.add(L1);
        sellTitle.setMaximumSize(new Dimension(500, 50));
        return sellTitle;
    }

    private JPanel contentPanel() {
        JPanel content = new JPanel();
        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


//        String[] sourceList = {"", "Hard Disk", "RAM"};
//        JLabel availableResourceFieldLabel = new JLabel("Available Resource: ");
//        JLabel resourceTypeFieldLabel = new JLabel("Resource Type: ");
//        JLabel salesAmountFieldLabel = new JLabel("Sales Amount: ");
//        JLabel creditsFieldLabel = new JLabel("Credits per Resource: ");
//        JLabel estimateCreditsFieldLabel = new JLabel("Estimate Credits Income: ");


        JLabel resourceNameLabel = new JLabel("Resource Name:");
        JLabel creditPerResourceLabel = new JLabel("Credit per Resource:");
        JLabel resourceAmountLabel = new JLabel("Resource Amount:");

        resourceName = new JTextField(20);
        creditPerResource = new JTextField(20);
        resourceAmount = new JTextField(20);

//        resourceType = new JTextField(20);
//        salesAmount = new JTextField(20);
//        credits = new JTextField(20);
//        estimateCredits = new JTextField(20);
//        JComboBox resourceList = new JComboBox(sourceList);
//        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
//        hGroup.addGroup(layout.createParallelGroup()
//                .addComponent(availableResourceFieldLabel)
//                .addComponent(resourceTypeFieldLabel)
//                .addComponent(salesAmountFieldLabel)
//                .addComponent(creditsFieldLabel)
//                .addComponent(estimateCreditsFieldLabel));
//        hGroup.addGroup(layout.createParallelGroup()
//                .addComponent(resourceType)
//                .addComponent(salesAmount)
//                .addComponent(credits)
//                .addComponent(estimateCredits)
//                .addComponent(resourceList));
//        layout.setHorizontalGroup(hGroup);
//
//        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
//        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(availableResourceFieldLabel)
//                .addComponent(resourceList));
//        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(resourceTypeFieldLabel)
//                .addComponent(resourceType));
//        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(salesAmountFieldLabel)
//                .addComponent(salesAmount));
//        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(creditsFieldLabel)
//                .addComponent(credits));
//        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(estimateCreditsFieldLabel)
//                .addComponent(estimateCredits));
//        layout.setVerticalGroup(vGroup);
//        content.setMaximumSize(new Dimension(500, 190));
//        content.setBackground(Color.WHITE);
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
        content.setMaximumSize(new Dimension(500, 190));
        content.setBackground(Color.WHITE);


        return content;

    }

    private JPanel buttonFieldPanel_2() {
        JPanel buttonField_2 = new JPanel();
        JButton sellButton = new JButton("SELL");
        buttonField_2.add(sellButton);
        buttonField_2.setMaximumSize(new Dimension(500, 50));
        buttonField_2.setBackground(Color.WHITE);
        return buttonField_2;
    }

    //start og the buy page
    private void BuyPane(JTabbedPane tabbedPane) {
        Container buy = new Container();
        tabbedPane.addTab("BUY", buy);
        buy.setBackground(Color.decode("#0b2862"));
        buy.setLayout(new BoxLayout(buy, BoxLayout.Y_AXIS));
        buy.add(BuyTitlePanel());
        buy.add(resourceListPane());
        buy.add(buyContentPanel());
        buy.add(makeBuyButtonsPanel());
        buy.add(Box.createHorizontalStrut(20));
    }

    private JPanel BuyTitlePanel() {
        JPanel buyTitle = new JPanel();
        buyTitle.setBackground(Color.decode("#0b2862"));
        JLabel L1 = new JLabel("Buy Resource");
        L1.setFont(new Font("Verdana", Font.BOLD, 24));
        L1.setForeground(Color.WHITE);
        buyTitle.add(L1);
        buyTitle.setMaximumSize(new Dimension(500, 50));
        return buyTitle;
    }

    private JPanel buyContentPanel() {
        JPanel buyContent = new JPanel();
        GroupLayout layout = new GroupLayout(buyContent);
        buyContent.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


//        String[] buySourceList = {"", "Hard Disk", "RAM"};
        JLabel resourceNameFieldLabel = new JLabel("Resource Name: ");
        JLabel resourceAmountFieldLabel = new JLabel("Resource Amount: ");
        resourceName = new JTextField(20);
        resourceAmount = new JTextField(20);
//        JComboBox buyResourceList = new JComboBox(buySourceList);
        GroupLayout.SequentialGroup hGroup_1 = layout.createSequentialGroup();
        hGroup_1.addGroup(layout.createParallelGroup()
                .addComponent(resourceNameFieldLabel)
                .addComponent(resourceAmountFieldLabel));
        hGroup_1.addGroup(layout.createParallelGroup()
                .addComponent(resourceName)
                .addComponent(resourceAmount));
        layout.setHorizontalGroup(hGroup_1);

        GroupLayout.SequentialGroup vGroup_1 = layout.createSequentialGroup();
        vGroup_1.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceNameFieldLabel)
                .addComponent(resourceName));
        vGroup_1.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceAmountFieldLabel)
                .addComponent(resourceAmount));

        layout.setVerticalGroup(vGroup_1);
        buyContent.setMaximumSize(new Dimension(500, 100));
        buyContent.setBackground(Color.WHITE);

        return buyContent;

    }

    private JPanel buttonFieldPanel_4() {
        JPanel buyButtonField = new JPanel();
        JButton sellButton = new JButton("BUY");
        buyButtonField.add(sellButton);
        buyButtonField.setMaximumSize(new Dimension(500, 50));
        buyButtonField.setBackground(Color.WHITE);
        return buyButtonField;
    }


    // start of the home page
    private void HomePane(JTabbedPane tabbedPane) {
        Container resource = new Container();
        tabbedPane.addTab("BUY & SELL", resource);
        resource.setBackground(Color.decode("#c8ddf2"));
        resource.setLayout(new BoxLayout(resource, BoxLayout.Y_AXIS));

        resource.add(Box.createVerticalStrut(20));
        resource.add(DetailsPanel());
        resource.add(Box.createVerticalStrut(20));
        resource.add(makeButtonsPanel());
        resource.add(Box.createVerticalStrut(20));

        resource.add(BuyDetailsPanel());
        resource.add(Box.createVerticalStrut(20));
        resource.add(makeBuyButtonsPanel());
        resource.add(Box.createVerticalStrut(20));

    }

    private JPanel DetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(resourceListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeResourceFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));
//        detailsPanel.add(makeButtonsPanel());
//        detailsPanel.add(Box.createHorizontalStrut(20));
        return detailsPanel;
    }

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
        scroller.setMaximumSize(new Dimension(500, 50));
        scroller.setBackground(Color.WHITE);

        return scroller;
    }

    private JPanel makeResourceFieldsPanel() {
        JPanel ResourceFieldsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(ResourceFieldsPanel);
        ResourceFieldsPanel.setLayout(layout);


        layout.setAutoCreateGaps(true);


        layout.setAutoCreateContainerGaps(true);

        String[] buySourceList = {"", "Hard Disk", "RAM"};
        JLabel resourceNameLabel = new JLabel("Resource Name:");
        JLabel creditPerResourceLabel = new JLabel("Credit per Resource:");
        JLabel resourceAmountLabel = new JLabel("Resource Amount:");


        JComboBox buyResourceList = new JComboBox(buySourceList);
        creditPerResource = new JTextField(20);
        resourceAmount = new JTextField(20);


        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();


        hGroup.addGroup(layout.createParallelGroup().addComponent(resourceNameLabel)
                .addComponent(creditPerResourceLabel).addComponent(resourceAmountLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(buyResourceList)
                .addComponent(creditPerResource).addComponent(resourceAmount));
        layout.setHorizontalGroup(hGroup);


        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();


        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceNameLabel).addComponent(buyResourceList));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(creditPerResourceLabel).addComponent(creditPerResource));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceAmountLabel).addComponent(resourceAmount));

        layout.setVerticalGroup(vGroup);

        return ResourceFieldsPanel;
    }

    private JPanel makeButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        newButton = new JButton("New");
        sellButton = new JButton("SELL");
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(newButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(sellButton);
        buttonPanel.setMaximumSize(new Dimension(500, 50));
        buttonPanel.setBackground(Color.WHITE);
        return buttonPanel;
    }


    private JPanel BuyDetailsPanel() {
        JPanel buyDetailsPanel = new JPanel();
        buyDetailsPanel.setLayout(new BoxLayout(buyDetailsPanel, BoxLayout.X_AXIS));
        buyDetailsPanel.add(Box.createHorizontalStrut(20));
        buyDetailsPanel.add(BuyResourceListPane());
        buyDetailsPanel.add(Box.createHorizontalStrut(20));
        buyDetailsPanel.add(makeBuyResourceFieldsPanel());
        buyDetailsPanel.add(Box.createHorizontalStrut(20));
//        buyDetailsPanel.add(makeBuyButtonsPanel());
//        buyDetailsPanel.add(Box.createHorizontalStrut(20));

        return buyDetailsPanel;
    }

    private JScrollPane BuyResourceListPane() {
//        resourceList = new JList(data.getModel());
//        resourceList.setFixedCellWidth(200);

        JScrollPane BuyScroller = new JScrollPane(buyResourceList);
        BuyScroller.setViewportView(buyResourceList);
        BuyScroller
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        BuyScroller
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        BuyScroller.setMinimumSize(new Dimension(200, 150));
        BuyScroller.setPreferredSize(new Dimension(250, 150));

        BuyScroller.setMaximumSize(new Dimension(500, 50));
        BuyScroller.setBackground(Color.WHITE);

        return BuyScroller;
    }

    private JPanel makeBuyResourceFieldsPanel() {
        JPanel BuyResourceFieldsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(BuyResourceFieldsPanel);
        BuyResourceFieldsPanel.setLayout(layout);


        layout.setAutoCreateGaps(true);


        layout.setAutoCreateContainerGaps(true);

        JLabel resourceNameLabel = new JLabel("Resource Name:");
        JLabel creditPerResourceLabel = new JLabel("Credit per Resource:");
        JLabel BuyResourceAmountLabel = new JLabel("Buy Resource Amount:");


        buyResourceAmount = new JTextField(20);
//        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();


        hGroup.addGroup(layout.createParallelGroup().addComponent(resourceNameLabel)
                .addComponent(creditPerResourceLabel).addComponent(BuyResourceAmountLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(buyResourceAmount));
        layout.setHorizontalGroup(hGroup);


        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();


        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceNameLabel));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(creditPerResourceLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(BuyResourceAmountLabel).addComponent(buyResourceAmount));

        layout.setVerticalGroup(vGroup);

        return BuyResourceFieldsPanel;
    }

    private JPanel makeBuyButtonsPanel() {
        JPanel BuyButtonPanel = new JPanel();
        BuyButtonPanel.setLayout(new BoxLayout(BuyButtonPanel, BoxLayout.X_AXIS));
        buyButton = new JButton("BUY");
        BuyButtonPanel.add(Box.createHorizontalStrut(50));
        BuyButtonPanel.add(buyButton);
        BuyButtonPanel.add(Box.createHorizontalStrut(50));
        BuyButtonPanel.setMaximumSize(new Dimension(500, 50));
        BuyButtonPanel.setBackground(Color.WHITE);

        return BuyButtonPanel;
    }


    private Container HistoryPane(JTabbedPane tabbedPane) {
        Container history = new Container();
        tabbedPane.addTab("History", history);
        history.setBackground(Color.decode("#c8ddf2"));
        history.setLayout(new BoxLayout(history, BoxLayout.Y_AXIS));

        return history;
    }


    private void addButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
        removeButton.addActionListener(listener);
        newButton.addActionListener(listener);
        sellButton.addActionListener(listener);
        buyButton.addActionListener(listener);
        changePassword.addActionListener(listener);
        logout.addActionListener(listener);
    }

    /**
     * Adds a listener to the name list
     */
    private void addResourceListListener(ListSelectionListener listener) {
        resourceList.addListSelectionListener(listener);
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
        creditPerResource.setText("");
        resourceAmount.setText("");
    }


    private void displaySell(Transaction transaction) {
        resourceName.setText(transaction.getAsset());
        creditPerResource.setText(String.valueOf(transaction.getValue()));
        resourceAmount.setText(String.valueOf(transaction.getAssetAmount()));
    }


    private void displayBuy(Transaction transaction) {
        resourceName.setText(transaction.getAsset());
        resourceAmount.setText(String.valueOf(transaction.getAssetAmount()));
    }

    private void setFieldsEditable(boolean editable) {
        resourceName.setEditable(editable);
        resourceAmount.setEditable(editable);
        creditPerResource.setEditable(editable);

    }


    private class ButtonListener implements ActionListener {


        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int size = data.getSize();

            JButton source = (JButton) e.getSource();
            if (source == newButton) {
                newPressed();
            } else if (source == sellButton) {
                sellPressed();
            } else if (source == buyButton) {
                buyPressed();
            } else if (source == removeButton) {
                removePressed();
            } else if (source == editButton) {
                editPressed();
            }

        }

        private void newPressed() {
            clearFields();
            setFieldsEditable(true);
            sellButton.setEnabled(true);
        }


        private void sellPressed() {
            if (resourceName.getText() != null && !resourceName.getText().equals("") &&
                    resourceAmount.getText() != null && !resourceAmount.getText().equals("") &&
                    creditPerResource.getText() != null && !creditPerResource.getText().equals("")) {
                Transaction transaction = new Transaction(
                        resourceName.getText(),
                        resourceAmount.getText(),
                        creditPerResource.getText());
                data.addResource;
            }

        }


        private void buyPressed() {
            if (resourceName.getText() != null && !resourceName.getText().equals("") &&
                    resourceAmount.getText() != null && !resourceAmount.getText().equals("")) {
                Transaction transaction = new Transaction(
                        resourceName.getText(),
                        resourceAmount.getText(),
                        creditPerResource.getText());
                data.addBuy;

            }


        }

        private void removePressed() {
            int index = resourceList.getSelectedIndex();
            data.deleteReource(resourceList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (data.getSize() != 0) {
                    index = 0;
                }
            }
            resourceList.setSelectedIndex(index);
        }

        private void editPressed() {
            //quite complex
        }



    }

    private class ResourceListListener implements ListSelectionListener{

        public void valueChanged(ListSelectionEvent e) {
            if (resourceList.getSelectedValue() != null
                    && !resourceList.getSelectedValue().equals("")) {
                displaySell(data.getResource(resourceList.getSelectedValue()),
                        displayBuy(data.getResource(resourceList.getSelectedValue()));
            }

        }
    }


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
