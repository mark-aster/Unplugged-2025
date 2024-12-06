package org.firstinspires.ftc.teamcode.tests.autonomous;

import static org.firstinspires.ftc.teamcode.subsystems.Constants.FIELD.TILE;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.subsystems.Debug;
import org.firstinspires.ftc.teamcode.subsystems.Presets;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@Autonomous(name = "BlueAllianceYellowSample", group = "Autonomous", preselectTeleOp = "TeleOpTest")
public final class BlueAllianceYellowSample extends LinearOpMode
{
    private void initHardware()
    {
        Motors.init(hardwareMap);
        Servos.init(hardwareMap);
        Sensors.init(hardwareMap);
        Debug.init(telemetry, FtcDashboard.getInstance());
    }

    private double normalizeAngle(double angle) {
        while (angle > Math.PI) angle -= 2 * Math.PI;
        while (angle < -Math.PI) angle += 2 * Math.PI;
        return angle;
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        initHardware();
        Pose2d beginPose = new Pose2d(-TILE + -TILE / 2,-2 * TILE + -TILE / 2, Math.toRadians(90));
        PinpointDrive drive = new PinpointDrive(hardwareMap, beginPose);

        // -- TODO POSITIONS -- //
        int rotateSamplePos = 0;

        int extendSamplePos1 = 0;
        double rotateClawSamplePos1 = 0;
        int extendSamplePos2 = 0;
        double rotateClawSamplePos2 = 0;
        int extendSamplePos3 = 0;
        double rotateClawSamplePos3 = 0;

        int extendParkPos = 0;
        int rotateParkPos = 0;

        Action goToBasket = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(2 * TILE, 2 * TILE, normalizeAngle(Math.toRadians(225) + Math.PI)), Math.toRadians(180))
                .build();

        TrajectoryActionBuilder goToSample1 = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(2 * TILE, 1.75 * TILE, normalizeAngle(Math.toRadians(90) + Math.PI)), Math.toRadians(180));

        TrajectoryActionBuilder goToSample2 = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(2 * TILE, 1.75 * TILE, normalizeAngle(Math.toRadians(120) + Math.PI)), Math.toRadians(180));

        TrajectoryActionBuilder goToSample3 = drive.actionBuilder(drive.pose)
                .setTangent(normalizeAngle(Math.toRadians(0) + Math.PI))
                .splineToLinearHeading(new Pose2d(2.1 * TILE, 1.5 * TILE, normalizeAngle(Math.toRadians(145) + Math.PI)), Math.toRadians(180));

        TrajectoryActionBuilder goToPark = drive.actionBuilder(drive.pose)
                .setTangent(normalizeAngle(Math.toRadians(90) + Math.PI))
                .splineToLinearHeading(new Pose2d(-beginPose.position.x / 3 + TILE / 2, -beginPose.position.y - 2 * TILE - TILE / 6, normalizeAngle(Math.toRadians(0) + Math.PI)), Math.toRadians(0));


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction
                        (
                                goToBasket,
                                Presets.Scorer.prepareLeaveSamplePos(),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.closeClawPos(),
                                new ParallelAction(goToSample1.build(),Presets.Scorer.preparePickupSamplePos()),

                                //Sample 1
                                Presets.Scorer.extendViper(extendSamplePos1 /*TODO FIND POSITION*/),
                                Presets.Scorer.rotateHorizontalClaw(rotateClawSamplePos1 /*TODO FIND POSITION*/),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.rotateViper(rotateSamplePos /*TODO FIND POSITION*/),
                                new SleepAction(0.2), //TODO TIME FOR VIPERS TO LOWER
                                Presets.Scorer.openClawPos(),
                                new SleepAction(0.1),
                                Presets.Scorer.pickupSamplePos(),

                                goToBasket,
                                Presets.Scorer.prepareLeaveSamplePos(),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.closeClawPos(),
                                new SleepAction(0.2),
                                new ParallelAction(goToSample2.build(),Presets.Scorer.preparePickupSamplePos()),

                                //Sample 2
                                Presets.Scorer.extendViper(extendSamplePos2 /*TODO FIND POSITION*/),
                                Presets.Scorer.rotateHorizontalClaw(rotateClawSamplePos2 /*TODO FIND POSITION*/),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.rotateViper(rotateSamplePos /*TODO FIND POSITION*/),
                                new SleepAction(0.2), //TODO TIME FOR VIPERS TO LOWER
                                Presets.Scorer.openClawPos(),
                                new SleepAction(0.1),
                                Presets.Scorer.pickupSamplePos(),

                                goToBasket,
                                Presets.Scorer.prepareLeaveSamplePos(),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.closeClawPos(),
                                new SleepAction(0.2),
                                new ParallelAction(goToSample3.build(),Presets.Scorer.preparePickupSamplePos()),

                                //Sample 3
                                Presets.Scorer.extendViper(extendSamplePos3 /*TODO FIND POSITION*/),
                                Presets.Scorer.rotateHorizontalClaw(rotateClawSamplePos3 /*TODO FIND POSITION*/),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.rotateViper(rotateSamplePos /*TODO FIND POSITION*/),
                                new SleepAction(0.2), //TODO TIME FOR VIPERS TO LOWER
                                Presets.Scorer.openClawPos(),
                                new SleepAction(0.1),
                                Presets.Scorer.pickupSamplePos(),

                                goToBasket,
                                Presets.Scorer.prepareLeaveSamplePos(),
                                new SleepAction(0.5), //TODO TIME FOR VIPERS TO GET TO POSITION
                                Presets.Scorer.closeClawPos(),
                                new SleepAction(0.2),
                                new ParallelAction(goToPark.build(),Presets.Scorer.preparePickupSamplePos()),

                                //Park
                                Presets.Scorer.extendViper(extendParkPos /*TODO FIND POSITION*/),
                                Presets.Scorer.rotateViper(rotateParkPos /*TODO FIND POSITION*/)
                        ));
    }
}