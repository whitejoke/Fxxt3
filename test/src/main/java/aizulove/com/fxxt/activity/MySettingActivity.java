package aizulove.com.fxxt.activity;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.view.DataCleanManager;
import aizulove.com.fxxt.view.ToastSingle;

public class MySettingActivity extends BaseActivity {

    private TextView titleview,tv_load;
    private RelativeLayout rl_del;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_setting, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }


    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
         rightimageView=(ImageView)findViewById(R.id.rightimageView);
         titleview=(TextView)findViewById(R.id.head_title);
         tv_load=(TextView)findViewById(R.id.tv_load);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleview.setText("设置");
        rl_del= (RelativeLayout) findViewById(R.id.rl_del);
    }

    protected  void setSelecttab() {
        SELECTTAB=4;
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        try {
            tv_load.setText(DataCleanManager.getTotalCacheSize(MySettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click(View view) {
        switch(view.getId()){
            case R.id.out:
                if(Data().equals("")) {
                    ToastSingle.showToast(context,"您还未登陆");
                    finish();
                }else {
                    clearData();
                    ToastSingle.showToast(context, "退出成功");
                    Intent intent = new Intent();
                    intent.setClass(context, MyActivity.class);
                    startActivity(intent);
                    finish();
                }
            break;
            case R.id.rl_del:
                try {
                    Log.i("susu", DataCleanManager.getTotalCacheSize(MySettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataCleanManager.clearAllCache(MySettingActivity.this);
                onRefsh();
                try {
                    //清除后的操作
                    Log.e("susu", DataCleanManager.getTotalCacheSize(MySettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rl_rewrite:
                if(Data().equals("")) {
                    Intent intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                Intent intent=new Intent();
                intent.setClass(MySettingActivity.this,RewritePasswordActivity.class);
                startActivity(intent);}
        }
    }
    public void onRefsh(){
        try {
            tv_load.setText(DataCleanManager.getTotalCacheSize(MySettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
