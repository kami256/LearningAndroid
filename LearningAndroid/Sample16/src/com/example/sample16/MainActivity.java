package com.example.sample16;

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
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

////////////////////////////////////////////////
//
//　位置情報の取得
//
////////////////////////////////////////////////

public class MainActivity extends Activity {

	private LocationManager locationManager = null;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(mButton1Listener);
	}

	private OnClickListener mButton1Listener = new OnClickListener() {
		public void onClick(View v) {
	        if (locationManager != null) {
	        	locationManager.removeUpdates(mLocationListener);
	        }
        	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        	// 3Gまたはwifiから位置情報を取得する設定
            boolean networkFlg =  locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            // GPSから位置情報を取得する設定
            boolean gpsFlg = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 0,mLocationListener);
         locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 0,mLocationListener);
        }
	};

	private  LocationListener mLocationListener  = new LocationListener() {
        public void onStatusChanged(String provider, int status,
                Bundle extras) {
        }
        public void onProviderEnabled(String provider) {
        }
        public void onProviderDisabled(String provider) {
        }
        public void onLocationChanged(Location location) {
        	String latitude = Double.toString(location.getLatitude());
        	String longitude = Double.toString(location.getLongitude());
        	String message = "";
            message += ("Latitude"+latitude);
            message += "\n";
            message += ("Longitude"+longitude);
            message += "\n";
            message += ("Accuracy"+Float.toString(location.getAccuracy()));
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            // 1回しか呼ばない
            locationManager.removeUpdates(mLocationListener);
            //yahooMap(latitude,longitude);
            getReuest(latitude,longitude);
        }
    };
    
    @Override
    protected void onPause() {
        if (locationManager != null) {
        	locationManager.removeUpdates(mLocationListener);
        }
        super.onPause();
    }

	private  void  yahooMap(String latitude,String longitude) {
		String urlStrng = "http://map.yahoo.co.jp/maps?type=scroll&pointer=on&sc=2&lat=" + latitude;
		urlStrng += "&lon=" + longitude;
    	//////////////////////////////////////////
		// 地図をブラウザでみる
		Intent intent = null;
        intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlStrng));
    	//////////////////////////////////////////
		// 地図をメールで送る
//		Uri uri=Uri.parse("mailto:test@test.com"); 
//  		Intent intent=new Intent(Intent.ACTION_SENDTO,uri); 
//    	intent.putExtra(Intent.EXTRA_SUBJECT,"ここにいます"); 
//    	intent.putExtra(Intent.EXTRA_TEXT,urlStrng); 
//    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
    	//////////////////////////////////////////
    	startActivity(intent); 
	}

	private	void	getReuest(String latitude,String longitude)
	{
		// お天気
		// http://openweathermap.org/
		String requestURL = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon=" + longitude
			 +"&xmode=json&cnt=1";
		// 逆ジオコーディングサービス
		// http://www.finds.jp/wsdocs/rgeocode/index.html.ja
//		String requestURL = "http://www.finds.jp/ws/rgeocode.php?json&lat=" + latitude + "&lon=" + longitude;
		Task task = new Task();
        task.execute(requestURL);
	}

	protected class Task extends AsyncTask<String, String, String>
	{
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(params[0]);
            byte[] result = null;
            String rtn = "";
            try{
                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                    result = EntityUtils.toByteArray(response.getEntity());
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
                JSONObject json = new JSONObject(result);
//////////////////////////////////////////////////////////////////
// お天気
                JSONObject obj = json.getJSONObject("city");
                // 地点名
                String cityName = obj.getString("name");
             	JSONArray listArray = json.getJSONArray("list");
             	JSONObject obj2 = listArray.getJSONObject(0);
             	// 気温(Kから℃に変換)
             	 JSONObject mainObj = obj2.getJSONObject("temp");
             	 float currentTemp = (float) (mainObj.getDouble("day") - 273.15f);
             	 String ct = currentTemp + "度";
                 JSONArray weatherArray = obj2.getJSONArray("weather");
                 // 天気
				String weather = weatherArray.getJSONObject(0)
						.getString("main");
				String temp = "場所(" + cityName + ") / 現在温度(" + ct + "度)";
				temp += " / 天気（" + weather + ")";
				Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG)
						.show();
//////////////////////////////////////////////////////////////////
				// 逆ジオコーディングサービス
/*                String	status = json.getString("status");
                if(status.equals("200")){
                	JSONObject res = json.getJSONObject("result");
                	String pname  = res.getJSONObject("prefecture").getString("pname");
                	String mname = res.getJSONObject("municipality").getString("mname");
                	String section = res.getJSONArray ("local").getJSONObject(0).getString("section");
                	String temp = pname  + mname + section;
                	Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
                } else {
                	Toast.makeText(getApplicationContext(), "error!", Toast.LENGTH_LONG).show();
                }
*/
            }
            catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "error!!!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
