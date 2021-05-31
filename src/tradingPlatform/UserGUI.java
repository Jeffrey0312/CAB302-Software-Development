package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserGUI extends JFrame implements ActionListener, Runnable {

    public static final int Width = 1000;
    public static final int Height = 600;

    private JTabbedPane tabbedPane;
    private JTable resourceTable, offerTable;
    private JMenu account;
    private JMenuBar menuBar;
    private JMenuItem changePassword, logout;
    private JTextField resourceType, estimateCredits, salesAmount, credits, resourceAmount;
    TradingPlatformData data;

    public UserGUI(String title) throws HeadlessException {
        super(title);
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
        ResourcePane(tabbedPane);
        OfferPane(tabbedPane);
        SellPane(tabbedPane);
        BuyPane(tabbedPane);

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
        resource.add(buttonFieldPanel_3());
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
                {"101", "Amit123", "Amit", "Jack", "Sales", "$670000"},
                {"102", "Jai007", "Jai", "Spring", "Human Resource", "$780000"},
                {"101", "Sachin334", "Sachin", "Eystein", "Design", "$700000"}};

        String column[] = {"ID", "USERNAME", "FIRST NAME", "LAST NAME", "ORGANIZATION", "CURRENT PRICE"};
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
    private JPanel buttonFieldPanel_3() {
        JPanel resourceBuyButtonField = new JPanel();
        JButton button_5 = new JButton("BUY");
        resourceBuyButtonField.add(button_5);
        resourceBuyButtonField.setMaximumSize(new Dimension(700, 50));
        return resourceBuyButtonField;
    }



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

        String[] sourceStrings = {"SELL", "BUY"};
        JComboBox sourceList = new JComboBox(sourceStrings);
        sourceList.setSelectedIndex(1);
        searchField_2.add(sourceList);
        searchField_2.setMaximumSize(new Dimension(1000, 50));
        return searchField_2;
    }

    private JScrollPane offersScrollPane() {
        String column_1[] = {"Resources ID","Resources","Order time","Organisation","Price","Total","Remaining","Last Updated"};
        String data_1[][] = {
                {"R1", "RAM", "27/04/2021","Happy Planet","$3,000","10,000", "8,000",  "27/04/2021"}};
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
        JButton buyButton = new JButton("Edit");
        JButton sellButton = new JButton("Remove");

        offerButtonField.add(buyButton);
        offerButtonField.add(sellButton);
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
        sell.add(contentPanel());
        sell.add(buttonFieldPanel_2());
    }

    private JPanel titlePanel(){
        JPanel sellTitle = new JPanel();
        sellTitle.setBackground(Color.decode("#0b2862"));
        JLabel L1 = new JLabel("Resource Title");
        L1.setFont(new Font("Verdana", Font.BOLD, 24));
        L1.setForeground(Color.WHITE);
        sellTitle.add(L1);
        sellTitle.setMaximumSize(new Dimension(500, 50));
        return  sellTitle;
    }

    private JPanel contentPanel(){
        JPanel content = new JPanel();
        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);



        String[] sourceList = {"", "Hard Disk", "RAM"};
        JLabel availableResourceFieldLabel = new JLabel("Available Resource: ");
        JLabel resourceTypeFieldLabel = new JLabel("Resource Type: ");
        JLabel salesAmountFieldLabel = new JLabel("Sales Amount: ");
        JLabel creditsFieldLabel = new JLabel("Credits per Resource: ");
        JLabel estimateCreditsFieldLabel = new JLabel("Estimate Credits Income: ");

        resourceType = new JTextField(20);
        salesAmount = new JTextField(20);
        credits = new JTextField(20);
        estimateCredits = new JTextField(20);
        JComboBox resourceList = new JComboBox(sourceList);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(availableResourceFieldLabel)
                .addComponent(resourceTypeFieldLabel)
                .addComponent(salesAmountFieldLabel)
                .addComponent(creditsFieldLabel)
                .addComponent(estimateCreditsFieldLabel));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(resourceType)
                .addComponent(salesAmount)
                .addComponent(credits)
                .addComponent(estimateCredits)
                .addComponent(resourceList));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(availableResourceFieldLabel)
                .addComponent(resourceList));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceTypeFieldLabel)
                .addComponent(resourceType));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(salesAmountFieldLabel)
                .addComponent(salesAmount));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(creditsFieldLabel)
                .addComponent(credits));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(estimateCreditsFieldLabel)
                .addComponent(estimateCredits));
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
        buy.add(buyContentPanel());
        buy.add(buttonFieldPanel_4());
    }

    private JPanel BuyTitlePanel(){
        JPanel buyTitle = new JPanel();
        buyTitle.setBackground(Color.decode("#0b2862"));
        JLabel L1 = new JLabel("Buy Resource");
        L1.setFont(new Font("Verdana", Font.BOLD, 24));
        L1.setForeground(Color.WHITE);
        buyTitle.add(L1);
        buyTitle.setMaximumSize(new Dimension(500, 50));
        return  buyTitle;
    }

    private JPanel buyContentPanel() {
        JPanel buyContent = new JPanel();
        GroupLayout layout = new GroupLayout(buyContent);
        buyContent.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        String[] buySourceList = {"", "Hard Disk", "RAM"};
        JLabel resourceNameFieldLabel = new JLabel("Resource Name: ");
        JLabel resourceAmountFieldLabel = new JLabel("Resource Amount: ");
        resourceAmount = new JTextField(20);
        JComboBox buyResourceList = new JComboBox(buySourceList);
        GroupLayout.SequentialGroup hGroup_1 = layout.createSequentialGroup();
        hGroup_1.addGroup(layout.createParallelGroup()
                .addComponent(resourceNameFieldLabel)
                .addComponent(resourceAmountFieldLabel));
        hGroup_1.addGroup(layout.createParallelGroup()
                .addComponent(resourceAmount)
                .addComponent(buyResourceList));
        layout.setHorizontalGroup(hGroup_1);

        GroupLayout.SequentialGroup vGroup_1 = layout.createSequentialGroup();
        vGroup_1.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceNameFieldLabel)
                .addComponent(buyResourceList));
        vGroup_1.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(resourceAmountFieldLabel)
                .addComponent(resourceAmount));

        layout.setVerticalGroup(vGroup_1);
        buyContent.setMaximumSize(new Dimension(500, 190));
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

    private class ClosingListener extends WindowAdapter {

        /**
         * @see WindowAdapter#windowClosing(WindowEvent)
         */
        public void windowClosing(WindowEvent e) {
            data.persist();
            System.exit(0);
        }
    }
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
