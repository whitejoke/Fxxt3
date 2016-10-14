package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView titleview;
    protected SharedPreferences sharedPreferences;
    private EditText username,password,fenxiao,email;
    private Button button;
    private ImageView erwima;

    public void setContentViews() {
        View convertView = mInflater.inflate(R.layout.activity_register, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {
        username=(EditText)findViewById(R.id.et_username);
        password=(EditText)findViewById(R.id.et_userpassword);
        email=(EditText)findViewById(R.id.et_useremail);
        fenxiao=(EditText)findViewById(R.id.et_fenxiao);
        button=(Button)findViewById(R.id.btn_regist);
        erwima= (ImageView) findViewById(R.id.erweimaimageView);
        erwima.setOnClickListener(this);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_regist:
                Map<String, String> map = new HashMap<String, String>();
                map.put("username",username.getText().toString());
                map.put("password", password.getText().toString());
                map.put("email", email.getText().toString());
                map.put("referralCode", fenxiao.getText().toString());
                new MemberRegistTask(context,map).execute();
                break;
            case R.id.erweimaimageView:
                startActivityForResult(new Intent(context, CaptureActivity.class), 0);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            String result= bundle.getString("result");
            fenxiao.setText(result);
            ToastSingle.showToast(context, result);
        } if(resultCode == RESULT_CANCELED) {
            ToastSingle.showToast(context,"扫描出错！");
        }
    }

    private class MemberRegistTask extends AsyncTask<Void,Void,String>{
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String, String> map;
        public MemberRegistTask(Context context,Map<String, String> map){
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
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.REGISTMEMBER;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.registMember(jsonStr.toString());
                }
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
                        ToastSingle.showToast(context, result);
                    }else {
                        ToastSingle.showToast(context, "邮箱已存在");
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
