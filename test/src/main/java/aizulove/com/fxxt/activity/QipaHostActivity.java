package aizulove.com.fxxt.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.page.BaikePage;
import aizulove.com.fxxt.modle.page.NewsPage;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class QipaHostActivity extends BaseActivity  {

	public void setContentViews(){
		View convertView = mInflater.inflate(R.layout.activity_qipa_host, null);
		layoutContent.addView(convertView);
	}



	protected void findViews() {
		((TextView)findViewById(R.id.head_title)).setText("奇葩说");
		mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
		mTabLayout = (SlidingTabLayout) findViewById(R.id.id_tab) ;
	}

	protected  void setSelecttab() {
		SELECTTAB=3;
	}
	/**
	 *
	 * 逻辑处理方法
	 */
	protected void DataTask(){
		mTab.add(new NewsPage("新闻")) ;
		mTab.add(new BaikePage("百科")) ;
		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
		//需要先为 viewpager 设置 adapter
		mTabLayout.setViewPager(mViewPager);
	}
}
