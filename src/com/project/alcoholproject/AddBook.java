package com.project.alcoholproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class AddBook extends Activity {

	private EditText edt1,edt2,edt3,edt4,edt5;
	
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String GAME_PREFERENCES = "GamePrefs";
	SharedPreferences settings; 
	SharedPreferences.Editor prefEditor;

	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	String data_string = "";
	String get_n = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book);
		// Show the Up button in the action bar.
		setupActionBar();
		edt1 = (EditText) findViewById(R.id.editText1);
		edt2 = (EditText) findViewById(R.id.editText2);
		edt3 = (EditText) findViewById(R.id.editText3);
		edt4 = (EditText) findViewById(R.id.editText4);
		edt5 = (EditText) findViewById(R.id.editText5);
		

	    
	}

	public void onOk(View view){
		
//		Toast.makeText(this, ""+edt1.getText(), 1).show();
		shared_preferences = getSharedPreferences("shared_preferences_test",MODE_PRIVATE);
		get_n = shared_preferences.getString("n", "0");

	    shared_preferences_editor = shared_preferences.edit();
	    int n = Integer.parseInt(get_n)+1;
	    shared_preferences_editor.putString("n", ""+n);
	    shared_preferences_editor.putString("name"+n, ""+(edt1.getText()));
	    shared_preferences_editor.putString("surname"+n, ""+(edt2.getText()));
	    shared_preferences_editor.putString("pub_id"+n, ""+(edt3.getText()));
	    shared_preferences_editor.putString("detail"+n, ""+edt4.getText());
	    shared_preferences_editor.putString("date"+n, ""+(edt5.getText()));

	    shared_preferences_editor.commit();
	    Intent book = new Intent(this, Book.class);
		startActivity(book);
	}
	
	public void onPic(View view){
		Toast.makeText(this, "ยังพัฒนาไม่เสร็จครับ T_T", 1).show();
	}
	
	public void onNo(View view){
		Intent book = new Intent(this, Book.class);
		startActivity(book);
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_book, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
