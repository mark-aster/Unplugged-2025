package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "motor test")
public class motorTest extends OpMode {

    DcMotorEx motor;
    DcMotorEx motor_2;


    @Override
    public void init() {
        telemetry.addData("status" , "initialized");
        telemetry.update();
        motor = hardwareMap.get(DcMotorEx.class , "armLeft");
        telemetry.addData("brat stanga: " , "merge");
        telemetry.update();
        motor_2 = hardwareMap.get(DcMotorEx.class , "armRight");
        telemetry.addData("brat drept: " , "merge");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y > 0.1) {
            motor.setPower(0.5);
            motor_2.setPower(-0.5);
        }
        if(gamepad1.left_stick_y < -0.1) {
            motor.setPower(-0.5);
            motor_2.setPower(0.5);
        }
    }
}

