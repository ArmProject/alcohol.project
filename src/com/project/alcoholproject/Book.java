package com.project.alcoholproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Book extends Activity  {


	
	
	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	String test_string = "";
	
	public String myString[] = {"Kreang","Pea","Da"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		ImageButton yourBitmap = (ImageButton) findViewById(R.id.plus);
		
//		TextView test = (TextView) findViewById(R.id.)
//		Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, newWidth, newHeight, true);
		ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_book, myString);
		ListView listView = (ListView) findViewById(R.id.testView1);
		listView.setAdapter(adapter);

//		shared_preferences = getSharedPreferences("shared_preferences_test",MODE_PRIVATE);
//		test_string = shared_preferences.getString("test_key1", "Default");
//		Toast.makeText(getApplicationContext(), test_string, Toast.LENGTH_SHORT).show();

		 
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	
	
	public void onPlus(View view){
		Intent add_book = new Intent(this, AddBook.class);
		startActivity(add_book);
	}

	// public class MyPerformanceArrayAdapter extends ArrayAdapter<String>{
	//
	// private final Activity context;
	// private final String[] listFace;
	//
	// public MyPerformanceArrayAdapter(Activity context, String[] listFace) {
	// this.context = context;
	//
	// // TODO Auto-generated constructor stub
	// }
	//
	// }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
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
