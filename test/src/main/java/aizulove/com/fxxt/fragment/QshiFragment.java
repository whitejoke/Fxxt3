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

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.FindDetailActivity;
import aizulove.com.fxxt.adapter.QshiqAdapter;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.task.QishiJulebuDataTask;
import aizulove.com.fxxt.view.GridListView;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class QshiFragment extends Fragment implements GridListView.OnMyGridViewRefreshListener,AdapterView.OnItemClickListener {

    private  View view;
    private GridListView listView;
    private QshiqAdapter adapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private List<Post> listMessage = new ArrayList<Post>();
    public static Fragment instance(){
        QshiFragment fragment = new QshiFragment() ;
        return fragment ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_qsjlb,null);//activity_qishiquan

        findViews();
        DataTask();
        initRefresh();
        return view;
    }
    private void initRefresh() {
        //materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new QishiJulebuDataTask(getContext(),listMessage,adapter,listView,materialRefreshLayout).execute();
            }

        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void findViews() {

        materialRefreshLayout= (MaterialRefreshLayout) view.findViewById(R.id.srl);
        listView=(GridListView)view.findViewById(R.id.qsjllistview);
    }


    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        Log.i("susu", "julebu");
        adapter=new QshiqAdapter(getContext(),listMessage);
        listView.setAdapter(adapter);
        new QishiJulebuDataTask(getContext(),listMessage,adapter,listView,materialRefreshLayout).execute();
        //listView.setOnMyGridViewRefreshListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        new QishiJulebuDataTask(getContext(),listMessage,adapter,listView,materialRefreshLayout).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(getContext(),FindDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("post",(Serializable) listMessage.get(position));
        intent.putExtras(bundle);
        intent.putExtra("title","车友俱乐部");
        startActivity(intent);
    }
}
