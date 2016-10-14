package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.ProductFragment;

/**
 * Created by joker on 2016/10/9.
 */
public class HotPage extends PagerItem {
    public Fragment createFragment(){
        return ProductFragment.instance(mTitle,isGuanzhu) ;
    }

    public HotPage(String mTitle,String isGuanzhu){
        this.mTitle=mTitle;
        this.isGuanzhu=isGuanzhu;
    }
}
