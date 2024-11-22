package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.teamcode.subsystems.Func.SetMotorPosition;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Func;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name = "ServoMotorConfigTest", group = "Tests")
@Config
public final class ServoMotorConfigTest extends LinearOpMode {

    private static final int SERVO_PIN_COUNT = 6;

    // Motor configuration
    @Config
    public static class M_Drive {
        public static int _0_LeftFrontPosition = 0;
        public static int _1_RightFrontPosition = 0;
        public static int _2_LeftBackPosition = 0;
        public static int _3_RightBackPosition = 0;
    }

    @Config
    public static class M_Sliders {
        public static int _4_ArmLeftPosition = 0;
        public static int _5_ArmRightPosition = 0;
        public static int _6_IntakeExtendPosition = 0;
        public static int _7_IntakeRotatePosition = 0;
    }

    // Servo configuration
    private Servo servosCH[] = new Servo[SERVO_PIN_COUNT]; // Servos on Control Hub
    private Servo servosEH[] = new Servo[SERVO_PIN_COUNT]; // Servos on Expansion Hub

    public static double servoPosition = 0;
    private int multiplier = 1;

    public static int motorID;
    public static int servoIndex = 0;
    public static boolean isCH = true;
    public static boolean apply = false;

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        waitForStart();
        while (opModeIsActive()) {
            update();
        }
    }

    private void initHardware() {
        // Initialize hardware
        Servos.init(hardwareMap);
        Motors.Init(hardwareMap);

        // Set motor directions
        Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialize all motors
        for (int i = 0; i < Motors.allMotors.length; i++) {
            if (Motors.allMotors[i] != null) {
                Motors.allMotors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Motors.allMotors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }

        // Initialize servos
        for (int i = 0; i < SERVO_PIN_COUNT; i++) {
            servosCH[i] = hardwareMap.tryGet(Servo.class, "CH" + i);
            servosEH[i] = hardwareMap.tryGet(Servo.class, "EH" + i + SERVO_PIN_COUNT);
        }

        Debug.init(telemetry, FtcDashboard.getInstance());
    }

    private void update() {
        // Motor control
        if (apply) {
            switch (motorID) {
                // Drive motors
                case 0: SetMotorPosition((DcMotorEx) Motors.leftFront, M_Drive._0_LeftFrontPosition); break;
                case 1: SetMotorPosition((DcMotorEx) Motors.rightFront, M_Drive._1_RightFrontPosition); break;
                case 2: SetMotorPosition((DcMotorEx) Motors.leftRear, M_Drive._2_LeftBackPosition); break;
                case 3: SetMotorPosition((DcMotorEx) Motors.rightRear, M_Drive._3_RightBackPosition); break;
                // Slider motors
                case 4: SetMotorPosition((DcMotorEx) Motors.armLeft, M_Sliders._4_ArmLeftPosition); break;
                case 5: SetMotorPosition((DcMotorEx) Motors.armRight, M_Sliders._5_ArmRightPosition); break;
                case 6: SetMotorPosition((DcMotorEx) Motors.intakeExtend, M_Sliders._6_IntakeExtendPosition); break;
                case 7: SetMotorPosition((DcMotorEx) Motors.intakeRotate, M_Sliders._7_IntakeRotatePosition); break;
            }
            apply = false;
        }

        // Servo control
        servoIndex += (Input.onKeyDown("right_bumper", gamepad1.right_bumper) && servoIndex < SERVO_PIN_COUNT - 1) ? 1 : 0;
        servoIndex -= (Input.onKeyDown("left_bumper", gamepad1.left_bumper) && servoIndex > 0) ? 1 : 0;

        multiplier = (gamepad1.right_trigger > 0.1) ? 100 : (gamepad1.left_trigger > 0.1) ? 10 : 1;

        servoPosition += (Input.onKeyDown("dpad_right", gamepad1.dpad_right) && servoPosition < 1) ? 0.001 * multiplier : 0;
        servoPosition -= (Input.onKeyDown("dpad_left", gamepad1.dpad_left) && servoPosition > 0) ? 0.001 * multiplier : 0;

        isCH = Input.onKeyDown("start", gamepad1.start) != isCH;

        if (Input.onKeyDown("a", gamepad1.a) || apply) {
            (isCH ? servosCH : servosEH)[servoIndex].setPosition(servoPosition);
            apply = false;
        }

        // Debugging
        Debug.log("motorIndex", servoIndex);
        Debug.log("motorPosition", servoPosition);
        Debug.log("isCH", isCH);
        Debug.log("currentServoPosition", (isCH ? servosCH : servosEH)[servoIndex].getPosition());
    }
}
