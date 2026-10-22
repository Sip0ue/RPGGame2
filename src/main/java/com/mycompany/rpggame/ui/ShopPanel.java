package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Hero;
import com.mycompany.rpggame.core.Shop;
import javax.swing.*;
import java.awt.*;


public class ShopPanel extends JPanel {
    private final Hero hero;
    private final JLabel coinLabel;

    private final JButton item4Btn;
    private final JButton item5Btn;

    public ShopPanel(SelectionWindow selectionWindow, Hero hero) {
        super(new BorderLayout());
        this.hero = hero;

        JPanel topPanel = new JPanel();
        coinLabel = new JLabel("Welcome to the shop!   Coins: "
                + hero.getCoins());
        topPanel.add(coinLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        JButton item1Btn = new JButton("1. Weapon Upgrade (50 coins) "
                + "- +5 Dmg");
        JButton item2Btn = new JButton("2. Defense Upgrade "
                + "(50 coins) - +5 Def");
        JButton item3Btn = new JButton("3. Crit Rate Boost (100 coins) - "
                + "+10% Crit");

        item4Btn = new JButton();
        item5Btn = new JButton();
        updateButtonLabels();

        JButton exitBtn = new JButton("Exit Shop");

        item1Btn.addActionListener(e -> handlePurchase(1));
        item2Btn.addActionListener(e -> handlePurchase(2));
        item3Btn.addActionListener(e -> handlePurchase(3));
        item4Btn.addActionListener(e -> handlePurchase(4));
        item5Btn.addActionListener(e -> handlePurchase(5));

        // Go back to the battle screen, in the same window
        exitBtn.addActionListener(e -> selectionWindow.showBattle());

        centerPanel.add(item1Btn);
        centerPanel.add(item2Btn);
        centerPanel.add(item3Btn);
        centerPanel.add(item4Btn);
        centerPanel.add(item5Btn);
        centerPanel.add(exitBtn);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void handlePurchase(int option) {
        String result = Shop.buyItem(hero, option);
        coinLabel.setText("Welcome to the shop!   Coins: "
                + hero.getCoins());
        updateButtonLabels();
        JOptionPane.showMessageDialog(this, result);
    }

    private void updateButtonLabels() {
        item4Btn.setText("4. Heal (25 Coins) - Owned: " + hero.getHealsLeft()
                + "/3");
        item5Btn.setText("5. Ultimate (40 Coins) - Owned: "
                + hero.getUltimatesLeft()
                + "/3");
    }
}
