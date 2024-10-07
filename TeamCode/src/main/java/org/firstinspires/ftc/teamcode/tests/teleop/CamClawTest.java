package org.firstinspires.ftc.teamcode.tests.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.OpenCV;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name = "CamClawTest", group = "TeleopTests")
public class CamClawTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        OpenCV.init(hardwareMap);
        Servos.init(hardwareMap);
        waitForStart();
        while (opModeIsActive())
        {
            RotateClaw(OpenCV.getAngle());
        }
    }

    private void RotateClaw(double angle)
    {
        double newAngle = map(angle, 0, 180, 1, 0);
        Servos.intakeHorizontal.setPosition(newAngle);
        telemetry.addData("Angle", angle);
        telemetry.update();
    }

    public static double map(double x, double a, double b, double c, double d)
    {
        return c + (x - a) * (d - c) / (b - a);
    }
}
