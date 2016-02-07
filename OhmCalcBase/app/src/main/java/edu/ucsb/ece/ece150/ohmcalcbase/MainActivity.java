package edu.ucsb.ece.ece150.ohmcalcbase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.SeekBar;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;



public class MainActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    static {
        OpenCVLoader.initDebug();
    }

    private OhmCalcCameraView ohmCalcCameraView;
    private OhmCalcImageProcessor ohmCalcProcessor;

    private BaseLoaderCallback loaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    ohmCalcCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ohmCalcCameraView=(OhmCalcCameraView)

    findViewById(R.id.OhmCalcCameraView);
        ohmCalcCameraView.setVisibility(SurfaceView.VISIBLE);
        ohmCalcCameraView.setZoomControl((SeekBar) findViewById(R.id.CameraZoomControls));
        ohmCalcCameraView.setCvCameraViewListener(this);

        ohmCalcProcessor = new OhmCalcImageProcessor();

        SharedPreferences settings = getPreferences(0);
        if(!settings.getBoolean("shownInstructions", false))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setNeutralButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("shownInstructions", true);
            editor.apply();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (ohmCalcCameraView != null)
            ohmCalcCameraView.disableView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (ohmCalcCameraView != null)
            ohmCalcCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return ohmCalcProcessor.processFrame(inputFrame);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        loaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
    }
}

