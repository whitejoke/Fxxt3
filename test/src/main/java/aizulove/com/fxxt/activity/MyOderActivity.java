package aizulove.com.fxxt.activity;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.OrderAdapter;
import aizulove.com.fxxt.adapter.ViewPagerAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.modle.page.OrderPage;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.SlidingTabLayout;

public class MyOderActivity extends BaseActivity implements SlidingTabLayout.AddListener {

    private TextView titleview;
    private GridView listView;
    private OrderAdapter adapter;
    private String type="";
    private SwipeRefreshLayout srl;
    private String url=VariablesOfUrl.GETORDERLISTBYUSERNAME;
    private List<Product> listMessage = new ArrayList<Product>();

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_cuxiao, null);
        layoutContent.addView(convertView);
        HiddenMeun();
    }

//    private void initDta() {
//        type=getIntent().getStringExtra("type");
//        if (type.equals("orderPay")){
//            AlertDialog.Builder builder = new AlertDialog.Builder(MyOderActivity.this);
//            builder.setMessage("支付完成");
//            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            builder.create().show();
//        }
//    }

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
        ((TextView)findViewById(R.id.head_title)).setText("我的订单");
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager) ;
        mTabLayout= (SlidingTabLayout) findViewById(R.id.id_tab);
//        listView=(GridView)findViewById(R.id.gv_order);
//        listView.setOnItemClickListener(this);
    }

    /**
     * 逻辑处理方法
     */
    protected void DataTask(){
        mTab.add(new OrderPage("全部",getMemberSharedPreference().getUsername())) ;
        mTab.add(new OrderPage("待付款",getMemberSharedPreference().getUsername())) ;
        mTab.add(new OrderPage("待发货",getMemberSharedPreference().getUsername())) ;
        mTab.add(new OrderPage("待收货",getMemberSharedPreference().getUsername())) ;
        mTab.add(new OrderPage("退货申请",getMemberSharedPreference().getUsername())) ;
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mTab));
        mTabLayout.setViewPager(mViewPager);
        mTabLayout.setOnAddListener(this);
//        adapter=new OrderAdapter(context,listMessage);
//        listView.setAdapter(adapter);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("page","1");
//        map.put("num", "222");
//        map.put("username", getMemberSharedPreference().getUsername().toString());
//        //Log.i("susu",getMemberSharedPreference().getUsername().toString());
//        new OrderTaSK(context,listMessage,adapter,map,url).execute();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent=new Intent();
//        intent.setClass(MyOderActivity.this,TransactionActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putSerializable("product",listMessage.get(position));
//        bundle.putString("type","order");
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }

    @Override
    public void getAdd(int position) {

    }
}
