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
    float critRate;
    
    // Other attributes
    private String name;
    private int level;
    private int type;
    private boolean lastAttackWasCrit = false;

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
    
    protected float calcCrit() {
        lastAttackWasCrit = isCrit(critRate);
        return lastAttackWasCrit ? (1 + critDmg) : 1;
    }

    public boolean wasLastAttackCrit() {
        return lastAttackWasCrit;
    }
    
    protected float calcTypeMult(int enemyType) {
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
    
    public void setHealsLeft(int healsLeft) {
        this.healsLeft = Math.min(healsLeft, MAX_HEAL);
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
}
