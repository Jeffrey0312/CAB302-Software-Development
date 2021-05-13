package tradingPlatform;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        invokeLater(new UserGUI("Electronic Asset Trading Platform"));
    }
}