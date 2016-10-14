package aizulove.com.fxxt.activity;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CuXiaoAdapter;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.modle.page.CuxiaoPage;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class CuxiaoActivity extends BaseActivity implements SlidingTabLayout.AddListener {
    private GridView listView;
    //private SwipeRefreshLayout srl;
    private MaterialRefreshLayout materialRefreshLayout;
    private CuXiaoAdapter adapter;
    private String url=VariablesOfUrl.GETCUXIAOMALLLIST;
    private List<Product> listMessage = new ArrayList<Product>();

    //protected SharedPreferences sharedPreferences;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cuxiao, null);
        layoutContent.addView(convertView);
        findViews();
        HiddenMeun();
    }


    protected void findViews() {
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("促销");
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
        mTabLayout= (SlidingTabLayout) findViewById(R.id.id_tab);
//        listView=(GridView)findViewById(R.id.listView);
//        listView.setOnItemClickListener(this);
    }

    protected  void setSelecttab(){
        SELECTTAB=1;
    }
    /**
     * 逻辑处理方法
     */

    protected void DataTask(){
        mTab.add(new CuxiaoPage("自行车",getMemberSharedPreference().getAttentionids())) ;
        mTab.add(new CuxiaoPage("电瓶车",getMemberSharedPreference().getAttentionids())) ;
        mTab.add(new CuxiaoPage("电动汽车",getMemberSharedPreference().getAttentionids())) ;
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setOnAddListener(this);
    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent= new Intent();
//        intent.setClass(context,ProductActivity.class);
//        Bundle bundle = new Bundle();
//
//        bundle.putSerializable("product",listMessage.get(position));
//        bundle.putSerializable("type","cuxiao");
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onRefresh() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("page","1");
//        map.put("num","999");
//        new CuXiaoDataTask(context,listMessage,adapter,map,url,materialRefreshLayout).execute();
//    }

    @Override
    public void getAdd(int position) {

    }
}