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
                        .forward(22)
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(110))
                        .splineToLinearHeading(new Pose2d(-44.9 , 22.6, Math.PI / 2),Math.toRadians(180))
                        .forward(30)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-49.1, 11.1 , Math.PI / 2) , Math.toRadians(90))
                        .forward(40)
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-60.4 , 21.5 , Math.PI / 2) , Math.toRadians(90))
                        .forward(35)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // First lap
                        .waitSeconds(1.2)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) //First  back
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // Second lap
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(180))
                        .splineToLinearHeading(new Pose2d(-57.2 , 58.2 , Math.PI / 2) , Math.toRadians(180)) // Second back
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(5.0 , 34.2 , Math.PI / 2) , Math.toRadians(180)) // Third Lap
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