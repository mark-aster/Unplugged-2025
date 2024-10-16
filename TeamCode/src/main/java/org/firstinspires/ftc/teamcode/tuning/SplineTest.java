package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException
    {
        Pose2d beginPose = new Pose2d(-36,-60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        Pose2d samplePos = new Pose2d(beginPose.position.x+4, beginPose.position.y+36,Math.toRadians(90));

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .splineTo(new Vector2d(samplePos.position.x, samplePos.position.y), Math.toRadians(135))
                        .turn(Math.toRadians(45))

                        .waitSeconds(1.5) // first sample
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(samplePos.position.x - 6, samplePos.position.y), Math.toRadians(0))

                        .waitSeconds(1.5) // second sample
                        .setReversed(false)
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(samplePos.position.x - 12, samplePos.position.y), Math.toRadians(0))


                        .waitSeconds(1.5) // third sample
                        .setReversed(false)
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(beginPose.position.x, beginPose.position.y + 48), Math.toRadians(180-35))
                        .turn(Math.toRadians(70))
                        .waitSeconds(3.5)
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(45))
                        .waitSeconds(1.5)
                        .splineTo(new Vector2d(beginPose.position.x + 24 * 3, beginPose.position.y), Math.toRadians(0))
                        .build()
        );
    }
}
