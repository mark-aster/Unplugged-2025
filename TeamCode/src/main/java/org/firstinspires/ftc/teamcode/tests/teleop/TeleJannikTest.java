package org.firstinspires.ftc.teamcode.tests.teleop;

import static org.firstinspires.ftc.teamcode.subsystems.hardware.Generic.imu;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Func;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Generic;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name="TeleJannikTest", group = "TeleopTests")
@Config
public class TeleJannikTest extends LinearOpMode
{
    public static boolean robotCentric = true;
    public static double speedDivider = 1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Init();
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) Update();
    }

    private void Init()
    {
        Motors.Init(hardwareMap);
        Servos.init(hardwareMap);
        Generic.init(hardwareMap);
        Debug.init(telemetry, FtcDashboard.getInstance());
        Motors.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void Update()
    {
        try
        {
            if(robotCentric)
                MoveRobotCentric();
            else
                MoveFieldCentric();

            //UpdateClaw();
            //UpdateArms();
            UpdateIntake();
        }
        catch (Exception ignore) {}
    }

    // -- Drive Train -- //

    private void MoveRobotCentric()
    {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        Motors.leftFront.setPower(frontLeftPower/speedDivider);
        Motors.leftRear.setPower(backLeftPower/speedDivider);
        Motors.rightFront.setPower(frontRightPower/speedDivider);
        Motors.rightRear.setPower(backRightPower/speedDivider);

        Debug.log("motor left front", Motors.leftFront.getPower());
        Debug.log("motor right front", Motors.rightFront.getPower());
        Debug.log("motor left back", Motors.leftRear.getPower());
        Debug.log("motor right back", Motors.rightRear.getPower());
    }

    private void MoveFieldCentric()
    {
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
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

    // -- Claws -- //

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

    // -- Arms -- //

    private void UpdateArms()
    {
        HandleArmVertical();
        HandleArmHorizontal();
    }

    private void HandleArmVertical()
    {
        int height = 0;
        if(gamepad1.right_stick_y > 0.01 && height <= Constants.VerticalViper.maxPosition)
        {
            height += (int) (10 * Func.deltaTime());
        }
        if(gamepad1.right_stick_y < -0.01 && height >= Constants.VerticalViper.minPosition)
        {
            height -= (int) (10 * Func.deltaTime());
        }
        ChangeVerticalArmHeight(height);
    }

    private void ChangeVerticalArmHeight(int height)
    {
        Func.SetMotorPosition((DcMotorEx) Motors.armLeft, height);
        Func.SetMotorPosition((DcMotorEx) Motors.armRight, height);
    }

    private void HandleArmHorizontal()
    {
        int height = 0;
        if(gamepad1.right_stick_x > 0.01 && height <= Constants.VerticalViper.maxPosition)
        {
            height += (int) (10 * Func.deltaTime());
        }
        if(gamepad1.right_stick_x < -0.01 && height >= Constants.VerticalViper.minPosition)
        {
            height -= (int) (10 * Func.deltaTime());
        }
        ChangeHorizontalArmHeight(height);
    }

    private void ChangeHorizontalArmHeight(int height)
    {
        Func.SetMotorPosition((DcMotorEx) Motors.armLeft, height);
        Func.SetMotorPosition((DcMotorEx) Motors.armRight, height);
    }

    // -- Intake -- //

    private void UpdateIntake()
    {
        HandleIntakeExtension();
        HandleIntakeRotation();
        Debug.log("rotate Position", rotatePos);
        Debug.log("extend Position", extendPos);
    }

    int rotatePos = 0;
    private int extendPos = 0;

    private int AdjustPosition(int currentPosition, float input, int min, int max, int speed) {
        currentPosition += (int) (Func.deltaTime() * speed * input);
        return Math.max(min, Math.min(currentPosition, max));
    }

    private void HandleIntakeRotation() {
        rotatePos = AdjustPosition(rotatePos,
                gamepad2.left_stick_y,
                Constants.IntakeVipers.minPositionRotate,
                Constants.IntakeVipers.maxPositionRotate,
                1);

        Func.SetMotorPosition((DcMotorEx) Motors.intakeRotate, rotatePos);
    }

    private void HandleIntakeExtension() {
        extendPos = AdjustPosition(extendPos,
                gamepad2.right_stick_y,
                Constants.IntakeVipers.minPositionExtend,
                Constants.IntakeVipers.maxPositionExtend,
                1);

        Func.SetMotorPosition((DcMotorEx) Motors.intakeExtend, extendPos);
    }
}