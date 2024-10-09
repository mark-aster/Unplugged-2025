package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Constants
{
    @Config
    public static class Claw
    {
        public static double verticalSpecimen = 0.98;
        public static double verticalIdle = 0.65;

        public static double horizontalCClock = 0.65;
        public static double horizontalClock = 0;
        public static double horizontalMid = 0.31;

        public static double rightClawOpen = 0.1;
        public static double rightClawClosed = 0.37;
        public static double leftClawOpen = 0.5;
        public static double leftClawClosed = 0.80;
    }
    @Config
    public static class Webcam
    {
        public static double delay = 0;
    }

}