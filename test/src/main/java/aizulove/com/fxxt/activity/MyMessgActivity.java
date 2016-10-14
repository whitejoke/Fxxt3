package aizulove.com.fxxt.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Integration;

public class MyMessgActivity extends Activity {

    private TextView titleview,tv_info_title,tv_info_date,tv_info_content;
    private WebView webView;
    Integration message=new Integration();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messag_info);
        initData();
        findViews();
        DataTask();
    }

    private void initData() {
        message= (Integration) getIntent().getSerializableExtra("message");
    }


    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
         titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)) .setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   finish();
               }
           });
        titleview.setText("消息详情");
        tv_info_title= (TextView) findViewById(R.id.tv_info_title);
        tv_info_date= (TextView) findViewById(R.id.tv_info_date);
        //tv_info_content= (TextView) findViewById(R.id.tv_info_content);
        webView= (WebView) findViewById(R.id.web_message);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(message.getAddtime());
        re_StrTime = sdf.format(new Date(lcc_time*1000L));

        tv_info_title.setText(message.getTitle());
        tv_info_date.setText(re_StrTime);
        //tv_info_content.setText(message.getContent());
        webView.loadDataWithBaseURL(null,message.getContent(),"text/html","utf-8",null);

    }


}
