package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import com.cjj.MaterialRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import aizulove.com.fxxt.adapter.QshiqAdapter;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class QishiJulebuDataTask extends AsyncTask<Void, Void, List<Post>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Post> listMessage;
    private QshiqAdapter adapter;
   private  GridListView listView;
    private MaterialRefreshLayout mlayout;
    public QishiJulebuDataTask(Context context, List<Post> listMessage, QshiqAdapter adapter, GridListView listView,MaterialRefreshLayout mlayout){
        super();
        this.context=context;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.listView=listView;
        this.mlayout=mlayout;
    }

    @Override
    protected List<Post> doInBackground(Void... params) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type","2");
        List<Post> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETPOSTLIST;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);

                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserPostList(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Post> result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result != null) {
                    listMessage.clear();
                    listMessage.addAll(result);
                    adapter.notifyDataSetChanged();
                    mlayout.finishRefresh();
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