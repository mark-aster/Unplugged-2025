package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "ColorSensor" , group = "Tests")
public class SensorTest extends LinearOpMode {
    private Servo servoOne;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.0;
    private double servoOnePositionTwo = 1.0;

    private ColorSensor colorSensor;
    private double valRosu;
    private double valAlbastru;
    private double valVerde;
    private double valAlpha;
    private double valTarget = 1000;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()) {
            getColor();
            colorTelemetry();
        }

        waitForStart();

        while(opModeIsActive()) {
            getColor();
            colorTelemetry();
            teleOpControls();
        }
    }

    public void initHardware() {
        initColorSensor();
        initServoOne();
    }

    public void initServoOne() {
        servoOne = hardwareMap.get(Servo.class , "servo1");
        servoOne.setDirection(Servo.Direction.FORWARD);
        servoOne.setPosition(servoOneInitPosition);
    }

    public void initColorSensor() {
        colorSensor = hardwareMap.get(ColorSensor.class , "sensorc");
    }

    public void getColor() {
        valRosu = colorSensor.red();
        valAlbastru = colorSensor.blue();
        valVerde = colorSensor.green();
        valAlpha = colorSensor.alpha();
    }

    public void teleOpControls() {
        if(valAlpha > valTarget) {
            servoOne.setPosition(servoOnePositionTwo);
        }
        else {
            servoOne.setPosition(servoOnePositionOne);
        }
    }

    public void colorTelemetry() {
        if(valRosu > valAlbastru) {
            telemetry.addData("culoare: " , "Rosu");
            telemetry.update();
        }
        else {
            telemetry.addData("culoare: " , "Albastru");
            telemetry.update();
        }

        if(valRosu > valVerde) {
            telemetry.addData("culoare: " , "Rosu");
            telemetry.update();
        }
        else {
            telemetry.addData("culoare: " , "Verde");
            telemetry.update();
        }
        if(valAlbastru > valVerde) {
            telemetry.addData("culoare: " , "Albastru");
            telemetry.update();
        }
        else {
            telemetry.addData("culoare: " , "Verde");
            telemetry.update();
        }

    }

    public void move() {

    }


}
