package aizulove.com.fxxt.modle.page;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import aizulove.com.fxxt.fragment.QshiFragment;

/**
 * Created by Administrator on 2016/4/27.
 */
public class QishiPage extends PagerItem{

    public Fragment createFragment(){
        return QshiFragment.instance() ;
    }

    public QishiPage(String mTitle){
        this.mTitle=mTitle;
    }



}
