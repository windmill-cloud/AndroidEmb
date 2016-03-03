package edu.ucsb.ece.ece150.ohmcalcbase;

/**
 * Created by xuanwang on 2/6/16.
 */

import android.util.SparseIntArray;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;



public class OhmCalcImageProcessor {

    private static final int NUM_CODES = 10;

    private static final Scalar COLOR_BOUNDS[][] = {
            { new Scalar(0, 0, 0),   new Scalar(180, 250, 90) },    // black 0
            { new Scalar(0, 90, 50), new Scalar(20, 250, 100) },    // brown 1
            { new Scalar(0, 0, 0),   new Scalar(0, 0, 0) },         // red (defined by two bounds)
            { new Scalar(7, 170, 80), new Scalar(22, 250, 200)},   // orange 3
            { new Scalar(30, 170, 100), new Scalar(40, 250, 255) }, // yellow 4
            { new Scalar(70, 80, 100), new Scalar(100, 255, 200) },   // green 5
            { new Scalar(105, 80, 100), new Scalar(115, 255, 200) },  // blue 6
            { new Scalar(120, 40, 100), new Scalar(140, 250, 220) }, // purple 7
            { new Scalar(0,0, 50), new Scalar(180, 50, 80) },       // gray 8
            { new Scalar(0, 0, 90), new Scalar(180, 15, 250) },     // white 9
            { new Scalar(10,100, 100), new Scalar(40, 250, 175) },       // gold 10
            { new Scalar(0, 0, 70), new Scalar(180, 15, 90) }      // silver 11
    };

    // red wraps around in HSV, so we need two ranges
    private static Scalar LOWER_RED1 = new Scalar(0, 60, 80);
    private static Scalar UPPER_RED1 = new Scalar(10, 255, 200);
    private static Scalar LOWER_RED2 = new Scalar(160, 60, 80);
    private static Scalar UPPER_RED2 = new Scalar(179, 255, 200);


    private static Scalar LOWER_BROWN1 = new Scalar(0, 50, 20);
    private static Scalar UPPER_BROWN1 = new Scalar(20, 100, 100);
    private static Scalar LOWER_BROWN2 = new Scalar(160, 20, 20);
    private static Scalar UPPER_BROWN2 = new Scalar(179, 100, 100);


    private SparseIntArray _locationValues = new SparseIntArray(4);

    public Mat processFrame(CameraBridgeViewBase.CvCameraViewFrame frame)
    {
        Mat imageMat = frame.rgba();
        int cols = imageMat.cols();
        int rows = imageMat.rows();
        Scalar color = new Scalar(153, 160, 160, 255);

        Mat subMat = imageMat.submat(rows/2 -10, rows/2+20, cols/2 - 80, cols/2 + 60);
        Imgproc.rectangle(imageMat, new Point(cols / 2 - 80, rows / 2 - 10), new Point(cols / 2 + 60, rows / 2 + 20), color, 3);

        Mat filteredMat = new Mat();
        Imgproc.cvtColor(subMat, subMat, Imgproc.COLOR_RGBA2BGR);
        Imgproc.bilateralFilter(subMat, filteredMat, 7, 80, 80);
        Imgproc.cvtColor(filteredMat, filteredMat, Imgproc.COLOR_BGR2HSV);

        findLocations(filteredMat);

        if(_locationValues.size() >= 3)
        {
            // recover the resistor value by iterating through the centroid locations
            // in an ascending manner and using their associated colour values
            int k_tens = _locationValues.keyAt(0);
            int k_units = _locationValues.keyAt(1);
            int k_power = _locationValues.keyAt(2);

            int value = 10*_locationValues.get(k_tens) + _locationValues.get(k_units);
            value *= Math.pow(10, _locationValues.get(k_power));

            String valueStr;
            if(value >= 1e3 && value < 1e6)
                valueStr = String.valueOf(value/1e3) + " KOhm";
            else if(value >= 1e6)
                valueStr = String.valueOf(value/1e6) + " MOhm";
            else
                valueStr = String.valueOf(value) + " Ohm";

            if(value <= 1e9)
                Imgproc.putText(imageMat, valueStr, new Point(cols / 2 - 100, rows / 2 - 200), Core.FONT_HERSHEY_SIMPLEX,
                        2, color, 3);
        }

        //Mat rect0 = new Mat(new Rect(0,0,cols / 2 - 100, rows));



        //Rect roi = new Rect(0, 0, cols / 2 - 100, rows);
        //color(roi.size(), 8, Scalar(0, 125, 125))
        //Scalar colorBlack = new Scalar(0, 0, 0, 0);
        Imgproc.rectangle(imageMat, new Point(cols / 2 - 100, rows / 2 - 30), new Point(cols / 2 + 100, rows / 2 + 30), color, 3);
        //Core.line(imageMat, new Point(cols / 2 - 100, rows / 2), new Point(cols / 2 + 100, rows / 2), color, 2);

        return imageMat;
    }



    // find contours of colour bands and the x-coords of their centroids
    private void findLocations(Mat searchMat)
    {
        _locationValues.clear();
        SparseIntArray areas = new SparseIntArray(4);

        for(int i = 0; i < NUM_CODES; i++)
        {
            Mat mask = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Mat hierarchy = new Mat();

            if(i == 1)
            {
                // combine the two ranges for red
                Core.inRange(searchMat, LOWER_BROWN1, UPPER_BROWN1, mask);
                Mat rmask2 = new Mat();
                Core.inRange(searchMat, LOWER_BROWN2, UPPER_BROWN2, rmask2);
                Core.bitwise_or(mask, rmask2, mask);
            }
            else if (i == 2){ // if searching red
                // combine the two ranges for red
                Core.inRange(searchMat, LOWER_RED1, UPPER_RED1, mask);
                Mat rmask2 = new Mat();
                Core.inRange(searchMat, LOWER_RED2, UPPER_RED2, rmask2);
                Core.bitwise_or(mask, rmask2, mask);
            }
            else if (i == 9) {
                continue;
            }
            else
                Core.inRange(searchMat, COLOR_BOUNDS[i][0], COLOR_BOUNDS[i][1], mask);

            Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            for (int contIdx = 0; contIdx < contours.size(); contIdx++)
            {
                int area;
                if ((area = (int)Imgproc.contourArea(contours.get(contIdx))) > 30)
                {
                    Moments M = Imgproc.moments(contours.get(contIdx));
                    int cx = (int) (M.get_m10() / M.get_m00());

                    // if a colour band is split into multiple contours
                    // we take the largest and consider only its centroid
                    boolean shouldStoreLocation = true;
                    for(int locIdx = 0; locIdx < _locationValues.size(); locIdx++)
                    {
                        if(Math.abs(_locationValues.keyAt(locIdx) - cx) < 10)
                        {
                            if (areas.get(_locationValues.keyAt(locIdx)) > area)
                            {
                                shouldStoreLocation = false;
                                break;
                            }
                            else
                            {
                                _locationValues.delete(_locationValues.keyAt(locIdx));
                                areas.delete(_locationValues.keyAt(locIdx));
                            }
                        }
                    }

                    if(shouldStoreLocation)
                    {
                        areas.put(cx, area);
                        _locationValues.put(cx, i);
                    }
                }
            }
        }
    }
}