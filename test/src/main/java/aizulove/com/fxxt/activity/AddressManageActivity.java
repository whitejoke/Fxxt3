package aizulove.com.fxxt.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Address;
import aizulove.com.fxxt.modle.entity.City;
import aizulove.com.fxxt.modle.entity.CityList;
import aizulove.com.fxxt.task.EditAddressTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;
import aizulove.com.fxxt.view.WheelView;

/**
 * Created by joker on 2016/8/5.
 */
public class AddressManageActivity extends BaseActivity implements View.OnClickListener {
    Address address=new Address();
    private EditText name,phone,place,postcode;
    private TextView province,city;
    private String url= VariablesOfUrl.EDITADDRESS;
    private String urlProvince=VariablesOfUrl.GETPROVINCELIST;
    private List<City> list=new ArrayList<City>();
    private List<String> PLANETS=new ArrayList<String>();
    private int areaid;
    private String type;
    private int cityid;
    private String arrchildid="";
    private List<CityList> list2=new ArrayList<CityList>();
    private List<String> PLANETS_Province=new ArrayList<>();
    private List<String> PLANETS_City=new ArrayList<>();
    private List<String> PLANETS_CityList=new ArrayList<>();

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_add_place, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }

    private void initData() {
        address= (Address) getIntent().getSerializableExtra("address");
        type=getIntent().getStringExtra("type");
        areaid=address.getAreaid();
        cityid=address.getCityid();
    }

    protected void findViews() {
        righttextView= (TextView) findViewById(R.id.righttextView);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        righttextView.setVisibility(View.VISIBLE);
        righttextView.setOnClickListener(this);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("地址管理");
        name= (EditText) findViewById(R.id.et_address_name);
        phone= (EditText) findViewById(R.id.et_address_phone);
        province= (TextView) findViewById(R.id.et_address_province);
        city= (TextView) findViewById(R.id.et_address_city);
        place= (EditText) findViewById(R.id.et_address_place);
        postcode= (EditText) findViewById(R.id.et_address_postcode);
        province.setOnClickListener(this);
        city.setOnClickListener(this);
    }

    protected void DataTask(){
        name.setText(address.getName());
        phone.setText(address.getPhone());
        place.setText(address.getAddress());
        postcode.setText(address.getPostcode());
        province.setText(address.getAreaname());
        city.setText(address.getCityname());

        Map<String,String> map=new HashMap<String, String>();
//        final ProvinceTask task=new ProvinceTask(context,list,urlProvince,map);
//        task.setOnDataFinishedListener(new ProvinceTask.OnDataFinishedListener() {
//            @Override
//            public void onDataSuccessfully(List<City> result) {
//                for (City city:result){
//                    PLANETS.add(city.getAreaname());
//                }
//            }
//            @Override
//            public void onDataFailed() {
//                Toast.makeText(AddressManageActivity.this, "加载失败！", Toast.LENGTH_SHORT).show();
//            }
//        });
//        task.execute();
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
        private List<City> parseXml() throws XmlPullParserException, IOException {
            List<City> cityList=null;
            XmlPullParser parser=getResources().getXml(R.xml.city_code);
            int eventType=parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        cityList=new ArrayList<City>();
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName=parser.getName();
                        if(tagName.equals("key")){
                            String cityName=parser.nextText();
                            parser.next();
                            String cityId=parser.nextText();
                            //City cityNameList=new City(cityName, cityId);
                            //cityList.add(cityNameList);
                        }
                        break;

                    default:
                        break;
                }
                eventType=parser.next();
            }
            return cityList;
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.righttextView:
                Map<String, String> map = new HashMap<String, String>();
                map.put("addressId", String.valueOf(address.getItemid()));
                map.put("addressInfo",place.getText().toString());
                map.put("editor", address.getEditor());
                map.put("listorder", String.valueOf(address.getListorder()));
                map.put("areaId", String.valueOf(areaid));
                map.put("mobile", getMemberSharedPreference().getMobile());
                map.put("username", getMemberSharedPreference().getUsername());
                map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                map.put("truename", name.getText().toString());
                map.put("note", "");
                map.put("postcode", postcode.getText().toString());
                map.put("telephone", phone.getText().toString());
                map.put("cityid", String.valueOf(cityid));
                new EditAddressTask(context,map,url).execute();
                Intent intent= new Intent();
                setResult(30, intent);
                finish();
                break;
            case R.id.et_address_province:
                View outerView = LayoutInflater.from(context).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(2);
                wv.setItems(PLANETS_Province);
                wv.setSeletion(0);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
//                        areaid=list.get(selectedIndex-2).getAreaid();
//                        province.setText(item);
                        arrchildid = list.get(selectedIndex - 2).getArrchildid();
                        areaid = list.get(selectedIndex - 2).getAreaid();
                        Log.i("TAG", String.valueOf(areaid));
                        province.setText(item);
                    }
                });

                new AlertDialog.Builder(AddressManageActivity.this)
                        .setTitle("请选择省份")
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .create().show();
                break;
            case R.id.et_address_city:
                if (arrchildid.equals("")){
                    new AlertDialog.Builder(AddressManageActivity.this)
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
                            cityid=Integer.parseInt(temp[selectedIndex-2]);
                            Log.i("TAG", String.valueOf(selectedIndex));
                            city.setText(item);
                        }
                    });

                    new AlertDialog.Builder(AddressManageActivity.this)
                            .setTitle("请选择城市")
                            .setView(outerView3)
                            .setPositiveButton("OK", null)
                            .create().show();
                }
                break;
        }
    }

}
