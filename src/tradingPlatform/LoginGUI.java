package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    public static final int LoginWidth = 300;
    public static final int LoginHeight = 300;

    private JLabel LoginLabel, UsernameLabel, PasswordLabel;
    private JTextField UsernameField, PasswordField;
    private JButton LoginButton;
    private JFrame LoginFrame;

    String staffLogin = "staff", staffPassword = "staff";
    String clientLogin = "client", clientPassword = "client";

    TradingPlatformData data;

    public LoginGUI(TradingPlatformData data){
        this.data = data;
        CreateLoginGUI();
        LoginButton.addActionListener(this);
    }

    public void CreateLoginGUI() {
        LoginFrame = new JFrame("Login");
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

    private JPanel LoginPanel() {
        GridBagLayout Layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel logpnl = new JPanel();
        logpnl.setLayout(Layout);

        LoginLabel = new JLabel("Login");
        c.weighty = 0.25;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        logpnl.add(LoginLabel,c);

        UsernameLabel = new JLabel("Username");
        c.weightx = 0.25;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        logpnl.add(UsernameLabel,c);

        PasswordLabel = new JLabel("Password");
        c.gridy = 2;
        logpnl.add(PasswordLabel,c);

        UsernameField = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.75;
        c.gridx = 1;
        c.gridy = 1;
        logpnl.add(UsernameField,c);

        PasswordField = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        logpnl.add(PasswordField,c);

        LoginButton = new JButton("Login");
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
        Object source = e.getSource();
        if (source == LoginButton) {
            if (isEqualToString(UsernameField, staffLogin) && isEqualToString(PasswordField, staffPassword)) {
                new ITGUI(data);
                LoginFrame.setVisible(false);
            } else if (isEqualToString(UsernameField, clientLogin) && isEqualToString(PasswordField, clientPassword)) {
                new UserGUI(data);
                LoginFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this,"Incorrect username and password.",
                        "", JOptionPane.ERROR_MESSAGE);
                UsernameField.setText("");
                PasswordField.setText("");
            }
        }
    }

    public boolean isEqualToString(JTextField textField, String compareTo) {
        String text = textField.getText();
        if(text.equals(compareTo)) {
            return true;
        }
        return false;
    }

}
