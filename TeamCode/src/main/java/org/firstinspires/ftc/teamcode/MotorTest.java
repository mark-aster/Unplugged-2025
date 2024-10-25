package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Mecanum Drive TeleOp", group="Linear Opmode")
public class MotorTest extends LinearOpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    @Override
    public void runOpMode() {


        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");


        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {

            double drive = -gamepad1.left_stick_y;  // fata/spate
            double strafe = gamepad1.left_stick_x;  // stanga/dreapta
            double rotate = gamepad1.right_stick_x; // 360

            double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(rotate), 1);
            double leftFrontPower = (drive + strafe + rotate)/denominator;
            double rightFrontPower = (drive - strafe - rotate)/denominator;
            double leftBackPower = (drive - strafe + rotate)/denominator;
            double rightBackPower = (drive + strafe - rotate)/denominator;

            leftFront.setPower(leftFrontPower);
            rightFront.setPower(rightFrontPower);
            leftBack.setPower(leftBackPower);
            rightBack.setPower(rightBackPower);


            telemetry.addData("Left Front Power", leftFrontPower);
            telemetry.addData("Right Front Power", rightFrontPower);
            telemetry.addData("Left Back Power", leftBackPower);
            telemetry.addData("Right Back Power", rightBackPower);
            telemetry.update();
        }
    }
}

