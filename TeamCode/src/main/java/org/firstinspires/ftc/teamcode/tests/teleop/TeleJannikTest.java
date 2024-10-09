package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Generic;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name="TeleJannikTest", group = "TeleopTests")
@Config
public class TeleJannikTest extends LinearOpMode
{
    boolean robotCentric = true;

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
        Debug.init(telemetry, FtcDashboard.getInstance());
    }

    private void Update()
    {
        try
        {
            if(robotCentric)
                MoveRobotCentric();
            else
                MoveFieldCentric();

            UpdateClaw();
            Arm();
        }
        catch (Exception ignore) {}
    }

    private void MoveRobotCentric()
    {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = -gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        Motors.leftFront.setPower(frontLeftPower);
        Motors.leftRear.setPower(backLeftPower);
        Motors.rightFront.setPower(frontRightPower);
        Motors.rightRear.setPower(backRightPower);

        Debug.log("motor left front", Motors.leftFront.getPower());
        Debug.log("motor right front", Motors.rightFront.getPower());
        Debug.log("motor left back", Motors.leftRear.getPower());
        Debug.log("motor right back", Motors.rightRear.getPower());
    }

    private void MoveFieldCentric()
    {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double botHeading = Generic.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

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