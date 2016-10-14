package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.utils.FormFile;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class FindActivity extends BaseActivity implements View.OnClickListener,View.OnLongClickListener{

    private SelectPicPopupWindow popWindow;
    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private  ImageView  temp;
    private Button fabuButton;
    private EditText findContent;
    private List<String> paths=new ArrayList<String>();
    private MaterialRefreshLayout materialRefreshLayout;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_find, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {
        fabuButton=(Button)findViewById(R.id.fabuButton);
        findContent=(EditText)findViewById(R.id.findContent);
        img0=(ImageView)findViewById(R.id.img0);
        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img0.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img0.setOnLongClickListener(this);
        img1.setOnLongClickListener(this);
        img2.setOnLongClickListener(this);
        fabuButton.setOnClickListener(this);
       ((TextView)findViewById(R.id.head_title)).setText("发布发现");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected  void setSelecttab(){
        SELECTTAB=2;
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){

    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            popWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, 1);
                break;
                case R.id.btn_pick_photo:
                    Intent local = new Intent();
                    local.setType("image/*");
                    local.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(local, 2);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.img0:
            popWindow = new SelectPicPopupWindow(this, itemsOnClick);
            popWindow.showAtLocation(img0, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
            temp=img0;
            break;
            case R.id.img1:
            popWindow = new SelectPicPopupWindow(this, itemsOnClick);
            popWindow.showAtLocation(img0, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
            temp=img1;
            break;
            case R.id.img2:
            popWindow = new SelectPicPopupWindow(this, itemsOnClick);
            popWindow.showAtLocation(img0, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
            temp=img2;
            break;
            case R.id.fabuButton:
                Map<String, String> map=new HashMap<String,String>();
                map.put("userId",getMemberSharedPreference().getUserid()+"");
                map.put("content",findContent.getText().toString());
                map.put("type", getIntent().getStringExtra("type") == null ? "1" : getIntent().getStringExtra("type"));
                map.put("logoId",getIntent().getStringExtra("logoId")==null?"0":getIntent().getStringExtra("logoId"));
                map.put("title", "title");
             new FindUploadTask(context,map).execute();
            break;
        }
    }

    /**
     * 拍照上传
     */
    @SuppressWarnings("deprecation")
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String srcPath="";
            switch (requestCode) {
                case 1:
                    Bundle extras = data.getExtras();
                    Bitmap b = (Bitmap) extras.get("data");
                    String name = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    String fileNmae = Environment.getExternalStorageDirectory().toString() + File.separator + name + ".jpg";
                    srcPath = fileNmae;
                    System.out.println(srcPath + "----------");
                    File myCaptureFile = new File(fileNmae);
                    try {
                        if (Environment.getExternalStorageState().equals(
                                Environment.MEDIA_MOUNTED)) {
                            if (!myCaptureFile.exists()) {
                                System.out.println(myCaptureFile.createNewFile()+ "----------");
                            }
                            BufferedOutputStream bos;
                            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                            b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                            bos.flush();
                            bos.close();
                        } else {
                            Toast toast = Toast.makeText(this,"保存失败，SD卡无效", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Uri uri = data.getData();
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = managedQuery(uri, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    srcPath = cursor.getString(column_index);
                    break;
                default:
                break;
            }
            switch (temp.getId()) {
             case R.id.img0:
              paths.add(srcPath);
             break;
             case R.id.img1:
              paths.add(srcPath);
             break;
             case R.id.img2:
              paths.add(srcPath);
             break;
            }

            Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
            temp.setImageBitmap(bitmap);

        }
    }

    @Override
    public boolean onLongClick(View v){
        switch (v.getId()){
            case R.id.img0:
            img0.setImageResource(R.mipmap.ic_photo);
            if (paths.size()>1) {
                paths.remove(0);
            }
            break;
            case R.id.img1:
            img1.setImageResource(R.mipmap.ic_photo);
            if (paths.size()>2) {
                paths.remove(1);
            }
            break;
            case R.id.img2:
            img2.setImageResource(R.mipmap.ic_photo);
            if (paths.size()>3) {
                paths.remove(2);
            }
            break;
        }
        return false;
    }

    class FindUploadTask extends AsyncTask<Void, Integer, String> {

        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private Map<String, String> map;
        private ProgressDialog progressDialog;

        public FindUploadTask(Context context,Map<String, String> map){
            super();
            this.map=map;
            this.context=context;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.UPLOADPOST;
                    List<FormFile> files=new ArrayList<FormFile>();
                    for (int i=0;i<paths.size();i++){
                        //revitionImageSize(paths.get(i),200);
                        FormFile formfile = new FormFile(new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()+i)+".jpg",new File(paths.get(i)),"files"+(i+1), "application/octet-stream");
                        files.add(formfile);
                    }
                    String jsonStr = NetWork.post(url, map, files);
                    if (jsonStr.equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.UpoladStr(jsonStr);
                                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("提示信息");
            progressDialog.setMessage("正在上传中，请稍后......");
            //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
            progressDialog.setCancelable(false);
            //    设置ProgressDialog样式为圆圈的形式
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    Intent intent= new Intent();
                    setResult(1, intent);
                    finish();
                    paths.clear();
                }else{
                    ToastSingle.showToast(context, "没有数据");
                }
            }else {
                ToastSingle.showToast(context, "请检查网络连接是否正常");
            }
        }
    }
    private Bitmap revitionImageSize(String path, int size) throws IOException {
        // 取得图片
        InputStream temp = this.getAssets().open(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 这个参数代表，不为bitmap分配内存空间，只记录一些该图片的信息（例如图片大小），说白了就是为了内存优化
        options.inJustDecodeBounds = true;
        // 通过创建图片的方式，取得options的内容（这里就是利用了java的地址传递来赋值）
        BitmapFactory.decodeStream(temp, null, options);
        // 关闭流
        temp.close();

        // 生成压缩的图片
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            // 这一步是根据要设置的大小，使宽和高都能满足
            if ((options.outWidth >> i <= size)
                    && (options.outHeight >> i <= size)) {
                // 重新取得流，注意：这里一定要再次加载，不能二次使用之前的流！
                temp = this.getAssets().open(path);
                // 这个参数表示 新生成的图片为原始图片的几分之一。
                options.inSampleSize = (int) Math.pow(2.0D, i);
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false;

                bitmap = BitmapFactory.decodeStream(temp, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }
}
