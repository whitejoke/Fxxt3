package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import aizulove.com.fxxt.R;
import aizulove.com.fxxt.alipay.Keys;
import aizulove.com.fxxt.alipay.PayResult;
import aizulove.com.fxxt.alipay.SignUtils;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class ChongZhiActivity extends BaseActivity {

    private TextView amont;
    private String rechargeCode;
    private String ordersn;
    private  String money;
    protected SharedPreferences sharedPreferences;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_congzhi, null);
        layoutContent.addView(convertView);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {
      amont=(TextView)findViewById(R.id.amont);
     ((TextView)findViewById(R.id.head_title)).setText("充值");
    blakimageView= ((ImageView)findViewById(R.id.blakimageView));
    blakimageView .setVisibility(View.VISIBLE);
    blakimageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
     ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
    }

    protected  void setSelecttab(){
        SELECTTAB=4;
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
         ordersn=getIntent().getStringExtra("ordersn");
         money=getIntent().getStringExtra("money");
        if (null!=ordersn&&null!=money){
          rechargeCode=ordersn;
          amont.setText(money);
          amont.setEnabled(false);
        }
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.czsubmit:
            if (null!=ordersn&&null!=money){
                ToastSingle.showToast(context, "money=="+money);
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId",getMemberSharedPreference().getUserid()+"");
                map.put("money",amont.getText().toString());
                map.put("rechargeCode",ordersn);
                new  MemberChongzhiTask(context,map).execute();
            }else {
            new RechargeCodeTask(context).execute();
            }
            break;
        }
    }

    private String getOrderInfo(String orderSn, double totalMoney) {
        StringBuilder sb = new StringBuilder();
        sb.append("partner=\"");
        sb.append(Keys.DEFAULT_PARTNER);
        sb.append("\"&out_trade_no=\"");
        sb.append(orderSn);
        sb.append("\"&subject=\"");
        sb.append("蓝车世界");
        sb.append("\"&body=\"");
        sb.append("蓝车世界");
        sb.append("\"&total_fee=\"");
        sb.append("0.01");
        sb.append("\"&notify_url=\"");
        sb.append(URLEncoder.encode("http://notify.java.jpxx.org/index.jsp"));
        sb.append("\"&service=\"mobile.securitypay.pay");
        sb.append("\"&_input_charset=\"UTF-8");
        sb.append("\"&return_url=\"");
        sb.append(URLEncoder.encode("http://m.alipay.com"));
        sb.append("\"&payment_type=\"1");
        sb.append("\"&seller_id=\"");
        sb.append(Keys.DEFAULT_SELLER);
        sb.append("\"&it_b_pay=\"1m");
        sb.append("\"");
        return new String(sb);
    }

    private String getOutTradeNo(String orderSn) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date =format.parse(orderSn);
        String key = format.format(date);
        java.util.Random r = new java.util.Random();
        key += r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            PayResult payResult = new PayResult((String) msg.obj);
            String resultInfo = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Double money=Double.parseDouble(amont.getText().toString())+getMemberSharedPreference().getMoney();
                editor.putString("money", money+"");
                editor.commit();
                Map<String, String> map = new HashMap<String, String>();
                map.put("rechargeCode",rechargeCode);
                new  RechargeStatusTask(context,map).execute();
            } else {
                if (TextUtils.equals(resultStatus, "8000")){
                    Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    private String sign(String content) {
        return SignUtils.sign(content, Keys.PRIVATE);
    }

    class RechargeCodeTask extends AsyncTask<Void, Void, String> {

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        public RechargeCodeTask(Context context){
            super();
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params){
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETRECHAGECODE;
                    Map<String, String> map=new HashMap<String,String>();
                    System.out.println("url=="+url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.RechargeCodeStr(jsonStr.toString());
                    System.out.println(result);                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("userId",getMemberSharedPreference().getUserid()+"");
                        map.put("money",amont.getText().toString());
                        map.put("rechargeCode",result);
                        rechargeCode=result;
                        new  MemberChongzhiTask(context,map).execute();
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

    class RechargeStatusTask extends AsyncTask<Void, Void, String>{
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String, String> map;
        public RechargeStatusTask(Context context, Map<String, String> map){
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
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.MODIFYRECHARGESTATUS;
                    System.out.println("url=="+url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.RechargeCodeStr(jsonStr.toString());
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
                        ToastSingle.showToast(context,result);
                    }else{
                        ToastSingle.showToast(context, "出错率");
                    }
                }else{
                    ToastSingle.showToast(context, "没有数据");
                }
            }else {
                ToastSingle.showToast(context, "请检查网络连接是否正常");
            }
        }
    }

    class MemberChongzhiTask extends AsyncTask<Void, Void, String>{

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private Map<String, String> map;
        public MemberChongzhiTask(Context context,Map<String, String> map){
            super();
            this.map=map;
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params){
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.RECHARGEMONEY;
                    System.out.println("url=="+url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.ChongzhiStr(jsonStr.toString());
                    System.out.println(result);                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        String orderInfo = getOrderInfo(rechargeCode,Double.parseDouble(amont.getText().toString()));
                        String sign = sign(orderInfo);
                        try {
                            sign = URLEncoder.encode(sign, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                            PayTask alipay = new PayTask(ChongZhiActivity.this);
                            String result = alipay.pay(payInfo, true);
                            Message msg = new Message();
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
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
