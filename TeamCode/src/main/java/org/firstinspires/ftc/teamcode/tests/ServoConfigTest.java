package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Input;

@TeleOp(name = "Servo Config Test", group = "Tests")
@Config
public class ServoConfigTest extends LinearOpMode
{
    private static int SERVO_PIN_COUNT = 6;

    private Servo servosCH[] = new Servo[SERVO_PIN_COUNT]; // Servos on Control Hub
    private Servo servosEH[] = new Servo[SERVO_PIN_COUNT]; // Servos on Expansion Hub

    public static double servoPosition = 0;
    private int multiplier = 1;

    public static int servoIndex = 0;
    public static boolean isCH = true;
    public static boolean Apply = false;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Init();
        waitForStart();
        while (opModeIsActive()) Update();
    }

    private void Init()
    {
        Debug.init(telemetry, FtcDashboard.getInstance());
        HardwareConfig();
    }

    private void Update()
    {
        servoIndex += (Input.onKeyDown("right_bumper", gamepad1.right_bumper)
                && servoIndex <= SERVO_PIN_COUNT - 1) ? 1 : 0;

        servoIndex -= (Input.onKeyDown("left_bumper", gamepad1.left_bumper)
                && servoIndex >= 0) ? 1 : 0;

        multiplier = (gamepad1.right_trigger > 0.1) ? 100 : (gamepad1.left_trigger > 0.1) ? 10 : 1;

        servoPosition += (Input.onKeyDown("dpad_right",gamepad1.dpad_right)
                && servoPosition < 1) ? 0.001 * multiplier : 0;

        servoPosition -= (Input.onKeyDown("dpad_left", gamepad1.dpad_left)
                && servoPosition > 0) ? 0.001 * multiplier : 0;

        isCH = Input.onKeyDown("start", gamepad1.start) != isCH;

        if (Input.onKeyDown("a", gamepad1.a) || Apply) {
            (isCH ? servosCH : servosEH)[servoIndex].setPosition(servoPosition);
            Apply = false;
        }

        Debug.log("motorIndex", servoIndex);
        Debug.log("motorPosition", servoPosition);
        Debug.log("isCH", isCH);
    }

    private void HardwareConfig()
    {
        for(int i = 0; i < SERVO_PIN_COUNT; i++)
        {
            servosCH[i] = hardwareMap.tryGet(Servo.class, "CH" + i);
            servosEH[i] = hardwareMap.tryGet(Servo.class, "EH" + i + SERVO_PIN_COUNT);
        }
    }
}
