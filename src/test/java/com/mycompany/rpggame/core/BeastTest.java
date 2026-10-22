package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BeastTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f, 0);
    }

    @Test
    void specialTest1() {
        Beast beast = new Beast();
        beast.setHp(10);
        Fighter target = createTarget(2);

        int damage = beast.useSpecialAttack(target);

        assertEquals(10 + damage / 2, beast.getHp());
    }

    @Test
    void specialTest2() {
        Beast beast = new Beast();
        Fighter target = createTarget(2);

        beast.useSpecialAttack(target);

        assertEquals(beast.getMaxHp(), beast.getHp());
    }
}
