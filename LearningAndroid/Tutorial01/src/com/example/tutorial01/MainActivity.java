package com.example.tutorial01;

/////////////////////////////////////////////////////////////////
//
//		Androidアプリケーション　チュートリアル
//
//		「神経衰弱」を作る
//
//		１）layoutファイルを修正する。
//			　gridレイアウトを使用（API Level 11以降）
//			   手軽に升目状のレイアウトが使えます
//			　画像ファイルを準備
//		２）マニフェストファイルは、修正していません。
//		３）ソースコードのポイント
//		３－１）升目に配置されたボタンを使うため、ボタンのリソースIDを
//			　　　配列に定義しました
//		３－２）対応する画像のリソースIDを配列に定義しました
//					初期化時に、入れ替えている
//		３－３）ボタンの状態を配列にしました
//					初期化時に、falseにしている（false:画像が裏の状態）
//		３－４）全てのボタンのリスナーを一つのリスナーで済ませている			
//					IDを見ることでどのボタンか、判断している
//		３－５）Handler.postDelayed()を使って、遅延処理を行っている
//					TimerTaskより、使いやすい
//					遅延後の処理でUI操作がそのまま、可能。Handlerであるため。
//					全て、UIスレッドで処理されるため、排他は不要。
/////////////////////////////////////////////////////////////////

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	// ボタンリソースID
	private final static int sButonArray[]={
			R.id.button10,R.id.button11,R.id.button12,R.id.button13,
			R.id.button20,R.id.button21,R.id.button22,R.id.button23,
			R.id.button30,R.id.button31,R.id.button32,R.id.button33,
			R.id.button40,R.id.button41,R.id.button42,R.id.button43,
	};
	// 画像リソースID
	private int mImageSource[]={
			R.drawable.img0,	R.drawable.img0,
			R.drawable.img1,	R.drawable.img1,
			R.drawable.img2,	R.drawable.img2,
			R.drawable.img3,	R.drawable.img3,
			R.drawable.img4,	R.drawable.img4,
			R.drawable.img5,	R.drawable.img5,
			R.drawable.img6,	R.drawable.img6,
			R.drawable.img7,	R.drawable.img7,
	};
	// 画像管理配列（true:表向き false：裏向き）
	private boolean [] mCardState = new boolean [16];
    // １枚目に選ばれたカードのインデックス
	private int mCurrentIndex = -1;
    // ２枚目に選ばれたカードのインデックス
	private int mSecondIndex = -1;
    // 成功回数管理用
	private int mSuccessCount = 0;
    // 遅延処理用
	private final Handler mHandler = new Handler();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 画像管理の初期化（シャッフル）
		initImageArray();
		// ボタン用リスナーの登録
		for(int i=0;i<sButonArray.length;i++){
			findViewById(sButonArray[i]).setOnClickListener(mButtonListener);
		}
	}

	// ボタンのリスナー
	private OnClickListener mButtonListener = new OnClickListener() {
		public void onClick(View v) {
			if(mSecondIndex != -1){
				// ２枚目の処理中
				return;
			}
			//　ボタンのIDを取得
			int id = v.getId();
			for(int i=0;i<sButonArray.length;i++){
				if(sButonArray[i] == id){
					if(!mCardState[i]){
						// 一旦、２枚目のカードを裏返す
						findViewById(id).setBackgroundResource(mImageSource[i]);
						mCardState[i] = true;
						// ２枚目か
						if (mCurrentIndex != -1) {
							// ２枚目の場所を覚える
							mSecondIndex = i;
				            if (mImageSource[mCurrentIndex] == mImageSource[i]) {
				            	// 一致
				            	mHandler.postDelayed(mSuccessTask, 1000);
				            } else {
				            	// 不一致
				            	mHandler.postDelayed(mMismatchTask, 1000);
				            }
				        } else {
				        	// 1枚目の場所を覚える
				        	mCurrentIndex = i;
				        }
					}
				}
			}
		}
	};
	
	// カードの初期化
	private void initImageArray() {
 		// 画像管理フラグをfalseに。（false:裏側）
		for(int i=0;i<mCardState.length;i++){
			mCardState[i] = false;
		}
 		Random rnd = new Random();
 		// 画像の入れ替え（シャッフル）
		for(int i = mImageSource.length; i > 1; i--){
			int	a = i - 1;
			int	b = rnd.nextInt(i);
			int	temp = mImageSource[a];
			mImageSource[a] = mImageSource[b];
			mImageSource[b] = temp;
		}
	}

	// カードが一致したときの遅延処理
	private final Runnable mSuccessTask = new Runnable() {
        @Override
        public void run() {
			// カードをクリアにする処理
			findViewById(sButonArray[mSecondIndex]).setBackgroundResource(0);        	
			findViewById(sButonArray[mCurrentIndex]).setBackgroundResource(0);
			if(mSuccessCount<7){
				mSuccessCount++;
			} else {
				findViewById(R.id.LinearLayout1).setVisibility(View.VISIBLE);
				Toast.makeText(getApplicationContext(), "成功！！！", Toast.LENGTH_LONG).show();
			}
			mCurrentIndex = -1;
	       	mSecondIndex = -1;
		}
    };

	// カードが不一致のときの遅延処理
	private final Runnable mMismatchTask = new Runnable() {
        @Override
        public void run() {
			// カードを元に戻す処理
			findViewById(sButonArray[mSecondIndex]).setBackgroundResource(R.drawable.station);
			findViewById(sButonArray[mCurrentIndex]).setBackgroundResource(R.drawable.station);
			mCardState[mCurrentIndex] = false;
			mCardState[mSecondIndex] = false;
        	mCurrentIndex = -1;
	       	mSecondIndex = -1;
		}
    };
}
