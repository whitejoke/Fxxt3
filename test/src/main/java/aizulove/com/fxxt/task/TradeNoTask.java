package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/8/8.
 */
public class TradeNoTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private String url,trade;
    OnDataFinishedListener onDataFinishedListener;
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }

    public TradeNoTask(Context context, String url,String trade) {
        this.context = context;
        this.url = url;
        this.trade=trade;
    }
    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                Map<String,String> map=new HashMap<>();
                String url = VariablesOfUrl.APP_BASE_URL +this.url;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.getMallOrderCode(jsonStr.toString());
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
        if(result!=null){
            trade=result;
            //onDataFinishedListener.onDataSuccessfully(result);
        }
    }

    public interface OnDataFinishedListener {

        public void onDataSuccessfully(String data);

    }
}
