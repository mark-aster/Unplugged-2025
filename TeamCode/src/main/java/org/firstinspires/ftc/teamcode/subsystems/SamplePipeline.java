package org.firstinspires.ftc.teamcode.subsystems;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.List;

public class SamplePipeline extends OpenCvPipeline
{
    public SamplePipeline(OpenCvWebcam camera)
    {
        webcam = camera;
    }
    public double angle;
    boolean viewportPaused;
    OpenCvWebcam webcam;

    @Override
    public Mat processFrame(Mat input) {
        // Convert the frame to HSV
        Mat hsvMat = new Mat();
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        // Threshold the image to get only blue colors
        Mat mask = new Mat();
        Scalar lowerBlue = new Scalar(90, 100, 100); // Adjusted lower bound for HSV blue
        Scalar upperBlue = new Scalar(130, 255, 255); // Adjusted upper bound for HSV blue
        Core.inRange(hsvMat, lowerBlue, upperBlue, mask);

        // Apply morphological operations to reduce noise
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.erode(mask, mask, kernel);
        Imgproc.dilate(mask, mask, kernel);

        // Find contours in the masked image
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Loop over all contours found
        for (MatOfPoint contour : contours) {
            // Filter contours by area to avoid noise
            double area = Imgproc.contourArea(contour);
            if (area > 500) { // Adjust this threshold based on your needs
                // Get the minimum area rectangle that bounds the contour
                RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

                // Draw the rotated rectangle
                Point[] boxPoints = new Point[4];
                rotatedRect.points(boxPoints);
                for (int i = 0; i < 4; i++) {
                    Imgproc.line(input, boxPoints[i], boxPoints[(i + 1) % 4], new Scalar(0, 255, 0), 2);
                }

                // Get the angle of the rectangle
                angle = rotatedRect.angle;

                if (rotatedRect.size.width < rotatedRect.size.height) {
                    angle += 90;  // Adjust for vertical orientation
                }

                if (angle < 0) {
                    angle += 360;  // Wrap around to ensure angle is positive
                } else if (angle >= 360) {
                    angle -= 360;  // Wrap around to keep it within 360 degrees
                }

                Imgproc.putText(input, String.format("Angle: %.2f", angle),
                        new Point(rotatedRect.center.x, rotatedRect.center.y),
                        Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(255, 255, 255), 1);

            }
        }

        // Release resources
        hsvMat.release();
        mask.release();

        // Return the input image with the rectangle drawn
        return input;
    }


    @Override
    public void onViewportTapped()
    {
        /*
         * The viewport (if one was specified in the constructor) can also be dynamically "paused"
         * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
         * when you need your vision pipeline running, but do not require a live preview on the
         * robot controller screen. For instance, this could be useful if you wish to see the live
         * camera preview as you are initializing your robot, but you no longer require the live
         * preview after you have finished your initialization process; pausing the viewport does
         * not stop running your pipeline.
         *
         * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
         */

        viewportPaused = !viewportPaused;

        if(viewportPaused)
        {
            webcam.pauseViewport();
        }
        else
        {
            webcam.resumeViewport();
        }
    }
}