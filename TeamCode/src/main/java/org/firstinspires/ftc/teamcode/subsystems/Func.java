package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Func
{
    public static double lastTime;

    public static void SetMotorPosition(DcMotorEx motor, int position, double power)
    {
        motor.setPower(power);
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void SetMotorPosition(DcMotorEx motor, int position)
    {
        motor.setPower(1);
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
