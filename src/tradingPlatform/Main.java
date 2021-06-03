package tradingPlatform;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
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