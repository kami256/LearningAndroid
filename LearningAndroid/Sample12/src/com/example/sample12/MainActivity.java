package com.example.sample12;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/////////////////////////////
// Fragmentを使わないコード例
/////////////////////////////

public class MainActivity extends Activity {

//追加したコード
		private	EditText editText = null;
		private	TextView textView = null;
//////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//追加したコード
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(mButton1Listener);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(mButton2Listener);
        editText = (EditText)findViewById(R.id.editText1);
        textView = (TextView)findViewById(R.id.textView1);
//////////////////		

	}

//追加したコード
	private OnClickListener mButton1Listener = new OnClickListener() {
        public void onClick(View v) {
            String text = editText.getText().toString();
            textView.setText(text);
			Toast.makeText(v.getContext(),
					text, Toast.LENGTH_SHORT).show();
        }
    };
	private OnClickListener mButton2Listener = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = null;
            intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://google.com/"));
            startActivity(intent);
        }
    };
//////////////////	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
