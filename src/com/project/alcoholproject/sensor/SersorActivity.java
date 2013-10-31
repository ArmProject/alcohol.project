package com.project.alcoholproject.sensor;

import com.project.alcoholproject.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SersorActivity extends Activity implements SensorEventListener {
	float k = 5;
	TextView txtShow, txtStat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		txtShow = (TextView) findViewById(R.id.txt_show);

		txtStat = (TextView) findViewById(R.id.txt_status);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;

		float x = values[0];
		float y = values[1];
		float z = values[2];
		txtShow.setText("x = " + x + "\ny = " + y + "\nz = " + z);
		if (y <= -k || y >= k) {
			txtStat.setText("You lose!!!!");
		} else {
			txtStat.setText("");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}
