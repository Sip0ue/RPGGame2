package com.mycompany.rpggame.core;

import java.util.Random;

public class Fighter {
    private static final Random RANDOM = new Random();
    // About healing
    public static final int MAX_HEAL = 3;
    private int healsLeft;
    public static final int HEAL_AMOUNT = 10;
    
    // About statistics of a character
    private int hp;
    private int maxHp;
    private int attackPoint;
    private int defensePoint;
    private float critDmg;
    private float critRate;
    
    // Other attributes
    private String name;
    private int level;
    private int type;
    
    public Fighter(int hp, int maxHp, String name, int attackPoint, 
        int defensePoint, int level, float critDmg, float critRate, int type) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.name = name;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
        this.level = level;
        this.healsLeft = MAX_HEAL;
        this.critDmg = critDmg;
        this.critRate = critRate;
        this.type = type;
    }
    
    public boolean isAlive() {
        return hp > 0;
    }

    // Deal damages to an ennemy
    public int attack(Fighter target) {
        float damage = Math.max(1, attackPoint 
                * calcTypeMult(target.getType()) 
                * calcCrit() 
                - target.defensePoint);
        target.takeDamage((int) damage);
        return (int) damage;
    }
    
    private boolean isCrit(float critRate) {
        float critNeed = RANDOM.nextFloat();
        return (critRate >= critNeed);
    }
    
    public float calcCrit() {
        boolean isCrit = isCrit(critRate);
        if (isCrit) {
            return (1 + critDmg);
        } else {
            return 1;
        }
    }
    
    public float calcTypeMult(int enemyType) {
        return (float) Types.WEAKLIST[enemyType][type];
    }

    // Taking damages, hp can't go under 0
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    public boolean canHeal() {
        return healsLeft > 0;
    }

    // Using the healing spell (stop at maxHp)
    public int heal() {
        if (!canHeal()) {
            return 0;
        }
        healsLeft -= 1;
        int newHp = Math.min(maxHp, hp + HEAL_AMOUNT);
        int healed = newHp - hp;
        hp = newHp;
        return healed;
    }

    // Getters & Setters
    public int getHealsLeft() {
        return healsLeft;
    }
   

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
