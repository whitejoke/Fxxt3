package aizulove.com.fxxt.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Product;

/**
 * Created by joker on 2016/11/2.
 */
public class OrderDetailActivity extends Activity {
    private Context context;
    private Product product;
    private TextView titleview;
    private ImageView orderImage;
    private TextView orderTitle;
    private TextView orderPrice;
    private TextView orderAmount;
    private TextView orderNum;
    private TextView orderNum2;
    private TextView orderNote;
    private TextView orderTrade_no;
    private TextView orderName,orderPhone,orderPlace,orderPostCode;
    private Button orderStatus;
    private String[] temp={"待确认","待付款","待发货","已发货",
            "交易成功","申请退款","已退款","货到付款",
            "买家关闭","卖家关闭"};


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        initData();
        findViews();
        DataTask();
    }

    private void initData() {
        product = (Product) getIntent().getSerializableExtra("product");
    }


    protected void findViews() {
        ((ImageView)findViewById(R.id.blakimageView)).setVisibility(View.VISIBLE);
        titleview=(TextView)findViewById(R.id.head_title);
        ((ImageView)findViewById(R.id.blakimageView)) .setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.blakimageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleview.setText("订单详情");
        orderImage=(ImageView)findViewById(R.id.iv_order);
        orderNote = (TextView) findViewById(R.id.tv_order_note);
        orderAmount = (TextView) findViewById(R.id.tv_order_amount);
        orderNum= (TextView) findViewById(R.id.tv_order_num);
        orderNum2= (TextView) findViewById(R.id.tv_order_num2);
        orderPrice= (TextView) findViewById(R.id.tv_order_price);
        orderTitle= (TextView) findViewById(R.id.tv_order_title);
        orderTrade_no= (TextView)findViewById(R.id.tv_order_trade_no);
        orderName= (TextView) findViewById(R.id.tv_order_name);
        orderPhone= (TextView) findViewById(R.id.tv_order_phone);
        orderPlace= (TextView) findViewById(R.id.tv_order_place);
        orderPostCode= (TextView) findViewById(R.id.tv_order_code);
        orderStatus= (Button) findViewById(R.id.btn_order_status);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        orderTitle.setText(product.getTitle());
        orderPrice.setText("单价: ¥ "+String.valueOf(product.getPrice()));
        if (product.getNote().equals("")){
            orderNote.setVisibility(View.INVISIBLE);
        }else {
            orderNote.setText(product.getNote());
        }
        orderNum.setText("数量:"+String.valueOf(product.getNumber()));
        orderNum2.setText("共计"+String.valueOf(product.getNumber())+"件商品");
        orderAmount.setText("合计: ¥ "+String.valueOf(product.getAmount()));
        orderTrade_no.setText("订单号 "+product.getTrade_no());
        String orderPath= product.getThumb();//
        if(null!=orderPath) {
            Picasso.with(context).load(orderPath).into(orderImage);
        }
        else{
            orderImage.setImageResource(R.mipmap.ic_phone);
        }
        orderName.setText("收货人:"+product.getTruename());
        orderPhone.setText("联系电话:"+product.getTelephone());
        orderPlace.setText("详细地址:"+product.getAddress());
        orderPostCode.setText("邮政编码:"+product.getPostcode());
        orderStatus.setText("订单状态:"+temp[product.getStatus()]);
    }

}
