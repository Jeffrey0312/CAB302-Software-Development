package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener, Runnable {

    public static final int LoginWidth = 300;
    public static final int LoginHeight = 300;

    public static void CreateLoginGUI() {
        JFrame LoginFrame = new JFrame("Login");
        LoginFrame.setSize(LoginWidth, LoginHeight);
        LoginFrame.setResizable(false);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginFrame.setLayout(new BorderLayout());
        LoginFrame.add(new JPanel(),BorderLayout.NORTH);
        LoginFrame.add(LoginPanel(),BorderLayout.CENTER);
        LoginFrame.setVisible(true);

    }

    private static JPanel LoginPanel() {
        FlowLayout Layout = new FlowLayout();
        JPanel logpnl = new JPanel();
        logpnl.setLayout(Layout);
        JPanel filler = new JPanel();
        JLabel LoginLabel = new JLabel("Login");
        logpnl.add(LoginLabel);
        JLabel UsernameLabel = new JLabel("Username");
        UsernameLabel.setSize(50,25);
        logpnl.add(UsernameLabel);
        JTextField UsernameField = new JTextField();
        return  logpnl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        CreateLoginGUI();
    }
}
