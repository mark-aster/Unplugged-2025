package subsystems.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motors
{
    public static DcMotor leftFront;
    public static DcMotor leftRear;
    public static DcMotor rightFront;
    public static DcMotor rightRear;

    public static DcMotor armLeft;
    public static DcMotor armRight;
    public static DcMotor intakeExtend;
    public static DcMotor intakeRotate;

    public static DcMotor[] allMotors = new DcMotor[8];

    public static void init(HardwareMap hardwareMap) {
        try
        {
            getMotors(hardwareMap);
            setZeroPowerBehaviour();
            setDirection();
            setAllMotors();
        }
        catch (Exception ignored) {}
    }

    private static void getMotors(HardwareMap hardwareMap)
    {
        leftFront = hardwareMap.tryGet(DcMotor.class, "leftFront");
        leftRear = hardwareMap.tryGet(DcMotor.class, "leftBack");
        rightFront = hardwareMap.tryGet(DcMotor.class, "rightFront");
        rightRear = hardwareMap.tryGet(DcMotor.class, "rightBack");

        armLeft = hardwareMap.tryGet(DcMotor.class, "armLeft");
        armRight = hardwareMap.tryGet(DcMotor.class, "armRight");
        intakeExtend = hardwareMap.tryGet(DcMotor.class, "intakeExtend");
        intakeRotate = hardwareMap.tryGet(DcMotor.class, "intakeRotate");
    }

    private static void setZeroPowerBehaviour()
    {
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private static void setDirection()
    {
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        armRight.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeRotate.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private static void setAllMotors() {
        DcMotor[] motors = {leftFront, rightFront, leftRear, rightRear, armLeft, armRight, intakeExtend, intakeRotate};

        for (int i = 0; i < motors.length; i++) {
            if (motors[i] != null) {
                allMotors[i] = motors[i];
            } else {
                allMotors[i] = null;
            }
        }
    }

}
