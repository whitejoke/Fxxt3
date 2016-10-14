package aizulove.com.fxxt.task;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.utils.FileUtil;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ZanPostTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private boolean typeFlag=true;
    private boolean judgeInternet;
    private  Map<String, String> map;
    public ZanPostTask(Context context,Map<String, String> map){
        this.map=map;
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... params) {
        judgeInternet = NetWork.checkNetWorkStatus(this.context);
        String result=null;
        try {
            if (judgeInternet) {
                String url= VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.ADDZANPOST;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.addZanStr(jsonStr.toString());
            }
        }catch (Exception e){
            return null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (judgeInternet) {
            if (result!=null){
                ToastSingle.showToast(context,result);
            }
        }
    }
}
