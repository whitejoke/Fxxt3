package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.PercentageFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class PercentagePage extends PagerItem{

    public Fragment createFragment(){

        return PercentageFragment.instance(type) ;
    }

    public PercentagePage(String mTitle,int type){
        this.mTitle=mTitle;
        this.type=type;
    }
}
