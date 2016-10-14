package aizulove.com.fxxt.utils;

import android.content.SharedPreferences;

import aizulove.com.fxxt.modle.entity.Member;

/**
 * Created by Administrator on 2016/6/2.
 */
public class SharedPreferenceUtil {


    public static Member getMemberSharedPreference(SharedPreferences sharedPreferences) {
        Member result=new Member();
        result.setUserid(Integer.parseInt(sharedPreferences.getString("userid", "0")));
        result.setCredit(Integer.parseInt(sharedPreferences.getString("credit", "0")));
        result.setUsername(sharedPreferences.getString("username", ""));
        result.setToken(sharedPreferences.getString("token", ""));
        result.setMobile(sharedPreferences.getString("mobile", ""));
        result.setMoney(Float.parseFloat(sharedPreferences.getString("money", "0.0")));
        result.setLogintimes(Integer.parseInt(sharedPreferences.getString("logintimes", "0")));
        return result;
    }
}
