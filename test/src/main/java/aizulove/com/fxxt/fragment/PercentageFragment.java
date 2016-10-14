package aizulove.com.fxxt.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.PercentageAdapter;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.modle.entity.Percentage;
import aizulove.com.fxxt.task.PercentageDataTask;
import aizulove.com.fxxt.view.GridListView;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class PercentageFragment extends Fragment implements GridListView.OnMyGridViewRefreshListener{

    private  View view;
    private GridListView listView;
    private PercentageAdapter adapter;
    private List<Percentage> listMessage = new ArrayList<Percentage>();
    public static Fragment instance(){
        PercentageFragment fragment = new PercentageFragment() ;
        return fragment ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_recharge,null);//activity_distribution_item
        findViews();
        DataTask();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void findViews() {
        listView=(GridListView)view.findViewById(R.id.rechargelistview);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new PercentageAdapter(getContext(),listMessage);
        listView.setAdapter(adapter);
        listView.setOnMyGridViewRefreshListener(this);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId",getMemberSharedPreference().getUserid()+"");//getMemberSharedPreference().getUserid()
        map.put("num","9999");
        map.put("page","1");
        new PercentageDataTask(getContext(),map,listMessage,adapter,listView).execute();
    }

    @Override
    public void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId",getMemberSharedPreference().getUserid()+"");//
        map.put("num","9999");
        map.put("page","1");
        new PercentageDataTask(getContext(),map,listMessage,adapter,listView).execute();
    }

    protected Member getMemberSharedPreference() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("member", getContext().MODE_WORLD_READABLE);
        Member result=new Member();
        result.setUserid(Integer.parseInt(sharedPreferences.getString("userid", "0")));
        result.setCredit(Integer.parseInt(sharedPreferences.getString("credit", "0")));
        result.setUsername(sharedPreferences.getString("username", ""));
        result.setMobile(sharedPreferences.getString("mobile", ""));
        result.setMoney(Float.parseFloat(sharedPreferences.getString("money", "0.0")));
        return result;
    }
}
