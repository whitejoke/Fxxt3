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
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.modle.page.HotPage;
import aizulove.com.fxxt.modle.page.PagerItem;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class HotActivity extends BaseActivity implements SlidingTabLayout.AddListener {
    /*viewpager*/
    protected ViewPager pViewPager ;
    /*自定义的 tabLayout*/
    protected SlidingTabLayout pTabLayout ;
    /*每个 tab 的 item*/
    protected List<PagerItem> pTab = new ArrayList<>() ;
    private TextView titleview;
    private GridView listView;
    private ProductAdapter adapter;
    private MaterialRefreshLayout mlayout;
    private String url=VariablesOfUrl.GETHOTMALLLIST;
    private List<Product> listMessage = new ArrayList<Product>();

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cuxiao, null);
        layoutContent.addView(convertView);
        findViews();
        HiddenMeun();
    }

    protected void findViews() {
        mlayout= (MaterialRefreshLayout) findViewById(R.id.srl);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("热门商品");
        pViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
        pTabLayout= (SlidingTabLayout) findViewById(R.id.id_tab);
    }

    protected  void setSelecttab(){
        SELECTTAB=1;
    }
    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        pTab.add(new HotPage("自行车",getMemberSharedPreference().getAttentionids())) ;
        pTab.add(new HotPage("电瓶车",getMemberSharedPreference().getAttentionids())) ;
        pTab.add(new HotPage("电动汽车",getMemberSharedPreference().getAttentionids())) ;
        pViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), pTab));
        pTabLayout.setViewPager(pViewPager);
        pTabLayout.setOnAddListener(this);
    }

    @Override
    public void getAdd(int position) {

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent= new Intent();
//        intent.setClass(context,ProductActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("product",listMessage.get(position));
//        bundle.putSerializable("type","hot");
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }

//    @Override
//    public void onRefresh() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("page","1");
//        map.put("num","2222");
//        new HotTask(context,listMessage,adapter,mlayout,map,url).execute();
//    }

}