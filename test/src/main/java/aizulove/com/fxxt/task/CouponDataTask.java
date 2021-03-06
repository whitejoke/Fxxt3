package aizulove.com.fxxt.task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.adapter.CouponAdapter;
import aizulove.com.fxxt.modle.entity.Coupon;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/5/3.
 */
public class CouponDataTask extends AsyncTask<Void, Void, List<Coupon>> {

    private Context context;
    private boolean judgeInternet;
    private boolean typeFlag=true;
    private List<Coupon> listMessage;
    private CouponAdapter adapter;
    private   Map<String, String> map;
   private  GridListView listView;
    public CouponDataTask(Context context, List<Coupon> listMessage,  Map<String, String> map,CouponAdapter adapter, GridListView listView){
        super();
        this.context=context;
        this.map=map;
        this.listMessage=listMessage;
        this.adapter=adapter;
        this.listView=listView;
    }

    @Override
    protected List<Coupon> doInBackground(Void... params) {

        List<Coupon> result = null;
        judgeInternet = NetWork.checkNetWorkStatus(context);
        try {
            if (judgeInternet) {
                String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETMYCOUPONLIST;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.jsonParserCouponList(jsonStr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<Coupon> result) {
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