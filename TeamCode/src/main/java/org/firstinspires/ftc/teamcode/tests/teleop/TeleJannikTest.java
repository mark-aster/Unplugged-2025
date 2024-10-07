package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.OpenCV;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Generic;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name="TeleJannikTest", group = "TeleopTests")
public class TeleJannikTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        Init();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive())
        {
            Update();
        }
    }

    private void Init()
    {
        Motors.init(hardwareMap);
        Servos.init(hardwareMap);
        Generic.init(hardwareMap);

        Motors.rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void Update()
    {
        Move();
        //UpdateClaw();
        Arm();
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

    private boolean isClawOpened = false;

    private void UpdateClaw()
    {
        HandleHorizontalRotation();
        HandleVerticalRotation();
        HandleClaws();
    }

    private void HandleVerticalRotation()
    {

        Servos.intakeVertical.setPosition(
                gamepad2.left_stick_y < -0.1 ? 0 :
                        gamepad2.left_stick_y > 0.1 ? 1 :
                                Servos.intakeVertical.getPosition()
        );
    }

    private void HandleHorizontalRotation()
    {

        Servos.intakeHorizontal.setPosition(
                gamepad2.right_stick_y < -0.1 ? Constants.Claw.horizontalMid :
                        gamepad2.right_stick_x < -0.1 ? 1 :
                                gamepad2.right_stick_x > 0.1 ? 0 :
                                        Servos.intakeHorizontal.getPosition()
        );
    }

    private void HandleClaws()
    {

        if(Input.onKeyDown("a", gamepad2.a))
        {
            if(!isClawOpened)
            {
                Servos.rightClaw.setPosition(0);
                Servos.leftClaw.setPosition(0);
                isClawOpened = true;
            }
            else
            {
                Servos.rightClaw.setPosition(1);
                Servos.leftClaw.setPosition(1);
                isClawOpened = false;
            }
        }
    }

    private void Arm()
    {

    }
}