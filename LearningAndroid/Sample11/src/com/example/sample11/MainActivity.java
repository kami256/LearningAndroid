package com.example.sample11;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

//追加したコード
		private	EditText editText = null;
		private	TextView textView = null;
//////////////////
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
//追加したコード
            Button button = (Button)rootView.findViewById(R.id.button1);
            button.setOnClickListener(mButton1Listener);
            Button button2 = (Button)rootView.findViewById(R.id.button2);
            button2.setOnClickListener(mButton2Listener);
            editText = (EditText)rootView.findViewById(R.id.editText1);
            textView = (TextView)rootView.findViewById(R.id.textView1);
//////////////////			
			return rootView;
		}

//追加したコード
		private OnClickListener mButton1Listener = new OnClickListener() {
            public void onClick(View v) {
            	String text = editText.getText().toString();
            	textView.setText(text);
				Toast.makeText(getActivity(),
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
	}

}
