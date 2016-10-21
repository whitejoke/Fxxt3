package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.City;
import aizulove.com.fxxt.modle.entity.CityList;
import aizulove.com.fxxt.task.EditAddressTask;
import aizulove.com.fxxt.task.UploadFileTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;
import aizulove.com.fxxt.view.WheelView;

public class UserCenterActivity extends BaseActivity implements View.OnClickListener {

    private SelectPicPopupWindow popWindow;
    private TextView changeHade,choose_sex,choose_province,choose_city;
    private EditText et_mobile,et_skype,et_qq,et_ali,et_department,
            et_sound,et_career,et_username,et_useremail,et_truename;
    private List<String> paths=new ArrayList<String>();
    private List<City> list=new ArrayList<City>();
    private List<CityList> list2=new ArrayList<CityList>();
    private List<CityList> list_city=new ArrayList<CityList>();
    private String urlProvince= VariablesOfUrl.GETPROVINCELIST;
    private String urlCity= VariablesOfUrl.GETCITYLIST;
    private ImageView head;
    private String[] PLANETS=new String[]{"男","女"};
    private List<String> PLANETS_Province=new ArrayList<>();
    private List<String> PLANETS_City=new ArrayList<>();
    private List<String> PLANETS_CityList=new ArrayList<>();
    private int areaid;
    private String arrchildid="";
    private List<String> child=new ArrayList<>();
    private String url=VariablesOfUrl.MODIFYMEMBER;
    private DisplayImageOptions options;
    private String picPath=null;
    int screenWidth;
    int screenHeight;
    private int gender;


    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_usercenter, null);
        layoutContent.addView(convertView);
        //sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        WindowManager wm= (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth= wm.getDefaultDisplay().getWidth();
        screenHeight=wm.getDefaultDisplay().getHeight();
        areaid=getMemberSharedPreference().getAreaid();
        if (areaid==0){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("arrchildid","");
        }
        gender=getMemberSharedPreference().getGender();
        arrchildid=getMemberSharedPreference().getArrchildid();
        Log.i("susu", String.valueOf(arrchildid));
        //System.out.print("手机宽"+screenWidth+"手机高"+screenHeight);
        findViews();
        DataTaskTest();
        HiddenMeun();
    }

    protected void DataTaskTest(){
        et_username.setText(getMemberSharedPreference().getUsername());
        et_useremail.setText(getMemberSharedPreference().getEmail());
        et_truename.setText(getMemberSharedPreference().getTruename()== null ? "":getMemberSharedPreference().getTruename());
        et_mobile.setText(getMemberSharedPreference().getMobile()== null ? "":getMemberSharedPreference().getMobile());
        et_skype.setText(getMemberSharedPreference().getSkype()== null ? "":getMemberSharedPreference().getSkype());
        et_qq.setText(getMemberSharedPreference().getQq()== null ? "":getMemberSharedPreference().getQq());
        et_ali.setText(getMemberSharedPreference().getAli()== null ? "":getMemberSharedPreference().getAli());
        et_department.setText(getMemberSharedPreference().getDepartment()== null ? "":getMemberSharedPreference().getDepartment());
        et_career.setText(getMemberSharedPreference().getCareer()== null ? "":getMemberSharedPreference().getCareer());

        choose_province.setText(getMemberSharedPreference().getProvince().equals("") ||getMemberSharedPreference().getProvince().equals("null")? "请选择省":getMemberSharedPreference().getProvince());
        choose_city.setText(getMemberSharedPreference().getCity().equals("")||getMemberSharedPreference().getCity().equals("null") ? "请选择市":getMemberSharedPreference().getCity());
        String[] temp={"男","女"};
        choose_sex.setText(getMemberSharedPreference().getGender()==0?"请选择性别":temp[getMemberSharedPreference().getGender() - 1]);

//        if (getMemberSharedPreference().getAreaid()!=0){
//            for (City city:list){
//                String[] array=city.getArrchildid().split(",");
//                for (int i=0;i<array.length;i++) {
//                    if (array[i].equals(getMemberSharedPreference().getAreaid())) {
//                        choose_province.setText(city.getAreaname());
//                        choose_city.setText(PLANETS_CityList.get(getMemberSharedPreference().getAreaid()-1));
//                    }
//                }
//
//            }
//        }

        if(null!= getMemberSharedPreference().getAvatarUrl()&&!getMemberSharedPreference().getAvatarUrl().equals("null") &&!getMemberSharedPreference().getAvatarUrl().equals("")) {
            Log.i("susu",getMemberSharedPreference().getAvatarUrl()+"---------");
            ImageLoader.getInstance().displayImage(VariablesOfUrl.imagePath+"/file/head/"+getMemberSharedPreference().getAvatarUrl(), head, options, null);
            Picasso.with(context).load(VariablesOfUrl.imagePath+"/file/head/"+getMemberSharedPreference().getAvatarUrl()).into(head);
        }else{
            Log.i("susu",getMemberSharedPreference().getAvatarUrl());
            head.setImageResource(R.mipmap.ic_user);
        }
        Map<String,String> map=new HashMap<String, String>();
        new ProvinceTask(context,list,urlProvince,map).execute();
        for (int i=1;i<392;i++){
            Map<String,String> parse=new HashMap<>();
            parse.put("arrchildid", String.valueOf(i));
            new CityTask(context,parse,list2).execute();
        }
    }
    public class ProvinceTask extends AsyncTask<Void,Void,List<City>> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private List<City> listMessage;
        private Map<String, String> map;
        private String url;

        public ProvinceTask(Context context, List<City> listMessage, String url,Map<String,String> map) {
            this.context = context;
            this.listMessage = listMessage;
            this.url = url;
            this.map=map;
        }

        @Override
        protected List<City> doInBackground(Void... params) {
            List<City> result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL +this.url;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getProvinceList(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<City> result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        listMessage.clear();
                        listMessage.addAll(result);
                        for (City city:listMessage){
                            PLANETS_Province.add(city.getAreaname());
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

    protected void findViews() {
        righttextView= (TextView) findViewById(R.id.righttextView);
        righttextView.setVisibility(View.VISIBLE);
        righttextView.setOnClickListener(this);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.head_title)).setText("个人资料");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changeHade= (TextView) findViewById(R.id.tv_changehade);
        choose_city= (TextView) findViewById(R.id.tv_choose_city);
        choose_province= (TextView) findViewById(R.id.tv_choose_province);
        choose_sex= (TextView) findViewById(R.id.tv_choose_sex);
        head= (ImageView) findViewById(R.id.iv_head);
        et_mobile= (EditText) findViewById(R.id.et_mobile);
        et_skype= (EditText) findViewById(R.id.et_skype);
        et_qq= (EditText) findViewById(R.id.et_qq);
        et_ali= (EditText) findViewById(R.id.et_ali);
        et_department= (EditText) findViewById(R.id.et_department);
        et_truename= (EditText) findViewById(R.id.et_truename);
        //et_sound= (EditText) findViewById(R.id.et_sound);

        et_career= (EditText) findViewById(R.id.et_career);
        et_username= (EditText) findViewById(R.id.et_username);
        et_useremail= (EditText) findViewById(R.id.et_user_email);
        et_career= (EditText) findViewById(R.id.et_career);
        et_career= (EditText) findViewById(R.id.et_career);
        changeHade.setOnClickListener(this);
        choose_sex.setOnClickListener(this);
        choose_province.setOnClickListener(this);
        choose_city.setOnClickListener(this);
    }


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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_changehade:
                popWindow = new SelectPicPopupWindow(this, itemsOnClick);
                popWindow.showAtLocation(changeHade, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.righttextView:
                savePerson();
                break;
            case R.id.tv_choose_sex:
                View outerView = LayoutInflater.from(context).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(0);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        choose_sex.setText(item);
                    }
                });

                new AlertDialog.Builder(UserCenterActivity.this)
                        .setTitle("请选择性别")
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .create().show();
                break;
            case R.id.tv_choose_province:
                View outerView2 = LayoutInflater.from(context).inflate(R.layout.wheel_view, null);
                WheelView wv2 = (WheelView) outerView2.findViewById(R.id.wheel_view_wv);
                wv2.setOffset(2);
                wv2.setItems(PLANETS_Province);
                wv2.setSeletion(0);
                wv2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        arrchildid=list.get(selectedIndex-2).getArrchildid();
                        choose_province.setText(item);
                    }
                });
                new AlertDialog.Builder(UserCenterActivity.this)
                        .setTitle("请选择省份")
                        .setView(outerView2)
                        .setPositiveButton("OK", null)
                        .create().show();

                break;
            case R.id.tv_choose_city:
                if (arrchildid.equals("")){
                    new AlertDialog.Builder(UserCenterActivity.this)
                            .setTitle("请选择省份")
                            .setPositiveButton("OK", null)
                            .create().show();
                }else {
                    PLANETS_City.clear();
                    final String[] temp=arrchildid.split(",");
                    for (int i=0;i<temp.length;i++){
                        PLANETS_City.add(PLANETS_CityList.get(Integer.parseInt(temp[i])-1));
                    }
                    View outerView3 = LayoutInflater.from(context).inflate(R.layout.wheel_view, null);
                    WheelView wv3 = (WheelView) outerView3.findViewById(R.id.wheel_view_wv);
                    wv3.setOffset(2);
                    wv3.setItems(PLANETS_City);
                    //wv3.setItems(Arrays.asList(temp));
                    wv3.setSeletion(0);
                    wv3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                        @Override
                        public void onSelected(int selectedIndex, String item) {
                            areaid=Integer.parseInt(temp[selectedIndex-2]);
                            choose_city.setText(item);
                        }
                    });

                    new AlertDialog.Builder(UserCenterActivity.this)
                            .setTitle("请选择城市")
                            .setView(outerView3)
                            .setPositiveButton("OK", null)
                            .create().show();
                }
                break;


        }
    }
        public class CityTask extends AsyncTask<Void,Void,List<CityList>> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private Map<String, String> map;
            private List<CityList> listmessage;

        public CityTask(Context context,Map<String,String> map,List<CityList> listmessage) {
            this.context = context;
            this.map=map;
            this.listmessage=listmessage;
        }

        @Override
        protected List<CityList> doInBackground(Void... params) {
            List<CityList> result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.GETCITYLIST;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    //Log.i("susu", String.valueOf(jsonStr));
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getCityList(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<CityList> result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        listmessage.clear();
                        listmessage.addAll(result);
                        for (CityList cityList:listmessage){
                            //Log.i("susu",cityList.getCityId());
                            PLANETS_CityList.add(cityList.getCityName());
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
    public class FileimgTask extends AsyncTask<Void,Void,String> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag = true;
        private Map<String, String> map;

        public FileimgTask(Context context, Map<String, String> map) {
            this.context = context;
            this.map = map;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.UPLOADPORTRAITS;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    Log.i("susu", String.valueOf(jsonStr));
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.uploadPortraits(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }
    }

    private void savePerson() {
        String sex=choose_sex.getText().toString();
        if (sex.equals("男")){
            gender=1;
        }
        if (sex.equals("女")){
            gender=2;
        }
        Map<String,String> map=new HashMap<>();
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        map.put("gender", String.valueOf(gender));
        map.put("truename", et_truename.getText()== null ? "":et_truename.getText().toString());
        map.put("mobile",et_mobile.getText()== null ? "":et_mobile.getText().toString());
        map.put("skype",et_skype.getText()== null ? "":et_skype.getText().toString());
        map.put("qq",et_qq.getText()== null ? "":et_qq.getText().toString());
        map.put("ali",et_ali.getText()== null ? "":et_ali.getText().toString());
        map.put("department",et_department.getText()== null ? "":et_department.getText().toString());
        map.put("sound","1");
        map.put("career",et_career.getText()== null ? "":et_career.getText().toString());
        map.put("areaId", String.valueOf(areaid) == null ? "0" : String.valueOf(areaid));
        new EditAddressTask(context,map,url).execute();
        new AlertDialog.Builder(UserCenterActivity.this)
                .setTitle("保存成功")
                .setPositiveButton("OK", null)
                .create().show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("truename", et_truename.getText().toString());
        editor.putString("skype", et_skype.getText().toString());
        editor.putString("qq", et_qq.getText().toString());
        editor.putString("ali", et_ali.getText().toString());
        editor.putString("department", et_department.getText().toString());
        editor.putString("sound", String.valueOf(1));
        editor.putString("career", et_career.getText().toString());
        editor.putString("areaId", String.valueOf(areaid));
        editor.putString("province",choose_province.getText().toString());
        editor.putString("city",choose_city.getText().toString());
        editor.putString("gender", String.valueOf(gender));
        editor.putString("arrchildid",arrchildid);
        editor.commit();
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
            paths.add(0,srcPath);
            picPath=srcPath;
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeFile(srcPath,options);
            int imgWidth=options.outWidth;
            int imgHeight=options.outHeight;
            int scale=1;
            int scalex=imgWidth/screenWidth;
            int scaley=imgHeight/screenHeight;
            scale=scalex>scaley?scalex:scaley;
            //按照缩放比显示图片
            options.inSampleSize=scale;
            //开始真正解析位图
            options.inJustDecodeBounds=false;

            Bitmap bitmap =  BitmapFactory.decodeFile(srcPath,options);
            head.setImageBitmap(bitmap);
            if(picPath!=null&&picPath.length()>0)
            {
                UploadFileTask uploadFileTask=new UploadFileTask(this,getMemberSharedPreference().getUserid());
                uploadFileTask.execute(picPath);
                new AlertDialog.Builder(UserCenterActivity.this)
                        .setTitle("上传图片成功")
                        .setPositiveButton("OK", null)
                        .create().show();
            }
        }


    }
}
