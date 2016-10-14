package aizulove.com.fxxt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.QbxqActivity;
import aizulove.com.fxxt.adapter.InformationAdapter;
import aizulove.com.fxxt.modle.entity.Information;
import aizulove.com.fxxt.task.InformationDataTask;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class NewsFragment extends Fragment implements OnItemClickListener {

    private  View view;
    private ListView listView;
    private InformationAdapter adapter;
    private List<Information> listMessage = new ArrayList<Information>();
    public static Fragment instance(){
        NewsFragment fragment = new NewsFragment() ;
        return fragment ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_information,null);
        findViews();
        DataTask();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    protected void findViews() {
        listView=(ListView)view.findViewById(R.id.activie_listview);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new InformationAdapter(getContext(),listMessage);
        listView.setAdapter(adapter);
        new InformationDataTask(getContext(),listMessage,adapter,listView).execute();
        listView.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(getContext(),QbxqActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("information",(Serializable) listMessage.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
