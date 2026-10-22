package com.mycompany.rpggame.core;

public class Archer extends Hero {
    public Archer() {
        // Statistics we chose for the archer to be balanced
        super(22, 22, "Archer", 9, 3, 1, (float) 1, (float) 0.05, Types.FIRE);
    }
    
    // Ignores defense, but uses type and crit
    @Override
    protected int specialAttack(Fighter target) {
        int damage = (int) Math.max(1, getAttackPoint()
                * calcTypeMult(target.getType())
                * calcCrit());
        target.takeDamage(damage);
        return damage;
    }
}
