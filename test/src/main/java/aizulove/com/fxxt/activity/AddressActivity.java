package aizulove.com.fxxt.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.AddressAdapter;
import aizulove.com.fxxt.modle.entity.Address;
import aizulove.com.fxxt.task.DelProductTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class AddressActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView titleview,tv_address_bianji,tv_address_del;
    private ImageView iv_add_address;
    private ListView listView;
    private AddressAdapter adapter;
    private SwipeRefreshLayout srl;
    private String url=VariablesOfUrl.GETADDRESSLISTBYUSERNAME;
    private String urlDel=VariablesOfUrl.DELADDRESS;
    private List<Address> listMessage = new ArrayList<Address>();
    private String type="";
    private MaterialRefreshLayout materialRefreshLayout;

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_address, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }
    private void initRefresh() {
        //materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("page","1");
                map.put("num", "999");
                map.put("username", getMemberSharedPreference().getUsername().toString());
                new AddressTask(context,listMessage,adapter,map,url).execute();
            }

        });
    }

    private void initData() {
        type=getIntent().getStringExtra("type");
    }

    protected void findViews() {
        materialRefreshLayout= (MaterialRefreshLayout) findViewById(R.id.srl);
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
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
        ((TextView)findViewById(R.id.head_title)).setText("地址管理");
        tv_address_bianji= (TextView) findViewById(R.id.tv_address_bianji);
        tv_address_del= (TextView) findViewById(R.id.tv_address_del);
        listView=(ListView)findViewById(R.id.lv_address);
        listView.setOnItemClickListener(this);
        iv_add_address= (ImageView) findViewById(R.id.iv_add_address);
        iv_add_address.setOnClickListener(this);

    }

    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new AddressAdapter(context,listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "999");
        map.put("username", getMemberSharedPreference().getUsername().toString());
        new AddressTask(context,listMessage,adapter,map,url).execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_address:
                Intent intent= new Intent();
                intent.putExtra("type",type);
                intent.setClass(context,AddPlaceActivity.class);
                startActivityForResult(intent, 11);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OnRefresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        View delView=listView.getChildAt(position);
        tv_address_del= (TextView) delView.findViewById(R.id.tv_address_del);
        tv_address_bianji= (TextView) delView.findViewById(R.id.tv_address_bianji);
        tv_address_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AddressActivity.this);
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("addressId", String.valueOf(listMessage.get(position).getItemid()));
                        //Log.i("susu", String.valueOf(listMessage.get(position).getItemid()));
                        new DelProductTask(getApplicationContext(),map,urlDel).execute();
                        OnRefresh();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        tv_address_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(context,AddressManageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("address",listMessage.get(position));
                intent.putExtras(bundle);
                intent.putExtra("type",type);
                startActivityForResult(intent,12);
            }
        });
        if (type.equals("product")){
            Intent intent=new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("address",listMessage.get(position));
            intent.putExtras(bundle);
            setResult(2,intent);
            finish();
        }
    }

    private void OnRefresh() {
        adapter=new AddressAdapter(context,listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "222");
        map.put("username", getMemberSharedPreference().getUsername().toString());
        //Log.i("susu",getMemberSharedPreference().getUsername().toString());
        new AddressTask(context,listMessage,adapter,map,url).execute();
    }

    public class AddressTask extends AsyncTask<Void,Void,List<Address>> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private List<Address> listMessage;
        private AddressAdapter adapter;
        private Map<String, String> map;
        private String url;

        public AddressTask(Context context, List<Address> listMessage, AddressAdapter adapter, Map<String, String> map, String url) {
            this.context = context;
            this.listMessage = listMessage;
            this.adapter = adapter;
            this.map = map;
            this.url = url;
        }

        @Override
        protected List<Address> doInBackground(Void... params) {
            List<Address> result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL +this.url;// VariablesOfUrl.GETLOGOLIST;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getAddressListByUsername(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Address> result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        for (Address address:result){
                            setAddressSharedPreference(address);
                        }
                        listMessage.clear();
                        listMessage.addAll(result);
                        adapter.notifyDataSetChanged();
                       // materialRefreshLayout.finishRefresh();
                    }else{
                        ToastSingle.showToast(context, "参数错误");
                    }
                }else{
                    ToastSingle.showToast(context, "没有数据");
                }
            }else {
                ToastSingle.showToast(context, "请检查网络连接是否正常");
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
