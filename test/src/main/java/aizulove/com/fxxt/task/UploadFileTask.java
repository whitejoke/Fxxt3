package aizulove.com.fxxt.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.utils.FormFile;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.UploadUtils;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class UploadFileTask extends AsyncTask<String, Void, String>{
	public String requestURL= VariablesOfUrl.APP_BASE_URL+VariablesOfUrl.UPLOADPORTRAITS;
   private  ProgressDialog pdialog;
   private  Activity context=null;
    private int userId;
    public UploadFileTask(Activity ctx,int userId){
    	this.context=ctx;
        this.userId=userId;
    }
    @Override
    protected void onPostExecute(String result) {
    }

	  @Override
	  protected void onPreExecute() {
	  }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

		@Override
		protected String doInBackground(String... params) {
//            File file=new File(params[0]);
//            return UploadUtils.uploadFile(file, requestURL);
            Map<String,String> map=new HashMap<>();
            //map.put("fileimg",params[0]);
            map.put("userId", String.valueOf(userId));
            Log.i("susu", String.valueOf(userId));
            String result = null;
            FormFile formfile = new FormFile(userId+"_member.png",new File(params[0]),"fileimg", "application/octet-stream");
            List<FormFile> files=new ArrayList<FormFile>();
            files.add(formfile);
            String jsonStr = null;
            try {
                jsonStr = NetWork.post(requestURL, map, files);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                result = JsonParserFactory.UpoladStr(jsonStr.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
	       @Override
	       protected void onProgressUpdate(Void... values) {
	       }

	
}