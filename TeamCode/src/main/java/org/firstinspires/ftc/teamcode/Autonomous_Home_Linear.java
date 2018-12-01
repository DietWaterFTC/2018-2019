package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Autonomous Home Linear", group="Autonomous")
//@Disabled
public class Autonomous_Home_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo tokenDelivery = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        tokenDelivery = hardwareMap.get(Servo.class, "token_delivery");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        tokenDelivery.setPosition(.5);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            setStop();

            setForward(9);

            setLeft(15);

            setForward(27);

            setLeft(32);

            setBackward(35);

            tokenDelivery.setPosition(.7);

            setForward(5);

            sleep(10000);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    public void setStop() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }

    public void setForward(long distance) {
        leftDrive.setPower(.5);
        rightDrive.setPower(.5);
        sleep(distance * 31);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }

    public void setBackward(long distance) {
        leftDrive.setPower(-.5);
        rightDrive.setPower(-.5);
        sleep(distance * 31);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }

    public void setLeft(long distance) {
        rightDrive.setPower(.5);
        sleep(distance * 31);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }

    public void setRight(long distance) {
        leftDrive.setPower(.5);
        sleep(distance * 31);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(1000);
    }
}
