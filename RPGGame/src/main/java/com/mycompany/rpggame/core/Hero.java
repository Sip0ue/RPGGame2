package com.mycompany.rpggame.core;

public abstract class Hero extends Fighter {
    
    // About ultimate attack
    public static final int MAX_ULTIMATE = 3;
    private int ultimatesLeft;
    private int xpBar;
    
    public Hero(int hp, int maxHp, String name, int attackPoint,
            int defensePoint, int level, float critDmg, float critRate, 
            int type) {
        super(hp, maxHp, name, attackPoint, defensePoint, level, critDmg, 
                critRate, type);
        this.ultimatesLeft = MAX_ULTIMATE;
        this.xpBar = 0;
    }
    // XP needed to reach the next level
    public int getXpToNextLevel() {
        return 50 * getLevel();
    }
    
    // Add xp and level up as many times as needed, return true if level up
    public boolean gainXp(int amount) {
        boolean leveledUp = false;
        xpBar += amount;
        while (xpBar >= getXpToNextLevel()) {
            xpBar -= getXpToNextLevel();
            levelUp();
            leveledUp = true;
        }
        return leveledUp;
    }


    // Up the statistics when a character level up (numbers have been modified
    //to have a balance game
    public void levelUp() {
        setMaxHp(getMaxHp() + 4);
        setHp(getHp() + 4);
        setAttackPoint(getAttackPoint() + 2);
        setDefensePoint(getDefensePoint() + 1);
        setLevel(getLevel()+1);
    }    
    
    public boolean canUseSpecialAttack() {
        return ultimatesLeft > 0;
    }
    
    // Use a special attack and update the counter of special attack
    public int useSpecialAttack(Fighter target) {
        if (!canUseSpecialAttack()) {
            return 0;
        }
        ultimatesLeft -= 1;
        return specialAttack(target);
    }
    
    // Each Hero should have a specialAttack implemented
    protected abstract int specialAttack(Fighter target);
    
    public int getUltimatesLeft() {
        return ultimatesLeft;
    }
    public int getXpBar() {
        return xpBar;
    }
    public void setXpBar(int xpBar){
        this.xpBar = xpBar;
    }
}
