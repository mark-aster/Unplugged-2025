package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="SingTest", group="Linear Opmode")
public class SingTest extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                leftFront.setPower(0.5);
            } else {
                leftFront.setPower(0);
            }

            if (gamepad1.b) {
                rightFront.setPower(-0.5);
            } else {
                rightFront.setPower(0);
            }

            if (gamepad1.x) {
                leftBack.setPower(0.5);
            } else {
                leftBack.setPower(0);
            }

            if (gamepad1.y) {
                rightBack.setPower(-0.5);
            } else {
                rightBack.setPower(0);
            }
        }
    }
}



