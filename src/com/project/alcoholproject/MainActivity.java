package com.project.alcoholproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String GAME_PREFERENCES = "GamePrefs";
	SharedPreferences settings; 
	SharedPreferences.Editor prefEditor;
	

	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	String test_string = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	    
	    shared_preferences = getSharedPreferences("shared_preferences_test",
	            MODE_PRIVATE);
	    test_string = shared_preferences.getString("test_key", "Default");

	    shared_preferences_editor = shared_preferences.edit();

	    shared_preferences_editor.putString("test_key1", "Kreang");
	    shared_preferences_editor.putString("test_key2", "Pea");
	    shared_preferences_editor.putString("test_key3", "Da");
	    shared_preferences_editor.commit();
		  
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

}
