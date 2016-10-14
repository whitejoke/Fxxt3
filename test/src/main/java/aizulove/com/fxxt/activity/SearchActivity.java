package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
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

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cuxiao, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTaskTest();
        HiddenMeun();
        //initRefresh();
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

    @Override
    public void click(View v,int position,View convertView) {

    }
}
