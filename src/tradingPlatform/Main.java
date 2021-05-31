package tradingPlatform;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {


    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        invokeLater(new UserGUI_1("Electronic Asset Trading Platform"));
//        //invokeLater(new LoginGUI());
//        invokeLater(new ITGUI("Electronic Asset Trading Platform"));


    }
}