package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "intakeTest" , group = "Tests")
public class IntakeTest extends LinearOpMode {
   Servo servoGheara;
   Servo servoGhearaDreapta;
   Servo servoRotit;
   ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("status: " , "initialized");
        telemetry.update();
        servoGheara = hardwareMap.get(Servo.class , "servo1");
        servoGhearaDreapta = hardwareMap.get(Servo.class , "servo2");
        servoRotit = hardwareMap.get(Servo.class , "servo3");
        waitForStart();
        update();
    }

    public void update() {

        while(opModeIsActive()) {
            telemetry.addData("RED" , colorSensor.red());
            telemetry.addData("BLUE" , colorSensor.blue());
            telemetry.addData("GREEN" , colorSensor.green());

            if(colorSensor.red() > 0.5 && colorSensor.blue() < 0.3 && colorSensor.green() < 0.3) {
                servoGheara.setPosition(0.7);
                servoRotit.setPosition(0.7);
            }
            if(colorSensor.blue() > 0.5 && colorSensor.red() < 0.3 && colorSensor.green() < 0.3) {
                servoGheara.setPosition(0.3);
                servoRotit.setPosition(0.3);

            }
            else {
                servoGheara.setPosition(0.5);
                servoRotit.setPosition(0.5);
            }

        }
    }


}
