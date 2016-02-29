package com.mygdx.game;

import android.content.Intent;
import android.media.MediaRouter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication  {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(),config);

		Intent intent1= new Intent(this,resist.class);
		startActivity(intent1);
//		startActivity(intent1);
//		final MyGdxGame game = new MyGdxGame();
//		initialize(new MyGdxGame(),config);
//		game.setMyGameCallback(this);
//		final View gameView = initializeForView(game, config);

//		setContentView(R.layout.activity_resist);
//		Intent go3D = new Intent(this, resist.class);
//		startActivity(go3D);
//		final Button calColor = (Button) findViewById(R.id.btn3D);
//		calColor.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				EditText resistValue = (EditText) findViewById(R.id.resistValue);
//				final int resist = Integer.parseInt(resistValue.getText().toString());
//				Gdx.app.postRunnable(new Runnable() {
//					@Override
//					public void run() {
//						game.addColor();
//						game.render();
//					}
//				});
//				setContentView(gameView);

//			}
//		});
//
//		Gdx.app.postRunnable(new Runnable() {
//			@Override
//            public void run() {
//				game.pressed();
//            	game.addColor();
//			}
//		});
//		Intent intent1= new Intent(getContext(),resist.class);
//		startActivity(intent1);

//	public String LOL="00000000";
	}

//	@Override
//	public void onStartActivityA() {
//		Intent intent = new Intent(this, resist.class);
//		startActivity(intent);
//	}
}


