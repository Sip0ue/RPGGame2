package com.mycompany.rpggame.core;

public class Ennemy extends Fighter {
    public Ennemy(String name, int level) {
        // Statistics of an Ennemy depending of his level
        super(18 + 6 * level, 18 + 6 * level, name, 4 + 2 * level, 2 + level, 
                level, (float) 0.5, (float) 0.05);
    }

    // The decision an Ennemy takes depending on his hp
    public String playTurn(Fighter target) {
        if (isLowHp() && canHeal()) {
            int healed = heal();
            return getName() + " heals " + healed + " HP";
        }
        return attackTurn(target);
    }

    public boolean isLowHp() {
        return getHp() * 100 < getMaxHp() * 35;
    }

    // attack and write the log of it
    protected String attackTurn(Fighter target) {
        int damage = attack(target);
        String log = "";
        if (damage > Math.max(1, this.getAttackPoint() 
                - target.getDefensePoint())) {
            log += this.getName() + " crits!\n";
        }
        log += getName() + " attacks for " + damage + " damage";
        return log;
    }
}
