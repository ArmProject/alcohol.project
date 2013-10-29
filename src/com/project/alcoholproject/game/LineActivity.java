package com.project.alcoholproject.game;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.call.Callback;
import org.andengine.util.color.Color;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

public class LineActivity extends BaseGameActivity {
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	private Camera camera;

	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(),
				this.camera);
		// engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		float size = 40;
		ResourceManager res = new ResourceManager(
				this.getVertexBufferObjectManager());
		res.setFont(FontFactory.createFromAsset(this.getFontManager(),
				this.getTextureManager(), 256, 256, this.getAssets(),
				"font/droid.ttf", size, true, android.graphics.Color.WHITE));

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		VertexBufferObjectManager vbo = this.getVertexBufferObjectManager();
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));

		int n = 16;
		int d = 4;

		final Algorithm algo = new Algorithm(d, n);

		final PointFactory factory = new PointFactory(scene);

		factory.random(algo.getData(), d, n, CAMERA_WIDTH, CAMERA_HEIGHT);
		factory.setOnTop(new Callback<Object>() {
			public void onCallback(Object obj) {
				int index = (Integer) obj;
				// Log.e("aaa", index + "");
				boolean stat = algo.check(index);
				// Log.e("bbb", stat + "");
				if (!stat) {
					factory.finish();
				} else {
					algo.set(index);
				}

			};
		});

		pOnCreateSceneCallback.onCreateSceneFinished(scene);
	}

	@Override
	public void onPopulateScene(Scene scene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

}