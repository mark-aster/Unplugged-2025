package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Func
{
    public static double lastTime;

    public static void SetMotorPosition(DcMotor motor, int position, double power)
    {
        motor.setPower(power);
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void SetMotorPosition(DcMotor motor, int position)
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

    public static int adjustPosition(int currentPosition, double input, int min, int max, double ticksPerSecond) {
        currentPosition += (int) (Func.deltaTime() * ticksPerSecond * input);
        return Math.max(min, Math.min(currentPosition, max));
    }

    public static double map(double x, double a, double b, double c, double d)
    {
        return c + (x - a) * (d - c) / (b - a);
    }
}
