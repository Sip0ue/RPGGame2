package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArcherTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f);
    }

    @Test
    void specialTest1() {
        Archer archer = new Archer();
        Fighter target = createTarget(50);

        int damage = archer.useSpecialAttack(target);

        assertEquals(9, damage);
        assertEquals(91, target.getHp());
    }

    @Test
    void specialTest2() {
        Archer archer = new Archer();
        Fighter target = createTarget(2);

        archer.levelUp();
        int damage = archer.useSpecialAttack(target);

        assertEquals(11, damage);
    }
}
