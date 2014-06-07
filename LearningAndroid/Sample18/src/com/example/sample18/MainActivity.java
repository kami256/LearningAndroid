package com.example.sample18;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

///////////////////////////////////////
//
//		プリファレンスを使う
//
///////////////////////////////////////

public class MainActivity extends Activity {

	SharedPreferences sharedPref;
	private TextView readText = null;
	private EditText  writeText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sharedPref = 
		        PreferenceManager.getDefaultSharedPreferences(this);

		readText = (TextView) findViewById(R.id.textView1);
		writeText = (EditText) findViewById(R.id.editText1);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(mButton1Listener);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(mButton2Listener);
	}

	private OnClickListener mButton1Listener = new OnClickListener() {
		public void onClick(View v) {
			readText.setText(
					sharedPref.getString("mailaddress", ""));
		}
	};
	
	private OnClickListener mButton2Listener = new OnClickListener() {
		public void onClick(View v) {
			Editor editor = sharedPref.edit();
	        editor.putString("mailaddress", writeText.getText().toString());
	        editor.commit();
		}
	};
	
	// 今回は、オプションメニューを使うために残してあります
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 今回は、オプションメニューを使うために残してあります
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, SettingPreferences.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
