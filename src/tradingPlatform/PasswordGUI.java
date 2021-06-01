package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGUI extends JFrame implements ActionListener, Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private static JButton okayButton, cancelButton;
    private static JLabel oldPasswordLabel, newPasswordLabel;
    private static JTextField oldPasswordField, newPasswordField;

    public static void CreateLoginGUI() {
        JFrame loginFrame = new JFrame("Change Password");
        loginFrame.setSize(WIDTH, HEIGHT);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(makePasswordPanel());
        loginFrame.setVisible(true);
    }

    private static JPanel makePasswordPanel() {
        JPanel userLoginPanel = new JPanel();
        userLoginPanel.setLayout(new BoxLayout(userLoginPanel, BoxLayout.Y_AXIS));
        userLoginPanel.add(Box.createVerticalStrut(20));
        userLoginPanel.add(passwordPanel());
        userLoginPanel.add(Box.createVerticalStrut(10));
        userLoginPanel.add(buttonPanel());
        userLoginPanel.add(Box.createVerticalStrut(20));
        return userLoginPanel;
    }

    private static JPanel passwordPanel() {
        JPanel logPanel = new JPanel();
        GroupLayout layout = new GroupLayout(logPanel);
        logPanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);
        oldPasswordLabel = new JLabel("Old Password:");
        newPasswordLabel = new JLabel("New Password:");
        oldPasswordField = new JTextField(30);
        newPasswordField = new JTextField(30);

        GroupLayout.SequentialGroup hUserGroup = layout.createSequentialGroup();
        hUserGroup.addGroup(layout.createParallelGroup()
                .addComponent(oldPasswordLabel)
                .addComponent(newPasswordLabel));
        hUserGroup.addGroup(layout.createParallelGroup()
                .addComponent(oldPasswordField)
                .addComponent(newPasswordField));
        layout.setHorizontalGroup(hUserGroup);

        GroupLayout.SequentialGroup vUserGroup = layout.createSequentialGroup();
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(oldPasswordLabel)
                .addComponent(oldPasswordField));
        vUserGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(newPasswordLabel)
                .addComponent(newPasswordField));
        layout.setVerticalGroup(vUserGroup);

        logPanel.setMaximumSize(new Dimension(220,200));

        return logPanel;
    }

    private static JPanel buttonPanel() {
        JPanel passwordButtonPanel = new JPanel();
        okayButton = new JButton("Okay");
        cancelButton =  new JButton("Cancel");
        passwordButtonPanel.add(okayButton);
        passwordButtonPanel.add(cancelButton);
        return passwordButtonPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        CreateLoginGUI();
    }
}
