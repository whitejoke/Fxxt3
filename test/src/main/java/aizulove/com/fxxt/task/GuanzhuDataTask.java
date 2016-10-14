package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.GuanzhuAdapter;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class GuanzhuDataTask extends AsyncTask<Void, Void, List<Logo>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Logo> listMessage;
    private GuanzhuAdapter adapter;
    private   Map<String, String> map;
   private  GridListView listView;
    private String urlString;
    public GuanzhuDataTask(Context context, List<Logo> listMessage, Map<String, String> map, GuanzhuAdapter adapter, GridListView listView,String urlString){
        super();
        this.context=context;
        this.map=map;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.listView=listView;
        this.urlString=urlString;
    }

    @Override
    protected List<Logo> doInBackground(Void... params) {

        List<Logo> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +urlString ;
                Log.i("susu",url);
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                Log.d("susu", String.valueOf(jsonStr));
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserGuanzhuList(jsonStr.toString());
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Logo> result) {
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