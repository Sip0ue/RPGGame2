package com.mycompany.rpggame.core;

public class Beast extends Hero {
    public Beast() {
        // Statistics of the beast lvl 1 (35 hp, 8 attacks, 3 defense)
        // Type = 2
        super(35, 35, "Beast", 8, 3, 1, (float) 0.8, (float) 0.05, 2);
    }
    
    // normal attack but heals the half of the damages he dealt
    @Override
    protected int specialAttack(Fighter target) {
        int damage = attack(target);
        setHp(Math.min(getMaxHp(), getHp() + damage / 2));
        return damage;
    }
}
