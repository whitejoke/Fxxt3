package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.task.ThirdRegistTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity implements View.OnClickListener,Handler.Callback,
         PlatformActionListener {

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR= 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    private static final int MSG_ACTION_CCALLBACK = 2;
    private EditText username;
    private EditText password;
    private TextView forgot,wechat,tvQq,Sinaweibo;
    private Button button;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_login, null);
        layoutContent.addView(convertView);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        ShareSDK.initSDK(this);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {

       // ((TextView)findViewById(R.id.head_title)).setText("用户登录");
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        forgot=(TextView)findViewById(R.id.forgot);
        button=(Button)findViewById(R.id.button);
        wechat= (TextView) findViewById(R.id.wechat);
        tvQq= (TextView) findViewById(R.id.tvQq);
        Sinaweibo= (TextView) findViewById(R.id.SinaWeibo);
        Sinaweibo.setOnClickListener(this);
        wechat.setOnClickListener(this);
        tvQq.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }

    protected  void setSelecttab() {
        this.SELECTTAB=4;
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
                Map<String, String> map = new HashMap<String, String>();
                map.put("username",username.getText().toString());
                map.put("password", password.getText().toString());
                new MemberLoginTask(context,map).execute();
                break;
            case R.id.forgot:
                Intent intent = new Intent();
                intent.setClass(context, ForgotActivity.class);
                startActivity(intent);
                break;
            case R.id.tvQq:
                //authorize(new QQ(this));
                Platform qqfd = ShareSDK.getPlatform(QQ.NAME);
                if (qqfd.isValid ()){
                    qqfd.removeAccount();
                }
                qqfd.setPlatformActionListener(this);//放在if之后不然不行
                qqfd.showUser(null);
                break;
            case R.id.wechat:
                //authorize(new Wechat(context));
                Platform weixinfd = ShareSDK.getPlatform(Wechat.NAME);
                if (weixinfd.isValid ()){
                    weixinfd.removeAccount();
                }
                weixinfd.setPlatformActionListener(this);//放在if之后不然不行
                weixinfd.showUser(null);
                break;
            case R.id.SinaWeibo:
                Platform sinafd = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (sinafd.isValid ()){
                    sinafd.removeAccount();
                }
                sinafd.setPlatformActionListener(this);//放在if之后不然不行
                //sinafd.SSOSetting(true);
                sinafd.showUser(null);
                break;
        }
    }
    private void authorize(Platform plat) {
        if(plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(false);
        plat.showUser(null);
    }
    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
        System.out.println(res);
        //获取资料
        Log.i("susu","111111");
        platform.getDb().getUserName();//获取用户名字
        platform.getDb().getUserIcon(); //获取用户头像
        String token = platform.getDb().getToken();
        System.out.println("token===" + token);
        Log.i("susu",platform.getDb().getUserId());
        String unionid = (String) res.get("unionid");
        System.out.println("unionid==="+unionid);
        Map<String,String> map=new HashMap<>();
        map.put("uid",platform.getDb().getUserId());
        new ThirdMemberLogin(context,map,platform.getDb().getUserId(),platform.getDb().getUserName()).execute();
    }

    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = throwable;
        UIHandler.sendMessage(msg, this);

        // 分享失败的统计
        ShareSDK.logDemoEvent(4, platform);
    }

    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }
    // 回调handleMessage
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.arg1) {
            case 1: {
                Log.i("susu","yes");
            }
            break;
            case 2: {
                String expName = msg.obj.getClass().getSimpleName();
                if ("WechatClientNotExistException".equals(expName)
                        || "WechatTimelineNotSupportedException".equals(expName)
                        || "WechatFavoriteNotSupportedException".equals(expName)) {
                    Toast.makeText(LoginActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case 3: {
                // 取消
                Toast.makeText(LoginActivity.this, "取消····", Toast.LENGTH_SHORT)
                        .show();
            }
            break;
        }

        return false;
    }

    class MemberLoginTask extends AsyncTask<Void, Void, Member> {

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String, String> map;
        public MemberLoginTask(Context context,Map<String, String> map){
            super();
            this.map=map;
            this.context=context;
        }

        @Override
        protected aizulove.com.fxxt.modle.entity.Member doInBackground(Void... params) {
            aizulove.com.fxxt.modle.entity.Member result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.LOGINMEMBER;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.jsonParserMember(jsonStr.toString());
                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(aizulove.com.fxxt.modle.entity.Member result){
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        setMemberSharedPreference(result);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", password.getText()+"");
                        editor.commit();
                        ToastSingle.showToast(context, "登录成功！");
                        Intent intent = new Intent();
                        intent.setClass(context, MyActivity.class);
                        startActivity(intent);
                    }else{
                        ToastSingle.showToast(context, "用户名或密码错误");
                    }
                }else{
                    ToastSingle.showToast(context, "没有数据");
                }
            }else {
                ToastSingle.showToast(context, "请检查网络连接是否正常");
            }
        }
    }
    class ThirdMemberLogin extends AsyncTask<Void, Void, Member> {

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private String uid,nickname;
        private  Map<String, String> map;
        public ThirdMemberLogin(Context context,Map<String, String> map,String uid,String nickname){
            super();
            this.uid=uid;
            this.nickname=nickname;
            this.map=map;
            this.context=context;
        }

        @Override
        protected Member doInBackground(Void... params) {
            Member result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.THIRDLOGINMEMBER;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    //Log.i("susu", String.valueOf(jsonStr));
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.thirdloginMember(jsonStr.toString());
                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Member result){
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        if (result.getCode().equals("1003")){
                            Map<String,String> map=new HashMap<>();
                            map.put("uid",uid);
                            //map.put("nickname",nickname);
                            new ThirdRegistTask(context,map).execute();
                            Map<String,String> parse=new HashMap<>();
                            parse.put("uid",uid);
                            new ThirdMemberLogin(context,parse,uid,nickname).execute();
                        }else if (result.getCode().equals("1000")){
                            setMemberSharedPreference(result);
                            ToastSingle.showToast(context, "登录成功！");
                            Intent intent = new Intent();
                            intent.setClass(context, MyActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        ToastSingle.showToast(context, "登陆失败");
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
