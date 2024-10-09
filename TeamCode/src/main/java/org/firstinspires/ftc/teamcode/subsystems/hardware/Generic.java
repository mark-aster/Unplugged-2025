package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Generic
{
    public static DistanceSensor sensor1;
    public static IMU imu;

    public static void init(HardwareMap hardwareMap)
    {
        try
        {
            IMU imu = hardwareMap.get(IMU.class, "imu");
            // Adjust the orientation parameters to match your robot
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
            // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
            imu.initialize(parameters);

            sensor1 = hardwareMap.tryGet(DistanceSensor.class, "sensor1");
        }
        catch (Exception ignore) {}
    }
}
