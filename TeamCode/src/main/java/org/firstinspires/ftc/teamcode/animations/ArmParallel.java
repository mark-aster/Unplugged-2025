package org.firstinspires.ftc.teamcode.animations;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Func;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Motors;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name = "Anim_ArmParallel", group = "Animations")
@Config
public class ArmParallel extends LinearOpMode
{
    private int position = 0;
    private boolean reversed = false;
    public static int Step;
    @Override
    public void runOpMode() throws InterruptedException
    {
        Servos.init(hardwareMap);
        Motors.Init(hardwareMap);
        Motors.intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive())
            playAnimation();
    }

    private void playAnimation()
    {
        if(!reversed)
        {
            if(position < 2700)
            {
                position+=Step;
            }
            else reversed = true;
        }
        else {
            if(position > 0)
            {
                position-=Step;
            }
            else reversed = false;
        }

        Func.SetMotorPosition((DcMotorEx) Motors.intakeRotate, position, 0.2);
        Servos.intakeVertical.setPosition(servoParallel());
    }

    private double servoParallel()
    {
        int motorPos = Motors.intakeRotate.getCurrentPosition();
        return 0.0001351549157*motorPos+0.3718019843366;
    }
}
