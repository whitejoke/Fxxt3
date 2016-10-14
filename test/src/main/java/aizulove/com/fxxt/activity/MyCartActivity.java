package aizulove.com.fxxt.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CarAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.CarTask;
import aizulove.com.fxxt.task.DelProductTask;
import aizulove.com.fxxt.task.EditAddressTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class MyCartActivity extends BaseActivity implements  AdapterView.OnItemClickListener, View.OnClickListener ,CarAdapter.Callback {

    private TextView titleview,tv_del;
    private Button btn_buyall;
    private GridView listView;
    private CarAdapter adapter;
    private SwipeRefreshLayout srl;
    private String url=VariablesOfUrl.GETCARTLISTBYUSERID;
    private String urls=VariablesOfUrl.ADDCART;
    private String urlCut=VariablesOfUrl.CANCELCART;

    private String urlDel=VariablesOfUrl.DELCART;
    private List<Product> listMessage = new ArrayList<Product>();
    private String urlGetTrade=VariablesOfUrl.GETMALLORDERCODE;
    private String urlSave=VariablesOfUrl.SAVEMALLORDER;
    private String urlOrder=VariablesOfUrl.ADDMALLORDER;
    private RelativeLayout cart;
    private int positions;

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_car, null);
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
        ((TextView)findViewById(R.id.head_title)).setText("购物车");

        listView=(GridView)findViewById(R.id.gv_car);
        btn_buyall= (Button) findViewById(R.id.btn_buyall);
        cart= (RelativeLayout) findViewById(R.id.rl_cart);
        btn_buyall.setOnClickListener(this);
        listView.setOnItemClickListener(this);

    }

    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new CarAdapter(context,listMessage,this);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "999");
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        new CarTask(context,listMessage,adapter,map,url,listView).execute();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        positions=position;
        View delView=listView.getChildAt(position);
        tv_del= (TextView) delView.findViewById(R.id.tv_dele);
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MyCartActivity.this);
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("strData",listMessage.get(position).getData().toString());
                        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                        new DelProductTask(getApplicationContext(),map,urlDel).execute();
                        Toast.makeText(context,"删除成功！",Toast.LENGTH_SHORT).show();
                        DataTask();
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
    }

    @Override
    public void onClick(View v) {
        if (getAddressSharedPreference().getName().equals("")){
            Toast.makeText(context,"地址信息暂无",Toast.LENGTH_SHORT).show();
        }else {
            for (Product product:listMessage){
                new TradeNoTask(context,urlGetTrade,product).execute();
                Map<String, String> map = new HashMap<String, String>();
                map.put("strData", product.getData());
                map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                new DelProductTask(getApplicationContext(),map,urlDel).execute();
                DataTask();
            }
            new AlertDialog.Builder(MyCartActivity.this)
                    .setTitle("已生成订单,请前往我的订单查看")
                    .setPositiveButton("确定", null)
                    .create().show();
        }

    }

    @Override
    public void clickToCar(View view, String type,int position) {
        if (type.equals("add")){
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            map.put("mallId", String.valueOf(listMessage.get(position).getItemid()));
            map.put("v1", listMessage.get(position).getOut_v1());
            map.put("v2",listMessage.get(position).getOut_v2());
            map.put("v3",listMessage.get(position).getOut_v3());
            map.put("i", String.valueOf(1));
            new EditAddressTask(getApplicationContext(),map,urls).execute();
            onRefresh();
        }
        if (type.equals("cut")){
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            map.put("mallId", String.valueOf(listMessage.get(position).getItemid()));
            map.put("v1", listMessage.get(position).getOut_v1());
            map.put("v2",listMessage.get(position).getOut_v2());
            map.put("v3",listMessage.get(position).getOut_v3());
            map.put("i", String.valueOf(1));
            new EditAddressTask(getApplicationContext(),map,urlCut).execute();
            onRefresh();
        }

    }

    private void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "999");
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        new CarTask(context,listMessage,adapter,map,url,listView).execute();
    }

    public class TradeNoTask extends AsyncTask<Void,Void,String> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private String url;
        private Product product;
        public TradeNoTask(Context context, String url,Product product) {
            this.context = context;
            this.url = url;
            this.product=product;
        }
        @Override
        protected String doInBackground(Void... params) {
            String result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    Map<String,String> map=new HashMap<>();
                    String url = VariablesOfUrl.APP_BASE_URL +this.url;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getMallOrderCode(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null){
                if (!result.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username",getMemberSharedPreference().getUsername());
                    map.put("seller",product.getEditor());
                    map.put("title",product.getTitle());
                    map.put("mallId", String.valueOf(product.getItemid()));
                    map.put("thumb",product.getThumb());
                    map.put("price", String.valueOf(product.getPrice()));
                    map.put("number", String.valueOf(product.getI()));
                    map.put("amount", String.valueOf(product.getPrice()*product.getI()));
                    map.put("fee", String.valueOf(0));
                    map.put("feeName","");
                    map.put("buyerName", getAddressSharedPreference().getName());
                    map.put("buyerAddress",getAddressSharedPreference().getAreaname()+getAddressSharedPreference().getAddress());
                    map.put("buyerPostcode",getAddressSharedPreference().getPostcode());
                    map.put("buyerPhone",getAddressSharedPreference().getPhone());
                    map.put("buyerMobile","");
                    map.put("addTime", String.valueOf(0));
                    map.put("tradeNo", result);
                    String[] temp=product.getV1().split("\\|");
                    String[] temp2=product.getV2().split("\\|");
                    String[] temp3=product.getV3().split("\\|");
                    String note="";
                    if (!product.getN1().equals("")){
                        note=note+product.getN1()+":"+temp[Integer.parseInt(product.getOut_v1())]+" ";
                    }
                    if (!product.getN2().equals("")){
                        note=note+product.getN2()+":"+temp2[Integer.parseInt(product.getOut_v2())]+" ";
                    }
                    if (!product.getN3().equals("")){
                        note=note+product.getN3()+":"+temp3[Integer.parseInt(product.getOut_v3())];
                    }
                    map.put("note", note);
                    new EditAddressTask(context,map,urlSave).execute();
                }
            }
        }

    }
}
