package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import aizulove.com.fxxt.fragment.FindFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class FindPage extends PagerItem{

    public Fragment createFragment(){
        return FindFragment.instance() ;
    }

    public FindPage(String mTitle){
        this.mTitle=mTitle;
    }
}
