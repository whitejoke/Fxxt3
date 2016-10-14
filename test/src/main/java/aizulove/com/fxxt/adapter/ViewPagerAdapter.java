package aizulove.com.fxxt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import aizulove.com.fxxt.modle.page.PagerItem;
import aizulove.com.fxxt.view.SlidingTabLayout;

/**
 * Created by Administrator on 2016/4/27.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabItemName{

    private List<PagerItem> mTab;
    public ViewPagerAdapter(FragmentManager fm,List<PagerItem> mTab) {
        super(fm);
        this.mTab=mTab;
    }

    @Override
    public Fragment getItem(int position) {
        return mTab.get(position).createFragment();
    }

    @Override
    public int getCount() {
        return mTab.size();
    }

    @Override
    public String getTabName(int position) {
        return mTab.get(position).getTitle();
    }
}