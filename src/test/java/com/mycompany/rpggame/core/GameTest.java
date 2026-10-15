package com.mycompany.rpggame.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

    private void winCurrentBattle(Game game) {
        game.getCurrentBattle().getEnemy().setHp(0);
    }

    private void goToBossFight(Game game) {
        for (int i = 0; i < game.getTotalBattles() - 1; i++) {
            winCurrentBattle(game);
            game.nextBattle();
        }
    }

    @Test
    void gameTest1() {
        Game game = new Game(new Warrior());

        assertEquals(1, game.getBattleNumber());
        assertEquals(4, game.getTotalBattles());
        assertEquals("Goblin", game.getCurrentBattle().getEnemy().getName());
        assertFalse(game.isBossFight());
        assertFalse(game.isOver());
    }

    @Test
    void gameTest2() {
        Game game = new Game(new Warrior());

        assertFalse(game.hasNextBattle());
        assertFalse(game.canReplayBattle());
    }

    @Test
    void gameTest3() {
        Game game = new Game(new Warrior());

        winCurrentBattle(game);

        assertTrue(game.hasNextBattle());
        assertTrue(game.canReplayBattle());
        assertFalse(game.isWon());
    }

    @Test
    void gameTest4() {
        Game game = new Game(new Warrior());
        game.getHero().setHp(5);
        winCurrentBattle(game);

        game.nextBattle();

        assertEquals(2, game.getBattleNumber());
        assertEquals("Orc", game.getCurrentBattle().getEnemy().getName());
        assertEquals(game.getHero().getMaxHp(), game.getHero().getHp());
    }

    @Test
    void replayBattleTest1() {
        Game game = new Game(new Warrior());
        Ennemy defeated = game.getCurrentBattle().getEnemy();
        game.getHero().setHp(5);
        winCurrentBattle(game);

        game.replayBattle();

        Ennemy fresh = game.getCurrentBattle().getEnemy();
        assertEquals(1, game.getBattleNumber());
        assertNotSame(defeated, fresh);
        assertEquals("Goblin", fresh.getName());
        assertEquals(fresh.getMaxHp(), fresh.getHp());
        assertEquals(game.getHero().getMaxHp(), game.getHero().getHp());
        assertFalse(game.getCurrentBattle().isOver());
    }

    @Test
    void winTest1() {
        Game game = new Game(new Warrior());
        goToBossFight(game);

        winCurrentBattle(game);

        assertTrue(game.isWon());
        assertTrue(game.isOver());
        assertFalse(game.hasNextBattle());
        assertFalse(game.canReplayBattle());
    }

    @Test
    void loseTest1() {
        Game game = new Game(new Warrior());

        game.getHero().setHp(0);

        assertTrue(game.isLost());
        assertTrue(game.isOver());
        assertFalse(game.isWon());
    }
}
