package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ArcherTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f, 0);
    }

    @Test
    void specialTest1() {
        Archer archer = new Archer();
        Fighter target = createTarget(50);

        int damage = archer.useSpecialAttack(target);

        // ignores DEF, no type modifier (Normal target); crit doubles damage
        assertTrue(damage == 9 || damage == 18);
        assertEquals(100 - damage, target.getHp());
    }

    @Test
    void specialTest2() {
        Archer archer = new Archer();
        Fighter target = createTarget(2);

        archer.levelUp();
        int damage = archer.useSpecialAttack(target);

        // ATK=11 after levelUp; crit doubles damage
        assertTrue(damage == 11 || damage == 22);
    }
}
