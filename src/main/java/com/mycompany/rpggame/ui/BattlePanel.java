package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Battle;
import com.mycompany.rpggame.core.Game;
import com.mycompany.rpggame.core.Hero;
import java.awt.*;
import java.net.URL;
import javax.swing.*;


public class BattlePanel extends JPanel {
    // All the elements that are present in the battle screen
    private final SelectionWindow selectionWindow;
    private final Game game;
    private final JTextArea messageArea = new JTextArea();
    private final JLabel heroSprite = new JLabel("", SwingConstants.CENTER);
    private final JLabel enemySprite = new JLabel("", SwingConstants.CENTER);

    private final JButton attackButton = new JButton("Attack");
    private final JButton specialButton = new JButton();
    private final JButton healButton = new JButton();
    private final JButton nextButton = new JButton("Next battle");
    private final JButton playAgainButton = new JButton("Play again");
    private final JButton restartButton = new JButton("Restart");
    private final JButton shopButton = new JButton("Shop");
    private final StatusBox heroBox = new StatusBox(true);
    private final StatusBox enemyBox = new StatusBox(false);

    private static final int IMAGE_SIZE = 150;


    public BattlePanel(SelectionWindow selectionWindow, Game game) {
        // Init the screen
        super(new BorderLayout(10, 10));
        this.selectionWindow = selectionWindow;
        this.game = game;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Arena: 4 cells (enemy box, enemy sprite, hero sprite, hero box)
        JPanel arena = new JPanel(new GridLayout(2, 2, 10, 10));
        arena.add(center(enemyBox));
        arena.add(enemySprite);
        arena.add(heroSprite);
        arena.add(center(heroBox));
        add(arena, BorderLayout.CENTER);

        // Bottom: message on the left, actions on the right
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        bottomPanel.add(messageArea);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        buttonPanel.add(attackButton);
        buttonPanel.add(specialButton);
        buttonPanel.add(healButton);
        buttonPanel.add(shopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(restartButton);
        bottomPanel.add(buttonPanel);

        bottomPanel.setPreferredSize(new Dimension(0, 150));
        add(bottomPanel, BorderLayout.SOUTH);

        // Couple the button with his action
        attackButton.addActionListener(e -> showLog(getBattle().attack()));
        specialButton.addActionListener(e -> showLog(getBattle().specialAttack()));
        healButton.addActionListener(e -> showLog(getBattle().heal()));
        nextButton.addActionListener(e -> startNextBattle());
        playAgainButton.addActionListener(e -> startReplayBattle());
        restartButton.addActionListener(e -> selectionWindow.showHeroSelection());
        shopButton.addActionListener(e -> selectionWindow.showShop());

        messageArea.setText(announceBattle());
        refresh();
    }

    private Battle getBattle() {
        return game.getCurrentBattle();
    }

    // Show the image of a fighter, or its name if there is no image yet
    private void showImage(JLabel image, String name) {
        URL url = getClass().getResource("/character/" + name + ".png");
        if (url == null) {
            image.setIcon(null);
            image.setText(name);
            return;
        }
        Image icon = new ImageIcon(url).getImage()
            .getScaledInstance(-1, IMAGE_SIZE, Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(icon));
        image.setText("");
    }

    // Keep a component at its preferred size, centered in its cell
    private JPanel center(JComponent component) {
        JPanel cell = new JPanel(new GridBagLayout());
        cell.add(component);
        return cell;
    }


    // Announce the next battle (if it's a boss or not)
    private String announceBattle() {
        String title = "Battle " + game.getBattleNumber() + " / " + game.getTotalBattles();
        if (game.isBossFight()) {
            title += " : FINAL BOSS";
        }
        return title + "\n" + getBattle().getEnemy().getName() + " appears!";
    }

    // update the state of a Hero at the end of a battle and go to the new one
    private void startNextBattle() {
        game.nextBattle();
        messageArea.setText(game.getHero().getName() + " recovers all HP\n" + announceBattle());
        refresh();
    }

    // heal the Hero and fight the same enemy again
    private void startReplayBattle() {
        game.replayBattle();
        messageArea.setText(game.getHero().getName() + " recovers all HP\n" + announceBattle());
        refresh();
    }

    // Write on the event console
    private void showLog(String log) {
        messageArea.setText(log);
        if (game.isWon()) {
            messageArea.append("You defeated the final boss, congratulations!\n");
        }
        refresh();
    }

    // Refresh the state of the game after each action
    public void refresh() {
        // update the state of the characters
        Battle battle = getBattle();
        Hero hero = battle.getHero();
        heroBox.update(hero);
        heroBox.updateXp(hero);
        enemyBox.update(battle.getEnemy());

        // show the image of each fighter
        showImage(heroSprite, hero.getName());
        showImage(enemySprite, battle.getEnemy().getName());

        //update the counter of ultimate and heal
        specialButton.setText("Special (" + hero.getUltimatesLeft() + ")");
        healButton.setText("Heal (" + hero.getHealsLeft() + ")");

        // if the game is over, it blocks the action of the player
        boolean over = battle.isOver();
        attackButton.setEnabled(!over);
        specialButton.setEnabled(!over && hero.canUseSpecialAttack());
        healButton.setEnabled(!over && hero.canHeal());
        nextButton.setVisible(game.hasNextBattle());
        playAgainButton.setVisible(game.canReplayBattle());
        restartButton.setVisible(game.isOver());
        shopButton.setVisible(battle.isWon() && !game.isOver());
    }
}
