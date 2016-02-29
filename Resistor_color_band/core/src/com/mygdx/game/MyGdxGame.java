package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.MathUtils;

import org.w3c.dom.css.RGBColor;

import java.util.Arrays;
import java.util.Random;

import sun.rmi.runtime.Log;

public class MyGdxGame extends ApplicationAdapter {


	public interface MyGameCallback {
		public void onStartActivityA();
	}

	public void setMyGameCallback(MyGameCallback callback) {
		myGameCallback = callback;
	}


	private MyGameCallback myGameCallback;


	public SpriteBatch sprites;
	public Environment environment;

	public PerspectiveCamera cam;

	public ModelBatch modelBatch;
	public ModelInstance instance;
	public Model model;
	public CameraInputController camController;
	public Texture logo;
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		sprites = new SpriteBatch();
		modelBatch = new ModelBatch();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		logo = new Texture("badlogic.jpg");

		cam = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(100f, 100f, 100f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();
		final ModelLoader modelLoader = new ObjLoader();


		try {
			model = modelLoader.loadModel(Gdx.files.internal("r1.obj"));
		} catch (Exception e) {
			System.out.println("Error, cannot load model");

		}
		instance = new ModelInstance(model);
		instance.transform.scale(8f, 8f, 8);
		camController = new CameraInputController(cam);
//		Color a = new Color(Color.RED);
		instance.materials.get(0).set(ColorAttribute.createDiffuse(getRandomColor()));
//		instance.materials.get(1).set(ColorAttribute.createDiffuse(Color.YELLOW));
//		instance.materials.get(2).set(ColorAttribute.createDiffuse(Color.GREEN));
//		instance.materials.get(3).set(ColorAttribute.createDiffuse(Color.PINK));
//		instance.materials.get(4).set(ColorAttribute.createDiffuse(Color.BLACK));
//		instance.materials.get(5).set(ColorAttribute.createDiffuse(Color.BLUE));
//		instance.materials.get(6).set(ColorAttribute.createDiffuse(Color.BROWN));
//		instance.materials.get(7).set(ColorAttribute.createDiffuse(Color.RED));
//		instance.materials.get(8).set(ColorAttribute.createDiffuse(Color.BLUE));
//		instance.materials.get(9).set(ColorAttribute.createDiffuse(Color.WHITE));
//		Gdx.input.setInputProcessor(new FirstOrderInputProcessor(cam, new Runnable() {
//			public void run() {
//				//////////////////################//
//				myGameCallback.onStartActivityA();
//			}
//	}));

	}


	@Override
	public void render() {
		camController.update();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instance, environment);
		modelBatch.end();
		sprites.begin();
		sprites.draw(logo, 50, 50, 200, 200);
		sprites.end();
	}

	@Override
	public void dispose() {
		// TODO: dispose of the model and sprite batch
		// TODO: dispose of the model
		model.dispose();
		modelBatch.dispose();
		sprites.dispose();
//		swar.dispose();
	}

	protected Color getRandomColor() {
		return new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), MathUtils.random());
	}

	protected void addColor(int[] color) {
//		instance.materials.get(0).set(ColorAttribute.createDiffuse(Color.WHITE));
////		instance.materials.get(0).set(ColorAttribute.createDiffuse(new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), MathUtils.random())));
//		instance.materials.get(1).set(ColorAttribute.createDiffuse(Color.SKY));
//		instance.materials.get(2).set(ColorAttribute.createDiffuse(Color.CORAL));
//		instance.materials.get(3).set(ColorAttribute.createDiffuse(Color.PINK));
//		instance.materials.get(4).set(ColorAttribute.createDiffuse(Color.BLACK));
//		instance.materials.get(5).set(ColorAttribute.createDiffuse(Color.RED));
//		instance.materials.get(6).set(ColorAttribute.createDiffuse(Color.BROWN));
//		instance.materials.get(7).set(ColorAttribute.createDiffuse(Color.RED));
//		instance.materials.get(8).set(ColorAttribute.createDiffuse(Color.BLUE));
//		instance.materials.get(9).set(ColorAttribute.createDiffuse(Color.YELLOW));
		int len = color.length;
		Color[] col = new Color[len];
		for (int i = 0; i < len; i++) {
			col[i]=which1(color[i]);

		}
		Gdx.app.log("color", Arrays.toString(color));
		String ll="BlUE";
		Gdx.app.log("col", Arrays.toString(col));
		instance.materials.get(1).set(ColorAttribute.createDiffuse(col[0]));
		instance.materials.get(7).set(ColorAttribute.createDiffuse(col[1]));
		instance.materials.get(9).set(ColorAttribute.createDiffuse(col[2]));
		instance.materials.get(6).set(ColorAttribute.createDiffuse(col[3]));
		instance.materials.get(4).set(ColorAttribute.createDiffuse(col[4]));
		instance.materials.get(8).set(ColorAttribute.createDiffuse(col[5]));
		instance.materials.get(0).set(ColorAttribute.createDiffuse(col[6]));
		instance.materials.get(5).set(ColorAttribute.createDiffuse(Color.SLATE));
		instance.materials.get(3).set(ColorAttribute.createDiffuse(Color.WHITE));
		instance.materials.get(2).set(ColorAttribute.createDiffuse(Color.WHITE));


		cam.update();
	}

	protected void pressed() {
		Gdx.input.setInputProcessor(new FirstOrderInputProcessor(cam, new Runnable() {
			public void run() {
				myGameCallback.onStartActivityA();
			}
		}));
	}

	public Color which1(int n) {
		switch (n) {
			case 0:
				return Color.BLACK;
			case 1:
				return Color.BROWN;
			case 2:
				return Color.RED;
			case 3:
				return Color.ORANGE;
			case 4:
				return Color.YELLOW;
			case 5:
				return Color.GREEN;
			case 6:
				return Color.BLUE;
			case 7:
				return Color.VIOLET;
			case 8:
				return Color.GRAY;
			case 9:
				return Color.WHITE;
			case 10:
				return Color.GOLD;
			case 11:
				return Color.LIGHT_GRAY;
			case -9:
				return Color.SLATE;
			default:return null;

		}
	}

}