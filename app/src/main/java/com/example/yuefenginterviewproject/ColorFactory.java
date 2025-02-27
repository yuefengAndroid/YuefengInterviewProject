package com.example.yuefenginterviewproject;

import android.graphics.Color;

public class ColorFactory {

    /**
     *                      ============= --border color
     *  background color---||-  Text --||--text color
     *                     =============
     */

    public static final String BG_COLOR_ALPHA = "33";
    public static final String BD_COLOR_ALPHA = "88";

    public static final String RED = "F44336";
    public static final String LIGHTBLUE = "03A9F4";
    public static final String AMBER = "FFC107";
    public static final String ORANGE = "FF9800";
    public static final String YELLOW = "FFEB3B";
    public static final String LIME = "CDDC39";
    public static final String BLUE = "2196F3";
    public static final String INDIGO = "3F51B5";
    public static final String LIGHTGREEN = "8BC34A";
    public static final String GREY = "9E9E9E";
    public static final String DEEPPURPLE = "673AB7";
    public static final String TEAL = "009688";
    public static final String CYAN = "00BCD4";

    public enum PURE_COLOR{CYAN, TEAL}

    public static final int NONE = -1;
    public static final int RANDOM = 0;
    public static final int PURE_CYAN = 1;
    public static final int PURE_TEAL = 2;

    public static final int SHARP666666 = Color.parseColor("#FF666666");
    public static final int SHARP727272 = Color.parseColor("#FF727272");

    private static final String[] COLORS = new String[]{RED, LIGHTBLUE, AMBER, ORANGE, YELLOW,
            LIME, BLUE, INDIGO, LIGHTGREEN, GREY, DEEPPURPLE, TEAL, CYAN};

    public static int[] onRandomBuild(){
        int random = (int)(Math.random() * COLORS.length);
        int bgColor = Color.parseColor("#" + BG_COLOR_ALPHA + COLORS[random]);
        int bdColor = Color.parseColor("#" + BD_COLOR_ALPHA + COLORS[random]);
        int tColor = SHARP666666;
        int tColor2 = SHARP727272;
        return new int[]{bgColor, bdColor, tColor, tColor2};
    }

    public static int[] onPureBuild(PURE_COLOR type){
        String color = type == PURE_COLOR.CYAN ? CYAN : TEAL;
        int bgColor = Color.parseColor("#" + BG_COLOR_ALPHA + color);
        int bdColor = Color.parseColor("#" + BD_COLOR_ALPHA + color);
        int tColor = SHARP727272;
        int tColor2 = SHARP666666;
        return new int[]{bgColor, bdColor, tColor, tColor2};
    }

}