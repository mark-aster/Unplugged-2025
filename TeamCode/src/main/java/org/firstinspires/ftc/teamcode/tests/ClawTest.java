package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Input;

@TeleOp(name = "Claw Test", group = "Tests")
public class ClawTest extends LinearOpMode {

    Servo intakeVertical;
    Servo intakeHorizontal;
    Servo rightClaw;
    Servo leftClaw;

    @Override
    public void runOpMode() throws InterruptedException {

        //Debug.init();

        intakeVertical = hardwareMap.get(Servo.class, "CH0");
        intakeHorizontal = hardwareMap.get(Servo.class, "CH1");
        rightClaw = hardwareMap.get(Servo.class, "CH3");
        leftClaw = hardwareMap.get(Servo.class, "CH2");

        double verticalSpecimen = 0.98;
        double verticalIdle = 0.65;

        double horizontalCClock = 0.65;
        double horizontalClock = 0;
        double horizontalMid = 0.31;

        double rightClawOpen = 0.1;
        double rightClawClosed = 0.37;
        double leftClawOpen = 0.3;
        double leftClawClosed = 0.62;

        waitForStart();
        while(opModeIsActive())
        {
            if(gamepad1.left_stick_y < -0.1)
            {
                intakeVertical.setPosition(verticalSpecimen);
            }
            if(gamepad1.left_stick_y > 0.1)
            {
                intakeVertical.setPosition(verticalIdle);
            }

            if(gamepad1.right_stick_y < -0.1)
            {
                intakeHorizontal.setPosition(horizontalMid);
            }
            if(gamepad1.right_stick_x < -0.1)
            {
                intakeHorizontal.setPosition(horizontalCClock);
            }
            if(gamepad1.right_stick_x > 0.1)
            {
                intakeHorizontal.setPosition(horizontalClock);
            }

            if(Input.onKeyDown("a", gamepad1.a))
            {
                rightClaw.setPosition(rightClawOpen);
                leftClaw.setPosition(leftClawOpen);
            }
            if(Input.onKeyDown("b", gamepad1.b))
            {
                rightClaw.setPosition(rightClawClosed);
                leftClaw.setPosition(leftClawClosed);
            }
        }
    }
}
