package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "motor test")
public class motorTest extends OpMode {

    DcMotor motor;
    DcMotor motor_2;


    @Override
    public void init() {
        telemetry.addData("status" , "initialized");
        telemetry.update();
        motor = hardwareMap.get(DcMotor.class , "brat_stanga_sus");
        telemetry.addData("brat stanga: " , "merge");
        telemetry.update();
        motor_2 = hardwareMap.get(DcMotor.class , "brat_dreapta_sus ");
        telemetry.addData("brat drept: " , "merge");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.touchpad_finger_2_y > 0.1) {
            motor.setPower(0.5);
            motor_2.setPower(0.5);
        }

        if(gamepad1.touchpad_finger_2_y < 0) {
            motor.setPower(-0.5);
            motor_2.setPower(-0.5);
        }
    }
}

