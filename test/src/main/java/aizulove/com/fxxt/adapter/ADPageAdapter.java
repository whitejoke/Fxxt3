package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import aizulove.com.fxxt.activity.ProductActivity;
import aizulove.com.fxxt.modle.entity.Advert;
import aizulove.com.fxxt.modle.entity.Product;

public class ADPageAdapter extends PagerAdapter {
	private List<View> adViews;
	private Context context;
	private List<Advert> list;
	public ADPageAdapter(List<View> adViews,Context context,List<Advert> list){
		this.adViews=adViews;
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		return adViews.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView(adViews.get(position));
	}

	@Override
	public Object instantiateItem(View container, final int position) {
		View view = adViews.get(position);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int id= Integer.parseInt(Uri.parse(list.get(position).getUrl()).getQueryParameter("itemid"));
				Product product = new Product();
				product.setItemid(id);
				Intent intent = new Intent();
				intent.setClass(context,ProductActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("product", product);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		ViewPager viewPager = (ViewPager) container;
		viewPager.addView(view);
		return adViews.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
}
