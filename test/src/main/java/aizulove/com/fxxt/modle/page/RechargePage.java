package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;
import aizulove.com.fxxt.fragment.RechargeFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class RechargePage extends PagerItem{

    public Fragment createFragment(){

        return RechargeFragment.instance() ;
    }

    public RechargePage(String mTitle){
        this.mTitle=mTitle;
    }
}
