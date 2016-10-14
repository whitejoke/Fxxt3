package aizulove.com.fxxt.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.SharedPreferenceUtil;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class UpdateMemberActivity extends Activity {

    private TextView titleview;
    private WebView webView;
    protected SharedPreferences sharedPreferences;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        findViews();
        DataTask();
    }

    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
//         webView = (WebView) findViewById(R.id.categroyWebview);
         titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)) .setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         titleview.setText("用户中心");
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
       // webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        String url=VariablesOfUrl.Base_URL.replace("${token}", SharedPreferenceUtil.getMemberSharedPreference(sharedPreferences).getToken())+VariablesOfUrl.EDITMEMBER;
        webView.loadUrl(url);
        webView.setWebChromeClient(new CategroyWebChromeViewClient());
        webView.setWebViewClient(new CategroyWebViewClient());
    }

    //Web视图
    private class CategroyWebChromeViewClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title){
            super.onReceivedTitle(view, title);
        }
    }

    private class CategroyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
