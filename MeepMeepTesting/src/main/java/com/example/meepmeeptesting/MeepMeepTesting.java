package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        int TILE = 24;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, 58, -1.57079633))
                        .forward(22)
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-44.9 , 22.6, Math.PI / 2),Math.toRadians(340))
                        .forward(30)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-46.5, 14.5 , Math.PI / 2) , Math.toRadians(90))
                        .forward(40)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-61.2 , 10.7 , Math.PI / 2) , Math.toRadians(210))
                        .forward(40)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // first turn
                        .waitSeconds(0.75)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-58.8 , 54.4 , Math.PI / 2) , Math.toRadians(180)) // first back
                        .waitSeconds(0.75)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // second turn
                        .waitSeconds(0.7)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-58.8 , 54.4 , Math.PI / 2) , Math.toRadians(180)) // second back
                        .waitSeconds(0.7)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // third turn
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-58.8 , 54.4 , Math.PI / 2) , Math.toRadians(180)) // third back
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}