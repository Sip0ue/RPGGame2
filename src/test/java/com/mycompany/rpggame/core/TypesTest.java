package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TypesTest {


    private Fighter createAttacker(int atk, int type) {
        return new Fighter(100, 100, "Attacker", atk, 0, 1, 0f, 0f, type);
    }

    private Fighter createTarget(int def, int type) {
        return new Fighter(100, 100, "Target", 1, def, 1, 0f, 0f, type);
    }

    @Test
    void typeTest1() {
        Fighter attacker = createAttacker(10, Types.WATER);
        Fighter target = createTarget(0, Types.FIRE);

        int damage = attacker.attack(target);

        assertEquals(13, damage);
    }

    @Test
    void typeTest2() {
        Fighter attacker = createAttacker(10, Types.FIRE);
        Fighter target = createTarget(0, Types.THUNDER);

        int damage = attacker.attack(target);

        assertEquals(13, damage);
    }

    @Test
    void typeTest3() {
        Fighter attacker = createAttacker(10, Types.THUNDER);
        Fighter target = createTarget(0, Types.WATER);

        int damage = attacker.attack(target);

        assertEquals(13, damage);
    }

    @Test
    void typeTest4() {
        Fighter attacker = createAttacker(10, Types.FIRE);
        Fighter target = createTarget(0, Types.WATER);

        int damage = attacker.attack(target);

        assertEquals(8, damage);
    }

    @Test
    void typeTest5() {
        Fighter attacker = createAttacker(10, Types.FIRE);
        Fighter target = createTarget(0, Types.FIRE);

        int damage = attacker.attack(target);

        assertEquals(10, damage);
    }

    @Test
    void typeTest6() {
        Fighter attacker = createAttacker(10, Types.NORMAL);
        Fighter target = createTarget(0, Types.FIRE);

        int damage = attacker.attack(target);

        assertEquals(10, damage);
    }
}