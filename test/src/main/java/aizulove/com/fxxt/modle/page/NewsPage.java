package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.NewsFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class NewsPage extends PagerItem{

    public Fragment createFragment(){

        return NewsFragment.instance() ;
    }

    public NewsPage(String mTitle){
        this.mTitle=mTitle;
    }
}
