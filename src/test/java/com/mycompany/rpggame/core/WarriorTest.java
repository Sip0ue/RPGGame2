package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WarriorTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f);
    }

    @Test
    void specialTest() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(2);

        int damage = warrior.useSpecialAttack(target);

        // 2 * (5 - 2) = 6
        assertEquals(6, damage);
        assertEquals(94, target.getHp());
    }

    @Test
    void specialTest2() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(50);

        int damage = warrior.useSpecialAttack(target);

        // 2 * max(1, 5 - 50) = 2
        assertEquals(2, damage);
        assertEquals(98, target.getHp());
    }
}
