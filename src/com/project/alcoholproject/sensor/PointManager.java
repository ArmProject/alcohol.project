package com.project.alcoholproject.sensor;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.call.Callback;
import org.andengine.util.color.Color;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.Toast;

public class PointManager implements SensorEventListener {
	float r = 100, xk = 4, yk = 4, c = 1;

	Scene scene;
	VertexBufferObjectManager vbo;
	Rectangle rec;
	boolean isFinish = false;
	Callback<Object> onFinish;

	public PointManager(Scene scene, VertexBufferObjectManager vbo) {
		this.scene = scene;
		this.vbo = vbo;
	}

	public void setOnFinish(Callback<Object> onFinish) {
		this.onFinish = onFinish;
	}

	private void createBorder(float x, float y, float w, float h) {
		Rectangle border = new Rectangle(x, y, w, h, vbo);
		border.setColor(Color.RED);

		scene.attachChild(border);
	}

	public void createPoint(float w, float h) {
		float dr = r;
		createBorder((w - dr) / 2, (h - dr) / 2, dr, dr);

		rec = new Rectangle((w - r) / 2, (h - r) / 2, r, r, vbo);
		rec.setColor(Color.BLACK);
		scene.attachChild(rec);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;

		float x = values[1];
		float y = values[0];
		// float z = values[2];

		if (!isFinish && rec != null) {

			if (x <= -c || x >= c || y <= -c || y >= c) {
				if (x <= -xk || x >= xk || y <= -yk || y >= yk) {
					isFinish = true;
					onFinish.onCallback(null);
				} else {
					x += rec.getX();
					y += rec.getY();
					rec.setPosition(x, y);
				}

			}

			// txtShow.setText("x = " + x + "\ny = " + y + "\nz = " + z);
			// Log.e("aa", x + "");
			// if (x <= -k || x >= k) {
			// Log.e("aa", x + "ss");
			// }
			// else {
			// txtStat.setText("");
			// }
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}
