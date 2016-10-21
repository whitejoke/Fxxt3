package aizulove.com.fxxt.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.page.GuanzhuPage;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class MyguanzuActivity extends BaseActivity  implements SlidingTabLayout.AddListener {
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
    }





    @Override
    public void getAdd(int position) {

    }
}
