package com.mycompany.rpggame.core;

public class Game {
    private final Hero hero;
    private final Ennemy[] enemies;
    private int currentIndex;
    private Battle currentBattle;

    // Creation of the game, we want 3 normal Ennemies and 1 Final Boss
    public Game(Hero hero) {
        this.hero = hero;
        this.enemies = new Ennemy[] {
            new Ennemy("Goblin", 1),
            new Ennemy("Orc", 2),
            new Ennemy("Troll", 3),
            new FinalBoss("Dragon", 4)
        };
        this.currentIndex = 0;
        this.currentBattle = new Battle(hero, enemies[0]);
    }

    // The player can fight the same enemy again after a win (not the boss)
    public boolean canReplayBattle() {
        return currentBattle.isWon() && !isBossFight();
    }

    public boolean hasNextBattle() {
        if (currentBattle.isWon()) {
            return currentIndex < enemies.length - 1;
        }
        return false;
    }
    
    // Fight a fresh copy of the current enemy to earn more xp
    public void replayBattle() {
        if (!canReplayBattle()) {
            return;
        }
        hero.setHp(hero.getMaxHp());
        Ennemy previous = enemies[currentIndex];
        currentBattle = new Battle(hero, new Ennemy(previous.getName(), previous.getLevel()));
    }


    // Launch the next battle if there is
    public void nextBattle() {
        if (!hasNextBattle()) {
            return;
        }
        hero.setHp(hero.getMaxHp());
        currentIndex += 1;
        currentBattle = new Battle(hero, enemies[currentIndex]);
    }
    
    // Utils functions, getters & setters

    public boolean isBossFight() {
        return currentIndex == enemies.length - 1;
    }

    public boolean isWon() {
        return isBossFight() && currentBattle.isWon();
    }

    public boolean isLost() {
        return currentBattle.isLost();
    }

    public boolean isOver() {
        return isWon() || isLost();
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }

    public Hero getHero() {
        return hero;
    }

    public int getBattleNumber() {
        return currentIndex + 1;
    }

    public int getTotalBattles() {
        return enemies.length;
    }
}
