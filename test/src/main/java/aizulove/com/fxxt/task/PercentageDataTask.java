package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import java.util.Map;
import aizulove.com.fxxt.adapter.PercentageAdapter;
import aizulove.com.fxxt.modle.entity.Percentage;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class PercentageDataTask extends AsyncTask<Void, Void, List<Percentage>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Percentage> listMessage;
    private PercentageAdapter adapter;
   private  GridListView listView;
    private Map<String, String> map;
    public PercentageDataTask(Context context,Map<String, String> map, List<Percentage> listMessage, PercentageAdapter adapter, GridListView listView){
        super();
        this.context=context;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.map=map;
        this.listView=listView;
    }

    @Override
    protected List<Percentage> doInBackground(Void... params) {
        List<Percentage> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETPERCENTAGEMES;
                System.out.println("url=="+url);
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserPercentageList(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Percentage> result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result != null) {
                    listMessage.clear();
                    listMessage.addAll(result);
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
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
}