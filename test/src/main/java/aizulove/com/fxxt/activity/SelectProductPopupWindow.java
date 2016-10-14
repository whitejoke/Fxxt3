package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import aizulove.com.fxxt.R;

/**
 * Created by joker on 2016/8/2.
 */

public class SelectProductPopupWindow extends Activity {
    private Button btn_recognize,btn_left,btn_right;
    private TextView tv_pop_price,tv_pop_title,tv_count;
    private LinearLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.alert_dialog);

        btn_recognize= (Button)this. findViewById(R.id.btn_recognize);
        btn_left= (Button)this. findViewById(R.id.btn_left);
        btn_right= (Button)this. findViewById(R.id.btn_right);
        tv_pop_price= (TextView)this. findViewById(R.id.tv_pop_price);
        tv_pop_title= (TextView)this. findViewById(R.id.tv_pop_title);
        tv_count= (TextView)this. findViewById(R.id.tv_count);
        layout=(LinearLayout)findViewById(R.id.pop_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //initDate();
    }

    private void initDate() {
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String price=intent.getStringExtra("price");
        String color=intent.getStringExtra("color");
        String v2=intent.getStringExtra("v2");
        tv_pop_title.setText(title);
        tv_pop_price.setText(price);
    }
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }


}
