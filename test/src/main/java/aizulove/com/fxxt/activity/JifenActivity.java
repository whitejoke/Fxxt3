package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import aizulove.com.fxxt.R;

public class JifenActivity extends BaseActivity {


    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_jifen, null);
        layoutContent.addView(convertView);
        HiddenMeun();
    }



    protected void findViews() {
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
       ((TextView)findViewById(R.id.head_title)).setText("我的积分");
    }

    protected  void setSelecttab() {
        SELECTTAB=4;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){

    }
}
