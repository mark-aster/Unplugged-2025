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
        Mat hsvMat = new Mat();
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        Mat mask = new Mat();
        Scalar lowerBlue = new Scalar(90, 100, 100);
        Scalar upperBlue = new Scalar(130, 255, 255);
        Core.inRange(hsvMat, lowerBlue, upperBlue, mask);

        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.erode(mask, mask, kernel);
        Imgproc.dilate(mask, mask, kernel);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        RotatedRect largestRotatedRect = null;
        double maxArea = 0;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > 500 && area > maxArea)
            {
                RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

                maxArea = area;
                largestRotatedRect = rotatedRect;
            }
        }

        if (largestRotatedRect != null)
        {
            Point[] boxPoints = new Point[4];
            largestRotatedRect.points(boxPoints);
            for (int i = 0; i < 4; i++)
            {
                Imgproc.line(input, boxPoints[i], boxPoints[(i + 1) % 4], new Scalar(0, 255, 0), 2);
            }

            angle = largestRotatedRect.angle;

            if (largestRotatedRect.size.width < largestRotatedRect.size.height)
            {
                angle += 90;
            }

            if (angle < 0)
            {
                angle += 360;
            }
            else if (angle >= 360)
            {
                angle -= 360;
            }

            Imgproc.putText(input, String.format("Angle: %.2f", angle),
                    new Point(largestRotatedRect.center.x, largestRotatedRect.center.y),
                    Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(255, 255, 255), 1);
        }

        hsvMat.release();
        mask.release();

        return input;
    }




    @Override
    public void onViewportTapped()
    {
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