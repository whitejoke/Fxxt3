package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.OrderFragment;

/**
 * Created by joker on 2016/10/9.
 */
public class OrderPage extends PagerItem {
    public Fragment createFragment(){
        return OrderFragment.instance(mTitle, name) ;
    }

    public OrderPage(String mTitle,String name){
        this.mTitle=mTitle;
        this.name=name;
    }
}
