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
 * Created by joker on 2016/10/31.
 */
public class TalkTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private boolean typeFlag=true;
    private boolean judgeInternet;
    private Map<String, String> map;
    public TalkTask(Context context, Map<String, String> map){
        this.map=map;
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... params) {
        judgeInternet = NetWork.checkNetWorkStatus(this.context);
        String result=null;
        try {
            if (judgeInternet) {
                String url= VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.SAVEMESSAGE;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                Log.i("susu", String.valueOf(jsonStr));
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.addAttentionLogoStr(jsonStr.toString());
            }
        }catch (Exception e){
            return null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if (judgeInternet) {
            if (result!=null){
                ToastSingle.showToast(context, result);
            }
        }
    }
}
