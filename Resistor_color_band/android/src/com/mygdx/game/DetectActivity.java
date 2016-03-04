package com.mygdx.game;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.SurfaceView;
//import android.view.WindowManager;
//import android.widget.SeekBar;
//
//import org.opencv.android.BaseLoaderCallback;
//import org.opencv.android.CameraBridgeViewBase;
//import org.opencv.android.LoaderCallbackInterface;
//import org.opencv.android.OpenCVLoader;
//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
//import org.opencv.objdetect.CascadeClassifier;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//
//
//public class DetectActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {
//
//    //private CameraBridgeViewBase mOpenCvCameraView;
//
//    protected ZoomCameraView mOpenCvCameraView;
//    public String TAG = "OpenCV";
//    public File mCascadeFile;
//    public CascadeClassifier haarCascade=null;
//    //private CvHaarClassifierCascade cascadeClassifier = null;
//    private Mat mRgba;
//    private String filename = "cascade.xml";
//    private int counter = 0;
//    private MatOfRect faces;
//    //protected SeekBar zoomControl;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detect);
//        Log.i(TAG, "called onCreate");
//        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        mOpenCvCameraView = (ZoomCameraView) findViewById(R.id.OpenCVCameraPreview);
//        mOpenCvCameraView.setFocusable(true);
//        mOpenCvCameraView.setMaxFrameSize(640, 360);
//        //mOpenCvCameraView.setRotation(50f);
//        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
//        mOpenCvCameraView.setZoomControl((SeekBar) findViewById(R.id.CameraZoomControls));
//        mOpenCvCameraView.setCvCameraViewListener(this);
//
//    }
//
//    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
//
//
//        @Override
//        public void onManagerConnected(int status) {
//            switch (status) {
//                case LoaderCallbackInterface.SUCCESS:
//                {
//                    Log.i(TAG, "OpenCV loaded successfully");
//                    try{
//
//
//                        //InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
//                        InputStream is = getResources().getAssets().open("haarcascade_frontalface_default_iso.xml");
//                        //InputStream is = assetManager.open("haarcascade_frontalface_default.xml");
//
//
//                        if (is == null) {
//                            Log.e("Cascade Error", "input stream null");
//                        }
//                        File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
//
//                        mCascadeFile = new File(cascadeDir,"cascade.xml");
//                        FileOutputStream os = new FileOutputStream(mCascadeFile);
//
//                        //FileOutputStream os = new FileOutputStream(file);
//
//
//                        byte[] buffer = new byte[4096];
//
//                        int bytesRead;
//                        while((bytesRead = is.read(buffer)) != -1) {
//                            Log.i("content", buffer.toString());
//                            os.write(buffer, 0, bytesRead);
//
//                        }
//                        is.close();
//                        os.close();
//                        //haarCascade = new CascadeClassifier(mCascadeFile.getAbsolutePath());
//                        haarCascade = new CascadeClassifier("/mnt/sdcard/test/haarcascade_resistors.xml");
//
//                        if (haarCascade.empty())
//                        {
//                            Log.i("Cascade Error","Failed to load cascade classifier");
//                            haarCascade = null;
//                        }
//
//
//
//
//                    }
//                    catch(Exception e) {
//                        Log.e("Cascade Error: ", "Cascade not found");
//                        Log.e("Cascade Error: ", e.toString());
//
//                    }
//                    mOpenCvCameraView.enableView();
//                    faces = new MatOfRect();
//                }
//                break;
//                default:
//                {
//                    super.onManagerConnected(status);
//                } break;
//            }
//        }
//    };
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_10, this, mLoaderCallback);
//    }
//
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        if (mOpenCvCameraView != null)
//            mOpenCvCameraView.disableView();
//    }
//
//    public void onDestroy() {
//        super.onDestroy();
//        if (mOpenCvCameraView != null)
//            mOpenCvCameraView.disableView();
//    }
//
//    public void onCameraViewStarted(int width, int height) {
//        mRgba = new Mat(height, width, CvType.CV_8UC4);
//    }
//
//    public void onCameraViewStopped() {
//    }
//
//    protected void writeFile(){
//        String string = "";
//        Log.i("Write a File", filename);
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(string.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
//        Mat mGray = inputFrame.gray();
//        mRgba = inputFrame.rgba();
//        /*if (mIsFrontCamera)
//        {
//            Core.flip(mRgba, mRgba, 1);
//        }*/
//
//        //Detecting face in the frame
//
//        if(haarCascade != null) {
//            Log.i("haha", "haha");
//            //if (counter == 4){
//            haarCascade.detectMultiScale(mGray, faces, 1.5, 28, 2, new Size(60, 20), new Size());
//            counter = 0;
//            //}
//        }
//        Rect[] facesArray = faces.toArray();
//        Log.i("NumOfFaces", Integer.toString(facesArray.length));
//        for (int i = 0; i < facesArray.length; i++)
//            Core.rectangle(mRgba, facesArray[i].tl(), facesArray[i].br(), new Scalar(100), 3);
//        return mRgba;
//
//
//
//        //return inputFrame.rgba();
//    }
//
//
//
//
//}


        import android.app.Activity;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.hardware.Camera;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.util.SparseIntArray;
        import android.view.SurfaceView;
        import android.view.WindowManager;
        import android.widget.SeekBar;

        import org.opencv.android.BaseLoaderCallback;
        import org.opencv.android.CameraBridgeViewBase;
        import org.opencv.android.LoaderCallbackInterface;
        import org.opencv.android.OpenCVLoader;
        import org.opencv.core.Core;
        import org.opencv.core.CvType;
        import org.opencv.core.Mat;
        import org.opencv.core.MatOfPoint;
        import org.opencv.core.MatOfRect;
        import org.opencv.core.Point;
        import org.opencv.core.Rect;
        import org.opencv.core.Scalar;
        import org.opencv.core.Size;
        import org.opencv.imgproc.Imgproc;
        import org.opencv.imgproc.Moments;
        import org.opencv.objdetect.CascadeClassifier;

        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;


public class DetectActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    //private CameraBridgeViewBase mOpenCvCameraView;

    protected ZoomCameraView mOpenCvCameraView;
    public String TAG = "OpenCV";
    public File mCascadeFile;
    public CascadeClassifier haarCascade=null;
    //private CvHaarClassifierCascade cascadeClassifier = null;
    private Mat mRgba;
    private String filename = "cascade.xml";
    private int counter = 0;
    private MatOfRect faces;
    //protected SeekBar zoomControl;

    private int NUM_CODES = 10;


    private static final Scalar COLOR_BOUNDS[][] = {
            { new Scalar(0, 0, 0),   new Scalar(180, 250, 90) },    // black 0
            { new Scalar(0, 90, 50), new Scalar(20, 250, 100) },    // brown 1
            { new Scalar(0, 0, 0),   new Scalar(0, 0, 0) },         // red (defined by two bounds)
            { new Scalar(5, 80, 60), new Scalar(15, 250, 250)},   // orange 3
            { new Scalar(30, 170, 100), new Scalar(40, 250, 255) }, // yellow 4
            { new Scalar(70, 80, 100), new Scalar(95, 255, 200) },   // green 5
            { new Scalar(100, 60, 60), new Scalar(115, 255, 230) },  // blue 6
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mOpenCvCameraView = (ZoomCameraView) findViewById(R.id.OpenCVCameraPreview);
        mOpenCvCameraView.setFocusable(true);
        mOpenCvCameraView.setMaxFrameSize(640, 400);
        //mOpenCvCameraView.setRotation(50f);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setZoomControl((SeekBar) findViewById(R.id.CameraZoomControls));
        mOpenCvCameraView.setCvCameraViewListener(this);

    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {


        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    try{


                        //InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
                        InputStream is = getResources().getAssets().open("haarcascade_resistors_0.xml");
                        //InputStream is = assetManager.open("haarcascade_frontalface_default.xml");


                        if (is == null) {
                            Log.e("Cascade Error", "input stream null");
                        }
                        File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);

                        mCascadeFile = new File(cascadeDir,"cascade.xml");
                        FileOutputStream os = new FileOutputStream(mCascadeFile);

                        //FileOutputStream os = new FileOutputStream(file);


                        byte[] buffer = new byte[4096];

                        int bytesRead;
                        while((bytesRead = is.read(buffer)) != -1) {
                            Log.i("content", buffer.toString());
                            os.write(buffer, 0, bytesRead);

                        }
                        is.close();
                        os.close();
                        haarCascade = new CascadeClassifier(mCascadeFile.getAbsolutePath());
                        //haarCascade = new CascadeClassifier("/mnt/sdcard/test/haarcascade_resistors.xml");

                        if (haarCascade.empty())
                        {
                            Log.i("Cascade Error","Failed to load cascade classifier");
                            haarCascade = null;
                        }




                    }
                    catch(Exception e) {
                        Log.e("Cascade Error: ", "Cascade not found");
                        Log.e("Cascade Error: ", e.toString());

                    }
                    mOpenCvCameraView.enableView();
                    faces = new MatOfRect();
                }
                break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_10, this, mLoaderCallback);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    public void onCameraViewStopped() {
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat mGray = inputFrame.gray();
        mRgba = inputFrame.rgba();
        Mat mBgr = new Mat();
        Mat mHsv = new Mat();
        Mat mask = new Mat();

        int cols = mRgba.cols();
        int rows = mRgba.rows();

        Log.d("cols", Integer.toString(cols));
        Log.d("rows", Integer.toString(rows));
        Mat filteredMAt = new Mat();


        Imgproc.cvtColor(mRgba, mBgr, Imgproc.COLOR_RGBA2BGR);
        Imgproc.bilateralFilter(mBgr, filteredMAt, 7, 80, 80);
        Imgproc.cvtColor(filteredMAt, mHsv, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(mBgr, mHsv, Imgproc.COLOR_BGR2HSV);

        Core.inRange(mHsv, COLOR_BOUNDS[3][0], COLOR_BOUNDS[3][1], mask);
/*
        Core.inRange(mHsv, LOWER_RED1, UPPER_RED1, mask);
        Mat rmask2 = new Mat();
        Core.inRange(mHsv, LOWER_RED2, UPPER_RED2, rmask2);
        Core.bitwise_or(mask, rmask2, mask);
*/
/*
        Core.inRange(mHsv, LOWER_BROWN1, UPPER_BROWN1, mask);
        Mat rmask2 = new Mat();
        Core.inRange(mHsv, LOWER_BROWN2, UPPER_BROWN2, rmask2);
        Core.bitwise_or(mask, rmask2, mask);
*/

        Core.bitwise_not(mask, mask);




        /*
        Core.inRange(mHsv, LOWER_RED2, UPPER_RED2, rmask2);
        Core.bitwise_or(mask, rmask2, mask);
*/

        /*if (mIsFrontCamera)
        {
            Core.flip(mRgba, mRgba, 1);
        }*/

        //Detecting face in the frame
        counter++;
        if (haarCascade != null) {
            Log.i("haha", "haha");
            if (counter == 6) {
                haarCascade.detectMultiScale(mGray, faces, 1.5, 25, 2, new Size(120, 40), new Size());
                counter = 0;
            }
        }

        Rect[] facesArray = faces.toArray();
        Log.i("NumOfFaces", Integer.toString(facesArray.length));
        ArrayList<Mat> matArray = new ArrayList<Mat>();
        ArrayList<SparseIntArray> codeArray = new ArrayList<SparseIntArray>();

        for (int i = 0; i < facesArray.length; i++) {
            double x0 = facesArray[i].tl().x;
            double y0 = facesArray[i].tl().y;
            double x1 = facesArray[i].br().x;
            double y1 = facesArray[i].br().y;
            Log.d("x0", String.valueOf((int)x0));
            Log.d("y0", String.valueOf((int)y0));
            Log.d("x1", String.valueOf((int)x1));
            Log.d("y1", String.valueOf((int)y1));

            Point rectCenter = new Point(facesArray[i].tl().x + facesArray[i].width / 2,
                    facesArray[i].tl().y + facesArray[i].height / 2);
            Point smallRectTl = new Point(rectCenter.x - 0.5 * facesArray[i].width / 2,
                    rectCenter.y + 0.02 * facesArray[i].height / 2);
            Point smallRectBr = new Point(rectCenter.x + 0.6 * facesArray[i].width / 2,
                    rectCenter.y + 0.5 * facesArray[i].height / 2);
            Point strPos = new Point(facesArray[i].tl().x , facesArray[i].tl().y - 15);

            Rect smallRoi = new Rect(smallRectTl, smallRectBr);
            Core.rectangle(mRgba, smallRoi.tl(), smallRoi.br(), new Scalar(100), 3);
            Rect roi = new Rect(facesArray[i].tl(), facesArray[i].br());
            //Mat subMat = mRgba.submat(roi);
            Mat subMat = mHsv.submat(smallRoi);

            String dispValStr = getResValue(findLocations(subMat));
            if (dispValStr != null || dispValStr != "") {
                Core.putText(mRgba, dispValStr, strPos, Core.FONT_HERSHEY_SIMPLEX,
                        0.5, new Scalar(0, 0, 100, 225), 1);
            }

        }


        Mat newRgba = new Mat();

        mRgba.copyTo(newRgba, mask);
        //Core.bitwise_and(mRgba, mask, newRgba);


        for (int i = 0; i < facesArray.length; i++){
            //Core.rectangle(mRgba, facesArray[i].tl(), facesArray[i].br(), new Scalar(100), 3);
            Core.rectangle(newRgba, facesArray[i].tl(), facesArray[i].br(), new Scalar(100), 3);
        }
        //return mRgba;
        return newRgba;


        //return inputFrame.rgba();
    }

    private SparseIntArray findLocations(Mat searchMat)  {
        SparseIntArray locationValues = new SparseIntArray();
        locationValues.clear();
        SparseIntArray areas = new SparseIntArray(4);
        int searchMatArea = searchMat.height() * searchMat.width();
        Log.d("AreaSearchMat", String.valueOf(searchMatArea));

        for(int i = 0; i < NUM_CODES; i++) {
            Mat mask = new Mat();
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
            Mat hierarchy = new Mat();
            Log.d("i", String.valueOf(i));

            if(i == 1){
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
            else if (i == 9 || i == 8) {
                continue;
            }
            else { // other colors
                Core.inRange(searchMat, COLOR_BOUNDS[i][0], COLOR_BOUNDS[i][1], mask);
            }

            Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            Log.d("ContourSize", String.valueOf(contours.size()));
            for (int contIdx = 0; contIdx < contours.size(); contIdx++) {
                int area;
                if ((area = (int)Imgproc.contourArea(contours.get(contIdx))) > (int) (searchMatArea * 2 / 280f)) {
                    Log.d("area", String.valueOf(area));
                    Moments M = Imgproc.moments(contours.get(contIdx));
                    int cx = (int) (M.get_m10() / M.get_m00());

                    // if a color band is split into multiple contours
                    // we take the largest and consider only its centroid
                    boolean shouldStoreLocation = true;
                    for(int locIdx = 0; locIdx < locationValues.size(); locIdx++)
                    {
                        Log.d("keyAt", String.valueOf(locationValues.keyAt(locIdx)));
                        Log.d("cx", String.valueOf(cx));
                        if(Math.abs(locationValues.keyAt(locIdx) - cx) < 10)
                        {
                            if (areas.get(locationValues.keyAt(locIdx)) > area)
                            {
                                shouldStoreLocation = false;
                                break;
                            }
                            else
                            {
                                locationValues.delete(locationValues.keyAt(locIdx));
                                areas.delete(locationValues.keyAt(locIdx));
                            }
                        }
                    }


                    if(shouldStoreLocation)
                    {
                        areas.put(cx, area);
                        locationValues.put(cx, i);
                    }
                }
            }
        }
        Log.d("locSize", String.valueOf(locationValues.size()));
        Log.d("locs:", String .valueOf(locationValues));
        return locationValues;
    }


    private String getResValue(SparseIntArray locValues){

        if(locValues.size() >= 3) {
            // recover the resistor value by iterating through the centroid locations
            // in an ascending manner and using their associated colour values
            int k_tens = locValues.keyAt(0);
            int k_units = locValues.keyAt(1);
            int k_power = locValues.keyAt(2);

            int value = 10 * locValues.get(k_tens) + locValues.get(k_units);
            value *= Math.pow(10, locValues.get(k_power));

            String valueStr;
            if(value >= 1e3 && value < 1e6)
                valueStr = String.valueOf(value/1e3) + " KOhm";
            else if(value >= 1e6)
                valueStr = String.valueOf(value/1e6) + " MOhm";
            else
                valueStr = String.valueOf(value) + " Ohm";

            if(locValues.size() >= 4){
                k_tens = locValues.keyAt(0);
                int k_unit1 = locValues.keyAt(1);
                int k_unit2 = locValues.keyAt(2);
                k_power = locValues.keyAt(3);

                if(k_tens == 1 && k_unit1 == 0 && k_unit2 == 0 && k_power == 1) {
                    value = 100 * locValues.get(k_tens) + 10 * locValues.get(k_unit1) + locValues.get(k_unit2);
                    value *= Math.pow(10, locValues.get(k_power));

                    if (value >= 1e3 && value < 1e6)
                        valueStr = String.valueOf(value / 1e3) + " KOhm";
                    else if (value >= 1e6)
                        valueStr = String.valueOf(value / 1e6) + " MOhm";
                    else
                        valueStr = String.valueOf(value) + " Ohm";
                }
            }


            if(value <= 1e9)
                return valueStr;

        }
        return "";
    }



}
