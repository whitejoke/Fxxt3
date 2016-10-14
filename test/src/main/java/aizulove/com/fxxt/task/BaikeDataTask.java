package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.InformationAdapter;
import aizulove.com.fxxt.modle.entity.Information;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class BaikeDataTask extends AsyncTask<Void, Void, List<Information>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Information> listMessage;
    private InformationAdapter adapter;
   private ListView listView;
    public BaikeDataTask(Context context, List<Information> listMessage, InformationAdapter adapter, ListView listView){
        super();
        this.context=context;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.listView=listView;
    }

    @Override
    protected List<Information> doInBackground(Void... params) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type","2");
        List<Information> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETINFORMATIONLIST;
                System.out.println("url=="+url);
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserInformationList(jsonStr.toString());
                System.out.println(result);;
            }
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Information> result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result != null) {
                    listMessage.clear();
                    listMessage.addAll(result);
                    adapter.notifyDataSetChanged();
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