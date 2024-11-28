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
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, 58, -1.39626))
                        .forward(17)
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-45.9 , 13.5,  Math.toRadians(90)),Math.toRadians(180))
                        .setTangent(60)
                        .splineToLinearHeading(new Pose2d(-44.5 , 13.7 , Math.toRadians(90)) , Math.toRadians(180)) //UP
                        .setTangent(Math.toRadians(60))
                        .splineToLinearHeading(new Pose2d(-51.3, 11.7 , Math.PI / 2) , Math.toRadians(90))
                        .forward(45)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-59.4 , 13.5 , Math.PI / 2) , Math.toRadians(90))
                        .forward(39)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(0)) // First lap
                        .waitSeconds(1.2)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) //First  back
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(0)) // Second lap
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) // Second back
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(0)) // Third Lap
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) // Third back
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}