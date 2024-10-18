package org.firstinspires.ftc.teamcode.tests.autonomous;

import static org.firstinspires.ftc.teamcode.subsystems.Constants.Field.TILE;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name = "RedAllianceRedSample", group = "Autonomous", preselectTeleOp = "TeleJannikTest")
public final class RedAllianceRedSample extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException
    {

        Pose2d beginPose = new Pose2d(2 * TILE,-2.5 * TILE, Math.toRadians(90));

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(beginPose)

                        .splineToLinearHeading(new Pose2d(beginPose.position.x, beginPose.position.y + TILE/1.5, Math.toRadians(90)), Math.toRadians(90))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x - TILE * 3.5, beginPose.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x, beginPose.position.y + TILE/1.5, Math.toRadians(65)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x - TILE * 3.5, beginPose.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x, beginPose.position.y + TILE/1.5, Math.toRadians(45)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x - TILE * 3.5, beginPose.position.y, Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(beginPose.position.x+ TILE/2, beginPose.position.y, Math.toRadians(180)), Math.toRadians(0))

                        .build()
        );
    }
}