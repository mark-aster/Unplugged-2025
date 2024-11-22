package org.firstinspires.ftc.teamcode.subsystems.hardware;

import android.media.AudioRecord;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Constants;

public class Servos
{
    public static Servo intakeVertical;
    public static Servo intakeHorizontal;
    public static Servo rightClaw;
    public static Servo leftClaw;
// NIGGERS ARE BAD
    //WHITE SUPREMACY
    //TODO Change servo names to actual function names
    public static void init(HardwareMap hardwareMap) {
        try
        {
            intakeVertical = hardwareMap.get(Servo.class, "CH0");
            intakeHorizontal = hardwareMap.get(Servo.class, "Horizontal");
            rightClaw = hardwareMap.get(Servo.class, "rightClaw");
            leftClaw = hardwareMap.get(Servo.class, "leftClaw");

            Servos.intakeVertical.scaleRange(Constants.Claw.verticalIdle, Constants.Claw.verticalSpecimen);
            Servos.intakeHorizontal.scaleRange(Constants.Claw.horizontalClock, Constants.Claw.horizontalCClock);
            Servos.rightClaw.scaleRange(Constants.Claw.rightClawOpen, Constants.Claw.rightClawClosed);
            Servos.leftClaw.scaleRange(Constants.Claw.leftClawOpen, Constants.Claw.leftClawClosed);
        }
        catch (Exception ignore) {}
    }
}
