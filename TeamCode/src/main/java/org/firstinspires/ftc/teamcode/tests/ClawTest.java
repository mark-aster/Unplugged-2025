package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Input;
import org.firstinspires.ftc.teamcode.subsystems.hardware.Servos;

@TeleOp(name = "Claw Test", group = "Tests")
public class ClawTest extends LinearOpMode
{
    boolean opened = false;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Servos.init(hardwareMap);
        waitForStart();
        while(opModeIsActive())
        {
            if(Input.onKeyDown("a", gamepad1.a))
            {
                Servos.rightClaw.setPosition(opened ? 1 : 0);
                Servos.leftClaw.setPosition(opened ? 0 : 1);
                opened = !opened;
            }
        }
    }
}
