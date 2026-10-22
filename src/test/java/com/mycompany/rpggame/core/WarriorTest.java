package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WarriorTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f, 0);
    }

    private Fighter createTarget(int defense, int type) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f, type);
    }

    @Test
    void specialTest() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(2);

        int damage = warrior.useSpecialAttack(target);

        // attack() = max(1, 7 - 2) = 5, then same damage again = 10 total
        assertEquals(10, damage);
        assertEquals(90, target.getHp());
    }

    @Test
    void specialTest2() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(50);

        int damage = warrior.useSpecialAttack(target);

        // attack() = max(1, 7 - 50) = 1, then same damage again = 2 total
        assertEquals(2, damage);
        assertEquals(98, target.getHp());
    }

    @Test
    void specialTest3() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(0, Types.WATER);

        int damage = warrior.useSpecialAttack(target);

        assertTrue(damage == 18 || damage == 26);
    }

    @Test
    void specialTest4() {
        Warrior warrior = new Warrior();
        Fighter target = createTarget(0, Types.FIRE);

        int damage = warrior.useSpecialAttack(target);

        assertTrue(damage == 10 || damage == 16);
    }
}
