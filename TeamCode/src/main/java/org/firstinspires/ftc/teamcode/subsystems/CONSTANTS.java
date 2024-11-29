package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

public class CONSTANTS
{
    @Config
    public static class INTAKE_CLAW
    {
        public static final double CLAW_OPEN = 0.6;
        public static final double CLAW_CLOSED = 0.47;

        //0.6 OPEN
        //0.47 CLOSED
    }

    @Config
    public static class VERTICAL_VIPERS
    {
        public static final int MAX_POSITION = 9000;
        public static final int MIN_POSITION = 0;
    }

    @Config
    public static class INTAKE_VIPERS
    {
        public static final int MAX_POSITION_ROTATE = 2500;
        public static final int MIN_POSITION_ROTATE = 0;

        public static final int MAX_POSITION_EXTEND = 700;
        public static final int MIN_POSITION_EXTEND = 0;
    }

    @Config
    public static class WEBCAM
    {
        public static final double DELAY = 0;
    }

    public static class FIELD
    {
        public static final double TILE = 24;
    }

}