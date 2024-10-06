package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Generic
{
    public static DistanceSensor sensor1;

    public static void init(HardwareMap hardwareMap) {
        sensor1 = hardwareMap.tryGet(DistanceSensor.class, "sensor1");
    }
}
