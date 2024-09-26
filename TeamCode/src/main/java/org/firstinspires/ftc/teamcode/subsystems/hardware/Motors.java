package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motors implements Hardware
{
    public static DcMotor leftFront;
    public static DcMotor leftRear;
    public static DcMotor rightFront;
    public static DcMotor rightRear;

    @Override
    public void init(HardwareMap hardwareMap) {
        leftFront = hardwareMap.tryGet(DcMotor.class, "leftFront");
        leftRear = hardwareMap.tryGet(DcMotor.class, "leftBack");
        rightFront = hardwareMap.tryGet(DcMotor.class, "rightFront");
        rightRear = hardwareMap.tryGet(DcMotor.class, "rightBack");
    }
}
