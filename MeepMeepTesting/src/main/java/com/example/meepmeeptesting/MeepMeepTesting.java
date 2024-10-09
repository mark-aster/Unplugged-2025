package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting
{
    static MeepMeep meepMeep;
    static Pose2d startPos = new Pose2d(-36,-60, Math.toRadians(90));
    static Pose2d samplePos = new Pose2d(startPos.getX()+4, startPos.getY()+36);

    public static void main(String[] args)
    {
        meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = redAllianceYellow();

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    private static RoadRunnerBotEntity redAllianceYellow()
    {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60,60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPos)

                        .splineTo(new Vector2d(samplePos.getX(), samplePos.getY()), Math.toRadians(135))
                        .turn(Math.toRadians(45), Math.toRadians(360), Math.toRadians(360))

                        .waitSeconds(1.5) // first sample
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .addTemporalMarker(() -> {
                            //Pickup Sample
                        })
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(samplePos.getX() - 6, samplePos.getY()), Math.toRadians(0))
                        .addTemporalMarker(() -> {
                            //Leave Sample
                        })
                        .setReversed(false)

                        .waitSeconds(1.5) // second sample
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .addTemporalMarker(() -> {
                            //Pickup Sample
                        })
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(samplePos.getX() - 12, samplePos.getY()), Math.toRadians(0))
                        .addTemporalMarker(() -> {
                            //Leave Sample
                        })
                        .setReversed(false)

                        .waitSeconds(1.5) // third sample
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(235))
                        .addTemporalMarker(() -> {
                            //Pickup Sample
                        })
                        .waitSeconds(1.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(startPos.getX(), startPos.getY() + 48), Math.toRadians(180-35))
                        .addTemporalMarker(() -> {
                            //Leave Sample
                        })
                        .setReversed(false)

                        .turn(Math.toRadians(70), Math.toRadians(30), Math.toRadians(30))
                        .addTemporalMarker(() -> {
                            //Pickup Sample
                        })
                        .waitSeconds(3.5)
                        .setReversed(true)
                        .splineTo(new Vector2d(-48,-48+12), Math.toRadians(45))
                        .addTemporalMarker(() -> {
                            //Leave Sample
                        })
                        .waitSeconds(1.5)
                        .setReversed(false)

                        .setReversed(true)
                        .splineTo(new Vector2d(startPos.getX() + 24 * 3, startPos.getY()+3), Math.toRadians(0))
                        .build());
    }

}