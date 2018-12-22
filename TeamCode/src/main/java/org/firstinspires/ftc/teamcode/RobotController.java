package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by sphun on 12/21/2018.
 */

public class RobotController {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public Servo tokenDelivery = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public RobotController(){

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        tokenDelivery = hwMap.get(Servo.class, "token_delivery");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        tokenDelivery.setPosition(.5);
    }
}
