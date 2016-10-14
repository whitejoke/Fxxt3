package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import aizulove.com.fxxt.fragment.AciveFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class AcivePage extends PagerItem{

    public Fragment createFragment(){
        return AciveFragment.instance() ;
    }

    public AcivePage(String mTitle){
        this.mTitle=mTitle;
    }
}
