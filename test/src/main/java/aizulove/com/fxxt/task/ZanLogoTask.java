package aizulove.com.fxxt.task;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ZanLogoTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private boolean typeFlag=true;
    private boolean judgeInternet;
    private  Map<String, String> map;
    public ZanLogoTask(Context context, Map<String, String> map){
        this.map=map;
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... params) {
        judgeInternet = NetWork.checkNetWorkStatus(this.context);
        String result=null;
        try {
            if (judgeInternet) {
                String url= VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.ADDZANLOGO;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.addLogoZanStr(jsonStr.toString());
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
                ToastSingle.showToast(context,result);
            }
        }
    }
}
