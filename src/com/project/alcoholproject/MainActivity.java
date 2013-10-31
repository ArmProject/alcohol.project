package com.project.alcoholproject;

import com.project.alcoholproject.line.LineActivity;
import com.project.alcoholproject.sensor.SensorActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	    

		  
		  setContentView(R.layout.activity_main);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void bookClick(View view){
		Intent book = new Intent(this, Book.class);
		startActivity(book);
	}
	
	public void onClickFind(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Find ยังพัฒนาไม่เสร็จครับ T_T");
		builder.show();
	}
	
	public void onClickLine(View view){
		Intent line = new Intent(this, LineActivity.class);
		startActivity(line);
	}
	
	public void onClickSensor(View view){
		Intent sensor = new Intent(this, SensorActivity.class);
		startActivity(sensor);
	}
	
	public void onClickSpeak(View view){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Speak ยังพัฒนาไม่เสร็จครับ T_T");
		builder.show();
	}

}
