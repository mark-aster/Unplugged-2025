package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servos implements Hardware
{
    public static Servo servo1;

    @Override
    public void init(HardwareMap hardwareMap) {
        servo1 = hardwareMap.tryGet(Servo.class, "servo1");
    }
}
