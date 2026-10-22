package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BattleTest {

    private Battle createAlmostWonBattle(Hero hero, Ennemy enemy) {
        enemy.setHp(1);
        return new Battle(hero, enemy);
    }

    @Test
    void winTest1() {
        Battle battle = createAlmostWonBattle(new Archer(), new Ennemy("Goblin", 1, 0));

        String log = battle.specialAttack();

        assertTrue(battle.isWon());
        assertTrue(battle.isOver());
        assertTrue(log.contains("Goblin is defeated"));
    }

    @Test
    void winTest2() {
        Archer archer = new Archer();
        Battle battle = createAlmostWonBattle(archer, new Ennemy("Goblin", 1, 0));

        String log = battle.specialAttack();

        // (1 - 1 + 2) * 25 = 50, exactly enough for level 2
        assertTrue(log.contains("gains 50 XP"));
        assertTrue(log.contains("reaches level 2"));
        assertEquals(2, archer.getLevel());
        assertEquals(0, archer.getXpBar());
    }

    @Test
    void winTest3() {
        Archer archer = new Archer();
        archer.setLevel(3);
        Battle battle = createAlmostWonBattle(archer, new Ennemy("Goblin", 1, 0));

        String log = battle.specialAttack();

        // XP minimum is 25 (max(1, 1-3+2)*25 = 25)
        assertTrue(log.contains("gains 25 XP"));
        assertEquals(25, archer.getXpBar());
        assertEquals(3, archer.getLevel());
    }

    @Test
    void winTest4() {
        Archer archer = new Archer();
        Battle battle = createAlmostWonBattle(archer, new FinalBoss("Dragon", 4, 0));

        String log = battle.specialAttack();

        // (4 - 1 + 2) * 25 + 50 = 175
        assertTrue(log.contains("gains 175 XP"));
    }
    
    @Test
    void ennemyTurnTest1() {
        Archer archer = new Archer();
        archer.setHp(5);
        Battle battle = new Battle(archer, new Ennemy("Goblin", 1, 0));

        String log = battle.heal();

        assertTrue(log.contains("Goblin attacks"));
        assertTrue(archer.getHp() < 15);
    }

    @Test
    void winTest5() {
        Archer archer = new Archer();
        Battle battle = createAlmostWonBattle(archer, new Ennemy("Goblin", 1, 0));
        battle.specialAttack();
        int healsBefore = archer.getHealsLeft();
        int ultimatesBefore = archer.getUltimatesLeft();

        assertEquals("The battle is over\n", battle.attack());
        assertEquals("The battle is over\n", battle.heal());
        assertEquals("The battle is over\n", battle.specialAttack());
        assertEquals(healsBefore, archer.getHealsLeft());
        assertEquals(ultimatesBefore, archer.getUltimatesLeft());
    }

    @Test
    void loseTest1() {
        Archer archer = new Archer();
        Battle battle = new Battle(archer, new Ennemy("Goblin", 1, 0));

        archer.setHp(0);

        assertTrue(battle.isLost());
        assertTrue(battle.isOver());
        assertFalse(battle.isWon());
        assertEquals("The battle is over\n", battle.attack());
    }

    @Test
    void healTest1() {
        Archer archer = new Archer();
        for (int i = 0; i < Fighter.MAX_HEAL; i++) {
            archer.heal();
        }
        Battle battle = new Battle(archer, new Ennemy("Goblin", 1, 0));

        assertEquals("No heals left\n", battle.heal());
    }

    @Test
    void ultTest1() {
        Archer archer = new Archer();
        Ennemy dummy = new Ennemy("Dummy", 10, 0);
        for (int i = 0; i < Hero.MAX_ULTIMATE; i++) {
            archer.useSpecialAttack(dummy);
        }
        Battle battle = new Battle(archer, new Ennemy("Goblin", 1, 0));

        assertEquals("No special attacks left\n", battle.specialAttack());
    }
}
