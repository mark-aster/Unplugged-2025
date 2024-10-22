package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="robotArm", group="Linear OpMode")
public class robotArm extends LinearOpMode {

    DcMotor elbowMotor1;
    DcMotor elbowMotor2;
    double ticks = 537.7;
    double newTarget;

    @Override
    public void runOpMode() throws InterruptedException {

        elbowMotor1 = hardwareMap.get(DcMotor.class, "elbowMotor1");
        elbowMotor2 = hardwareMap.get(DcMotor.class, "elbowMotor2");


        telemetry.addData("Status", "Initialized");
        telemetry.update();


        elbowMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbowMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbowMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbowMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {


            if (gamepad1.a) {
                encoder(2,0.5);
            }

            telemetry.addData("Elbow Motor Ticks: ", elbowMotor1.getCurrentPosition());
            telemetry.update();


            if (gamepad1.b) {
                encoder(0,0.8);
            }

        }
    }


    public void encoder(int turnage,double speed) {
        newTarget = ticks / turnage;

        elbowMotor1.setTargetPosition((int) newTarget);
        elbowMotor1.setPower(speed);
        elbowMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elbowMotor2.setTargetPosition((int) newTarget);
        elbowMotor2.setPower(speed);
        elbowMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }
}