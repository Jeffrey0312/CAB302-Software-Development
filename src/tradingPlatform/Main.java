package tradingPlatform;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //invokeLater(new UserGUI("Electronic Asset Trading Platform"));
        //invokeLater(new LoginGUI());
        //invokeLater(new PasswordGUI());
        invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    private static void createAndShowGUI() {
        new LoginGUI(
                new TradingPlatformData(
                        new NetworkDataSource()
                )
        );
    }

}