package com.example.sample15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

////////////////////////////////////////////////
//
//画面遷移（別のActivityへ移る）
//
////////////////////////////////////////////////

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(mButton1Listener);
	}
	private OnClickListener mButton1Listener = new OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(MainActivity.this, SubActivity.class);
        	// データを送る
        	intent.putExtra("StoreData", "TestData");
        	startActivity(intent); 
        }
    };
}
