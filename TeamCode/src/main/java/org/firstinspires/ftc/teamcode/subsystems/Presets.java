package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

public class Presets
{
    public static class Scorer
    {
        public static Action extendViper(int position) {
            return telemetryPacket -> {
                setHorizontalVipers(position);
                return true;
            };
        }

        public static Action rotateViper(int position)
        {
            return telemetryPacket -> {
                setRotationVipers(position);
                return true;
            };
        }

        public static Action rotateHorizontalClaw(double position)
        {
            return telemetryPacket -> {
                setHorizontalServo(position);
                return true;
            };
        }

        public static Action preparePickupSpecimenPos() {
            return telemetryPacket -> {
                setVerticalVipers(0);
                setHorizontalVipers(0);
                setRotationVipers(100);
                setVerticalServo(0.0935);
                setHorizontalServo(0.5);
                return true;
            };
        }

        public static Action closeClawPos() {
            return telemetryPacket -> {
                setClawServo(0);
                return true;
            };
        }

        public static Action prepareSpecimenLeavePos() {
            return telemetryPacket -> {
                setVerticalVipers(958);
                setHorizontalVipers(931);
                setRotationVipers(723);
                setVerticalServo(0);
                setHorizontalServo(0.5);
                return true;
            };
        }

        public static Action leaveSpecimenPos() {
            return telemetryPacket -> {
                setClawServo(0);
                setHorizontalVipers(0);
                setVerticalVipers(0);
                return true;
            };
        }

        public static Action preparePickupSamplePos() {
            return telemetryPacket -> {
                setClawServo(0);
                setVerticalVipers(Constants.VERTICAL_VIPERS.MIN_POSITION);
                setHorizontalVipers(Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND);
                setHorizontalServo(0);
                setVerticalServo(0.4124);
                setRotationVipers(495);
                return true;
            };
        }

        public static Action openClawPos() {
            return telemetryPacket -> {
                setClawServo(1);
                return true;
            };
        }

        public static Action pickupSamplePos() {
            return telemetryPacket -> {
                setHorizontalVipers(Constants.INTAKE_VIPERS.MIN_POSITION_EXTEND);
                setHorizontalServo(1);
                setVerticalServo(0);
                return true;
            };
        }

        public static Action prepareLeaveSamplePos() {
            return telemetryPacket -> {
                setVerticalVipers(1570);
                setRotationVipers(1853);
                setHorizontalVipers(1794);
                setVerticalServo(0.475);
                setHorizontalServo(0.5186);
                return true;
            };
        }

        public static void setVerticalVipers(int position)
        {
            Func.SetMotorPosition(Motors.armLeft, position);
            Func.SetMotorPosition(Motors.armRight, position);
        }

        public static void setHorizontalVipers(int position)
        {
            Func.SetMotorPosition(Motors.intakeExtend, position);
        }

        public static void setRotationVipers(int position)
        {
            Func.SetMotorPosition(Motors.intakeRotate, position);
        }

        public static void setVerticalServo(double position)
        {
            Servos.verticalRotate.setPosition(position);
        }

        public static void setHorizontalServo(double position)
        {
            Servos.horizontalRotate.setPosition(position);
        }

        public static void setClawServo(double position)
        {
            Servos.clawRotate.setPosition(position);
        }
    }
}
