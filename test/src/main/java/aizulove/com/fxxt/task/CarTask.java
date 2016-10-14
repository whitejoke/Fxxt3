package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CarAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by joker on 2016/8/2.
 */
public class CarTask extends AsyncTask<Void,Void,List<Product>> {
    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Product> listMessage;
    private CarAdapter adapter;
    private GridView listView;
    private Map<String, String> map;
    private String url;
    public CarTask(Context context, List<Product> listMessage, CarAdapter adapter, Map<String, String> map, String url,GridView view) {
        this.context = context;
        this.listMessage = listMessage;
        this.adapter = adapter;
        this.map = map;
        this.url = url;
        this.listView=view;
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
                result = JsonParserFactory.getCartListByUserId(jsonStr.toString());
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
                    if (listMessage.size()<1){
                        listView.setBackgroundResource(R.mipmap.shop);
                    }
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
