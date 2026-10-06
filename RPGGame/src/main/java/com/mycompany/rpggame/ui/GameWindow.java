package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Archer;
import com.mycompany.rpggame.core.Beast;
import com.mycompany.rpggame.core.Game;
import com.mycompany.rpggame.core.Hero;
import com.mycompany.rpggame.core.Warrior;
import java.awt.*;
import javax.swing.*;

public class GameWindow extends JFrame {

    // Creation of the first window, the home menu
    public GameWindow() {
        super("BenLeViRPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        showHeroSelection();
        setVisible(true);
    }
    
    // Show the heroes you can select
    public void showHeroSelection() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(new JLabel("Choose your hero", SwingConstants.CENTER));
        panel.add(createHeroButton(new Warrior()));
        panel.add(createHeroButton(new Archer()));
        panel.add(createHeroButton(new Beast()));

        setContentPane(panel);
        revalidate();
    }

    // Make the Hero selection clickable and make it start the game
    private JButton createHeroButton(Hero hero) {
        String text = hero.getName()
                + "   HP " + hero.getMaxHp()
                + "   ATK " + hero.getAttackPoint()
                + "   DEF " + hero.getDefensePoint();
        JButton button = new JButton(text);
        button.addActionListener(e -> startGame(hero));
        return button;
    }

    // Starting the game, switching to the battle screen
    private void startGame(Hero hero) {
        setContentPane(new BattlePanel(this, new Game(hero)));
        // Mandatory when we change a screen
        revalidate();
    }
}
