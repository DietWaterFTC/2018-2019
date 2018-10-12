package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "VuforiaNavigation_Test")
public class VuforiaNavigation_Test extends LinearOpMode {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;
    VuforiaTrackable target;
    VuforiaTrackableDefaultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGLMatrix phoneLocation;

    public static final String VUFORIA_KEY = "AdF2fDH/////AAABmQuWoIn1IkJhjVotLJb2ay8WNNW5MqbLJzfKlPU+ThOWdxjTQ2D4h96Yj+liCgL+eSnysijOv9CvpygVAeJysdh8jV3SZtlVrsMWWNWGSHsQ/8xhCofOzUui5NevYRAMwXTsV99CBORiGqMtCQnxKRY4MEFKm6kVVqOnEVtlcS5vErF6BM28eHU3YuX8LOUoXMuH1h3apl8s0eXjTHazCrxpNTy8kXkaIMwYcLcviqfWzsOe6BwFY9Ppf477uJJmM5oNYMqHhHU6TC8vGWE24wJhOpMObrQu3tiavXrvebLjds38AVf/gMF25WY+H6dgKh56Y/aV6DqmLo24qwLRF67C4j9G1S5rB05v8TajW30A"

    public void runOpMode() {

        waitForStart();

        while (opModeIsActive()) {

            telemetry.update();
            idle();

        }

    }

    public void setupVuforia() {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);

        visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("2018-PicturesDB");

        target = visionTargets.get(0);
        target.setName("Nebula");
    }
}
