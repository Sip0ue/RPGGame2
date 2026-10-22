package com.mycompany.rpggame.core;

public class Shop {
    
    public static final String ITEM_1 = "Weapon Upgrade (+5 Base Dmg)";
    public static final String ITEM_2 = "Defense Upgrade (+5 Defense)";
    public static final String ITEM_3 = "Critical Strike Boost (+10% Crit)";
    public static final String ITEM_4 = "Heal (Max 3)";
    public static final String ITEM_5 = "Ultimate (Max 3)";
    
    public static final int COST_1 = 50;
    public static final int COST_2 = 50;
    public static final int COST_3 = 100;
    public static final int COST_4 = 25;
    public static final int COST_5 = 40;
    
    public static String buyItem(Hero hero, int itemChoice) {
        switch (itemChoice) {
            case 1:
                if (hero.getCoins() >= COST_1) {
                    hero.setCoins(hero.getCoins() - COST_1);
                    hero.setAttackPoint(hero.getAttackPoint() + 5);
                    return "Success! Purchased " + ITEM_1 + ".";
                }
                break;
                
            case 2:
                if (hero.getCoins() >= COST_2) {
                    hero.setCoins(hero.getCoins() - COST_2);
                    hero.setDefensePoint(hero.getDefensePoint() + 5);
                    return "Success! Purchased " + ITEM_2 + ".";
                }
                break;
                
            case 3:
                if (hero.getCoins() >= COST_3) {
                    hero.setCoins(hero.getCoins() - COST_3);
                    hero.setCritRate(hero.getCritRate() + (float) 0.10);
                    return "Success! Purchased " + ITEM_3 + ".";
                }
                break;
            
            case 4:
                if (hero.getHealsLeft() >= 3) {
                    return "You already have the maximum"
                            + " amount of Heals (3)!";
                }
                if (hero.getCoins() >= COST_4) {
                    hero.setCoins(hero.getCoins() - COST_4);
                    hero.setHealsLeft(hero.getHealsLeft() + 1);
                    return "Success! Purchased a Heal"
                            + " (Current: " + hero.getHealsLeft() + "/3)";
                }
                break;
            
            case 5:
                if (hero.getUltimatesLeft() >= 3) {
                    return "You already have the maximum"
                            + " amount of Ultimates(3)!";
                }
                if (hero.getCoins() >= COST_5) {
                    hero.setCoins(hero.getCoins() - COST_5);
                    hero.setUltimatesLeft(hero.getUltimatesLeft() + 1);
                    return "Success! Purchased an Ultimate Attack"
                            + " (Current: " + hero.getUltimatesLeft() + "/3)";
                }
                break;
            default:
                return "Invalid Choice";
        }
        
        return "Not enough gold!";
    }
}
