package aizulove.com.fxxt.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by joker on 2016/8/31.
 */
public class PageAdapter extends PagerAdapter {
    private List<View> adViews;
    public PageAdapter(List<View> adViews){
        this.adViews=adViews;
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
    public Object instantiateItem(View container, int position) {
        View view = adViews.get(position);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return adViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}