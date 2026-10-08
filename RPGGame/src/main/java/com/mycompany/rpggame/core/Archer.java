package com.mycompany.rpggame.core;

public class Archer extends Hero {
    public Archer() {
        // Statistics we chose for the archer to be balanced
        //type = 1
        super(22, 22, "Archer", 9, 3, 1, (float) 1, (float) 0.05, 1);
    }
    
    // Damage that ignores the defense of the ennemy
    @Override
    protected int specialAttack(Fighter target) {
        int damage = getAttackPoint();
        target.takeDamage(damage);
        return damage;
    }
}
