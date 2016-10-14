package aizulove.com.fxxt.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.GuanzhuAdapter;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.modle.page.GuanzhuPage;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class MyguanzuActivity extends BaseActivity  implements SlidingTabLayout.AddListener {

    private String urlString = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETATTENTIONLIST;
    private GridListView listView;
    private GuanzhuAdapter adapter;
    private List<Logo> listMessage = new ArrayList<Logo>();
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cuxiao, null);
        layoutContent.addView(convertView);
        HiddenMeun();
    }


    protected void findViews() {
        rightimageView= (ImageView)findViewById(R.id.rightimageView);
        ((TextView)findViewById(R.id.head_title)).setText("我的关注");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //listView.setOnItemClickListener(this);
        //listView=(GridListView)findViewById(R.id.guanzhu_listview);
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
        mTabLayout= (SlidingTabLayout) findViewById(R.id.id_tab);
    }

    protected  void setSelecttab(){
        SELECTTAB=4;
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        mTab.add(new GuanzhuPage("自行车",getMemberSharedPreference().getUserid())) ;
        mTab.add(new GuanzhuPage("电瓶车",getMemberSharedPreference().getUserid())) ;
        mTab.add(new GuanzhuPage("电动汽车",getMemberSharedPreference().getUserid())) ;
        mTab.add(new GuanzhuPage("俱乐部",getMemberSharedPreference().getUserid())) ;
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setOnAddListener(this);
//        adapter=new GuanzhuAdapter(context,listMessage);
//        listView.setAdapter(adapter);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
//        new GuanzhuDataTask(context,listMessage,map,adapter,listView,urlString).execute();
//        listView.setOnMyGridViewRefreshListener(this);
    }



//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent= new Intent();
//        intent.setClass(context,JulbdetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("logo",(Serializable) listMessage.get(position));
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }


    @Override
    public void getAdd(int position) {

    }
}
