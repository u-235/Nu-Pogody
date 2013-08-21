package game;

import javax.swing.Icon;

import abstractgame.GameIcons;

public class Icons extends GameIcons
{
    public static final int EGG_0             = 0;
    public static final int EGG_1             = 1;
    public static final int EGG_2             = 2;
    public static final int EGG_3             = 3;
    public static final int CRASH             = 5;
    public static final int STEP_L0           = 6;
    public static final int STEP_L1           = 7;
    public static final int STEP_L2           = 8;
    public static final int STEP_L3           = 9;
    public static final int STEP_R0           = 10;
    public static final int STEP_R1           = 11;
    public static final int STEP_R2           = 12;
    public static final int STEP_R3           = 13;
    public static final int WOLF              = 14;
    public static final int WOLF_TOP_LEFT     = 14;
    public static final int WOLF_TOP_RIGHT    = 15;
    public static final int WOLF_BOTTOM_RIGHT = 16;
    public static final int WOLF_BOTTOM_LEFT  = 17;
    public static final int BACKGRAUND        = 18;
    public static final int DIGIT_0           = 19;
    public static final int DIGIT_1           = 20;
    public static final int DIGIT_2           = 21;
    public static final int DIGIT_3           = 22;
    public static final int DIGIT_4           = 23;
    public static final int DIGIT_5           = 24;
    public static final int DIGIT_6           = 25;
    public static final int DIGIT_7           = 26;
    public static final int DIGIT_8           = 27;
    public static final int DIGIT_9           = 28;
    public static final int LEFT              = 29;
    public static final int RIGHT             = 30;
    public static final int RUBBIT            = 31;
    public static final int LIVE              = 32;

    @Override
    public Icon[] loadIcons() {
        Icon[] icons = new Icon[33];
        // TODO Auto-generated method stub
        icons[EGG_0] = getIcon("icons/egg0.gif");
        icons[EGG_1] = getIcon("icons/egg1.gif");
        icons[EGG_2] = getIcon("icons/egg2.gif");
        icons[EGG_3] = getIcon("icons/egg3.gif");
        icons[CRASH] = getIcon("icons/crash.gif");
        icons[STEP_L0] = getIcon("icons/step_l0.gif");
        icons[STEP_L1] = getIcon("icons/step_l1.gif");
        icons[STEP_L2] = getIcon("icons/step_l2.gif");
        icons[STEP_L3] = getIcon("icons/step_l3.gif");
        icons[STEP_R0] = getIcon("icons/step_r0.gif");
        icons[STEP_R1] = getIcon("icons/step_r1.gif");
        icons[STEP_R2] = getIcon("icons/step_r2.gif");
        icons[STEP_R3] = getIcon("icons/step_r3.gif");
        icons[WOLF_TOP_LEFT] = getIcon("icons/wolf-tl.gif");
        icons[WOLF_TOP_RIGHT] = getIcon("icons/wolf-tr.gif");
        icons[WOLF_BOTTOM_RIGHT] = getIcon("icons/wolf-br.gif");
        icons[WOLF_BOTTOM_LEFT] = getIcon("icons/wolf-bl.gif"); 
        icons[BACKGRAUND] = getIcon("icons/backgraund.gif");
        icons[DIGIT_0] = getIcon("icons/digit0.gif");
        icons[DIGIT_1] = getIcon("icons/digit1.gif");
        icons[DIGIT_2] = getIcon("icons/digit2.gif");
        icons[DIGIT_3] = getIcon("icons/digit3.gif");
        icons[DIGIT_4] = getIcon("icons/digit4.gif");
        icons[DIGIT_5] = getIcon("icons/digit5.gif");
        icons[DIGIT_6] = getIcon("icons/digit6.gif");
        icons[DIGIT_7] = getIcon("icons/digit7.gif");
        icons[DIGIT_8] = getIcon("icons/digit8.gif");
        icons[DIGIT_9] = getIcon("icons/digit9.gif");
        icons[LEFT] = getIcon("icons/left.gif");
        icons[RIGHT] = getIcon("icons/right.gif");
        icons[RUBBIT] = getIcon("icons/rubbit.gif");
        icons[LIVE] = getIcon("icons/live.gif");
        return icons;
    }
}
