package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;
import aizulove.com.fxxt.fragment.WithdrawFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class WithdrawPage extends PagerItem{

    public Fragment createFragment(){

        return WithdrawFragment.instance() ;
    }

    public WithdrawPage(String mTitle){
        this.mTitle=mTitle;
    }
}
