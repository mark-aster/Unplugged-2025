package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-59+24, -61, Math.toRadians(90)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+6, Math.toRadians(90+14.3)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+7, Math.toRadians(180+14.3)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+6, Math.toRadians(90+30.2)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+7, Math.toRadians(180+14.3)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+6, Math.toRadians(90+42.7)))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+7, Math.toRadians(180+14.3)))

                        .lineToSplineHeading(new Pose2d(-35, -10, Math.toRadians(-30)))
                        .turn(Math.toRadians(55), Math.toRadians(15), Math.toRadians(15))
                        .lineToSplineHeading(new Pose2d(-59+24-6, -61+7, Math.toRadians(180+14.3)))

                        .lineToSplineHeading(new Pose2d(47,-56, Math.toRadians(90)))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}