package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/25.
 */
public class ThirdRegistTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private  Map<String, String> map;
    public ThirdRegistTask(Context context,Map<String, String> map){
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
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.THIRDREGISTMEMBER;
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
                    ToastSingle.showToast(context, "失败");
                }
            }else{
                ToastSingle.showToast(context, "没有数据");
            }
        }else {
            ToastSingle.showToast(context, "请检查网络连接是否正常");
        }
    }

}
