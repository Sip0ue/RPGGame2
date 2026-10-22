package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Archer;
import com.mycompany.rpggame.core.Beast;
import com.mycompany.rpggame.core.Game;
import com.mycompany.rpggame.core.Hero;
import com.mycompany.rpggame.core.Types;
import com.mycompany.rpggame.core.Warrior;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class SelectionWindow extends JFrame {
    private Game game;
    private BattlePanel battlePanel;
    // Creation of the first window, the home menu
    
    public SelectionWindow() {
        super("BenLeViRPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        showHeroSelection();
        setVisible(true);
    }
    
    private static final Color BACKGROUND = new Color(26, 18, 8);
    private static final Color BTN_BACKGROUND = new Color(58, 34, 16);

    private static final Color BTN_FOREGROUND = new Color(240, 208, 128);

    // Show the heroes you can select
    public void showHeroSelection() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));

        URL logoUrl = getClass().getResource("/logo.png");
        if (logoUrl != null) {
            Image scaled = new ImageIcon(logoUrl).getImage()
                    .getScaledInstance(200, -1, Image.SCALE_REPLICATE);
            JLabel logoLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
            panel.add(logoLabel, BorderLayout.NORTH);
        }

        JPanel heroPanel = new JPanel(new GridLayout(3, 1, 0, 12));
        heroPanel.setOpaque(false);
        heroPanel.add(createHeroButton(new Warrior()));
        heroPanel.add(createHeroButton(new Archer()));
        heroPanel.add(createHeroButton(new Beast()));
        panel.add(heroPanel, BorderLayout.CENTER);

        setContentPane(panel);
        revalidate();
    }

    // Make the Hero selection clickable and make it start the game
    private JButton createHeroButton(Hero hero) {
        String text = "<html><center>" + hero.getName()
                + " (<font color='" + Types.getColor(hero.getType()) + "'>"
                + Types.getName(hero.getType()) + "</font>)<br>"
                + "HP " + hero.getMaxHp()
                + "  ATK " + hero.getAttackPoint()
                + "  DEF " + hero.getDefensePoint()
                + "</center></html>";
        JButton button = new JButton(text);

        URL spriteUrl = getClass().getResource("/character/" + hero.getName() + ".png");
        if (spriteUrl != null) {
            Image sprite = new ImageIcon(spriteUrl).getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(sprite));
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setVerticalTextPosition(SwingConstants.CENTER);
            button.setIconTextGap(15);
        }

        button.setBackground(BTN_BACKGROUND);
        button.setForeground(BTN_FOREGROUND);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> startGame(hero));
        return button;
    }

    // Starting the game, switching to the battle screen
    private void startGame(Hero hero) {
        game = new Game(hero);
        battlePanel = new BattlePanel(this, game);
        showScreen(battlePanel);
        // Mandatory when we change a screen
        revalidate();
    }
    
    //Open the shop in this window, replacing the battle screen
    public void showShop() {
        showScreen(new ShopPanel(this, game.getHero()));
    }
    
    //Come back from the shop to the same battle screen
    public void showBattle() {
        battlePanel.refresh();
        showScreen(battlePanel);
    }
    
    private void showScreen(JPanel screen) {
        setContentPane(screen);
        revalidate();
        repaint();
    }
}
