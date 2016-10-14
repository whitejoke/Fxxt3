package aizulove.com.fxxt.activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.page.AcivePage;
import aizulove.com.fxxt.modle.page.FindPage;
import aizulove.com.fxxt.modle.page.QishiPage;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class ActiveHostActivity extends BaseActivity implements  SlidingTabLayout.AddListener,View.OnClickListener{

	private  int position=0;


	public void setContentViews(){
		View convertView = mInflater.inflate(R.layout.activity_active_host, null);
		layoutContent.addView(convertView);
	}

	protected void findViews() {
		rightimageView=(ImageView)findViewById(R.id.rightimageView);
		rightimageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_add));
		rightimageView.setVisibility(View.VISIBLE);
		rightimageView.setOnClickListener(this);
		blakimageView=(ImageView)findViewById(R.id.blakimageView);
		((TextView)findViewById(R.id.head_title)).setText("骑士圈");
		mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
		mTabLayout= (SlidingTabLayout) findViewById(R.id.id_tab);
	}

	protected  void setSelecttab(){
		SELECTTAB=2;
	}

	/**
	 *
	 * 逻辑处理方法
	 */
	protected void DataTask(){
		mTab.add(new FindPage("发现")) ;
		mTab.add(new QishiPage("车友俱乐部")) ;
		mTab.add(new AcivePage("活动")) ;
		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
		mTabLayout.setViewPager(mViewPager);
		mTabLayout.setOnAddListener(this);
	}

	@Override
	public void getAdd(int position){
		switch (position){
			case 0:
				this.position=position;
				rightimageView.setVisibility(View.VISIBLE);
				break;
			case  1:
				this.position=position;
				rightimageView.setVisibility(View.GONE);
				break;
			case 2:
				this.position=position;
				rightimageView.setVisibility(View.GONE);
				break;

		}
	}

	@Override
	public void onClick(View v){
		Intent intent;
		if (position==0){
			if(Data().equals("")) {
				intent = new Intent();
				intent.setClass(context, HostActivity.class);
				startActivity(intent);
				finish();
			}else {
				intent = new Intent();
				intent.putExtra("type", "1");
				intent.setClass(context, FindActivity.class);
				startActivityForResult(intent, 1001);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
		mTabLayout.setViewPager(mViewPager);
		mTabLayout.setOnAddListener(this);
	}
}