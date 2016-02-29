package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class FirstOrderInputProcessor extends CameraInputController {
    final Runnable changeColorsAction;

    public FirstOrderInputProcessor(Camera c, Runnable changeColors) {
        super(c);
        changeColorsAction = changeColors;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /* add the condition for finding whether we are clicking the lower-left logo position */
//        Gdx.app.log("MyTag", "LOL");
        int height= Gdx.graphics.getHeight();
        if (pointer==0 && screenX > 50 && screenX <200 && screenY >height-200 && screenY < height-50) {

            if (changeColorsAction != null) {
                Gdx.app.log("MyTag", "DOT");
                // call the changeColorsAction runnable interface, implemented in Main
                changeColorsAction.run();
                return true;
            }
        }

        // otherwise, just do camera input controls
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
