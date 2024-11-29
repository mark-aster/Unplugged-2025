package org.firstinspires.ftc.teamcode.subsystems.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.CONSTANTS;

public class Servos
{
    public static Servo verticalRotate;
    public static Servo horizontalRotate;
    public static Servo clawRotate;

    public static void init(HardwareMap hardwareMap) {
        try
        {
            verticalRotate = hardwareMap.get(Servo.class, "verticalRotate");
            horizontalRotate = hardwareMap.get(Servo.class, "horizontalRotate");
            clawRotate = hardwareMap.get(Servo.class, "clawRotate");

            verticalRotate.scaleRange(CONSTANTS.INTAKE_CLAW.CLAW_CLOSED, CONSTANTS.INTAKE_CLAW.CLAW_OPEN);
            //horizontalRotate.scaleRange();
        }
        catch (Exception ignore) {}
    }
}
