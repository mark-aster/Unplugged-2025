package subsystems;

import com.acmerobotics.dashboard.config.Config;

@Config
public final class Constants
{
    @Config
    public static class TELEOP
    {
        public static double INITIAL_CHASSIS_SPEED = 0.5;
        public static double VERTICAL_VIPER_SPEED = 1000;
        public static double HORIZONTAL_VIPER_SPEED = 1000;
        public static double ROTATION_VIPER_SPEED = 1000;
    }

    public static class INTAKE_CLAW
    {
        public static double OPEN = 0.52;
        public static double CLOSED = 0.35;

        public static double HORIZONTAL_MIN = 0.26;
        public static double HORIZONTAL_MAX = 0.82;

        public static double VERTICAL_MIN = 0;
        public static double VERTICAL_MAX = 1;
    }

    public static class VERTICAL_VIPERS
    {
        public static int MAX_POSITION = 2000;
        public static int MIN_POSITION = 0;
    }

    public static class INTAKE_VIPERS
    {
        public static int MAX_POSITION_ROTATE = 2500;
        public static int MIN_POSITION_ROTATE = 0;

        public static int MAX_POSITION_EXTEND = 2200;
        public static int MIN_POSITION_EXTEND = 0;
    }
}
