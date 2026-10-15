package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeroTest {

    @Test
    void gainXpTest1() {
        Hero hero = new Warrior();

        boolean leveledUp = hero.gainXp(30);

        assertFalse(leveledUp);
        assertEquals(1, hero.getLevel());
        assertEquals(30, hero.getXpBar());
    }

    @Test
    void gainXpTest2() {
        Hero hero = new Warrior();

        boolean leveledUp = hero.gainXp(70); // 50 needed at level 1

        assertTrue(leveledUp);
        assertEquals(2, hero.getLevel());
        assertEquals(20, hero.getXpBar());
    }
    
    @Test
    void gainXpTest3() {
        Hero hero = new Warrior();

        boolean leveledUp = hero.gainXp(150);

        assertTrue(leveledUp);
        assertEquals(3, hero.getLevel());
        assertEquals(0, hero.getXpBar());
    }
    
    @Test
    void levelUpTest1() {
        Hero hero = new Warrior();
        
        hero.gainXp(50);
        
        assertEquals(34, hero.getHp());
        assertEquals(7, hero.getDefensePoint());
        assertEquals(7, hero.getAttackPoint());

    }

    @Test
    void noSpecialAttackLeftAfterThreeUses() {
        Hero hero = new Warrior();
        Ennemy target = new Ennemy("Goblin", 10);

        for (int i = 0; i < Hero.MAX_ULTIMATE; i++) {
            hero.useSpecialAttack(target);
        }
        int hpBefore = target.getHp();

        assertFalse(hero.canUseSpecialAttack());
        assertEquals(0, hero.getUltimatesLeft());
        assertEquals(0, hero.useSpecialAttack(target));
        assertEquals(hpBefore, target.getHp());
    }
}
