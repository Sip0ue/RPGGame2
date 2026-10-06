package com.mycompany.rpggame.core;

import java.util.Random;

public class FinalBoss extends Ennemy {
    // About ultimate
    public static final int MAX_ULTIMATE = 3;
    private int ultimatesLeft;

    // We need it for the choices he did
    private static final Random RANDOM = new Random();

    public FinalBoss(String name, int level) {
        super(name, level);
        this.ultimatesLeft = MAX_ULTIMATE;
    }

    public boolean canUseUltimate() {
        return ultimatesLeft > 0;
    }

    // Special attack of the boss, 2x normal attack
    public int useUltimate(Fighter target) {
        if (!canUseUltimate()) {
            return 0;
        }
        ultimatesLeft -= 1;
        int damage = 2 * Math.max(1, getAttackPoint() - target.getDefensePoint());
        target.takeDamage(damage);
        return damage;
    }

    // use the ultimate and write it
    @Override
    protected String attackTurn(Fighter target) {
        if (canUseUltimate() && RANDOM.nextBoolean()) {
            int damage = useUltimate(target);
            return getName() + " uses its ultimate for " + damage + " damage";
        }
        return super.attackTurn(target);
    }

    public int getUltimatesLeft() {
        return ultimatesLeft;
    }
}
