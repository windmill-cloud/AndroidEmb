package com.mygdx.game;
//
//import android.content.Context;
//import android.hardware.Camera;
//import android.util.AttributeSet;
//import android.widget.SeekBar;
//
//import org.opencv.android.JavaCameraView;
//
///**
// * Created by xuanwang on 2/11/16.
// */
//
//
//
//public class ZoomCameraView extends JavaCameraView {
//    public ZoomCameraView(Context context, int cameraId) {
//        super(context, cameraId);
//    }
//
//    public ZoomCameraView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    protected SeekBar seekBar;
//
//    public void setZoomControl(SeekBar _seekBar)
//    {
//        seekBar=_seekBar;
//    }
//
//    protected void enableZoomControls(Camera.Parameters params)
//    {
//
//        final int maxZoom = params.getMaxZoom();
//        seekBar.setMax(maxZoom);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                                               int progressvalue=0;
//                                               @Override
//                                               public void onProgressChanged(SeekBar seekBar, int progress,
//                                                                             boolean fromUser) {
//                                                   // TODO Auto-generated method stub
//                                                   progressvalue=progress;
//                                                   Camera.Parameters params = mCamera.getParameters();
//                                                   params.setZoom(progress);
//                                                   mCamera.setParameters(params);
//
//
//
//                                               }
//                                               @Override
//                                               public void onStartTrackingTouch(SeekBar seekBar) {
//                                                   // TODO Auto-generated method stub
//
//                                               }
//                                               @Override
//                                               public void onStopTrackingTouch(SeekBar seekBar) {
//                                                   // TODO Auto-generated method stub
//
//                                               }
//
//
//
//                                           }
//
//        );
//
//    }
//
//
//    protected boolean initializeCamera(int width, int height)
//    {
//
//        boolean ret = super.initializeCamera(width, height);
//
//
//        Camera.Parameters params = mCamera.getParameters();
//
//        if(params.isZoomSupported())
//            enableZoomControls(params);
//
//        mCamera.setParameters(params);
//
//        return ret;
//    }
//
//}

        import android.content.Context;
        import android.hardware.Camera;
        import android.util.AttributeSet;
        import android.widget.SeekBar;

        import org.opencv.android.JavaCameraView;

        import java.util.List;

/**
 * Created by xuanwang on 2/11/16.
 */



public class ZoomCameraView extends JavaCameraView {
    public ZoomCameraView(Context context, int cameraId) {
        super(context, cameraId);
    }

    public ZoomCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected SeekBar seekBar;

    public void setZoomControl(SeekBar _seekBar)
    {
        seekBar=_seekBar;
    }

    protected void enableZoomControls(Camera.Parameters params)
    {

        final int maxZoom = params.getMaxZoom();
        seekBar.setMax(maxZoom);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               int progressvalue=0;
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress,
                                                                             boolean fromUser) {
                                                   // TODO Auto-generated method stub
                                                   progressvalue=progress;
                                                   Camera.Parameters params = mCamera.getParameters();
                                                   params.setZoom(progress);
                                                   mCamera.setParameters(params);



                                               }
                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {
                                                   // TODO Auto-generated method stub

                                               }
                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   // TODO Auto-generated method stub

                                               }



                                           }

        );

    }


    protected boolean initializeCamera(int width, int height)
    {

        boolean ret = super.initializeCamera(width, height);

        Camera.Parameters params = mCamera.getParameters();

        List<String> FocusModes = params.getSupportedFocusModes();
        if (FocusModes != null && FocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO))
        {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        }
        else if(FocusModes != null && FocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE))
        {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }

        List<String> FlashModes = params.getSupportedFlashModes();
        if(FlashModes != null && FlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH))
        {
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        }

        if(params.isZoomSupported())
            enableZoomControls(params);

        mCamera.setParameters(params);

        return ret;
    }

}
