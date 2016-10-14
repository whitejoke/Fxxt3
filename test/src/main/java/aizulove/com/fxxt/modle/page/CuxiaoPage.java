package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.CuxiaoFragment;

/**
 * Created by joker on 2016/10/9.
 */
public class CuxiaoPage extends PagerItem {
    public Fragment createFragment(){
        return CuxiaoFragment.instance(mTitle,isGuanzhu) ;
    }

    public CuxiaoPage(String mTitle,String isGuanzhu){
        this.mTitle=mTitle;
        this.isGuanzhu=isGuanzhu;
    }
}
