package com.mycompany.rpggame;

import com.mycompany.rpggame.ui.GameWindow;
import javax.swing.SwingUtilities;

public class RPGGame {

    public static void main(String[] args) {
        // Launch the game window in a clean way (cf Swing doc)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();
            }
        });
    }
}
