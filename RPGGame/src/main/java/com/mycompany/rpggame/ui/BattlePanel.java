package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Battle;
import com.mycompany.rpggame.core.Fighter;
import com.mycompany.rpggame.core.Game;
import com.mycompany.rpggame.core.Hero;
import java.awt.*;
import javax.swing.*;

public class BattlePanel extends JPanel {
    // All the elements that are present in the battle screen
    private final GameWindow window;
    private final Game game;
    private final JLabel heroLabel = new JLabel();
    private final JLabel enemyLabel = new JLabel();
    private final JTextArea logArea = new JTextArea();
    private final JButton attackButton = new JButton("Attack");
    private final JButton specialButton = new JButton();
    private final JButton healButton = new JButton();
    private final JButton nextButton = new JButton("Next battle");
    private final JButton restartButton = new JButton("Restart");

    public BattlePanel(GameWindow window, Game game) {
        // Init the screen
        super(new BorderLayout(10, 10));
        this.window = window;
        this.game = game;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the state of the battle
        JPanel statsPanel = new JPanel(new GridLayout(2, 1));
        statsPanel.add(heroLabel);
        statsPanel.add(enemyLabel);
        add(statsPanel, BorderLayout.NORTH);

        // Add the event console
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Add the button with their actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(specialButton);
        buttonPanel.add(healButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(restartButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Couple the button with his action
        attackButton.addActionListener(e -> showLog(getBattle().attack()));
        specialButton.addActionListener(e -> showLog(getBattle().specialAttack()));
        healButton.addActionListener(e -> showLog(getBattle().heal()));
        nextButton.addActionListener(e -> startNextBattle());
        restartButton.addActionListener(e -> window.showHeroSelection());

        announceBattle();
        refresh();
    }

    private Battle getBattle() {
        return game.getCurrentBattle();
    }

    // Announce the next battle (if it's a boss or not)
    private void announceBattle() {
        String title = "--- Battle " + game.getBattleNumber() + " / " + game.getTotalBattles();
        if (game.isBossFight()) {
            title += " : FINAL BOSS";
        }
        logArea.append(title + " ---\n");
        logArea.append(getBattle().getEnemy().getName() + " appears!\n");
    }

    // update the state of a Hero at the end of a battle and go to the new one
    private void startNextBattle() {
        game.nextBattle();
        logArea.append("\n" + game.getHero().getName() + " reaches level "
                + game.getHero().getLevel() + " and recovers all HP\n");
        announceBattle();
        refresh();
    }

    // Write on the event console
    private void showLog(String log) {
        logArea.append(log);
        if (game.isWon()) {
            logArea.append("You defeated the final boss, congratulations!\n");
        }
        refresh();
    }

    // Refresh the state of the game after each action
    private void refresh() {
        // update the state of the characters
        Battle battle = getBattle();
        Hero hero = battle.getHero();
        heroLabel.setText(describe(hero));
        enemyLabel.setText(describe(battle.getEnemy()));
        
        //update the counter of ultimate and heal
        specialButton.setText("Special (" + hero.getUltimatesLeft() + ")");
        healButton.setText("Heal (" + hero.getHealsLeft() + ")");

        // if the game is over, it blocks the action of the player
        boolean over = battle.isOver();
        attackButton.setEnabled(!over);
        specialButton.setEnabled(!over && hero.canUseSpecialAttack());
        healButton.setEnabled(!over && hero.canHeal());
        nextButton.setVisible(game.hasNextBattle());
        restartButton.setVisible(game.isOver());
    }

    // return the name, level, and hp of a Fighter
    private String describe(Fighter fighter) {
        return fighter.getName() + " (level " + fighter.getLevel() + ")   HP "
                + fighter.getHp() + " / " + fighter.getMaxHp();
    }
}
