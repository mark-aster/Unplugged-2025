package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Func;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

import java.util.Locale;

@TeleOp(name="TeleOpTest", group = "TeleopTests")
public class TeleOpTest extends LinearOpMode
{
    // -- Controllers -- //
    private final Gamepad ChassisController = gamepad1;
    private final Gamepad ScorerController = gamepad2;

    // -- Positions -- //
    private int verticalPosition = Constants.VERTICAL_VIPERS.MIN_POSITION;
    private int horizontalPosition = Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND;
    private int rotationPosition = Constants.INTAKE_VIPERS.MIN_POSITION_ROTATE;

    private double clawPosition = Constants.INTAKE_CLAW.CLOSED;

    @Override
    public void runOpMode() throws InterruptedException
    {
        initHardware();
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) startLoop();
    }

    private void initHardware()
    {
        Motors.init(hardwareMap);
        Servos.init(hardwareMap);
        Sensors.init(hardwareMap);
        Debug.init(telemetry, FtcDashboard.getInstance());

        reverseMotors();
    }

    private void reverseMotors()
    {
        Motors.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void startLoop()
    {
        try
        {
            handleChassis(); // Player 1
            handleScoring(); // Player 2
            debugOdometry(); // Odometry Testing
        }
        catch (Exception e) {
            Debug.log("Error", e.getMessage());
        }
        Debug.update();
    }

    // -- Chassis Controls -- //

    private void handleChassis()
    {
        handleMovement();
    }

    private void handleMovement()
    {
        int invertInput = Input.isDown("chassis_right_bumper", ChassisController.right_bumper) ? 1 : -1;
        double speedInput = ChassisController.right_trigger;
        double maxSpeed = Constants.TELEOP.INITIAL_CHASSIS_SPEED + speedInput * (1 - Constants.TELEOP.INITIAL_CHASSIS_SPEED);

        double forwardInput = (-ChassisController.left_stick_y) * invertInput;
        double lateralInput = (ChassisController.left_stick_x * 1.1) * invertInput;
        double angularInput = (ChassisController.right_stick_x) * invertInput;

        double denominator = Math.max(Math.abs(forwardInput) + Math.abs(lateralInput) + Math.abs(angularInput), 1);
        double frontLeftPower = ((forwardInput + lateralInput + angularInput) / denominator) * maxSpeed;
        double backLeftPower = ((forwardInput - lateralInput + angularInput) / denominator) * maxSpeed;
        double frontRightPower = ((forwardInput - lateralInput - angularInput) / denominator) * maxSpeed;
        double backRightPower = ((forwardInput + lateralInput - angularInput) / denominator) * maxSpeed;

        Motors.leftFront.setPower(frontLeftPower);
        Motors.leftRear.setPower(backLeftPower);
        Motors.rightFront.setPower(frontRightPower);
        Motors.rightRear.setPower(backRightPower);

        // Debug

        Debug.log("Debug", "Chassis");
        Debug.log("frontLeftPower", frontLeftPower);
        Debug.log("backLeftPower", backLeftPower);
        Debug.log("frontRightPower", frontRightPower);
        Debug.log("backRightPower", backRightPower);
    }

    // -- Manipulator / Scorer Controls -- //

    private void handleScoring()
    {
        handleVipers();
        handleClaws();
    }

    private void handleVipers()
    {
        double armsInput = ScorerController.left_stick_y;
        double rotateInput = ScorerController.right_stick_y;
        double extendInput = ScorerController.right_trigger - ScorerController.left_trigger;

        verticalPosition = Func.adjustPosition(verticalPosition,
                armsInput,
                Constants.VERTICAL_VIPERS.MIN_POSITION,
                Constants.VERTICAL_VIPERS.MAX_POSITION,
                Constants.TELEOP.VERTICAL_VIPER_SPEED);

        horizontalPosition = Func.adjustPosition(horizontalPosition,
                extendInput,
                Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND,
                Constants.INTAKE_VIPERS.MAX_POSITION_EXTEND,
                Constants.TELEOP.HORIZONTAL_VIPER_SPEED);

        rotationPosition = Func.adjustPosition(rotationPosition,
                rotateInput,
                Constants.INTAKE_VIPERS.MIN_POSITION_ROTATE,
                Constants.INTAKE_VIPERS.MAX_POSITION_ROTATE,
                Constants.TELEOP.ROTATION_VIPER_SPEED);

        Func.SetMotorPosition(Motors.armLeft, verticalPosition);
        Func.SetMotorPosition(Motors.armRight, verticalPosition);

        Func.SetMotorPosition(Motors.intakeExtend, horizontalPosition);

        Func.SetMotorPosition(Motors.intakeRotate, rotationPosition);

        // Debug

        Debug.log("Debug", "-- Vertical Vipers-- ");
        Debug.log("verticalPosition", verticalPosition);
        Debug.log("horizontalPosition", horizontalPosition);
        Debug.log("rotationPosition", rotationPosition);
    }

    private void handleClaws()
    {
        int clawHorizontalInput = Input.onKeyDown("scorer_dpad_right",
                ScorerController.dpad_right) ? 1
                : Input.onKeyDown("scorer_dpad_left", ScorerController.dpad_left) ? 0
                : -1;

        int clawVerticalInput = Input.onKeyDown("scorer_dpad_up", ScorerController.dpad_up) ? 1
                : Input.onKeyDown("scorer_dpad_down", ScorerController.dpad_down) ? 0
                : -1;

        boolean clawOpenInput = Input.onKeyDown("scorer_a", ScorerController.a);

        Servos.horizontalRotate.setPosition(clawHorizontalInput);
        Servos.verticalRotate.setPosition(clawVerticalInput);

        if(clawOpenInput && clawPosition == Constants.INTAKE_CLAW.OPEN) clawPosition = Constants.INTAKE_CLAW.CLOSED;
        else if(clawOpenInput && clawPosition == Constants.INTAKE_CLAW.CLOSED) clawPosition = Constants.INTAKE_CLAW.OPEN;

        // Debug

        Debug.log("Debug", "-- Claws --");
        Debug.log("clawPosition", clawPosition);
    }

    // -- Odometry Testing -- //

    private void debugOdometry()
    {
        Debug.log("Debug", "-- Odometry --");
        Pose2D pos = Sensors.odo.getPosition();
        String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.MM), pos.getY(DistanceUnit.MM), pos.getHeading(AngleUnit.DEGREES));
        Debug.log("Position", data);

        Pose2D vel = Sensors.odo.getVelocity();
        String velocity = String.format(Locale.US,"{XVel: %.3f, YVel: %.3f, HVel: %.3f}", vel.getX(DistanceUnit.MM), vel.getY(DistanceUnit.MM), vel.getHeading(AngleUnit.DEGREES));
        Debug.log("Velocity", velocity);

        Debug.log("Status", Sensors.odo.getDeviceStatus());
    }
}