package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Crater Linear", group="Autonomous")
//@Disabled
public class Autonomous_Crater_Linear extends LinearOpMode {

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
        tokenDelivery.setPosition(.2);
        //leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            setStop();

            setForward(13);

            setRight(42);

            setBackward(72);

            setLeft(10);

            setBackward(75);

            setRight(8);

            tokenDelivery.setPosition(0);

            setLeft(8);

            setForward(20);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            break;
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
        runtime.reset();
        while(opModeIsActive() && runtime.milliseconds() < distance * 31) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        //sleep(1000);
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
