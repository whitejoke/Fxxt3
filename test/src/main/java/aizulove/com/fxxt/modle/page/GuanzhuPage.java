package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;

import aizulove.com.fxxt.fragment.GuanzhuFragment;

/**
 * Created by joker on 2016/10/9.
 */
public class GuanzhuPage extends PagerItem {
    public Fragment createFragment(){
        return GuanzhuFragment.instance(mTitle, id) ;
    }

    public GuanzhuPage(String mTitle,int id){
        this.mTitle=mTitle;
        this.id=id;
    }
}
