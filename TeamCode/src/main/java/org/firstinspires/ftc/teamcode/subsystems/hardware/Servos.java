package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Constants;

public class Servos
{
    public static Servo verticalRotate;
    public static Servo horizontalRotate;
    public static Servo clawRotate;

    public static void init(HardwareMap hardwareMap) {
        try
        {
            horizontalRotate = hardwareMap.get(Servo.class, "horizontal");
            verticalRotate = hardwareMap.get(Servo.class, "vertical");
            clawRotate = hardwareMap.get(Servo.class, "claw");

//            horizontalRotate.scaleRange(Constants.INTAKE_CLAW.HORIZONTAL_MIN, Constants.INTAKE_CLAW.HORIZONTAL_MAX);
//            verticalRotate.scaleRange(Constants.INTAKE_CLAW.VERTICAL_MIN, Constants.INTAKE_CLAW.VERTICAL_MAX);
//            clawRotate.scaleRange(Constants.INTAKE_CLAW.CLOSED, Constants.INTAKE_CLAW.OPEN);
        }
        catch (Exception ignore) {}
    }
}
