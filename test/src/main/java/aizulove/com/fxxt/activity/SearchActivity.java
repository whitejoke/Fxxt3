package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.AddAttentionTask;
import aizulove.com.fxxt.task.CancelHotAttention;
import aizulove.com.fxxt.task.CategoryDataTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/9/26.
 */
public class SearchActivity extends BaseActivity implements AdapterView.OnItemClickListener ,ProductAdapter.Callback{
    private GridView listView;
    private MaterialRefreshLayout materialRefreshLayout;
    private ProductAdapter adapter;
    private String url= VariablesOfUrl.GETMALLLISTBYKEYWORD;
    private List<Product> listMessage = new ArrayList<Product>();
    private String keyword=null;
    private TextView guanzhu;
    private List<String> temp=new ArrayList<>();
    private int count=0;
    private static final String SEP1 = ",";

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_search, null);
        layoutContent.addView(convertView);
        String test=sharedPreferences.getString("attentionids","");
        String[] p=test.split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }
        removeDuplicate(temp);
        initData();
        findViews();
        DataTaskTest();
        HiddenMeun();
        //initRefresh();
    }
    public static List<String> removeDuplicate(List<String> list)

    {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    private void initData() {
        keyword=getIntent().getStringExtra("keyword");
    }

//    private void initRefresh() {
//        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
//            @Override
//            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("page","1");
//                map.put("num","999");
//                new CategoryDataTask(context,listMessage,adapter,map,url).execute();
//            }
//
//        });
//    }

    protected void findViews() {
        //materialRefreshLayout= (MaterialRefreshLayout) findViewById(R.id.srl);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("搜索");
        listView=(GridView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    protected void DataTaskTest(){
        adapter=new ProductAdapter(context,listMessage,getMemberSharedPreference().getAttentionids(),this);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "999");
        map.put("keyword",keyword);
        new CategoryDataTask(context,listMessage,adapter,map,url).execute();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(context,ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",listMessage.get(position));
        bundle.putSerializable("type","cuxiao");
        intent.putExtras(bundle);
        startActivity(intent);
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
    public void click(View v,int position,View convertView) {
        guanzhu= (TextView) v.findViewById(R.id.tv_guanzhu);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).equals(listMessage.get(position).getItemid().toString())) {
                guanzhu.setText("关注+");
                Log.i("susu", "hava");
                count = 1;
                temp.remove(i);
                String attentionids = ListToString(temp);
                Log.i("susu", attentionids);
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
                map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
                new CancelHotAttention(context, map).execute();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("attentionids", attentionids);
                editor.commit();
                break;
            } else {
                //Log.i("susu", "not have");
                //Log.i("susu", String.valueOf(listMessage.get(position).getItemid()));
                count = 0;
            }
        }
        String test = ListToString(temp);
        Log.i("susu", test);
        Log.i("susu", String.valueOf(temp));
        if (count == 0) {
            guanzhu.setText("已关注");
            temp.add(listMessage.get(position).getItemid().toString());
            String attentionids = ListToString(temp);
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
            map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
            new AddAttentionTask(context, map).execute();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("attentionids", attentionids);
            editor.commit();
        }
        count = 0;
    }
}
