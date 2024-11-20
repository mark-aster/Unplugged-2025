package org.firstinspires.ftc.teamcode.tests;

import android.transition.Slide;

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
import org.firstinspires.ftc.teamcode.subsystems.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;

@TeleOp(name = "MotorConfigTest", group = "Tests")
@Config
public class MotorConfigTest extends LinearOpMode
{
    public static class Drive
    {
        public static class LeftFront
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.leftFront;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class RightFront
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.rightFront;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class LeftBack
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.leftRear;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class RightBack
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.rightRear;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }
    }

    public static class Sliders
    {
        public static class ArmLeft
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.armLeft;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class ArmRight
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.armRight;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class IntakeExtend
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.intakeExtend;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }

        public static class IntakeRotate
        {
            public static DcMotorEx Motor = (DcMotorEx) Motors.intakeRotate;
            public static int Position = 0;
            public static float PowerMultiplier = 1;
        }
    }

    public static boolean Apply;
    public static int MotorID;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Init();
        waitForStart();
        while (opModeIsActive()) Update();
    }

    private void Init()
    {
        for(int i = 0; i < Motors.allMotors.length; i++)
        {
            if(Motors.allMotors[i].equals(null)) continue;
            Motors.allMotors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Motors.allMotors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void Update()
    {
        if(Apply)
        {
            switch (MotorID)
            {
                // -- Drive -- //

                case 0: SetMotorPosition(Drive.LeftFront.Motor, Drive.LeftFront.Position, Drive.LeftFront.PowerMultiplier); break;
                case 1: SetMotorPosition(Drive.RightFront.Motor, Drive.RightFront.Position, Drive.RightFront.PowerMultiplier); break;
                case 2: SetMotorPosition(Drive.LeftBack.Motor, Drive.LeftBack.Position, Drive.LeftBack.PowerMultiplier); break;
                case 3: SetMotorPosition(Drive.RightBack.Motor, Drive.RightBack.Position, Drive.RightBack.PowerMultiplier); break;

                // -- Sliders -- //

                case 4: SetMotorPosition(Sliders.ArmLeft.Motor, Sliders.ArmLeft.Position, Sliders.ArmLeft.PowerMultiplier); break;
                case 5: SetMotorPosition(Sliders.ArmRight.Motor, Sliders.ArmRight.Position, Sliders.ArmRight.PowerMultiplier); break;
                case 6: SetMotorPosition(Sliders.IntakeExtend.Motor, Sliders.IntakeExtend.Position, Sliders.IntakeExtend.PowerMultiplier); break;
                case 7: SetMotorPosition(Sliders.IntakeRotate.Motor, Sliders.IntakeRotate.Position, Sliders.IntakeRotate.PowerMultiplier); break;
            }
            Apply = false;
        }
    }

    private void SetMotorPosition(DcMotorEx motor, int position, float posMultiplier)
    {
        motor.setPower(1);
        motor.setTargetPosition(position*(int)posMultiplier);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
