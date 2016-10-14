package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ProductDataTask extends AsyncTask<Void, Void, List<Product>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Product> listMessage;
    private ProductAdapter adapter;
   private  GridListView listView;
    private Map<String, String> map;
    private String Url;
    public ProductDataTask(Context context, Map<String, String> map, List<Product> listMessage, ProductAdapter adapter, GridListView listView,String url){
        super();
        this.context=context;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.map=map;
        this.listView=listView;
        this.Url=url;
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
        List<Product> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL +Url ;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                //Log.i("susu", String.valueOf(jsonStr));
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserProductList(jsonStr.toString());
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