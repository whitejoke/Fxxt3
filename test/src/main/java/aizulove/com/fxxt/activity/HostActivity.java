package aizulove.com.fxxt.activity;

import android.annotation.TargetApi;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.view.ToastSingle;

public class HostActivity extends TabActivity {

    private TabHost tabHost;
    private ImageView erweimaimageView;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_tabhost);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViews();
        Intent intent;
        tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
        tabHost.setup();
        ((TextView)findViewById(R.id.head_title)).setText("用户登录和注册");
        View view2 = this.getLayoutInflater().inflate(R.layout.activity_tab1, null);
        TextView tv2 = (TextView) view2.findViewById(R.id.tv);
        tv2.setText("登录");
        View view3 = this.getLayoutInflater().inflate(R.layout.activity_tab2, null);
        TextView tv3 = (TextView) view3.findViewById(R.id.tv);
        tv3.setText("注册");
        intent=new Intent(this,LoginActivity.class);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("登录").setIndicator(view2).setContent(intent);

        intent=new Intent(this,RegisterActivity.class);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("注册").setIndicator(view3).setContent(intent);

        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
    }

    private void findViews() {
//        erweimaimageView= (ImageView) findViewById(R.id.erweimaimageView);
//        erweimaimageView.setVisibility(View.VISIBLE);
//        erweimaimageView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v) {
//        Intent intent;
//        switch (v.getId()){
//            case R.id.erweimaimageView:
//                startActivityForResult(new Intent(HostActivity.this, CaptureActivity.class), 0);
//                break;
//        }
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode,Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==RESULT_OK){
//            Bundle bundle=data.getExtras();
//            String result= bundle.getString("result");
//            ToastSingle.showToast(HostActivity.this, result);
//        } if(resultCode == RESULT_CANCELED) {
//            ToastSingle.showToast(HostActivity.this,"扫描出错！");
//        }
//    }
}
