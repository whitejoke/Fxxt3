package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class TiXianActivity extends BaseActivity {


    private TextView jr;
    private TextView trade;
    private TextView truename;
    private TextView amont;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_tixian, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }



    protected void findViews() {
        jr=(TextView)findViewById(R.id.jr);
        trade=(TextView)findViewById(R.id.trade);
        truename=(TextView)findViewById(R.id.truename);
        amont=(TextView)findViewById(R.id.amont);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("提现");
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
    }

    protected  void setSelecttab() {
        SELECTTAB=4;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        jr.setText(String.valueOf(getMemberSharedPreference().getMoney()));
    }

    public void click(View view) {
        switch (view.getId()){
         case R.id.submit:
           Map<String,String>map=new HashMap<String,String>();
             map.put("userId",getMemberSharedPreference().getUserid()+"");
             map.put("amont",amont.getText().toString());
             map.put("truename",truename.getText().toString());
             map.put("trade",trade.getText().toString());
            new  TixianTask(context,map).execute();
          break;
        }
    }

    class TixianTask extends AsyncTask<Void, Void, String> {

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private Map<String, String> map;
        public TixianTask(Context context,Map<String, String> map){
            super();
            this.map=map;
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.APPLYWITHDRAW;
                    System.out.println("url==" + url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.TxianStr(jsonStr.toString());
                    System.out.println(result);                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        ToastSingle.showToast(context, result);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Double money=Double.parseDouble(amont.getText().toString())-getMemberSharedPreference().getMoney();
                        editor.putString("money", money+"");
                        editor.commit();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }else{
                        ToastSingle.showToast(context, "出错啦");
                    }
                }else{
                    ToastSingle.showToast(context, "没有数据");
                }
            }else {
                ToastSingle.showToast(context, "请检查网络连接是否正常");
            }
        }
    }
}
