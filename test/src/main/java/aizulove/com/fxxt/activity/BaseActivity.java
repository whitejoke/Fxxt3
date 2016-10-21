package aizulove.com.fxxt.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Address;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.modle.page.PagerItem;
import aizulove.com.fxxt.view.SlidingTabLayout;

/**
 * Created by Administrator on 2016/4/27.
 */
public class BaseActivity extends FragmentActivity {
    /*viewpager*/
    protected ViewPager mViewPager ;
    /*自定义的 tabLayout*/
    protected SlidingTabLayout mTabLayout ;
    /*每个 tab 的 item*/
    protected List<PagerItem> mTab = new ArrayList<>() ;


    protected LinearLayout  layoutContent;
    /**
     * 首页
     */
    protected LinearLayout lilyHome;

    /**
     * 用户中心
     */
    protected LinearLayout lilyUser;

    /**
     * 购物车
     */
    protected LinearLayout lilyCar;

    /**
     * 更多
     */
    protected LinearLayout lilyMore;


    protected LinearLayout menuLayout;


    protected Context context;

    protected LayoutInflater mInflater;

    protected SharedPreferences sharedPreferences;

    protected SharedPreferences sharedPreferencesAddress;

    protected int SELECTTAB=1;

    protected ImageView blakimageView;
    protected ImageView rightimageView;
    protected TextView righttextView;

    protected AlertDialog dialog;

    protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        sharedPreferences = getSharedPreferences("member", MODE_WORLD_READABLE);
        sharedPreferencesAddress=getSharedPreferences("address", MODE_WORLD_READABLE);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_frame);
        initView();
        setSelecttab();
        selectTab();
        setContentViews();
        findViews();
        setListener();
        DataTask();
    }

    protected void HiddenMeun(){
        menuLayout.setVisibility(View.GONE);
    }

    private void initView() {
        layoutContent=(LinearLayout)findViewById(R.id.layoutContent);
        lilyHome=(LinearLayout)findViewById(R.id.lilyHome);
        lilyUser=(LinearLayout)findViewById(R.id.lilyUser);
        lilyCar=(LinearLayout)findViewById(R.id.lilyCar);
        lilyMore=(LinearLayout)findViewById(R.id.lilyMore);
        menuLayout=(LinearLayout)findViewById(R.id.menuLayout);

        lilyHome.setOnClickListener(new ClickListener());
        lilyUser.setOnClickListener(new ClickListener());
        lilyCar.setOnClickListener(new ClickListener());
        lilyMore.setOnClickListener(new ClickListener());
    }


    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.lilyHome:
                    intent= new Intent();
                    intent.setClass(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.lilyUser :
                    intent = new Intent();
                    intent.setClass(context, ActiveHostActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.lilyCar:
                    intent = new Intent();
                     intent.setClass(context, QipaHostActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.lilyMore :
                    if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                    }else {
                    intent = new Intent();
                    intent.setClass(context, MyActivity.class);
                    startActivity(intent);
                    finish();
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void selectTab(){
        lilyHome.setSelected(false);
        lilyUser.setSelected(false);
        lilyCar.setSelected(false);
        lilyMore.setSelected(false);
        switch (SELECTTAB) {
            case 1:
                lilyHome.setSelected(true);
            break;

            case 2:
                lilyUser.setSelected(true);
                break;

            case 3:
                lilyCar.setSelected(true);
                break;

            case 4:
                lilyMore.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     * @param listView
     */
    public void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目demwk
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    protected View dialog(int id){
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout=inflater.inflate(id, null);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setCancelable(true);
        dialog= builder.show();
        return  layout;
    }
    /**
     * set normal SharedPreferences
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    protected void setSP(String key, Object value) {
        String type = value.getClass().getSimpleName();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) value);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) value);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    /**
     * set normal SharedPreferences
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     */
    protected Object getSP(String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }


    protected void setAddressSharedPreference(Address result){
        clearDataAddress();
        SharedPreferences.Editor editor = sharedPreferencesAddress.edit();
        editor.putString("userid", result.getUserid()+"");
        editor.putString("name", result.getName()+"");
        editor.putString("phone", result.getPhone()+"");
        editor.putString("username", result.getUsername() == null ? "" : result.getUsername());
        editor.putString("mobile", result.getMobile()==null?"":result.getMobile());
        editor.putString("areaname", result.getAreaname()==null?"":result.getAreaname());
        editor.putString("postcode", result.getPostcode()==null?"":result.getPostcode());
        editor.putString("address",result.getAddress()+"");
        editor.commit();
    }
    protected Address getAddressSharedPreference() {
        Address result=new Address();
        result.setUserid(sharedPreferencesAddress.getString("userid", ""));
        result.setName(sharedPreferencesAddress.getString("name", ""));
        result.setPhone(sharedPreferencesAddress.getString("phone", ""));
        result.setUsername(sharedPreferencesAddress.getString("username", ""));
        result.setAreaname(sharedPreferencesAddress.getString("areaname", ""));
        result.setMobile(sharedPreferencesAddress.getString("mobile", ""));
        result.setPostcode(sharedPreferencesAddress.getString("postcode", ""));
        result.setAddress(sharedPreferencesAddress.getString("address", ""));
        return result;
    }
    protected void setMemberSharedPreference(Member result) {
        clearData();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userid", result.getUserid()+"");
        editor.putString("credit", result.getCredit()+"");
        editor.putString("username", result.getUsername() == null||result.getUsername().equals("null") ? "" : result.getUsername());
        editor.putString("password", result.getPassword() == null ? "" : result.getPassword());
        editor.putString("mobile", result.getMobile() == null ||result.getMobile().equals("null")? "" : result.getMobile());

        editor.putString("token", result.getToken() == null ? "" : result.getToken());
        editor.putString("money", result.getMoney()+"");
        editor.putString("email", result.getEmail()==null ||result.getEmail().equals("null")? "":result.getEmail()+"");
        editor.putString("avatarUrl", result.getAvatarUrl()==null||result.getAvatarUrl().equals("null")?"":result.getAvatarUrl()+"");

        editor.putString("level",result.getLevel()==null||result.getLevel().equals("null")?"":result.getLevel()+"");
        editor.putString("referralCode",result.getReferralCode()==null||result.getReferralCode().equals("null")?"":result.getReferralCode()+"");

        editor.putString("areaid",result.getAreaid()+"");
        editor.putString("gender",result.getGender()+"");
        editor.putString("truename", result.getTruename() == null||result.getTruename().equals("null") ? "" :result.getTruename()+"");
        editor.putString("skype", result.getSkype() == null||result.getSkype().equals("null") ? "" :result.getSkype()+"");
        editor.putString("qq", result.getQq() == null ||result.getQq().equals("null")? "" :result.getQq()+"");
        editor.putString("ali", result.getAli() == null||result.getAli().equals("null") ? "" :result.getAli()+"");
        editor.putString("department", result.getDepartment() == null||result.getDepartment().equals("null") ? "" :result.getDepartment()+"");
        editor.putString("sound", result.getSound()+"");
        editor.putString("career", result.getCareer() == null ||result.getCareer().equals("null")? "" :result.getCareer()+"");
        editor.putString("areaId", result.getAreaid()+"");
        editor.putString("city",result.getCity() == null || result.getCity().equals("null") ? "":result.getCity()+"");
        editor.putString("province",result.getProvince()==null||result.getProvince().equals("null")?"":result.getProvince()+"");
        editor.putString("logintimes",result.getLogintimes()+"");
        editor.putString("attentionids",result.getAttentionids()+"");
        editor.commit();
    }

    public Member getMemberSharedPreference() {
        Member result=new Member();
        result.setUserid(Integer.parseInt(sharedPreferences.getString("userid", "0")));
        result.setCredit(Integer.parseInt(sharedPreferences.getString("credit", "0")));
        result.setUsername(sharedPreferences.getString("username", ""));
        result.setPassword(sharedPreferences.getString("password", ""));
        result.setToken(sharedPreferences.getString("token", ""));
        result.setMobile(sharedPreferences.getString("mobile", ""));
        result.setEmail(sharedPreferences.getString("email", ""));
        result.setAvatarUrl(sharedPreferences.getString("avatarUrl", ""));

        result.setLevel(sharedPreferences.getString("level",""));
        result.setReferralCode(sharedPreferences.getString("referralCode",""));

        result.setGender(Integer.parseInt(sharedPreferences.getString("gender","0")));
        result.setAreaid(Integer.parseInt(sharedPreferences.getString("areaid","0")));

        result.setArrchildid(sharedPreferences.getString("arrchildid",""));

        result.setTruename(sharedPreferences.getString("truename", ""));
        result.setSkype(sharedPreferences.getString("skype", ""));
        result.setQq(sharedPreferences.getString("qq", ""));
        result.setAli(sharedPreferences.getString("ali", ""));
        result.setDepartment(sharedPreferences.getString("department", ""));
        result.setSound(Integer.parseInt(sharedPreferences.getString("sound", "0")));
        result.setCareer(sharedPreferences.getString("career", ""));
        result.setAreaid(Integer.parseInt(sharedPreferences.getString("areaId", "0")));
        result.setProvince(sharedPreferences.getString("province",""));
        result.setCity(sharedPreferences.getString("city", ""));
        result.setMoney(Float.parseFloat(sharedPreferences.getString("money", "0.0")));
        result.setLogintimes(Integer.parseInt(sharedPreferences.getString("logintimes", "0")));
        result.setAttentionids(sharedPreferences.getString("attentionids",""));
       return result;
    }

    public String Data() {
        String content;
        SharedPreferences sharedPreferences = getSharedPreferences("member",MODE_WORLD_READABLE);
        content = sharedPreferences.getString("username", "");
        return content;
    }

    protected void clearData() {
        sharedPreferencesAddress.edit().clear().commit();
        sharedPreferences.edit().clear().commit();
    }
    protected void clearDataAddress() {
        sharedPreferencesAddress.edit().clear().commit();
    }

    protected  void setSelecttab() {}
    /**
     *
     * 设置监听方法
     */
    protected  void setListener() {}

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){}

    /**
     *
     * 设置布局文件
     */
    protected void setContentViews(){}

    /**
     * 查找View
     */
    protected void findViews(){}
}
