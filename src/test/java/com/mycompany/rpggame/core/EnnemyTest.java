package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EnnemyTest {

    private Fighter createTarget() {
        return new Fighter(100, 100, "Victim", 1, 2, 1, 0f, 0f, 0);
    }

    @Test
    void levelTest1() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);

        assertEquals(1, goblin.getLevel());
        assertEquals(24, goblin.getHp());
        assertEquals(24, goblin.getMaxHp());
        assertEquals(6, goblin.getAttackPoint());
        assertEquals(3, goblin.getDefensePoint());
    }

    @Test
    void levelTest2() {
        Ennemy orc = new Ennemy("Orc", 2, 0);

        assertEquals(30, orc.getMaxHp());
        assertEquals(8, orc.getAttackPoint());
        assertEquals(4, orc.getDefensePoint());
    }

    @Test
    void lowHpTest1() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);

        goblin.setHp(8); // 8 / 24 = 33%, the limit is 35%

        assertTrue(goblin.isLowHp());
    }

    @Test
    void lowHpTest2() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);

        goblin.setHp(9); // 9 / 24 = 37.5%, the limit is 35%

        assertFalse(goblin.isLowHp());
    }

    @Test
    void healTest1() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);
        goblin.setHp(8);
        Fighter target = createTarget();

        String log = goblin.playTurn(target);

        assertTrue(log.contains("Goblin heals 10 HP"));
        assertEquals(18, goblin.getHp());
        assertEquals(Fighter.MAX_HEAL - 1, goblin.getHealsLeft());
        assertEquals(100, target.getHp());
    }

    @Test
    void attackTest1() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);
        Fighter target = createTarget();

        String log = goblin.playTurn(target);

        assertTrue(log.contains("Goblin attacks"));
        assertTrue(target.getHp() < 100);
        assertEquals(Fighter.MAX_HEAL, goblin.getHealsLeft());
    }

    @Test
    void attackTest2() {
        Ennemy goblin = new Ennemy("Goblin", 1, 0);
        for (int i = 0; i < Fighter.MAX_HEAL; i++) {
            goblin.heal();
        }
        goblin.setHp(8);
        Fighter target = createTarget();

        String log = goblin.playTurn(target);

        assertTrue(log.contains("Goblin attacks"));
        assertEquals(8, goblin.getHp());
        assertTrue(target.getHp() < 100);
    }
}
