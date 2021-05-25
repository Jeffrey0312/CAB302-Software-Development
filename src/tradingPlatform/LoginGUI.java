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
        LoginFrame.add(new JPanel(),BorderLayout.SOUTH);
        LoginFrame.add(new JPanel(),BorderLayout.EAST);
        LoginFrame.add(new JPanel(),BorderLayout.WEST);
        LoginFrame.add(LoginPanel(),BorderLayout.CENTER);
        LoginFrame.setVisible(true);

    }

    private static JPanel LoginPanel() {
        GridBagLayout Layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel logpnl = new JPanel();
        logpnl.setLayout(Layout);

        JLabel LoginLabel = new JLabel("Login");
        c.weighty = 0.25;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        logpnl.add(LoginLabel,c);

        JLabel UsernameLabel = new JLabel("Username");
        c.weightx = 0.25;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        logpnl.add(UsernameLabel,c);

        JLabel PasswordLabel = new JLabel("Password");
        c.gridy = 2;
        logpnl.add(PasswordLabel,c);

        JTextField UsernameField = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.75;
        c.gridx = 1;
        c.gridy = 1;
        logpnl.add(UsernameField,c);

        JTextField PasswordField = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        logpnl.add(PasswordField,c);

        JButton LoginButton = new JButton("Login");
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        logpnl.add(LoginButton,c);

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
