package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motors
{
    public static DcMotor leftFront;
    public static DcMotor leftRear;
    public static DcMotor rightFront;
    public static DcMotor rightRear;

    public static DcMotorEx armLeft;
    public static DcMotorEx armRight;

    public static void init(HardwareMap hardwareMap) {
        leftFront = hardwareMap.tryGet(DcMotor.class, "leftFront");
        leftRear = hardwareMap.tryGet(DcMotor.class, "leftBack");
        rightFront = hardwareMap.tryGet(DcMotor.class, "rightFront");
        rightRear = hardwareMap.tryGet(DcMotor.class, "rightBack");

        armLeft = hardwareMap.tryGet(DcMotorEx.class, "armLeft");
        armRight = hardwareMap.tryGet(DcMotorEx.class, "armRight");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
