package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "IMU Test", group = "Sensor")
public class ImuTest extends LinearOpMode {

    private BNO055IMU imu;
    private Orientation angles;

    @Override
    public void runOpMode() {

        imu = hardwareMap.get(BNO055IMU.class, "imu");


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // fisier unde se salveaza!
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";

        imu.initialize(parameters);

        // calibrare
        telemetry.addData("Status", "Calibrating IMU...");
        telemetry.update();

        while (!isStopRequested() && !imu.isGyroCalibrated()) {
            telemetry.addData("Status", "Calibrating...");
            telemetry.update();
            sleep(50); // destul?
        }

        telemetry.addData("Status", "IMU Calibrated");
        telemetry.update();


        waitForStart();


        while (opModeIsActive()) {
                                                                    // nu uita de pozitii (Control hub , USB , logo)
            angles = imu.getAngularOrientation();


            telemetry.addData("Heading (Z)", angles.firstAngle);
            telemetry.addData("Roll (X)", angles.secondAngle);
            telemetry.addData("Pitch (Y)", angles.thirdAngle);
            telemetry.update();


            sleep(50);  // de intrebat daca este destul :/
        }
    }
}
