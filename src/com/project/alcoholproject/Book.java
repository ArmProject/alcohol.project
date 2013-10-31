package com.project.alcoholproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Book extends Activity {

	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	String test_string = "",get_name,get_surname,get_pub_id,get_detail,get_date;
	ArrayList<A> name = new ArrayList<A>();
	public String myString[] = { "Kreang", "Pea", "Da" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
//		ImageButton yourBitmap = (ImageButton) findViewById(R.id.plus);
		
		shared_preferences = getSharedPreferences("shared_preferences_test",MODE_PRIVATE);
		String get_n = shared_preferences.getString("n","0");
		int n;
		n = Integer.parseInt(get_n);
		A a;
		for(int i=1;i<=n;i++){
			get_name = shared_preferences.getString("name"+i, "");
			get_surname = shared_preferences.getString("surname"+i, "");
			get_pub_id = shared_preferences.getString("pub_id"+i, "");
			get_detail = shared_preferences.getString("detail"+i, "");
			get_date = shared_preferences.getString("date"+i, "");
			
			a = new A();
			int wi = i%4;
			if(wi==1){
				a.ImageView =R.drawable.face_1;
			}else if(wi==2){
				a.ImageView =R.drawable.face_2;
			}else if(wi==3){
				a.ImageView =R.drawable.face_3;
			}else if(wi==4){
				a.ImageView =R.drawable.face_4;
			}
			
			a.TextView1 = "ชื่อ : "+get_name;
			a.TextView2 = "นามสกุล : "+get_surname;
			a.TextView3 = "เลขประจำตัวประชาชน : "+get_pub_id;
			a.TextView5 = "รายละเอียด : "+get_detail;
			a.TextView4 = "วันที่บันทึก : "+get_date;
			name.add(a);
		}
		
		
//		TextView test = (TextView) findViewById(R.id.)
//		Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, newWidth, newHeight, true);
//		ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_form, myString);
//		ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_form, R.id.textView1, myString);
		ExtArrayAdapter adapter = new ExtArrayAdapter(this, R.layout.list_view_form, name);
		ListView listView = (ListView) findViewById(R.id.testView1);
		listView.setAdapter(adapter);

//		shared_preferences = getSharedPreferences("shared_preferences_test",MODE_PRIVATE);
//		test_string = shared_preferences.getString("test_key1", "Default");
//		Toast.makeText(getApplicationContext(), test_string, Toast.LENGTH_SHORT).show();

		 
		// Show the Up button in the action bar.
		setupActionBar();
	}

	public void onPlus(View view) {
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

class ExtArrayAdapter extends ArrayAdapter<A> {
	private ArrayList<A> objects;

	public ExtArrayAdapter(Context context, int textViewResourceId,
			ArrayList<A> A) {
		// super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		super(context, textViewResourceId, A);
		this.objects = A;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_view_form, null);
		}

		A i = objects.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView tt1 = (TextView) convertView.findViewById(R.id.textView1);
			TextView tt2 = (TextView) convertView.findViewById(R.id.textView2);
			TextView tt3 = (TextView) convertView.findViewById(R.id.textView3);
			TextView tt4 = (TextView) convertView.findViewById(R.id.textView4);
			TextView tt5 = (TextView) convertView.findViewById(R.id.textView5);
			
			ImageView ttd = (ImageView) convertView
					.findViewById(R.id.imageView1);

			tt1.setText(i.TextView1);
			tt2.setText(i.TextView2);
			tt3.setText(i.TextView3);
			tt4.setText(i.TextView4);
			tt5.setText(i.TextView5);
			ttd.setImageResource(i.ImageView);
		}
		return convertView;
	}
}

class A {
	int ImageView;
	String TextView1;
	String TextView2;
	String TextView3;
	String TextView4;
	String TextView5;

}