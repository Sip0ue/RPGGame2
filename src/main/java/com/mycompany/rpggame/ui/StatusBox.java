package com.mycompany.rpggame.ui;

import com.mycompany.rpggame.core.Fighter;
import com.mycompany.rpggame.core.Hero;
import java.awt.*;
import javax.swing.*;

public class StatusBox extends JPanel {
    // Elements of the box
    private final JLabel nameLabel = new JLabel();
    private final JLabel levelLabel = new JLabel();
    private final JProgressBar hpBar = new JProgressBar();
    private final JLabel hpText = new JLabel("", SwingConstants.RIGHT);
    private final JProgressBar xpBar = new JProgressBar();

    // The hero box shows more details (hp numbers and xp)
    public StatusBox(boolean isHeroBox) {
        super(new GridLayout(0, 1, 0, 4));
        setBackground(new Color(248, 248, 216));
        setBorder(BorderFactory.createCompoundBorder(
                // Black
                BorderFactory.createLineBorder(new Color(64, 64, 64), 3),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        // First line: name on the left, level on the right
        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setOpaque(false);
        titleRow.add(nameLabel, BorderLayout.WEST);
        titleRow.add(levelLabel, BorderLayout.EAST);
        add(titleRow);

        add(createBarRow("HP", hpBar));

        if (isHeroBox) {
            add(hpText);
            xpBar.setForeground(new Color(64, 144, 248));
            add(createBarRow("XP", xpBar));
        }
        setPreferredSize(new Dimension(280, isHeroBox ? 120 : 70));
    }

    // A line with a small title on the left and a bar filling the rest
    private JPanel createBarRow(String title, JProgressBar bar) {
        JPanel row = new JPanel(new BorderLayout(6, 0));
        row.setOpaque(false);
        row.add(new JLabel(title), BorderLayout.WEST);
        row.add(bar, BorderLayout.CENTER);
        return row;
    }

    // Update name, level and hp bar
    public void update(Fighter fighter) {
        nameLabel.setText(fighter.getName());
        levelLabel.setText("Lv." + fighter.getLevel());
        hpBar.setMaximum(fighter.getMaxHp());
        hpBar.setValue(fighter.getHp());
        hpBar.setForeground(hpColor(fighter));
        hpText.setText(fighter.getHp() + " / " + fighter.getMaxHp());
    }

    // Update the xp bar (only for the hero box)
    public void updateXp(Hero hero) {
        xpBar.setMaximum(hero.getXpToNextLevel());
        xpBar.setValue(hero.getXpBar());
    }

    // Green above 50%, yellow above 20%, red below
    private Color hpColor(Fighter fighter) {
        int percent = fighter.getHp() * 100 / fighter.getMaxHp();
        if (percent > 50) {
            // Green
            return new Color(72, 200, 80);
        }
        if (percent > 20) {
            // Orange
            return new Color(240, 200, 40);
        }
        // Red
        return new Color(232, 64, 48);
    }
}
