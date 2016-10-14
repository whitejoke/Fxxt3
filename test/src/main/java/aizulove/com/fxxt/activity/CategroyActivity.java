package aizulove.com.fxxt.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CategoryAdapter;
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.GridViewItem;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.AddAttentionTask;
import aizulove.com.fxxt.task.CancelHotAttention;
import aizulove.com.fxxt.task.CategoryDataTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class CategroyActivity extends BaseActivity implements AdapterView.OnItemClickListener ,ProductAdapter.Callback{

    private static final String SEP1 = ",";
    private CategoryAdapter adapter;
    private List<GridViewItem> data;

    private ProductAdapter category_adapter;
    private List<Product> listMessage=new ArrayList<Product>();
    private GridView gv_show;

    int gridViewSelectPosition = -1;
    private TextView tv_title;
    protected SharedPreferences sharedPreferences;
    private TextView titleview;

    private GridView gridView;
    private RadioGroup rgLeft;

    private String url= VariablesOfUrl.GETMALLLISTBYKEYWORD;
    String type="自行车";
    String[] rbTextArray={"凤凰","恩达","邦德富士达","欧亚马","飞鸽","黑马","彪牌","永久","喜德盛"};
    private List<String> temp=new ArrayList<>();
    private int count=0;
    private RadioButton radioButton;

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_category, null);
        layoutContent.addView(convertView);
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        findViews();
        initListener();
        HiddenMeun();
        //onRefresh();
    }

    private void onRefresh() {
        temp.clear();
        String[] p=getMemberSharedPreference().getAttentionids().split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("page", "1");
//        map.put("num", "9999");
//        map.put("keyword",radioButton.getText().toString());
//        new CategoryDataTask(getApplicationContext(),listMessage,category_adapter,map, url).execute();
    }


    private void initListener() {
        tv_title.setText(type);
        data=new ArrayList<GridViewItem>();
        for (int i = 0; i < rbTextArray.length; i++) {
            GridViewItem gridViewItem = new GridViewItem();
            gridViewItem.setBtn_text(rbTextArray[i]);
            data.add(gridViewItem);
        }
        adapter=new CategoryAdapter(getApplicationContext(),data,gridViewSelectPosition);
        gridView.setAdapter(adapter);
        rgLeft.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String type;
                String[] rbTextArray;
                switch (checkedId){
                    case R.id.rb_cycle:
                        type="自行车";
                        rbTextArray=new String[]{"凤凰","恩达","邦德富士达","欧亚马","飞鸽","黑马","彪牌","永久","喜德盛"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_cycle_clothes:
                        type="骑行服饰";
                        rbTextArray=new String[]{"骑行服","自行车包","自行车手套","骑行头盔","骑行眼镜","袖套","自行车头巾","骑行鞋","骑行袜","面罩"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_cycle_equl:
                        type="骑行服饰";
                        rbTextArray=new String[]{"自行车灯","自行车锁","打气筒","码表及配件","水壶架","车铃/喇叭","货架/尾架","自行车水壶","灯架/灯夹","自行车手机架","护链贴"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_cycle_elect:
                        type="电瓶车";
                        rbTextArray=new String[]{"优狐","新日","彪牌"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_cycle_elect_access:
                        type="电瓶车配件";
                        rbTextArray=new String[]{"头盔","手套","雨衣","眼镜","护膝","挡风被","其他装备"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_cycle_elecling_access:
                        type="电瓶车骑行配件";
                        rbTextArray=new String[]{"电瓶车刹车装置","电瓶车外胎","电瓶车坐垫","电瓶车灯","电瓶车电瓶","车锁","充电器","其他"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_elect_car:
                        type="电动汽车";
                        rbTextArray=new String[]{"雷丁","比亚迪","众泰","三菱","奇瑞","特斯拉"};
                        switchFragment(rbTextArray,type);
                        break;
                    case R.id.rb_elect_car_access:
                        type="电动汽车配件";
                        rbTextArray=new String[]{"雷丁","比亚迪","众泰","三菱","奇瑞","特斯拉"};
                        switchFragment(rbTextArray,type);
                        break;
                    default:
                        break;
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //定义适配器
                adapter=new CategoryAdapter(getApplicationContext(),data,position);
                //绑定适配器
                gridView.setAdapter(adapter);

                radioButton= (RadioButton) view.findViewById(R.id.rb_item);
                DataTask(radioButton.getText().toString());
            }
        });
    }

    private void DataTask(String rbText) {
        String[] p=getMemberSharedPreference().getAttentionids().split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }
        //Collections.addAll(temp, getMemberSharedPreference().getAttentionids().split(","));
        category_adapter=new ProductAdapter(getApplicationContext(),listMessage,getMemberSharedPreference().getAttentionids(),this);
        gv_show.setAdapter(category_adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
        map.put("num", "9999");
        map.put("keyword",rbText);
        new CategoryDataTask(getApplicationContext(),listMessage,category_adapter,map, url).execute();

    }

    private void switchFragment(String[] content,String title) {
        tv_title.setText(title);
        data=new ArrayList<GridViewItem>();
        for (int i = 0; i < content.length; i++) {
            //Log.i("susu", String.valueOf(content.length));
            GridViewItem gridViewItem = new GridViewItem();
            gridViewItem.setBtn_text(content[i]);
            data.add(gridViewItem);
        }
        adapter=new CategoryAdapter(getApplicationContext(),data,gridViewSelectPosition);
        gridView.setAdapter(adapter);
    }


    protected void findViews() {
        tv_title= (TextView) findViewById(R.id.tv_gridtitle);
        gridView= (GridView) findViewById(R.id.gridview);
        gv_show= (GridView) findViewById(R.id.gv_show);
        rgLeft= (RadioGroup) findViewById(R.id.rg_left);

        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
         titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)) .setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         titleview.setText("分类");
        gv_show.setOnItemClickListener(this);
    }

    /**
     *
     * 逻辑处理方法
     */
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
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void click(View v,int position,View convertView) {
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
}
