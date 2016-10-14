package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/10/11.
 */
public class CancelHotAttention extends AsyncTask<Void,Void,String> {
    private Context context;
    private Map<String, String> map;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    public CancelHotAttention(Context context,Map<String,String> map){
        this.context=context;
        this.map=map;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result=null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.CANCELHOTATTENTION;// VariablesOfUrl.GETLOGOLIST;
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
                if (!result.equals("1000")){
                    ToastSingle.showToast(context, "失败了！");
                }
            }else{
                ToastSingle.showToast(context, "没有数据");
            }
        }else {
            ToastSingle.showToast(context, "请检查网络连接是否正常");
        }
    }
}
