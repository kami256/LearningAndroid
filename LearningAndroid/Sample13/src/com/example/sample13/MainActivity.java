package com.example.sample13;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

////////////////////////////////////////
//
//Activityのライフサイクルを調べる
//
////////////////////////////////////////
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Sample13", "onCreate");
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("Sample13", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("Sample13", "onResume");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("Sample13", "onRestart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("Sample13", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("Sample13", "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("Sample13", "onDestroy");
	}
}
