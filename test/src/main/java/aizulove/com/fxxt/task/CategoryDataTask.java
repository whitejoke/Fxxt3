package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/1.
 */
public class CategoryDataTask extends AsyncTask<Void,Void,List<Product>>{
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Product> listMessage;
    private ProductAdapter adapter;
    private GridView listView;
    private Map<String, String> map;
    private String url;

    public CategoryDataTask(Context context, List<Product> listMessage, ProductAdapter adapter, Map<String, String> map, String url) {
        this.context = context;
        this.listMessage = listMessage;
        this.adapter = adapter;
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
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.getMallListByKeyword(jsonStr.toString());
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
