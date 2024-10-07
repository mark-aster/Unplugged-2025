package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Constants;

public class Servos
{
    public static Servo intakeVertical;
    public static Servo intakeHorizontal;
    public static Servo rightClaw;
    public static Servo leftClaw;

    //TODO Change servo names to actual function names
    public static void init(HardwareMap hardwareMap) {
        intakeVertical = hardwareMap.get(Servo.class, "CH0");
        intakeHorizontal = hardwareMap.get(Servo.class, "CH1");
        rightClaw = hardwareMap.get(Servo.class, "CH3");
        leftClaw = hardwareMap.get(Servo.class, "CH2");

        Servos.intakeVertical.scaleRange(Constants.Claw.verticalIdle, Constants.Claw.verticalSpecimen);
        Servos.intakeHorizontal.scaleRange(Constants.Claw.horizontalClock, Constants.Claw.horizontalCClock);
        Servos.rightClaw.scaleRange(Constants.Claw.rightClawOpen, Constants.Claw.rightClawClosed);
        Servos.leftClaw.scaleRange(Constants.Claw.leftClawOpen, Constants.Claw.leftClawClosed);
    }
}
