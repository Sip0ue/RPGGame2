package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FinalBossTest {

    private Fighter createTarget(int defense) {
        return new Fighter(100, 100, "Victim", 1, defense, 1, 0f, 0f, 0);
    }

    @Test
    void statsTest1() {
        FinalBoss dragon = new FinalBoss("Dragon", 4, 0);

        // hp 18 + 6 * 4, attack 4 + 2 * 4, defense 2 + 4
        assertEquals(42, dragon.getMaxHp());
        assertEquals(12, dragon.getAttackPoint());
        assertEquals(6, dragon.getDefensePoint());
        assertEquals(FinalBoss.MAX_ULTIMATE, dragon.getUltimatesLeft());
    }

    @Test
    void specialTest1() {
        FinalBoss dragon = new FinalBoss("Dragon", 4, 0);
        Fighter target = createTarget(6);

        int damage = dragon.useUltimate(target);

        // 2 * (12 - 6) = 12
        assertEquals(12, damage);
        assertEquals(88, target.getHp());
    }

    @Test
    void specialTest2() {
        FinalBoss dragon = new FinalBoss("Dragon", 4, 0);
        Fighter target = createTarget(50);

        int damage = dragon.useUltimate(target);

        // 2 * max(1, 12 - 50) = 2
        assertEquals(2, damage);
    }

    @Test
    void healTest1() {
        FinalBoss dragon = new FinalBoss("Dragon", 4, 0);
        dragon.setHp(10);
        Fighter target = createTarget(6);

        String log = dragon.playTurn(target);

        assertTrue(log.contains("Dragon heals 10 HP"));
        assertEquals(20, dragon.getHp());
        assertEquals(100, target.getHp());
    }
}
