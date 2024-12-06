package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
    // -- Chassis -- //
    private boolean inverted = false;

    // -- Scorer -- //
    private boolean keepClawParallel = true;
    private boolean isSpeciman = false;
    private int currentStep = 0;
    private int currentStepSpecimen = 0;

    // -- Positions Motors -- //
    private int verticalPosition = Constants.VERTICAL_VIPERS.MIN_POSITION;
    private int horizontalPosition = Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND;
    private int rotationPosition = Constants.INTAKE_VIPERS.MIN_POSITION_ROTATE;

    // -- Positions Servo -- //
    private double clawServoPosition = 0;
    private double horizontalClawPosition = 0;
    private double verticalClawServoPosition = 0;

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

        setPresetPositionsSample();
        setPositions();
        reverseMotors();
    }

    private void setPositions()
    {
        Func.SetMotorPosition(Motors.armLeft, verticalPosition);
        Func.SetMotorPosition(Motors.armRight, verticalPosition);
        Func.SetMotorPosition(Motors.intakeExtend, horizontalPosition);
        Func.SetMotorPosition(Motors.intakeRotate, rotationPosition);
    }

    private void reverseMotors()
    {
        Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void startLoop()
    {
        try
        {
            handleChassis(); // Player 1
            handleScoring(); // Player 2
            //debugOdometry(); // Odometry Testing
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
        int invertInput = Input.isDown("chassis_right_bumper", gamepad1.right_bumper) ? 1 : -1;
        double speedInput = gamepad1.right_trigger;
        double maxSpeed = Constants.TELEOP.INITIAL_CHASSIS_SPEED + speedInput * (1 - Constants.TELEOP.INITIAL_CHASSIS_SPEED);

        double forwardInput = -gamepad1.left_stick_y * invertInput;
        double lateralInput = gamepad1.left_stick_x * 1.1 * invertInput;
        double angularInput = -gamepad1.right_stick_x;

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
/*
        Debug.log("Debug", "Chassis");
        Debug.log("frontLeftPower", frontLeftPower);
        Debug.log("backLeftPower", backLeftPower);
        Debug.log("frontRightPower", frontRightPower);
        Debug.log("backRightPower", backRightPower);*/
    }

    // -- Manipulator / Scorer Controls -- //

    private void handleScoring()
    {
        handleVipers();
        handleClaws();
    }

    private void handleVipers()
    {
        double rotateInput = -gamepad2.right_stick_y;
        double extendInput = -gamepad2.left_stick_y;
        double armsInput = gamepad2.right_bumper ? 1 : 0 - gamepad2.right_trigger;

        verticalPosition = Func.adjustPositionMotor(verticalPosition,
                armsInput,
                Constants.VERTICAL_VIPERS.MIN_POSITION,
                Constants.VERTICAL_VIPERS.MAX_POSITION,
                Constants.TELEOP.VERTICAL_VIPER_SPEED);

        horizontalPosition = Func.adjustPositionMotor(horizontalPosition,
                extendInput,
                Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND,
                Constants.INTAKE_VIPERS.MAX_POSITION_EXTEND,
                Constants.TELEOP.HORIZONTAL_VIPER_SPEED);

        rotationPosition = Func.adjustPositionMotor(rotationPosition,
                rotateInput,
                Constants.INTAKE_VIPERS.MIN_POSITION_ROTATE,
                Constants.INTAKE_VIPERS.MAX_POSITION_ROTATE,
                Constants.TELEOP.ROTATION_VIPER_SPEED);

        Func.updateLastTime();

        setPositions();

        // Debug

        Debug.log("Debug", "-- Vertical Vipers-- ");
        Debug.log("verticalPosition", verticalPosition);
        Debug.log("horizontalPosition", horizontalPosition);
        Debug.log("horizontalInput", extendInput);
        Debug.log("rotationPosition ", rotationPosition);
        Debug.log("rotationInput", rotateInput);
        Debug.log("currentStep", currentStep);
    }

    private void handleClaws()
    {
        double clawHorizontalInput = Input.isDown("scorer_left_bumper", gamepad2.left_bumper) ? -1
                : gamepad2.left_trigger > 0.01 ? 1
                : 0;

        horizontalClawPosition = Func.adjustPositionServo(horizontalClawPosition,
                clawHorizontalInput,
                0,
                1,
                1);

        Servos.horizontalRotate.setPosition(horizontalClawPosition);
        Servos.clawRotate.setPosition(clawServoPosition);

        if (keepClawParallel) Servos.verticalRotate.setPosition(servoParallel());
        else Servos.verticalRotate.setPosition(verticalClawServoPosition);

        if(Input.onKeyDown("scorer_options", gamepad2.start)) isSpeciman = !isSpeciman;

        if (Input.onKeyDown("scorer_a", gamepad2.a))
        {
            if(!isSpeciman)
            {
                currentStep++;
                if (currentStep > 4) currentStep = 0;
            }
            else
            {
                currentStepSpecimen++;
                if (currentStepSpecimen > 3) currentStepSpecimen = 0;
            }
        }
        if (Input.onKeyDown("scorer_b", gamepad2.b) && !isSpeciman) { currentStep = 0;}
        else if (Input.onKeyDown("scorer_b", gamepad2.b) && isSpeciman) {currentStepSpecimen = 0;}

        if (Input.isDown("scorer_a", gamepad2.a) || Input.isDown("scorer_b", gamepad2.b))
        {
            if(!isSpeciman)
                setPresetPositionsSample();
            else
                setPresetPositionsSpecimen();
        }

    }

    private void setPresetPositionsSpecimen()
    {
        switch(currentStepSpecimen)
        {
            case 0:
                preparePickupSpecimenPos();
                break;
            case 1:
                closeClawPos();
                break;
            case 2:
                prepareSpecimenLeavePos();
                break;
            case 3:
                leaveSpecimenPos();
                break;
        }
    }

    private void setPresetPositionsSample()
    {
        switch (currentStep)
        {
            case 0:
                preparePickupSamplePos();
                break;
            case 1:
                openClawPos();
                break;
            case 2:
                pickupSamplePos();
                break;
            case 3:
                prepareLeaveSamplePos();
                break;
            case 4:
                closeClawPos();
                break;
        }
    }

    private void preparePickupSpecimenPos()
    {
        verticalPosition = 0;
        horizontalPosition = 0;
        rotationPosition = 100;
        verticalClawServoPosition = 0.0935;
        horizontalClawPosition = 0.5;
    }

    private void closeClawPos()
    {
        clawServoPosition = 0;
    }

    private void prepareSpecimenLeavePos()
    {
        verticalPosition = 958;
        horizontalPosition = 931;
        rotationPosition = 723;
        verticalClawServoPosition = 0;
        horizontalClawPosition = 0.5;
    }

    private void leaveSpecimenPos()
    {
        closeClawPos();
        horizontalPosition = 0;
        verticalPosition = 0;
    }

    private void preparePickupSamplePos()
    {
        clawServoPosition = 0;
        verticalPosition = Constants.VERTICAL_VIPERS.MIN_POSITION;
        horizontalPosition = Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND;
        horizontalClawPosition = 0;
        verticalClawServoPosition = 0.4124;
        rotationPosition = 495;
    }

    private void openClawPos()
    {
        clawServoPosition = 1;
    }

    private void pickupSamplePos()
    {
        keepClawParallel = false;
        horizontalPosition = Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND;
        horizontalClawPosition = 1;
        verticalClawServoPosition = 0;
    }

    private void prepareLeaveSamplePos()
    {
        verticalPosition = 1570;
        rotationPosition = 1853;
        horizontalPosition = 1794;
        verticalClawServoPosition = 0.475;
        horizontalClawPosition = 0.5186;
    }

    private double servoParallel()
    {
        int motorPos = Motors.intakeRotate.getCurrentPosition();
        return 0.000136*motorPos+0.37;
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
        /*Debug.log("Velocity", velocity);

        Debug.log("Status", Sensors.odo.getDeviceStatus());*/
    }
}