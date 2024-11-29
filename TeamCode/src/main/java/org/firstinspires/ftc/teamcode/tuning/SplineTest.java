package org.firstinspires.ftc.teamcode.tuning;

import static org.firstinspires.ftc.teamcode.subsystems.CONSTANTS.FIELD.TILE;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException
    {
        Pose2d beginPoseYellow = new Pose2d(-TILE * 1.5,-2.5 * TILE, Math.toRadians(90));

        Pose2d beginPoseRed = new Pose2d(2 * TILE,-2.5 * TILE, Math.toRadians(90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPoseRed);

        waitForStart();


        Actions.runBlocking(
                drive.actionBuilder(beginPoseRed)

                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x, beginPoseRed.position.y + TILE/1.5, Math.toRadians(90)), Math.toRadians(90))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x - TILE * 3.5, beginPoseRed.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x, beginPoseRed.position.y + TILE/1.5, Math.toRadians(65)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x - TILE * 3.5, beginPoseRed.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x, beginPoseRed.position.y + TILE/1.5, Math.toRadians(45)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x - TILE * 3.5, beginPoseRed.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(beginPoseRed.position.x+ TILE/2, beginPoseRed.position.y, Math.toRadians(180)), Math.toRadians(0))

                        .build()
        );
    }
}