package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "intakeTest" , group = "Tests")
public class IntakeTest extends LinearOpMode {
   Servo servoGheara;
   Servo servoGhearaDreapta;
   Servo servoRotit;
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

        while(opModeIsActive()){
            if(gamepad1.b) {
                servoGheara.setPosition(0.7);
            }
            else {
                servoGheara.setPosition(0);
            }
            if(gamepad1.a) {
                servoGhearaDreapta.setPosition(0.7);
            }
            else {
                servoGhearaDreapta.setPosition(0);
            }
            if (gamepad1.x){
                servoRotit.setPosition(0.7);
            }
            else {
                servoRotit.setPosition(0);
            }
        }
    }


}
