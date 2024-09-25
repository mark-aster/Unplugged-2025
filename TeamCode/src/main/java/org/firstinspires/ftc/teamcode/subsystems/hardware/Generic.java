package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Generic implements Hardware
{
    public static DistanceSensor sensor1;
    @Override
    public void init(HardwareMap hardwareMap) {
        sensor1 = hardwareMap.tryGet(DistanceSensor.class, "sensor1");
    }
}
