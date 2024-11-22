package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

public class Constants
{
    @Config
    public static class Claw
    {
        public static double verticalSpecimen = 0.699;
        public static double verticalIdle = 0.94;

        public static double horizontalCClock = 0.7;
        public static double horizontalClock = 0.035;
        public static double horizontalMid = 0.31;

        public static double rightClawOpen = 0.44; //TODO
        public static double rightClawClosed = 0.55;
        public static double leftClawOpen = 0.7; //TODO
        public static double leftClawClosed = 0.75;
    }

    @Config
    public static class VerticalViper
    {
        public static int maxPosition = 9000;
        public static int minPosition = 0;
    }

    @Config
    public static class IntakeVipers
    {
        public static int maxPositionRotate = 2500;
        public static int minPositionRotate = 0;

        public static int maxPositionExtend = 700;
        public static int minPositionExtend = 0;
    }

    @Config
    public static class Webcam
    {
        public static double delay = 0;
    }

    public static class Field
    {
        public static final double TILE = 24;
    }

}