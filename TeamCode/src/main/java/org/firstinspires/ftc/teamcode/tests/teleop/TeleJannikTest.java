package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.CONSTANTS;
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
    private final Gamepad ChassisController = gamepad1;
    private final Gamepad ScorerController = gamepad2;

    private int verticalPosition = CONSTANTS.VERTICAL_VIPERS.MIN_POSITION;
    private int horizontalPosition = CONSTANTS.INTAKE_VIPERS.MIN_POSITION_EXTEND;
    private int rotationPosition = CONSTANTS.INTAKE_VIPERS.MIN_POSITION_ROTATE;

    private double clawPosition = CONSTANTS.INTAKE_CLAW.CLAW_CLOSED;

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
        Motors.init(hardwareMap);
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
            HandleChassis(); // Player 1
            HandleScoring(); // Player 2
        }
        catch (Exception ignore) {}
    }

    // -- Chassis -- //

    private void HandleChassis()
    {
        HandleMovement();
    }

    private void HandleMovement()
    {
        double y = -ChassisController.left_stick_y;
        double x = ChassisController.left_stick_x * 1.1;
        double rx = ChassisController.right_stick_x;

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

    // -- Manipulator / Scorer -- //

    private void HandleScoring()
    {
        HandleVipers();
    }

    private void HandleVipers()
    {
        double armsInput = ScorerController.left_stick_y;
        double rotateInput = ScorerController.right_stick_y;
        double extendInput = ScorerController.right_trigger - ScorerController.left_trigger;

        verticalPosition = Func.adjustPosition(verticalPosition,
                armsInput,
                CONSTANTS.VERTICAL_VIPERS.MIN_POSITION,
                CONSTANTS.VERTICAL_VIPERS.MAX_POSITION,
                1);

        horizontalPosition = Func.adjustPosition(horizontalPosition,
                extendInput,
                CONSTANTS.INTAKE_VIPERS.MIN_POSITION_EXTEND,
                CONSTANTS.INTAKE_VIPERS.MAX_POSITION_EXTEND,
                1);

        rotationPosition = Func.adjustPosition(rotationPosition,
                rotateInput,
                CONSTANTS.INTAKE_VIPERS.MIN_POSITION_ROTATE,
                CONSTANTS.INTAKE_VIPERS.MAX_POSITION_ROTATE,
                1);
    }

    private void HandleClaw()
    {
        boolean ClawOpenInput = Input.onKeyDown("scorer_a", ScorerController.a);

        if(ClawOpenInput && clawPosition == CONSTANTS.INTAKE_CLAW.CLAW_OPEN) clawPosition = CONSTANTS.INTAKE_CLAW.CLAW_CLOSED;
        else if(ClawOpenInput && clawPosition == CONSTANTS.INTAKE_CLAW.CLAW_CLOSED) clawPosition = CONSTANTS.INTAKE_CLAW.CLAW_OPEN;
    }

}