package aizulove.com.fxxt.activity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.task.EmailTask;

public class ForgotActivity extends Activity {

    private TextView titleview;
    private EditText email;
    private Button next;
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        context=this;
        findViews();
        DataTask();

    }
    //    public void setContentViews(){
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        View convertView = mInflater.inflate(R.layout.activity_product, null);
//
//        layoutContent.addView(convertView, lp);
//        findViews();
//        DataTask();
//        HiddenMeun();
//    }


    protected void findViews() {
        titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         titleview.setText("获取验证码");
        email= (EditText) findViewById(R.id.et_user_email);
        next= (Button) findViewById(R.id.btn_next);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Map<String,String> map=new HashMap<String, String>();
               map.put("email",email.getText().toString());
               new EmailTask(context,map,email.getText().toString()).execute();
           }
       });
    }


}
