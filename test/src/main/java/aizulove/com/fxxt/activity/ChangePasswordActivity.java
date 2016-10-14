package aizulove.com.fxxt.activity;

import android.view.View;

import aizulove.com.fxxt.R;

/**
 * Created by joker on 2016/8/11.
 */
public class ChangePasswordActivity extends BaseActivity {
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_setting, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }
}
