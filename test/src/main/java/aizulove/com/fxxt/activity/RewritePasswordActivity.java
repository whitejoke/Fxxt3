package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/12.
 */
public class RewritePasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText oldpassword,password,newpassword;
    private Button button;
    private TextView titleview;

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_password, null);
        layoutContent.addView(convertView);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        titleview=(TextView)findViewById(R.id.head_title);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleview.setText("修改密码");

        oldpassword=(EditText)findViewById(R.id.oldpassword);
        password=(EditText)findViewById(R.id.nowpassword);
        newpassword=(EditText)findViewById(R.id.newpassword);
        button=(Button)findViewById(R.id.button);
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
            case R.id.button:
                if (oldpassword.getText().toString().equals("")){
                    Toast.makeText(context,"旧密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if (password.getText().toString().equals("")){
                        Toast.makeText(context,"新密码不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        if (newpassword.getText().toString().equals("")){
                            Toast.makeText(context,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            if (newpassword.getText().toString().equals(password.getText().toString())){
                                Log.i("susu",getMemberSharedPreference().getPassword().toString());
                                if (oldpassword.getText().toString().equals(getMemberSharedPreference().getPassword().toString())){
                                    Map<String, String> map = new HashMap<String, String>();
                                    map.put("oldPassword",oldpassword.getText().toString());
                                    map.put("password", password.getText().toString());
                                    map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                                    new MemberPasswordTask(context,map).execute();
                                }else {
                                    Toast.makeText(context,"原密码错误",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context,"两次密码不一致",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                break;
        }
    }

    private class MemberPasswordTask extends AsyncTask<Void,Void,String>{
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String, String> map;
        public MemberPasswordTask(Context context, Map<String, String> map) {
            this.map=map;
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result=null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.MODIFYPASSWORD;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.modifyPassword(jsonStr.toString());
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
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", password.getText()+"");
                        editor.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, MyActivity.class);
                        startActivity(intent);
                    }else{
                        ToastSingle.showToast(context, "修改失败");
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
