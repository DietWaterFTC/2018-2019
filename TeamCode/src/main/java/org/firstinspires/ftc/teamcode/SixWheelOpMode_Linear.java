package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="6 Wheel Base, Main Functions", group="Linear Opmode")
public class SixWheelOpMode_Linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armBaseRight = null;
    private DcMotor armBaseLeft = null;
    private DcMotor armExtend = null;
    private DcMotor armCollector = null;
    private DcMotor lifterDown = null;
    private DcMotor lifterUp = null;
    private Servo tokenDelivery = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        armBaseLeft = hardwareMap.get(DcMotor.class, "arm_base_left");
        armBaseRight = hardwareMap.get(DcMotor.class, "arm_base_right");
        armExtend = hardwareMap.get(DcMotor.class, "arm_extend");
        armCollector = hardwareMap.get(DcMotor.class, "arm_collector");
        lifterDown = hardwareMap.get(DcMotor.class, "lifter_down");
        lifterUp = hardwareMap.get(DcMotor.class, "lifter_up");
        tokenDelivery = hardwareMap.get(Servo.class, "token_delivery");
		
		// Set direction of all motors
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        armBaseLeft.setDirection(DcMotor.Direction.FORWARD);
        armBaseLeft.setDirection(DcMotor.Direction.REVERSE);
        armExtend.setDirection(DcMotor.Direction.FORWARD);
        armCollector.setDirection(DcMotor.Direction.REVERSE);
        lifterDown.setDirection(DcMotor.Direction.FORWARD);
        lifterUp.setDirection(DcMotor.Direction.FORWARD);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double armBasePower;
            double armExtendPower;
            double nitro;
            double armNitro;
            double armCollectorPower;
            double lifterDownPower = 0;
            double lifterUpPower = 0;
			
			// Temporary token delivery code.
            if (gamepad1.b) {
                tokenDelivery.setPosition(.1);
            } else {
                tokenDelivery.setPosition(.5);
            }

            // Lifter controls
            if (gamepad1.left_trigger > 0) {
                lifterDownPower = gamepad1.left_trigger;
                lifterUpPower = -gamepad1.left_trigger;
            } else if (gamepad1.right_trigger > 0) {
                lifterUpPower = gamepad1.right_trigger / 2;
                lifterDownPower = -gamepad1.right_trigger / 2;
            } else {
                lifterDownPower = 0;
                lifterUpPower = 0;
            }

            // POV Mode uses left stick to go forward, and right stick to turn.
            double drive = gamepad1.left_stick_y / 2;
            double turn;
            if (drive != 0) {
                turn  = gamepad1.right_stick_x / 2;
            } else {
                turn = gamepad1.right_stick_x / 3;
            }

            if (gamepad1.right_bumper) {
                nitro = 2;
            } else {
                nitro = 1;
            }

            armBasePower = gamepad2.left_stick_y/4;
            armExtendPower = gamepad2.right_stick_y/4;
            armNitro = (gamepad2.right_trigger * 4) + 1;
			
			// Turns on and off the arm collector end.
            if (gamepad2.a) {
                armCollectorPower = .75;
            } else if (gamepad2.b) {
                armCollectorPower = -.75;
            } else if (gamepad2.y) {
                armCollectorPower = 1;
            } else {
                armCollectorPower = 0;
            }
			
			// Does turning math and ensures no motors are overpowered.
            leftPower = Range.clip((drive - turn) * nitro , -1.0, 1.0) ;
            rightPower = Range.clip((drive + turn) * nitro, -1.0, 1.0) ;
            armBasePower = Range.clip(armBasePower * armNitro, -1, 1) ;
            armExtendPower = Range.clip(armExtendPower * armNitro, -1.0, 1.0) ;
            armCollectorPower = Range.clip(armCollectorPower, -1, 1);
            lifterDownPower = Range.clip(lifterDownPower, -1.0, 1.0);
            lifterUpPower = Range.clip(lifterUpPower, -1.0, 1.0);

            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            armBaseLeft.setPower(armBasePower);
            armBaseRight.setPower(armBasePower);
            armExtend.setPower(armExtendPower);
            armCollector.setPower(armCollectorPower);
            lifterDown.setPower(lifterDownPower);
            lifterUp.setPower(lifterUpPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drive Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Arm Motors", "base (%.2f), extend (%.2f)", armBasePower, armExtendPower);
            telemetry.addData("Arm Collector", "power (%.2f)", armCollectorPower);
            telemetry.addData("Lifter UP/DOWN", "up (%.2f), down (%.2f)",lifterUpPower, lifterDownPower);
            telemetry.update();
        }
    }
}
