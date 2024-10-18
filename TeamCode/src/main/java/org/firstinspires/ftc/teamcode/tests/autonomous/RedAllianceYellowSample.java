package org.firstinspires.ftc.teamcode.tests.autonomous;

import static org.firstinspires.ftc.teamcode.subsystems.Constants.Field.TILE;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name = "RedAllianceYellowSample", group = "Autonomous", preselectTeleOp = "TeleJannikTest")
public final class RedAllianceYellowSample extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException
    {

        Pose2d beginPose = new Pose2d(-TILE + -TILE / 2,-2 * TILE + -TILE / 2, Math.toRadians(90));
        Pose2d samplePos = new Pose2d(beginPose.position.x+TILE/6, beginPose.position.y+TILE+TILE/2, Math.toRadians(90));


        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(beginPose)

                        .splineToLinearHeading(new Pose2d(samplePos.position.x, samplePos.position.y, Math.toRadians(180)), Math.toRadians(90))

                        .waitSeconds(1.5) // first sample
                        .setTangent(Math.toRadians(225))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(225))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(samplePos.position.x - TILE / 4, samplePos.position.y, Math.toRadians(180)), Math.toRadians(45))

                        .waitSeconds(1.5) // second sample
                        .setTangent(Math.toRadians(225))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(225))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(samplePos.position.x - TILE / 2 , samplePos.position.y, Math.toRadians(180)), Math.toRadians(90))

                        .waitSeconds(1.5) // third sample
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(270))

                        .waitSeconds(1.5) // fourth sample
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(beginPose.position.x+TILE/3, beginPose.position.y + 2 * TILE + TILE/6, Math.toRadians(0)), Math.toRadians(45))
                        .waitSeconds(3.5)
                        .setTangent(Math.toRadians(245))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(245))

                        .waitSeconds(1.5) // park
                        .setTangent(Math.toRadians(0))
                        .waitSeconds(1)
                        .splineToLinearHeading(new Pose2d(beginPose.position.x + 3 * TILE, beginPose.position.y, Math.toRadians(0)), Math.toRadians(-90))

                        .build()
        );
    }
}