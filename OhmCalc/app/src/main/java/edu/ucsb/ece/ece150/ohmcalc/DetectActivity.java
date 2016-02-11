package edu.ucsb.ece.ece150.ohmcalc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class DetectActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private CameraBridgeViewBase mOpenCvCameraView;
    public String TAG = "OpenCV";
    public File mCascadeFile;
    public CascadeClassifier haarCascade=null;
    //private CvHaarClassifierCascade cascadeClassifier = null;
    private Mat mRgba;
    private String filename = "cascade.xml";
    private int counter = 0;
    private MatOfRect faces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.OpenCVCameraPreview);
        mOpenCvCameraView.setFocusable(true);
        mOpenCvCameraView.setMaxFrameSize(640, 360);
        //mOpenCvCameraView.setRotation(50f);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
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
                        InputStream is = getResources().getAssets().open("haarcascade_frontalface_default_iso.xml");
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
                        //haarCascade = new CascadeClassifier(mCascadeFile.getAbsolutePath());
                        haarCascade = new CascadeClassifier("/mnt/sdcard/test/haarcascade_resistors.xml");

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

    protected void writeFile(){
        String string = "";
        Log.i("Write a File", filename);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat mGray = inputFrame.gray();
        mRgba = inputFrame.rgba();
        /*if (mIsFrontCamera)
        {
            Core.flip(mRgba, mRgba, 1);
        }*/

        //Detecting face in the frame

        if(haarCascade != null) {
            Log.i("haha", "haha");
            //if (counter == 4){
            haarCascade.detectMultiScale(mGray, faces, 1.5, 28, 2, new Size(60, 20), new Size());
            counter = 0;
            //}
        }
        Rect[] facesArray = faces.toArray();
        Log.i("NumOfFaces",Integer.toString(facesArray.length));
        for (int i = 0; i < facesArray.length; i++)
            Core.rectangle(mRgba, facesArray[i].tl(), facesArray[i].br(), new Scalar(100), 3);
        return mRgba;



        //return inputFrame.rgba();
    }
}