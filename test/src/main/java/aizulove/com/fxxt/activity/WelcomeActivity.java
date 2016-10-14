package aizulove.com.fxxt.activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import aizulove.com.fxxt.R;

public class WelcomeActivity extends BaseActivity {

	public void setContentViews(){
		View convertView = mInflater.inflate(R.layout.activity_welcome, null);
		layoutContent.addView(convertView);
		HiddenMeun();
	}



	protected void findViews() {

	}

	/**
	 *
	 * 逻辑处理方法
	 */
	protected void DataTask(){
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(context, MainActivity.class));//MainActivity.class
				finish();
			}
		}, 2000);
	}

}
