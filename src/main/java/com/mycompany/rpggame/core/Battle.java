package com.mycompany.rpggame.core;

public class Battle {
    private final Hero hero;
    private final Ennemy enemy;

    public Battle(Hero hero, Ennemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    // Attack and write it 
    public String attack() {
        if (isOver()) {
            return "The battle is over\n";
        }
        int damage = hero.attack(enemy);
        String log = "";
        if (damage > Math.max(1, hero.getAttackPoint() 
                - enemy.getDefensePoint())) {
            log += hero.getName() + " crits!\n";
        }
        log += hero.getName() + " attacks for " + damage + " damage\n";
        log += endTurn();
        return log;
    }

    // use a special attack and write it 
    public String specialAttack() {
        if (isOver()) {
            return "The battle is over\n";
        }
        if (!hero.canUseSpecialAttack()) {
            return "No special attacks left\n";
        }
        int damage = hero.useSpecialAttack(enemy);
        String log = hero.getName() + " uses a special attack for " + damage + " damage\n";
        log += endTurn();
        return log;
    }

    // heal and write it
    public String heal() {
        if (isOver()) {
            return "The battle is over\n";
        }
        if (!hero.canHeal()) {
            return "No heals left\n";
        }
        int healed = hero.heal();
        String log = hero.getName() + " heals " + healed + " HP\n";
        log += endTurn();
        return log;
    }

    // Check if the game is over and if not, the ennemy plays
    private String endTurn() {
        String log = "";
        if (enemy.isAlive()) {
            log += enemy.playTurn(hero) + "\n";
        }

        if (isWon()) {
            // Xp Gain System
            log += enemy.getName() + " is defeated, you win!\n";
            int xpWon = (Math.max(0, enemy.getLevel() - hero.getLevel()  
                    + 2)) * 25;
            if (enemy instanceof FinalBoss) {
                xpWon += 50;
            }
            log += hero.getName() + " gains " + xpWon + " XP\n";
            if (hero.gainXp(xpWon)) {
                log += hero.getName() + " reaches level " + hero.getLevel() 
                        + "!\n";
            }

        } else if (isLost()) {
            log += hero.getName() + " is defeated, game over\n";
        }
        return log;
    }

    // Utils functions
    public boolean isWon() {
        return !enemy.isAlive();
    }

    public boolean isLost() {
        return !hero.isAlive();
    }

    public boolean isOver() {
        return isWon() || isLost();
    }

    public Hero getHero() {
        return hero;
    }

    public Ennemy getEnemy() {
        return enemy;
    }
}
