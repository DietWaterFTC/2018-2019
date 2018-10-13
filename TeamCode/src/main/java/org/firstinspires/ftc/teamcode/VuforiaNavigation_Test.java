package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "VuforiaNavigation_Test", group = "Autonomous")
public class VuforiaNavigation_Test extends LinearOpMode {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;
    VuforiaTrackable target;
    VuforiaTrackableDefaultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGLMatrix phoneLocation;

    public static final String VUFORIA_KEY = "AdF2fDH/////AAABmQuWoIn1IkJhjVotLJb2ay8WNNW5MqbLJzfKlPU+ThOWdxjTQ2D4h96Yj+liCgL+eSnysijOv9CvpygVAeJysdh8jV3SZtlVrsMWWNWGSHsQ/8xhCofOzUui5NevYRAMwXTsV99CBORiGqMtCQnxKRY4MEFKm6kVVqOnEVtlcS5vErF6BM28eHU3YuX8LOUoXMuH1h3apl8s0eXjTHazCrxpNTy8kXkaIMwYcLcviqfWzsOe6BwFY9Ppf477uJJmM5oNYMqHhHU6TC8vGWE24wJhOpMObrQu3tiavXrvebLjds38AVf/gMF25WY+H6dgKh56Y/aV6DqmLo24qwLRF67C4j9G1S5rB05v8TajW30A";

    public void runOpMode() {

        setupVuforia();

        lastKnownLocation = createMatrix(0, 0, 0, 0, 0, 0);

        waitForStart();

        visionTargets.activate();

        while (opModeIsActive()) {

            OpenGLMatrix latestLocation = listener.getUpdatedRobotLocation();

            if (latestLocation != null) {
                lastKnownLocation = latestLocation;
            }

            telemetry.addData("Tracking: " + target.getName(), listener.isVisible());
            telemetry.addData("Last Known Location: " + formatMatrix(lastKnownLocation), listener.isVisible());

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
        target.setLocation(createMatrix(0, 500, 0, 90, 0, 90));

        phoneLocation = createMatrix(0, 0, 0, 0, 0,0);

        listener = (VuforiaTrackableDefaultListener) target.getListener();
        listener.setPhoneInformation(phoneLocation, parameters.cameraDirection);
    }

    public OpenGLMatrix createMatrix(float x, float y, float z, float u, float v, float w) {
        return OpenGLMatrix.translation(x,y,x).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, u, v, w));
    }

    public String formatMatrix(OpenGLMatrix matrix) {
        return matrix.formatAsTransform();
    }
}
