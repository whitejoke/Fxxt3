package aizulove.com.fxxt.activity;


import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.IntegrationAdapter;
import aizulove.com.fxxt.modle.entity.Integration;
import aizulove.com.fxxt.modle.entity.Member;
import aizulove.com.fxxt.task.IntegrationTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class MyJifenActivity extends BaseActivity {

    private TextView tv_balance;
    private ListView listView;
    private IntegrationAdapter adapter;
    private SwipeRefreshLayout srl;
    private String url=VariablesOfUrl.GETCREDITLISTBYUSERNAME;
    private List<Integration> listMessage = new ArrayList<Integration>();

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_integration, null);
        layoutContent.addView(convertView);
        findViews();
        DataTask();
        HiddenMeun();
    }

    protected void findViews() {
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.head_title)).setText("我的积分");

        listView=(ListView)findViewById(R.id.lv_integration);
        tv_balance= (TextView) findViewById(R.id.tv_balance);
    }

    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        Member member=getMemberSharedPreference();
        tv_balance.setText(String.valueOf(member.getCredit()));
        adapter=new IntegrationAdapter(context,listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "222");
        map.put("username", getMemberSharedPreference().getUsername().toString());
        //Log.i("susu", getMemberSharedPreference().getUsername().toString());
        new IntegrationTask(context,listMessage,adapter,map,url).execute();
    }

}
