package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.teamcode.subsystems.Func.SetMotorPosition;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name = "MotorConfigTest", group = "Tests")
@Config
public final class MotorConfigTest extends LinearOpMode
{

    @Config
    public static class M_Drive
    {
        public static int _0_LeftFrontPosition = 0;

        public static int _1_RightFrontPosition = 0;

        public static int _2_LeftBackPosition = 0;

        public static int _3_RightBackPosition = 0;
    }

    @Config
    public static class M_Sliders
    {
        public static int _4_ArmLeftPosition = 0;

        public static int _5_ArmRightPosition = 0;

        public static int _6_IntakeExtendPosition = 0;

        public static int _7_IntakeRotatePosition = 0;
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
        Servos.init(hardwareMap);
        Motors.init(hardwareMap);

        //Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);

        for(int i = 0; i < Motors.allMotors.length; i++)
        {
            if(Motors.allMotors[i] == null) continue;
            Motors.allMotors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Motors.allMotors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void Update()
    {
        TestRotationPerp();
        if(Apply)
        {
            switch (MotorID)
            {
                // -- Drive -- //

                case 0: SetMotorPosition(Motors.leftFront, M_Drive._0_LeftFrontPosition); break;
                case 1: SetMotorPosition(Motors.rightFront, M_Drive._1_RightFrontPosition); break;
                case 2: SetMotorPosition(Motors.leftRear, M_Drive._2_LeftBackPosition); break;
                case 3: SetMotorPosition(Motors.rightRear, M_Drive._3_RightBackPosition); break;

                // -- Sliders -- //

                case 4: SetMotorPosition(Motors.armLeft, M_Sliders._4_ArmLeftPosition); break;
                case 5: SetMotorPosition(Motors.armRight, M_Sliders._5_ArmRightPosition); break;
                case 6: SetMotorPosition(Motors.intakeExtend, M_Sliders._6_IntakeExtendPosition); break;
                case 7: SetMotorPosition(Motors.intakeRotate, M_Sliders._7_IntakeRotatePosition); break;
            }
            Apply = false;
        }
    }

    private void TestRotationPerp()
    {

    }



}
