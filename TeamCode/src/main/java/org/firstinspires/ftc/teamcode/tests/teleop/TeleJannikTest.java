package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name="TeleJannikTest")
@Config
public class TeleJannikTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Init();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            Update();
        }
    }

    private void Init()
    {
        Motors.init(hardwareMap);
        Servos.init(hardwareMap);

        Motors.rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void Update()
    {
        Move();
        Claw();
    }

    private void Move()
    {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        Motors.leftFront.setPower(frontLeftPower);
        Motors.leftRear.setPower(backLeftPower);
        Motors.rightFront.setPower(frontRightPower);
        Motors.rightRear.setPower(backRightPower);
    }
    private void Claw()
    {
        Servos.intakeVertical.setPosition(
                gamepad2.left_stick_y < -0.1 ? Constants.Claw.verticalSpecimen :
                        gamepad2.left_stick_y > 0.1 ? Constants.Claw.verticalIdle :
                                Servos.intakeVertical.getPosition() // retain the current position if no condition is met
        );

        Servos.intakeHorizontal.setPosition(
                gamepad2.right_stick_y < -0.1 ? Constants.Claw.horizontalMid :
                        gamepad2.right_stick_x < -0.1 ? Constants.Claw.horizontalCClock :
                                gamepad2.right_stick_x > 0.1 ? Constants.Claw.horizontalClock :
                                        Servos.intakeHorizontal.getPosition() // retain the current position if no condition is met
        );


        if(Input.onKeyDown("a", gamepad2.a))
        {
            if(Servos.rightClaw.getPosition() == Constants.Claw.rightClawClosed)
            {
                Servos.rightClaw.setPosition(Constants.Claw.rightClawOpen);
                Servos.leftClaw.setPosition(Constants.Claw.leftClawOpen);
            }
            else
            {
                Servos.rightClaw.setPosition(Constants.Claw.rightClawClosed);
                Servos.leftClaw.setPosition(Constants.Claw.leftClawClosed);
            }
        }
    }

    private void Arm()
    {

    }
}