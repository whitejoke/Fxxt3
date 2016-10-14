package aizulove.com.fxxt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.ActivieDetailActivity;
import aizulove.com.fxxt.adapter.AcivieAdapter;
import aizulove.com.fxxt.modle.entity.Activity;
import aizulove.com.fxxt.task.AcivieDataTask;
import aizulove.com.fxxt.view.GridListView;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class AciveFragment extends Fragment implements GridListView.OnMyGridViewRefreshListener,AdapterView.OnItemClickListener {

    private  View view;
    private GridListView listView;
    private AcivieAdapter adapter;
    private List<Activity> listMessage = new ArrayList<Activity>();
    public static Fragment instance(){
        AciveFragment fragment = new AciveFragment() ;
        return fragment ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_activie,null);
        findViews();
        DataTask();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void findViews() {
        listView=(GridListView)view.findViewById(R.id.activie_listview);
    }


    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        Log.i("susu","活动");
        adapter=new AcivieAdapter(getContext(),listMessage);
        listView.setAdapter(adapter);
        new AcivieDataTask(getContext(),listMessage,adapter,listView).execute();
        listView.setOnMyGridViewRefreshListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        new AcivieDataTask(getContext(),listMessage,adapter,listView).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(getContext(),ActivieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("activity",(Serializable) listMessage.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
