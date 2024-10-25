package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "intakeTest" , group = "Tests")
public class IntakeTest extends LinearOpMode {
   Servo servo1;
   Servo servo2;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("status: " , "initialized");
        telemetry.update();
        servo1 = hardwareMap.get(Servo.class , "servo1");
        servo2 = hardwareMap.get(Servo.class , "servo2");
    }

    public void waitforstart() {

        while(opModeIsActive()){
            if(gamepad1.dpad_up) {
                servo1.setPosition(0.7);
                servo2.setPosition(0.7);
            }
            if(gamepad1.dpad_down) {
                servo1.setPosition(0.2);
                servo2.setPosition(0.2);
            }
        }
    }


}
