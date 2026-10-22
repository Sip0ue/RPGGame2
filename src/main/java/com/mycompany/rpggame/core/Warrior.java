package com.mycompany.rpggame.core;

public class Warrior extends Hero {
    public Warrior() {
        // Statistics of the warriors lvl 1
        super(30, 30, "Warrior", 7, 5, 1, (float) 0.5, (float) 0.05, Types.THUNDER);
    }
    
    // normal attack x2, with type and crit
    @Override
    protected int specialAttack(Fighter target) {
        int hit = attack(target);
        target.takeDamage(hit);
        return hit * 2;
    }
}
