package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/10.
 */
public class MoneyPayTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private boolean typeFlag=true;
    private boolean judgeInternet;
    private Map<String, String> map;
    private String url;

    public MoneyPayTask(Context context, Map<String, String> map,String url) {
        this.context = context;
        this.map = map;
        this.url=url;
    }


    @Override
    protected String doInBackground(Void... params) {
        judgeInternet = NetWork.checkNetWorkStatus(this.context);
        String result=null;
        try {
            if (judgeInternet) {
                String url= VariablesOfUrl.APP_BASE_URL +this.url;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.moneyPay(jsonStr.toString());
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
