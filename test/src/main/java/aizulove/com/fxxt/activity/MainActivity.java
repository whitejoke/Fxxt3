package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ADPageAdapter;
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Advert;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.AddAttentionTask;
import aizulove.com.fxxt.task.CancelHotAttention;
import aizulove.com.fxxt.task.ProductDataTask;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

public class MainActivity extends BaseActivity implements View.OnClickListener, GridListView.OnMyGridViewRefreshListener,AdapterView.OnItemClickListener, View.OnKeyListener,ProductAdapter.Callback {

    private boolean judgeInternet;
    private boolean typeFlag = true;

    private LinearLayout jlb;
    private LinearLayout categroybt;
    private  LinearLayout llCuxiao;
    private  LinearLayout llHot;
    private  LinearLayout llNew;
    private  LinearLayout llGuanzhu;
    private LinearLayout myOrder;
    private LinearLayout myCar;
    private TextView llMore;
    private GridListView listView;
    private ImageView headrightimageView;
    private List<Product> listMessage = new ArrayList<Product>();
    private ProductAdapter adapter;//

    private List<View> adViews=new ArrayList<View>();
    private ImageView[] pointViews_ad;
    private List<Advert>ads;
    private ImageView imageView;
    private ViewGroup pointGroup_ad;
    private ViewPager vp_ad;
    private DisplayImageOptions options;
    private EditText et_choose;
    private String url=VariablesOfUrl.GETHOMEPAGELIST;
    private List<String> temp=new ArrayList<>();
    private int count=0;
    private static final String SEP1 = ",";

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_main, null);
        layoutContent.addView(convertView);
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
        findViews();
        DataTask();
    }


    protected void findViews() {
        et_choose= (EditText) findViewById(R.id.et_choose);
        rightimageView=(ImageView)findViewById(R.id.headrightimageView);
        blakimageView=(ImageView)findViewById(R.id.headblakimageView);
        headrightimageView=(ImageView)findViewById(R.id.headrightimageView);
        vp_ad=(android.support.v4.view.ViewPager)findViewById(R.id.vp_ad);
        pointGroup_ad=(LinearLayout)findViewById(R.id.ll_point_ad);

        listView=(GridListView)findViewById(R.id.Hotlistview);

        categroybt=(LinearLayout)findViewById(R.id.categroybt);
        llCuxiao=(LinearLayout)findViewById(R.id.llCuxiao);
        llHot=(LinearLayout)findViewById(R.id.llHot);
        llNew=(LinearLayout)findViewById(R.id.llNew);
        myCar=(LinearLayout)findViewById(R.id.my_car);
        llMore=(TextView)findViewById(R.id.llMore);
        categroybt=(LinearLayout)findViewById(R.id.categroybt);
        myOrder=(LinearLayout)findViewById(R.id.myOrder);
        llGuanzhu=(LinearLayout)findViewById(R.id.llGuanzhu);
        jlb=(LinearLayout)findViewById(R.id.jlb);
        blakimageView.setOnClickListener(this);
        headrightimageView.setOnClickListener(this);
        categroybt.setOnClickListener(this);
        llCuxiao.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        llMore.setOnClickListener(this);
        llGuanzhu.setOnClickListener(this);
        llHot.setOnClickListener(this);
        llNew.setOnClickListener(this);
        myCar.setOnClickListener(this);
        jlb.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        et_choose.setOnKeyListener(this);
    }
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append(SEP1);
                }  else {
                    sb.append(list.get(i));
                    sb.append(SEP1);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Intent intent= new Intent();
        intent.setClass(context,ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",listMessage.get(position));
        bundle.putSerializable("type","hot");
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            Intent intent=new Intent();
            intent.setClass(context,SearchActivity.class);
            intent.putExtra("keyword",et_choose.getText().toString());
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void click(View v, final int position,View convertView) {
        final TextView guanzhu= (TextView) v.findViewById(R.id.tv_guanzhu);
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).equals(listMessage.get(position).getItemid().toString())) {
                        guanzhu.setText("关注+");
                        Log.i("susu", "hava");
                        count=1;
                        temp.remove(i);
                        String attentionids=ListToString(temp);
                        Log.i("susu",attentionids);
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                        map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
                        new CancelHotAttention(context,map).execute();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("attentionids",attentionids);
                        editor.commit();
                        break;
                    } else {
                        //Log.i("susu", "not have");
                        //Log.i("susu", String.valueOf(listMessage.get(position).getItemid()));
                        count=0;
                    }
                }
                String test=ListToString(temp);
                Log.i("susu",test);
                Log.i("susu", String.valueOf(temp));
                if (count==0){
                    guanzhu.setText("取消关注");
                    temp.add(listMessage.get(position).getItemid().toString());
                    String attentionids=ListToString(temp);
                    Map<String,String> map=new HashMap<String, String>();
                    map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                    map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
                    new AddAttentionTask(context,map).execute();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("attentionids",attentionids);
                    editor.commit();
                }
                count=0;
    }


    private class GetAdvertTask extends AsyncTask<Void, Void, List<Advert>>{

        public GetAdvertTask() {
            super();
        }

        @Override
        protected List<Advert> doInBackground(Void... params) {
            Map<String, String> map = new HashMap<String, String>();
            List<Advert> result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETHOMEPAGELIST;
                    System.out.println("url=="+url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    System.out.println("jsonStr=="+jsonStr);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.jsonParserAdvertList(jsonStr.toString());
                }
            } catch (Exception e) {
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Advert> result) {
            ads=result;
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        if (result.size()>0){
                            adViews.clear();
                            for (Advert s : ads) {
                                ImageView iv = new ImageView(MainActivity.this);
                                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                                if(null!=s.getImageSrc()) {
                                    Picasso.with(context).load(s.getImageSrc()).into(iv);
                                    //ImageLoader.getInstance().displayImage(s.getImageSrc(),iv, options, null);
                                }
                                adViews.add(iv);
                            }

                            System.out.println("adViews.size()==="+adViews.size());
                            //
                            pointViews_ad = new ImageView[adViews.size()];
                            pointGroup_ad.removeAllViews();
                            for (int i = 0; i < adViews.size(); i++) {
                                imageView = new ImageView(MainActivity.this);
                                android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
                                params.setMargins(2, 0, 2, 0);
                                imageView.setLayoutParams(params);
                                pointViews_ad[i] = imageView;
                                if (i == 0){
                                    //默认选中第一张图片,加入焦点
                                    pointViews_ad[i].setBackgroundResource(R.mipmap.ic_dot_focused_3);
                                }else{
                                    pointViews_ad[i].setBackgroundResource(R.mipmap.ic_point);
                                }
                                //把每一个导航小圆点都加入到ViewGroup中
                                pointGroup_ad.addView(pointViews_ad[i]);
                            }

                            System.out.println("pointViews_ad=="+pointViews_ad.length);

                            vp_ad.setAdapter(new ADPageAdapter(adViews,context,result));
                            vp_ad.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
                                @Override
                                public void onPageSelected(int arg0){
                                    pointViews_ad[arg0].setBackgroundResource(R.mipmap.ic_dot_focused_3);
//                                    View view=adViews.get(arg0);
//                                    view.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                        }
//                                    });
                                    for (int i = 0; i < pointViews_ad.length; i++) {
                                        if (arg0 != i) {
                                            pointViews_ad[i].setBackgroundResource(R.mipmap.ic_point);
                                        }
                                    }
                                    vp_ad.setCurrentItem(arg0);
                                }
                                @Override
                                public void onPageScrolled(int arg0, float arg1, int arg2){

                                }
                                @Override
                                public void onPageScrollStateChanged(int arg0){

                                }
                            });
                        }
                    }
                }
            }else{
                ToastSingle.showToast(context, "请检查网络连接是否正常");
            }
        }
    }

    protected  void setSelecttab() {
        SELECTTAB=1;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        String[] p=getMemberSharedPreference().getAttentionids().split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }
        adapter=new ProductAdapter(context,listMessage,getMemberSharedPreference().getAttentionids(),this);
        listView.setAdapter(adapter);
        listView.setOnMyGridViewRefreshListener(this);
        Map<String, String> map = new HashMap<String, String>();
        new ProductDataTask(context,map,listMessage,adapter,listView,url).execute();
        new GetAdvertTask().execute();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
        case R.id.jlb:
            intent = new Intent();
            intent.setClass(context, JulebuActivity.class);
            startActivity(intent);
        break;
        case R.id.headblakimageView:
            intent = new Intent();
            intent.setClass(context, CategroyActivity.class);
            startActivity(intent);
            break;
        case R.id.categroybt:
            intent = new Intent();
            intent.setClass(context, CategroyActivity.class);
            startActivity(intent);
            break;
        case R.id.llCuxiao:
            intent = new Intent();
            intent.setClass(context, CuxiaoActivity.class);
            startActivity(intent);
            break;
        case R.id.llHot:
            intent = new Intent();
            intent.setClass(context,HotActivity.class);
            startActivity(intent);
            break;
        case R.id.llNew:
            intent = new Intent();
            intent.setClass(context,QipaHostActivity.class);
            startActivity(intent);
            break;
            case R.id.llGuanzhu:
             if(Data().equals("")) {
                intent = new Intent();
                intent.setClass(context, HostActivity.class);
                startActivity(intent);
                finish();
             }else {
                intent = new Intent();
                intent.setClass(context,MyguanzuActivity.class);
                startActivity(intent);
             }
            break;
            case R.id.headrightimageView:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    intent = new Intent();
                    intent.setClass(context,MyActivity.class);
                    startActivity(intent);
                }
            break;
            case R.id.myOrder:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    intent = new Intent();
                    intent.setClass(context, MyOderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_car:
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    intent = new Intent();
                    intent.setClass(context, MyCartActivity.class);
                    startActivity(intent);
                }
            break;
            case R.id.llMore:
            intent = new Intent();
            intent.setClass(context,HotActivity.class);
            startActivity(intent);
            break;
        }
    }

    @Override
    public void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        new ProductDataTask(context,map,listMessage,adapter,listView,url).execute();
    }

}