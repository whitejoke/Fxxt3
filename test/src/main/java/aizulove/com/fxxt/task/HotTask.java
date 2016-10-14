package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.cjj.MaterialRefreshLayout;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/7/26.
 */
public class HotTask extends AsyncTask<Void,Void,List<Product>> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Product> listMessage;
    private ProductAdapter adapter;
    private GridView listView;
    private MaterialRefreshLayout srl;
    private Map<String, String> map;
    private String url;

    public HotTask(Context context, List<Product> listMessage, ProductAdapter adapter, MaterialRefreshLayout srl, Map<String, String> map, String url) {
        this.context = context;
        this.listMessage = listMessage;
        this.adapter = adapter;
        this.srl=srl;
        this.map = map;
        this.url = url;
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
        List<Product> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +this.url;// VariablesOfUrl.GETLOGOLIST;
                System.out.println("url=="+url);
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                //Log.i("susu", String.valueOf(jsonStr));
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserCuXiaoMallList(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (typeFlag) {// 返回有数据
                if (result != null) {
                    //listMessage.clear();
                    listMessage.addAll(result);
                    adapter.notifyDataSetChanged();
                    //Log.i("susu","yes");
                    srl.finishRefresh();
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
