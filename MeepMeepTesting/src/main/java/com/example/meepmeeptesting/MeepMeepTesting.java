package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting
{
    static MeepMeep meepMeep;
    static final double TILE = 24;

    static Pose2d beginPoseYellow = new Pose2d(-TILE + -TILE / 2,-2 * TILE + -TILE / 2, Math.toRadians(90));
    static Pose2d samplePosYellow = new Pose2d(beginPoseYellow.getX()+TILE/6, beginPoseYellow.getY()+TILE+TILE/2);

    static Pose2d beginPoseRed = new Pose2d(2 * TILE,-2 * TILE + -TILE / 2, Math.toRadians(90));

    public static void main(String[] args)
    {
        meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity BotRedAllianceRed = redAllianceRed();
        RoadRunnerBotEntity BotRedAllianceYellow = redAllianceYellow();

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(BotRedAllianceRed)
                .addEntity(BotRedAllianceYellow)
                .setShowFPS(true)
                .start();
    }

    private static RoadRunnerBotEntity redAllianceYellow()
    {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60,60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(beginPoseYellow)

                        .splineToLinearHeading(new Pose2d(samplePosYellow.getX(), samplePosYellow.getY(), Math.toRadians(180)), Math.toRadians(90))

                        .addTemporalMarker(() -> {})
                        .waitSeconds(1.5) // first sample
                        .setTangent(Math.toRadians(225))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(225))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(samplePosYellow.getX() - TILE / 4, samplePosYellow.getY(), Math.toRadians(180)), Math.toRadians(45))

                        .addTemporalMarker(() -> {})
                        .waitSeconds(1.5) // second sample
                        .setTangent(Math.toRadians(225))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(225))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(samplePosYellow.getX() - TILE / 2 , samplePosYellow.getY(), Math.toRadians(180)), Math.toRadians(90))

                        .addTemporalMarker(() -> {})
                        .waitSeconds(1.5) // third sample
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(270))

                        .addTemporalMarker(() -> {})
                        .waitSeconds(1.5) // fourth sample
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(beginPoseYellow.getX()+TILE/3, beginPoseYellow.getY() + 2 * TILE + TILE/6, Math.toRadians(0)), Math.toRadians(45))
                        .waitSeconds(3.5)
                        .setTangent(Math.toRadians(245))
                        .splineToLinearHeading(new Pose2d(-2 * TILE,-2 * TILE + TILE / 2, Math.toRadians(235)), Math.toRadians(245))

                        .addTemporalMarker(() -> {})
                        .waitSeconds(1.5) // park
                        .setTangent(Math.toRadians(0))
                        .waitSeconds(1)
                        .splineToLinearHeading(new Pose2d(beginPoseYellow.getX() + 3 * TILE, beginPoseYellow.getY(), Math.toRadians(0)), Math.toRadians(-90))

                        .build()
                );
    }

    private static RoadRunnerBotEntity redAllianceRed()
    {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60,60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(beginPoseRed)

                        .addTemporalMarker(() -> {}) // first sample
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX(), beginPoseRed.getY() + TILE/1.5, Math.toRadians(90)), Math.toRadians(90))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX() - TILE * 3.5, beginPoseRed.getY(), Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .addTemporalMarker(() -> {}) // second sample
                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX(), beginPoseRed.getY() + TILE/1.5, Math.toRadians(65)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX() - TILE * 3.5, beginPoseRed.getY(), Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .addTemporalMarker(() -> {}) // second sample
                        .setTangent(Math.toRadians(15))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX(), beginPoseRed.getY() + TILE/1.5, Math.toRadians(45)), Math.toRadians(0))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(200))
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX() - TILE * 3.5, beginPoseRed.getY(), Math.toRadians(180) ), Math.toRadians(190))
                        .waitSeconds(1.5)

                        .addTemporalMarker(() -> {}) // park
                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(beginPoseRed.getX()+ TILE/2, beginPoseRed.getY(), Math.toRadians(180)), Math.toRadians(0))

                        .build()
                );
    }

    private static RoadRunnerBotEntity splineTest()
    {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60,60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(180)))

                        .setTangent(Math.toRadians(180.0))
                        .splineToLinearHeading(new Pose2d(70.0, 70.0,Math.toRadians(180)), Math.toRadians(0.0))
                        .build());
    }
}