package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.MessageAdapter;
import aizulove.com.fxxt.modle.entity.Integration;
import aizulove.com.fxxt.task.MessageTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class MymessageActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private MessageAdapter adapter;
    private SwipeRefreshLayout srl;
    private String url= VariablesOfUrl.GETMESSAGELISTBYUSERNAME;
    private List<Integration> listMessage = new ArrayList<Integration>();

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_messg, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }



    protected void findViews() {
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       ((TextView)findViewById(R.id.head_title)).setText("我的消息");
        listView=(ListView)findViewById(R.id.lv_message);
        listView.setOnItemClickListener(this);
    }
    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new MessageAdapter(context,listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "222");
        map.put("username", getMemberSharedPreference().getUsername().toString());
        //Log.i("susu", getMemberSharedPreference().getUsername().toString());
        new MessageTask(context,listMessage,adapter,map,url).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(context,MyMessgActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("message",listMessage.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
