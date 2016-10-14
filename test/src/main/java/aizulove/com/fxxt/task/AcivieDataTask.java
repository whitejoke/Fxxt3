package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import aizulove.com.fxxt.adapter.AcivieAdapter;
import aizulove.com.fxxt.modle.entity.Activity;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class AcivieDataTask extends AsyncTask<Void, Void, List<Activity>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Activity> listMessage;
    private AcivieAdapter adapter;
   private  GridListView listView;
    public AcivieDataTask(Context context, List<Activity> listMessage, AcivieAdapter adapter, GridListView listView){
        super();
        this.context=context;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.listView=listView;
    }

    @Override
    protected List<Activity> doInBackground(Void... params) {
        Map<String, String> map = new HashMap<String, String>();
        List<Activity> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETACTIVITYLIST;
                System.out.println("url=="+url);
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserActivityList(jsonStr.toString());
                System.out.println(result);
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Activity> result) {
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