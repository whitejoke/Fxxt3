package aizulove.com.fxxt.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Map;

import aizulove.com.fxxt.activity.ChangePwActivity;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/9/20.
 */
public class EmailTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private Map<String, String> map;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private String email;
    public EmailTask(Context context,Map<String,String> map,String email){
        this.context=context;
        this.map=map;
        this.email=email;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result=null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.FORGETMEMBER;// VariablesOfUrl.GETLOGOLIST;

                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                Log.i("susu", String.valueOf(jsonStr));
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.forgetMember(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result.equals("1000")){
                    Intent intent=new Intent();
                    intent.setClass(context,ChangePwActivity.class);
                    intent.putExtra("email",email);
                    context.startActivity(intent);
                }else {
                    ToastSingle.showToast(context, "没有该邮箱！");
                }
            }else{
                ToastSingle.showToast(context, "没有数据");
            }
        }else {
            ToastSingle.showToast(context, "请检查网络连接是否正常");
        }
    }
}
