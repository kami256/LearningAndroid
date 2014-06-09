package com.example.exercise02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//////////////////////////////////////////
//
// 課題２　解答例
//
//////////////////////////////////////////
public class MainActivity extends Activity {

	private	TextView textView = null;
	private Button quizButton = null;
	private int  quizCount = 0;				// 出題カウンタ（何問目の出題か？）
	private int successCount = 0;			// 正解カウンタ
	private final String quizText[]={		// クイズの問題文
		    "日本の首都は、東京。(Yes or No)",
		    "日本の人口は、１万ぐらい。(Yes or No)",
		    "京都は、北海道にある。(Yes or No)",
		    "壱岐は、九州にある。(Yes or No)",
		    "四国は、日本ではない。(Yes or No)"
	};
	private final boolean answerArray[]={	// クイズの解答
		    true, false,false,true,false
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		quizCount = quizText.length;
		quizButton = (Button)findViewById(R.id.button1);
		quizButton.setOnClickListener(mButton1Listener);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(mButton2Listener);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(mButton3Listener);
        textView = (TextView)findViewById(R.id.textView1);
	}

	private void question() {
    	if(quizCount < quizText.length){
    		textView.setText(quizText[quizCount]);
    	} else {
    		String temp = quizText.length + "中" +  successCount + "問正解。";
    		textView.setText(temp);
            quizButton.setText("開始");
    	}
	}

	private void answer(View v,boolean ans)
	{
    	if(quizCount < quizText.length){
    		if(answerArray[quizCount] == ans){
    			Toast.makeText(v.getContext(),
    					"正解", Toast.LENGTH_SHORT).show();
    			successCount++;
    		}
    		quizCount++;
    		question();
    	}
	}
	
	// クイズの開始
	private OnClickListener mButton1Listener = new OnClickListener() {
        public void onClick(View v) {
        	quizCount = 0;
        	successCount = 0;
        	question();
        	quizButton.setText("最初から");
        }
    };

    // Yes Button
	private OnClickListener mButton2Listener = new OnClickListener() {
        public void onClick(View v) {
        	answer(v,true);
        }
    };

    //No Button
	private OnClickListener mButton3Listener = new OnClickListener() {
        public void onClick(View v) {
        	answer(v,false);
        }
    };
}
