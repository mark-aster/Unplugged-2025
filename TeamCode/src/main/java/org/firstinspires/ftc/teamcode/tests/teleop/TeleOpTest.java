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
    private boolean keepClawParallel = false    ;
    private boolean isSpeciman = false;
    private int currentStep = 1;
    private int currentStepSpecimen = 0;

    // -- Positions Motors -- //
    private int verticalPosition = Constants.VERTICAL_VIPERS.MIN_POSITION;
    private int horizontalPosition = Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND;
    private int rotationPosition = Constants.INTAKE_VIPERS.MIN_POSITION_ROTATE;

    // -- Positions Servo -- //
    private double clawServoPosition = 0;
    private double horizontalClawPosition = 0.84;
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

        setPositions();
        setPresetPositionsSample();

        reverseMotors();
    }

    private void setPositions()
    {
    }

    private void reverseMotors()
    {

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
    }

    // -- Manipulator / Scorer Controls -- //

    private void handleScoring()
    {
        handleClaws();
    }

    private void handleVipers()
    {
        double rotateInput = -gamepad1.right_stick_y;
        double extendInput = -gamepad1.left_stick_y;
        double armsInput = gamepad1.right_bumper ? 1 : 0 - gamepad1.right_trigger;

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
                1.5);

        double clawVerticalInput = Input.isDown("scorer_right_bumper", gamepad2.right_bumper) ? -1
                : gamepad2.right_trigger > 0.01 ? 1
                : 0;

        verticalClawServoPosition = Func.adjustPositionServo(verticalClawServoPosition, clawVerticalInput,0.5,0.8,1);

        Servos.horizontalRotate.setPosition(horizontalClawPosition);
        Servos.clawRotate.setPosition(clawServoPosition);
        Servos.verticalRotate.setPosition(verticalClawServoPosition);

        if(Input.onKeyDown("scorer_options", gamepad2.start)) isSpeciman = !isSpeciman;

        if (Input.onKeyDown("scorer_a", gamepad2.a))
        {
            if(!isSpeciman)
            {
                currentStep++;
                if (currentStep > 1) currentStep = 0;
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
                setPresetPositionsSample();
        }
        Func.updateLastTime();
    }

    private void setPresetPositionsSample()
    {
        switch (currentStep)
        {
            case 0:
                openClawPos();
                break;
            case 1:
                closeClawPos();
                break;
        }
    }

    private void preparePickupSpecimenPos()
    {
        verticalClawServoPosition = 0.69;
        horizontalClawPosition = 0.5;
        clawServoPosition = 0.38;
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
        verticalClawServoPosition = 0.69;
        clawServoPosition = 0.38;
    }

    private void openClawPos()
    {
        clawServoPosition = 0.5;
    }

    private void pickupSamplePos()
    {
        horizontalClawPosition = 1;
        verticalClawServoPosition = 0.69;
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