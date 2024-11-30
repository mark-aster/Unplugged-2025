package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.GoBildaPinpointDriver;

public class Sensors
{
    public static IMU imu;
    public static GoBildaPinpointDriver odo;

    public static void init(HardwareMap hardwareMap)
    {
        try
        {
            imu = hardwareMap.get(IMU.class, "imu");
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
            imu.initialize(parameters);

            odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
            odo.setOffsets(Constants.ODOMETRY_COMPUTER.X_OFFSET, Constants.ODOMETRY_COMPUTER.Y_OFFSET);
            odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
            odo.resetPosAndIMU();

        }
        catch (Exception ignore) {}
    }
}
