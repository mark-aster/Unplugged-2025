package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, 58, -1.57079633))
                        .strafeRight(20)
                        .setTangent(Math.toRadians(210))
                        .splineToLinearHeading(new Pose2d(-43.3 , 4.6,  Math.toRadians(90)),Math.toRadians(145))
                        .setTangent(90)
                        .splineToLinearHeading(new Pose2d(-48.5 , 52.1 , Math.toRadians(90)) , Math.toRadians(180)) //UP
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-50.2, 14.1 , Math.PI / 2) , Math.toRadians(150)) // Down
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-54.2 , 52.6 , Math.PI / 2) , Math.toRadians(180)) // UP 2
                        .setTangent(Math.toRadians(150))
                        .splineToLinearHeading(new Pose2d(-58.6 , 16.3 , Math.toRadians(90)) , Math.toRadians(0))
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(0)) // First lap
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(0)) // Second lap
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) // Second back
                        .waitSeconds(0.7)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(0)) // Third Lap
                        .waitSeconds(0.8)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) // Third back
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    // Idee: reverse
}