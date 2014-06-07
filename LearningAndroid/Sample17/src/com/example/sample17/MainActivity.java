package com.example.sample17;

import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//////////////////////////////////////////
//
// Webアクセスのサンプルコード
//　最寄り駅検索
//
//////////////////////////////////////////
public class MainActivity extends Activity {

	private EditText latText  = null;
	private EditText lonText = null;
	private TextView resText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		latText = (EditText) findViewById(R.id.editText1);
		lonText = (EditText) findViewById(R.id.editText2);
		resText = (TextView) findViewById(R.id.textView3);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(mButton1Listener);
	}

	private OnClickListener mButton1Listener = new OnClickListener() {
		public void onClick(View v) {
			String latitude = latText.getText().toString();
			String longitude= lonText.getText().toString();
			String requestURL = "http://map.simpleapi.net/stationapi?y="
					+ latitude + "&x=" + longitude +"&output=json";
//			resText.setText(requestURL);
    		Toast.makeText(getApplicationContext(), requestURL, 
			Toast.LENGTH_LONG).show();
			Task task = new Task();
	        task.execute(requestURL);
		}
	};

	protected class Task extends AsyncTask<String, String, String>
	{
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(params[0]);
            String rtn = "";
            try{
                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                	byte[] result = EntityUtils.toByteArray(response.getEntity());
                    rtn = new String(result, "UTF-8");
                }
            }
            catch (Exception e) {
            }
            return rtn;
        }
        
        @Override
        protected void onPostExecute(String result)
        {
           try {
        	   // JSONArrayがエントリのため、これをしないと例外で落ちる
        	   String jsonBase = "{\"root\":" + result + "}";
        	   JSONObject json = new JSONObject(jsonBase);
//       		Toast.makeText(getApplicationContext(), json.toString(), 
//				Toast.LENGTH_LONG).show();
        	   JSONObject obj   = json.getJSONArray("root").getJSONObject(0);
        	   String name = obj.getString("name");
        	   resText.setText(name );
            }
            catch (JSONException e) {
    			resText.setText("Json Error!!!" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
