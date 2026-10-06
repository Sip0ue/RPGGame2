package com.mycompany.rpggame.core;

public class Warrior extends Hero {
    public Warrior() {
        // Statistics of the warriors lvl 1
        super(30, 30, "Warrior", 5, 6, 1, (float) 0.5, (float) 0.05);
    }
    
    // normal attack x2
    @Override
    protected int specialAttack(Fighter target) {
        int damage = 2 * Math.max(1, getAttackPoint() - target.getDefensePoint());
        target.takeDamage(damage);
        return damage;
    }
}
