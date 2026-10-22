package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FighterTest {

    private static final float NEVER_CRIT = 0f;
    private static final float ALWAYS_CRIT = 1f;

    private Fighter createFighter(int hp, int attack, int defense) {
        return new Fighter(hp, 30, "Victim", attack, defense, 1, 0.5f, NEVER_CRIT, 0);
    }

    @Test
    void isAliveTest1() {
        Fighter fighter = createFighter(1, 5, 2);

        assertTrue(fighter.isAlive());
    }

    @Test
    void isNAliveTest2() {
        Fighter fighter = createFighter(0, 5, 2);

        assertFalse(fighter.isAlive());
    }

    @Test
    void takeDamageTest1() {
        Fighter fighter = createFighter(30, 5, 2);

        fighter.takeDamage(12);

        assertEquals(18, fighter.getHp());
    }

    @Test
    void takeDamageTest2() {
        Fighter fighter = createFighter(30, 5, 2);

        fighter.takeDamage(100);

        assertEquals(0, fighter.getHp());
        assertFalse(fighter.isAlive());
    }

    // --- heal ---

    @Test
    void healTest1() {
        Fighter fighter = createFighter(10, 5, 2);

        int healed = fighter.heal();

        assertEquals(Fighter.HEAL_AMOUNT, healed);
        assertEquals(10 + Fighter.HEAL_AMOUNT, fighter.getHp());
        assertEquals(Fighter.MAX_HEAL - 1, fighter.getHealsLeft());
    }

    @Test
    void healTest2() {
        Fighter fighter = createFighter(25, 5, 2);

        int healed = fighter.heal();

        assertEquals(5, healed);
        assertEquals(30, fighter.getHp());
    }

    @Test
    void healTest3() {
        Fighter fighter = createFighter(1, 5, 2);

        for (int i = 0; i < Fighter.MAX_HEAL; i++) {
            fighter.heal();
        }
        int hpBefore = fighter.getHp();

        assertFalse(fighter.canHeal());
        assertEquals(0, fighter.heal());
        assertEquals(hpBefore, fighter.getHp());
    }

    @Test
    void healTest4() {
        Fighter fighter = createFighter(30, 5, 2);

        int healed = fighter.heal();

        assertEquals(0, healed);
        assertEquals(Fighter.MAX_HEAL - 1, fighter.getHealsLeft());
    }

    @Test
    void attackTest1() {
        Fighter attacker = createFighter(30, 8, 2);
        Fighter target = createFighter(30, 5, 3);

        int damage = attacker.attack(target);

        assertEquals(5, damage);
        assertEquals(25, target.getHp());
    }

    @Test
    void attackTest2() {
        Fighter attacker = createFighter(30, 2, 2);
        Fighter target = createFighter(30, 5, 10);

        int damage = attacker.attack(target);

        assertEquals(1, damage);
        assertEquals(29, target.getHp());
    }

    @Test
    void critTest1() {
        Fighter attacker = new Fighter(30, 30, "Attacker", 8, 2, 1, 0.5f, ALWAYS_CRIT, 0);
        Fighter target = createFighter(30, 5, 3);

        int damage = attacker.attack(target);

        assertEquals(9, damage);        // 8 * (1 + 0.5) - 3 = 9
        assertEquals(21, target.getHp());
    }
}
