package aizulove.com.fxxt.activity;


import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class HelpActivity extends BaseActivity {

    private TextView titleview;
    private WebView webView;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_category, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }


    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
         rightimageView=(ImageView)findViewById(R.id.rightimageView);
//         webView = (WebView) findViewById(R.id.categroyWebview);
         titleview=(TextView)findViewById(R.id.head_title);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         titleview.setText("帮助");
    }

    protected  void setSelecttab() {
        SELECTTAB=1;
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
       // webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl(VariablesOfUrl.HELP);
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
