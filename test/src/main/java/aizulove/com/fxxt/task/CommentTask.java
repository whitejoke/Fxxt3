package aizulove.com.fxxt.task;
import android.content.Context;
import android.os.AsyncTask;

import java.util.Map;

import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by Administrator on 2016/4/29.
 */
public class CommentTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private boolean typeFlag=true;
    private boolean judgeInternet;
    private  Map<String, String> map;
    public CommentTask(Context context, Map<String, String> map){
        this.map=map;
        this.context=context;
    }

    @Override
    protected String doInBackground(Void... params) {
        judgeInternet = NetWork.checkNetWorkStatus(this.context);
        String result=null;
        try {
            if (judgeInternet) {
                String url= VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.SAVECOMMENT;
                StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                if (jsonStr.toString().equals("[]")) {
                    typeFlag = false;
                }
                result = JsonParserFactory.addCommentStr(jsonStr.toString());
            }
        }catch (Exception e){
            return null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if (judgeInternet) {
            if (result!=null){

            }
        }
    }
}
