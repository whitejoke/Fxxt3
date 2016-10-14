package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/8/13.
 */
public class OrderStatusTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private String url;
    private Map<String,String> map;
    public OrderStatusTask(Context context, String url,Map<String,String> map) {
        this.context = context;
        this.url = url;
        this.map=map;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result=null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +this.url;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.equals("[]")) {
                    typeFlag = false;
                }
                result=JsonParserFactory.modifyMallOrderStatus(jsonStr.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
