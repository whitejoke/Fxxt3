package aizulove.com.fxxt.modle.page;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public  class PagerItem {
    /*item的 title*/
    protected String mTitle ;
    protected int id;
    protected String name;
    protected String isGuanzhu;
    protected Context context;

    protected ImageView rightimageView;


    public  Fragment createFragment(){
        return null;
    };

    public String getTitle() {
        return mTitle;
    }
}
