package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame implements ActionListener, Runnable {

    public static final int Width = 1000;
    public static final int Height = 600;

    private JTabbedPane tabbedPane;
    private JTable resourceTable, offerTable;
    private JMenu account;
    private JMenuBar menuBar;
    private JMenuItem changePassword, logout;
    private JTextField resourceType, estimateCredits, salesAmount, credits;

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

    }

    // start of the resource page
    private void ResourcePane(JTabbedPane tabbedPane) {
        Container resource = new Container();
        tabbedPane.addTab("Resource", resource);
        resource.setBackground(Color.decode("#0b2862"));
        resource.setLayout(new BoxLayout(resource, BoxLayout.Y_AXIS));

        resource.add(searchFieldPanel());
        resource.add(buttonFieldPanel());
        resource.add(resourceScrollPane());
    }

    private JPanel searchFieldPanel() {
        JPanel searchField = new JPanel();
        JTextField tf = new JTextField(30);
        JButton search = new JButton("Search");
        searchField.add(tf);
        searchField.add(search);
        return searchField;
    }

    private JPanel buttonFieldPanel() {
        JPanel buttonField = new JPanel();
        JButton button_1 = new JButton("Computational Resource");
        JButton button_2 = new JButton("Hardware Resource");
        JButton button_3 = new JButton("Software Resource");
        buttonField.add(button_1);
        buttonField.add(button_2);
        buttonField.add(button_3);
        return buttonField;
    }

    private JScrollPane resourceScrollPane() {
        String data[][] = {
                {"101", "Amit123", "Amit", "Jack", "Sales", "$670000", "BUY"},
                {"102", "Jai007", "Jai", "Spring", "Human Resource", "$780000", "BUY"},
                {"101", "Sachin334", "Sachin", "Eystein", "Design", "$700000", "BUY"}};

        String column[] = {"ID", "USERNAME", "FIRST NAME", "LAST NAME", "ORGANIZATION", "PRICE", "Button"};
        resourceTable = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(resourceTable);

        return scrollPane;

    }

    //start of the offer page
    private void OfferPane(JTabbedPane tabbedPane) {
        Container offer = new Container();
        tabbedPane.addTab("Offer", offer);
        offer.setBackground(Color.decode("#0b2862"));
        offer.setLayout(new BoxLayout(offer, BoxLayout.Y_AXIS));

        offer.add(searchFieldPanel_2());
        offer.add(offersScrollPane());

    }
    private JPanel searchFieldPanel_2() {
        JPanel searchField_2 = new JPanel();
        JTextField tf = new JTextField(30);
        JButton search = new JButton("Search");
        searchField_2.add(tf);
        searchField_2.add(search);

        String[] sourceStrings = {"SELL", "BUY"};
        JComboBox sourceList = new JComboBox(sourceStrings);
        sourceList.setSelectedIndex(1);
        searchField_2.add(sourceList);

        return searchField_2;
    }

    private JScrollPane offersScrollPane() {
        String column_1[] = {"Resources", "Organization", "Total", "Available", "Price", "Date", "Button","Button"};
        String data_1[][] = {
                {"R1", "Happy Planet", "10,000", "8,000", "$3,000", "27/04/2021", "Edit", "Remove"}};
        offerTable = new JTable(data_1, column_1);
        JScrollPane scrollPane_2 = new JScrollPane(offerTable);

        return scrollPane_2;
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
        JPanel title = new JPanel();
        JLabel L1 = new JLabel("Resource Title");
        title.add(L1);
        title.setMaximumSize(new Dimension(700, 200));
        return  title;
    }

    private JPanel contentPanel(){
        JPanel content = new JPanel();

        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);



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
        content.setMaximumSize(new Dimension(700, 150));

        return content;

    }

    private JPanel buttonFieldPanel_2() {
        JPanel buttonField_2 = new JPanel();
        JButton button_4 = new JButton("Create SELL Offer");

        buttonField_2.add(button_4);

        return buttonField_2;
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
