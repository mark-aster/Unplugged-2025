package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "Intake Test TeleOp", group = "Tuning")
public class IntakeTest extends LinearOpMode {


    private Servo clawServo1, clawServo2, liftServo, rotateServo, orientationServo;


    private ColorSensor colorSensor;



    public void runOpMode() {
        clawServo1 = hardwareMap.get(Servo.class, "claw1");
        clawServo2 = hardwareMap.get(Servo.class, "claw2");
        liftServo = hardwareMap.get(Servo.class, "lift");
        rotateServo = hardwareMap.get(Servo.class, "rotate");
        orientationServo = hardwareMap.get(Servo.class, "orientation");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");


        clawServo1.setPosition(0.0); // Open claw
        clawServo2.setPosition(0.0); // Open claw
        liftServo.setPosition(0.0); // Lower lift
        rotateServo.setPosition(0.0); // Neutral rotation position
        orientationServo.setPosition(0.5); // Neutral orientation

        waitForStart();

        while (opModeIsActive()) {


            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();


            telemetry.addData("Color Sensor", "Red: %d, Green: %d, Blue: %d", red, green, blue);


            if (gamepad1.a) {
                closeClaw();
            } else if (gamepad1.b) {
                releaseClaw();
            }


            if (gamepad1.left_trigger > 0.1) {
                liftObject();
            } else if (gamepad1.right_trigger > 0.1) {
                lowerLift();
            }


            if (gamepad1.left_stick_x > 0.1) {
                rotateObject(1);  // Rotate right
            } else if (gamepad1.left_stick_x < -0.1) {
                rotateObject(-1);  // Rotate left
            }


            if (gamepad1.dpad_up) {
                orientationServo.setPosition(0.0);  // Move orientation to the up position
            } else if (gamepad1.dpad_down) {
                orientationServo.setPosition(1.0);  // Move orientation to the down position
            } else {
                orientationServo.setPosition(0.5);  // Neutral position
            }

            telemetry.update();
        }
    }


    private void closeClaw() {
        clawServo1.setPosition(0.5);  // todo: de optimizat pentru actual parameters!
        clawServo2.setPosition(0.5);
    }

    private void releaseClaw() {
        clawServo1.setPosition(0.0);
        clawServo2.setPosition(0.0);
    }

    private void liftObject() {
        liftServo.setPosition(0.8);
    }

    private void lowerLift() {
        liftServo.setPosition(0.0);
    }

    private void rotateObject(int direction) {
        if (direction > 0) {
            rotateServo.setPosition(0.8);
        } else {
            rotateServo.setPosition(0.2);
        }
    }
}
