package aizulove.com.fxxt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CouponAdapter;
import aizulove.com.fxxt.task.CouponDataTask;
import aizulove.com.fxxt.view.GridListView;

public class YouhuiActivity extends BaseActivity implements GridListView.OnMyGridViewRefreshListener, AdapterView.OnItemClickListener {

    private GridListView listView;
    private CouponAdapter adapter;
    private String type;
    private int term;
    private List<aizulove.com.fxxt.modle.entity.Coupon> listMessage = new ArrayList<aizulove.com.fxxt.modle.entity.Coupon>();
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_youhui,null);
        layoutContent.addView(convertView);
        initData();
        HiddenMeun();
    }

    private void initData() {
        type=getIntent().getStringExtra("type");
        term=getIntent().getIntExtra("term",0);
    }


    protected void findViews() {
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        listView=(GridListView)findViewById(R.id.youhui_listview);
        ((TextView)findViewById(R.id.head_title)).setText("我的优惠券");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancle = new Intent();
                setResult(999,cancle);
                finish();
            }
        });
        listView.setOnItemClickListener(this);
    }

    protected  void setSelecttab() {
        SELECTTAB=4;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new CouponAdapter(context,listMessage,term);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        new CouponDataTask(context,listMessage,map,adapter,listView).execute();
        listView.setOnMyGridViewRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        new CouponDataTask(context,listMessage,map,adapter,listView).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type.equals("youhui")){
            TextView tv_can= (TextView) view.findViewById(R.id.tv_judge);
            if (tv_can.getText().equals("优惠券可用")){
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("youhui",listMessage.get(position));
                intent.putExtras(bundle);
                setResult(4,intent);
                finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent cancle = new Intent();
        setResult(999,cancle);
        finish();
    }
}
