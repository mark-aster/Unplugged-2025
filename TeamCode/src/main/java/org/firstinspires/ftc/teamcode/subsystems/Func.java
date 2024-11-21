package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Func
{
    public static double lastTime;

    public static void SetPosition(DcMotorEx motor, int position)
    {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(1);
    }

    public static double deltaTime() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTime) / 1e9;
        lastTime = currentTime;
        return deltaTime;
    }

    public static double map(double x, double a, double b, double c, double d)
    {
        return c + (x - a) * (d - c) / (b - a);
    }
}
