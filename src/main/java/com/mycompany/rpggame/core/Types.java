package com.mycompany.rpggame.core;

public class Types {

    public static final int NORMAL = 0;
    public static final int FIRE = 1;
    public static final int WATER = 2;
    public static final int THUNDER = 3;

    private static final String[] NAMES = {"Normal", "Fire", "Water", "Thunder"};

    public static final double[][] WEAKLIST = {
        {1, 1,   1,    1   },  // Normal: neutral
        {1, 1,   1.3,  0.8 },  // Fire:   weak to Water, strong vs Thunder
        {1, 0.8, 1,    1.3 },  // Water:  weak to Thunder, strong vs Fire
        {1, 1.3, 0.8,  1   }}; // Thunder: weak to Fire, strong vs Water

    public static String getName(int type) {
        return NAMES[type];
    }

    public static String getColor(int type) {
        return switch (type) {
            case FIRE    -> "#FF4500";
            case WATER   -> "#1E90FF";
            case THUNDER -> "#FFD700";
            default      -> "#AAAAAA";
        };
    }
}
