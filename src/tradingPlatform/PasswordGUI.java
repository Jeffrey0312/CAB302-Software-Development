package tradingPlatform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private JButton okayButton, cancelButton;
    private JLabel oldPasswordLabel, newPasswordLabel;
    private JTextField oldPasswordField, newPasswordField;
    private JFrame passwordFrame;

    TradingPlatformData data;

    public PasswordGUI(TradingPlatformData data){
        this.data = data;
        CreateLoginGUI();
        okayButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void CreateLoginGUI() {
        passwordFrame = new JFrame("Change Password");
        passwordFrame.setSize(WIDTH, HEIGHT);
        passwordFrame.setResizable(false);
        passwordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordFrame.setLayout(new BorderLayout());
        passwordFrame.add(makePasswordPanel());
        passwordFrame.setVisible(true);
    }

    private JPanel makePasswordPanel() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.add(Box.createVerticalStrut(20));
        passwordPanel.add(passwordPanel());
        passwordPanel.add(Box.createVerticalStrut(10));
        passwordPanel.add(buttonPanel());
        passwordPanel.add(Box.createVerticalStrut(20));
        return passwordPanel;
    }

    private JPanel passwordPanel() {
        JPanel passwordInfoPanel = new JPanel();
        GroupLayout layout = new GroupLayout(passwordInfoPanel);
        passwordInfoPanel.setLayout(layout);
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

        passwordInfoPanel.setMaximumSize(new Dimension(300,200));

        return passwordInfoPanel;
    }

    private JPanel buttonPanel() {
        JPanel passwordButtonPanel = new JPanel();
        okayButton = new JButton("Okay");
        cancelButton =  new JButton("Cancel");
        passwordButtonPanel.add(okayButton);
        passwordButtonPanel.add(cancelButton);
        return passwordButtonPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == okayButton) {

        } else if (source == cancelButton) {
            passwordFrame.setVisible(false);
        }
    }
}
