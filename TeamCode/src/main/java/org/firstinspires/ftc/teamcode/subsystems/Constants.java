package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;

@Config
public final class Constants
{
    @Config
    public static class TELEOP
    {
        public static double INITIAL_CHASSIS_SPEED = 0.5;
        public static double VERTICAL_VIPER_SPEED = 100;
        public static double HORIZONTAL_VIPER_SPEED = 100;
        public static double ROTATION_VIPER_SPEED = 100;
    }

    public static class ODOMETRY_COMPUTER
    {
        public static double X_OFFSET = 142;
        public static double Y_OFFSET = 32;
    }

    public static class INTAKE_CLAW
    {
        public static double OPEN = 0.6;
        public static double CLOSED = 0.47;

        public static double HORIZONTAL_MIN = 0.525;
        public static double HORIZONTAL_MAX = 1;

        public static double VERTICAL_MIN = 0;
        public static double VERTICAL_MAX = 1;
    }

    public static class VERTICAL_VIPERS
    {
        public static int MAX_POSITION = 1700;
        public static int MIN_POSITION = 0;
    }

    public static class INTAKE_VIPERS
    {
        public static int MAX_POSITION_ROTATE = 2500;
        public static int MIN_POSITION_ROTATE = 0;

        public static int MAX_POSITION_EXTEND = 700;
        public static int MIN_POSITION_EXTEND = 0;
    }

    public static class FIELD
    {
        public static double TILE = 24;
    }
}
