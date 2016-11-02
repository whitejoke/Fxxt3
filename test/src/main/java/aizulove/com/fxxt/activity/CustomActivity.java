package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.task.TalkTask;

/**
 * Created by joker on 2016/10/31.
 */
public class CustomActivity extends Activity implements View.OnClickListener {
    private EditText talk_title,talk_content;
    private Button talk_send;
    private TextView titleview;
    private SharedPreferences sharedPreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        findViews();

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
        titleview.setText("客服留言");
        talk_content= (EditText) findViewById(R.id.talk_content);
        talk_title= (EditText) findViewById(R.id.talk_title);
        talk_send= (Button) findViewById(R.id.talk_send);
        talk_send.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Map<String,String> map=new HashMap<>();
        map.put("content",talk_content.getText().toString());
        map.put("title",talk_title.getText().toString());
        map.put("userId",sharedPreferences.getString("userId",""));
        new TalkTask(getApplicationContext(),map).execute();
    }

//    private class MyTextWatch implements TextWatcher {
//        private View view;
//        public MyTextWatch(EditText input_talk) {
//            this.view=input_talk;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            isValid();
//        }
//    }
//
//    private boolean isValid() {
//        if (input_talk.getText().toString().trim().equals("")||input_talk.getText().toString().trim().isEmpty()){
//            layout_talk.setError(getString(R.string.error_talk));
//            input_talk.requestFocus();
//            return false;
//        }
//        layout_talk.setErrorEnabled(false);
//        return true;
//    }
}
