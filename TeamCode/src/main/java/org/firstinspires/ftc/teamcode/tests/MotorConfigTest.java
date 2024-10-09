package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;

@TeleOp(name = "MotorConfigTest", group = "Tests")
@Config
public class MotorConfigTest extends LinearOpMode
{
    private DcMotorEx[] motorsArm;

    public static int motorPosition = 0;

    public static int motorIndex = 0;
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
        Motors.init(hardwareMap);

        motorsArm = new DcMotorEx[]{
                Motors.armRight
        };

        for(int i = 0; i < 1; i++)
        {
            motorsArm[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorsArm[i].setTargetPosition(0);
            motorsArm[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorsArm[i].setPower(1);

        }
    }

    private void Update()
    {
        motorIndex += (Input.onKeyDown("right_bumper", gamepad1.right_bumper)
                && motorIndex <= motorsArm.length - 1) ? 1 : 0;

        motorIndex -= (Input.onKeyDown("left_bumper", gamepad1.left_bumper)
                && motorIndex >= 0) ? 1 : 0;

        int multiplier = (gamepad1.right_trigger > 0.1) ? 100 : (gamepad1.left_trigger > 0.1) ? 10 : 1;

        motorPosition += (Input.onKeyDown("dpad_right",gamepad1.dpad_right)
                && motorPosition < 1) ? 10 * multiplier : 0;

        motorPosition -= (Input.onKeyDown("dpad_left", gamepad1.dpad_left)
                && motorPosition > 0) ? 10 * multiplier : 0;

        isCH = Input.onKeyDown("start", gamepad1.start) != isCH;

        if (Input.onKeyDown("a", gamepad1.a) || Apply) {
            motorsArm[motorIndex].setTargetPosition(motorIndex == 1 ? -motorPosition : motorPosition);
            motorsArm[motorIndex].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorsArm[motorIndex].setPower(1);
            Apply = false;
        }

        Debug.log("motorIndex", motorIndex);
        Debug.log("motorPosition", motorPosition);
        Debug.log("isCH", isCH);
    }
}
