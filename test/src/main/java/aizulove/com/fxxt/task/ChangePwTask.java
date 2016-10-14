package aizulove.com.fxxt.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.activity.HostActivity;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/9/20.
 */
public class ChangePwTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private Map<String, String> map;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    public ChangePwTask(Context context,Map<String,String> map){
        this.context=context;
        this.map=map;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result=null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.RESETPASSWD;// VariablesOfUrl.GETLOGOLIST;

                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
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
                    intent.setClass(context, HostActivity.class);
                    context.startActivity(intent);
                    ToastSingle.showToast(context, "修改成功！");
                }else {
                    ToastSingle.showToast(context, "验证码错误！");
                }
            }else{
                ToastSingle.showToast(context, "没有数据");
            }
        }else {
            ToastSingle.showToast(context, "请检查网络连接是否正常");
        }
    }
}
