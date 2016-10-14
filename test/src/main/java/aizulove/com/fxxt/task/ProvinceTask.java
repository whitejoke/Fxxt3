package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.modle.entity.City;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/11.
 */
public class ProvinceTask extends AsyncTask<Void,Void,List<City>> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<City> listMessage;
    private Map<String, String> map;
    private String url;
    OnDataFinishedListener onDataFinishedListener;
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }

    public ProvinceTask(Context context, List<City> listMessage, String url,Map<String,String> map) {
        this.context = context;
        this.listMessage = listMessage;
        this.url = url;
        this.map=map;
    }

    @Override
    protected List<City> doInBackground(Void... params) {
        List<City> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +this.url;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.getProvinceList(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<City> result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result != null) {
                    listMessage.clear();
                    listMessage.addAll(result);
                    onDataFinishedListener.onDataSuccessfully(result);

                }else{
                    ToastSingle.showToast(context, "参数错误");
                }
            }else{
                ToastSingle.showToast(context, "没有数据");
            }
        }else {
            ToastSingle.showToast(context, "请检查网络连接是否正常");
        }
    }
    public interface OnDataFinishedListener {

        public void onDataSuccessfully(List<City> result);

        public void onDataFailed();

    }
}
