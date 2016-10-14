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
import aizulove.com.fxxt.task.ChangePwTask;

/**
 * Created by joker on 2016/9/20.
 */
public class ChangePwActivity extends Activity {
    private TextView titleview,email;
    private EditText yanzheng,password;
    private Button change;
    private String useremail=null;
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);
        context=this;
        initData();
        findViews();
        DataTask();
    }

    private void initData() {
        useremail=getIntent().getStringExtra("email");
    }

    protected void findViews() {
        titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleview.setText("修改密码");
        email= (TextView) findViewById(R.id.tv_user_email);
        yanzheng= (EditText) findViewById(R.id.et_yanzheng);
        password= (EditText) findViewById(R.id.et_user_pw);
        change= (Button) findViewById(R.id.btn_change);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        email.setText(useremail);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map=new HashMap<>();
                map.put("email",useremail);
                map.put("code",yanzheng.getText().toString());
                map.put("password",password.getText().toString());
                new ChangePwTask(context,map).execute();
            }
        });

    }

}
