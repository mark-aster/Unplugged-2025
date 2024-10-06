package org.firstinspires.ftc.teamcode.tests.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Input;

@TeleOp(name = "RoadrunnerTeleOpTest", group = "TeleopTests")
public class DrivingTest extends LinearOpMode
{
    MecanumDrive drive;

    boolean invert = false;
    byte invertMultiplier = -1;

    boolean slow = false;
    byte slowMultiplier = 3;
//markesteunfrumossiundragut
    //kiss
    //luv
    //tepupajannik
    //<3
        @Override
    public void runOpMode() throws InterruptedException
    {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        waitForStart();
        while (opModeIsActive())
        {
            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(
                                    invertMultiplier * gamepad1.left_stick_y / slowMultiplier,
                                    invertMultiplier * gamepad1.left_stick_x / slowMultiplier
                            ),
                            invertMultiplier * gamepad1.right_stick_x / slowMultiplier
                    )
            );

            slow ^= Input.onKeyDown("right_bumper", gamepad1.right_bumper);
            invert ^= Input.onKeyDown("left_bumper", gamepad1.left_bumper);

            invertMultiplier = (byte) (invert ? 1 : -1);
            slowMultiplier = (byte) (slow ? 3 : 1);
        }
    }
}
