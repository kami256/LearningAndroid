package com.example.sample10;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends Activity {

	private View view;

	// ハンドラを作成
	private Handler handler = new Handler();
	// ビューの再描画間隔(ミリ秒)
	private final static long INTERVAL_TIME = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new SampleView(this);
		setContentView(view);

		// ビュー再描画タイマー
		// タイマーを作成
		Timer timer = new Timer(false);
		// 「INTERVAL_TIME」ミリ秒おきにタスク(TimerTask)を実行
	    timer.schedule(new TimerTask(){
	    	public void run(){
	    		handler.post(new Runnable(){
	    			public void run(){
	    				// ビューを再描画
	    				view.invalidate();
	    			}});
	    	}
	    }, 0, INTERVAL_TIME);
		
//		setContentView(null);
//		setContentView(R.layout.activity_main);

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}

}
