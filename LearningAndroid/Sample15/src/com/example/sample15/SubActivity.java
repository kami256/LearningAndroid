package com.example.sample15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
    
		// データの受取
		Intent intent = getIntent();
        String arg = intent.getStringExtra("StoreData");
        Toast.makeText(this,arg, Toast.LENGTH_LONG).show();
		
		Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(mButton1Listener);
	}
	private OnClickListener mButton1Listener = new OnClickListener() {
        public void onClick(View v) {
        	// 前の画面に戻る
        	finish();
        }
    };

}
