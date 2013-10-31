package com.project.alcoholproject.sensor;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.call.Callback;
import org.andengine.util.color.Color;

import com.project.alcoholproject.R;
import com.project.alcoholproject.line.Algorithm;
import com.project.alcoholproject.line.PointFactory;
import com.project.alcoholproject.line.ResourceManager;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	private Camera camera;
	float k = 5;
	TextView txtShow, txtStat;

	// @Override
	// protected void onCreate(Bundle pSavedInstanceState) {
	// super.onCreate(pSavedInstanceState);
	// SensorManager sensorManager = (SensorManager)
	// getSystemService(Context.SENSOR_SERVICE);
	// Sensor sensor = sensorManager
	// .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	// sensorManager.registerListener(, sensor,
	// SensorManager.SENSOR_DELAY_NORMAL);
	// }

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

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));

		PointManager point = new PointManager(scene,
				this.getVertexBufferObjectManager());
		point.createPoint(CAMERA_WIDTH, CAMERA_HEIGHT);
		point.setOnFinish(new Callback<Object>() {

			@Override
			public void onCallback(Object pCallbackValue) {
				Toast.makeText(SensorActivity.this, "You lose!!!",
						Toast.LENGTH_LONG).show();
			}
		});
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(point, sensor,
				SensorManager.SENSOR_DELAY_UI);

		pOnCreateSceneCallback.onCreateSceneFinished(scene);
	}

	@Override
	public void onPopulateScene(Scene scene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

}
