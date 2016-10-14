package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.LogoAdapter;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.task.JulebuDataTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;

public class JulebuActivity extends BaseActivity implements GridListView.OnMyGridViewRefreshListener,AdapterView.OnItemClickListener,View.OnClickListener{

    private GridListView listView;
    private LogoAdapter adapter;
    private EditText search;
    private String url=VariablesOfUrl.GETLOGOLIST;
    private List<Logo> listMessage = new ArrayList<Logo>();
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cyjlb, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }


    protected void findViews() {
        search=(EditText)findViewById(R.id.search);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("车友俱乐部");
        listView=(GridListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        search.setOnClickListener(this);
    }

    protected  void setSelecttab(){
        SELECTTAB=1;
    }
    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new LogoAdapter(context,listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "999999");
        new JulebuDataTask(context,listMessage,adapter,listView,map,url).execute();
        listView.setOnMyGridViewRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num","999999");
        new JulebuDataTask(context,listMessage,adapter,listView,map,url).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(context,JulbdetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("logo",(Serializable) listMessage.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        url= VariablesOfUrl.SEARCHLOGO;
        Map<String, String> map = new HashMap<String, String>();
        map.put("keyword",search.getText().toString());
        map.put("page","1");
        map.put("num","999999");
        new JulebuDataTask(context,listMessage,adapter,listView,map,url).execute();
    }
}
