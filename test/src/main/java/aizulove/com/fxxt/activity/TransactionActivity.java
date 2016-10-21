package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.alipay.PayResult;
import aizulove.com.fxxt.alipay.SignUtils;
import aizulove.com.fxxt.modle.entity.Address;
import aizulove.com.fxxt.modle.entity.Coupon;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.EditAddressTask;
import aizulove.com.fxxt.task.MoneyPayTask;
import aizulove.com.fxxt.task.OrderStatusTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/8/8.
 */
public class TransactionActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_truename,tv_phone,tv_address,
            tv_trade_youhui,tv_trade_title,tv_trade_price,
            tv_trade_count,tv_trade_color,tv_trade_size,
            tv_trade_notknow,tv_trade_amount;
    private EditText et_trade_note;
    private ImageView iv_trade_image;
    Product product=new Product();
    Address address=new Address();
    Coupon coupon=new Coupon();
    private String v1="",v2="",v3="";
    private String urlYu= VariablesOfUrl.MONEYPAY;
    private String type;
    private int pop_count=1;
    private Double cost=0.0;
    private int amount;
    private LinearLayout ll_test1;
    private RadioButton rb_trade_zhi,rb_trade_yu;
    private String urlGetTrade=VariablesOfUrl.GETMALLORDERCODE;
    private String urlSave=VariablesOfUrl.SAVEMALLORDER;
    private String urlOrderStatus=VariablesOfUrl.MODIFYMALLORDERSTATUS;

    // 商户PID
    public static final String PARTNER = "2088121898529513";
    // 商户收款账号
    public static final String SELLER = "zyp@nbhissen.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALA" +
                    "XSjm/znSsz7gGkQ1qQX/m8aTISUfyOIM0ClrsZ96Mrt0W" +
                    "R1Gf+dVACd9pa5jLko5RMewlffDocotQPYzPbJikLOcj2k" +
                    "YKldLqcjBcZAOGwUU9Q5WzWwuwbAsuvDNLcZD3bezQajvYfPxRF" +
                    "bgR4zHjhOlbNamAc41w7oT1T64lAgMBAAECgYBAJDulTXimh0wifEHZ4" +
                    "Rp6vGTI4SvQSSrMn+gltncfWZ9YaQURdnRKeUkfWAcZYQ3BSVIl8oItfn46w" +
                    "L+qyuODG1MMeSuJ1LA+go67dKCOR65f12MoxCPQaBSMiIzIFHl9iXlvrBCtmx" +
                    "haHi4LI37suImFOTlQRZOCWg4h3MTPAQJBAOGtcAyCWZwAkIZhncURPJ/y2qT" +
                    "berEd/+h3KIA8D6y3nA8Z1OxXKpAOpunUVnaiyD46auStqHrL/UKposB8QrsCQ" +
                    "QDHwD1PBYg5zKVdDzilb5JPKfXbonEQhDQllktgrJJyyNzasuFE18ShlvFNFmLK" +
                    "gBEzdyEX1XUF9Qh8VWY1GPSfAkA87fSYjADhi415mpVcBWFW1dyOMeuYMj9LZhB" +
                    "7PrwEp7Xspc7E2GvXZP1PmFKXyS9DDcaaYwBf1v9TpjUYvvPNAkBjuX8iR4HGUX" +
                    "wxZJsERRqHeUxsdcH62/Xo2gsR3bZTpiJU8T90DkD155TaxpVVwfBUgGI+Mux0p+" +
                    "uvnzcixS3DAkEAqLS8DG9YiLWfve5NM9VoA52DT0L0eh0thKz5U2tCUO68CIheT" +
                    "a79WuwTP/ddnasmAaT+47d2YKrfvXbSenUAiw==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        if (type.equals("order")){
                            PayFromOrderZhi();
                        }
                        if (type.equals("product"))
                            new TradeNoTask(context,urlGetTrade,product).execute();
                            Toast.makeText(TransactionActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(TransactionActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else if(TextUtils.equals(resultStatus, "6001")){
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(TransactionActivity.this, "交易取消", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(TransactionActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void PayFromOrderZhi() {
        Map<String,String> map=new HashMap<>();
        map.put("tradeNo",product.getTrade_no());
        map.put("status", String.valueOf(2));
        new OrderStatusTask(context,urlOrderStatus,map).execute();
        Intent intent=new Intent();
        intent.setClass(context,MyOderActivity.class);
        startActivity(intent);
    }


    @Override
    protected void setContentViews() {
        View convertView = mInflater.inflate(R.layout.activity_trade, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }
    private void initData() {
        type=getIntent().getStringExtra("type");
        if (type.equals("order")){
            product = (Product) getIntent().getSerializableExtra("product");
        }
        if (type.equals("product")){
            product = (Product) getIntent().getSerializableExtra("product");
            v1 = getIntent().getStringExtra("v1");
            v2 =  getIntent().getStringExtra("v2");
            v3 = getIntent().getStringExtra("v3");
            pop_count= getIntent().getIntExtra("count",1);
        }
    }

    protected void findViews() {
        rightimageView = (ImageView) findViewById(R.id.rightimageView);
        blakimageView = ((ImageView) findViewById(R.id.blakimageView));
        blakimageView.setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView) findViewById(R.id.head_title)).setText("交易详情");

        et_trade_note= (EditText) findViewById(R.id.et_trade_note);
        tv_truename= (TextView) findViewById(R.id.tv_truename);
        tv_phone= (TextView) findViewById(R.id.tv_phone);
        tv_address= (TextView) findViewById(R.id.tv_address);
        tv_trade_youhui= (TextView) findViewById(R.id.tv_trade_youhui);

        tv_trade_title= (TextView) findViewById(R.id.tv_trade_title);
        tv_trade_price= (TextView) findViewById(R.id.tv_trade_price);
        tv_trade_count= (TextView) findViewById(R.id.tv_trade_count);
        iv_trade_image= (ImageView) findViewById(R.id.iv_trade_image);

        tv_trade_color= (TextView) findViewById(R.id.tv_trade_color);
        tv_trade_size= (TextView) findViewById(R.id.tv_trade_size);
        tv_trade_notknow= (TextView) findViewById(R.id.tv_trade_notknow);
        tv_trade_amount= (TextView) findViewById(R.id.tv_trade_amount);

        rb_trade_zhi= (RadioButton) findViewById(R.id.rb_trade_zhi);
        rb_trade_yu= (RadioButton) findViewById(R.id.rb_trade_yu);
        rb_trade_zhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_trade_yu.setChecked(false);
            }
        });
        rb_trade_yu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_trade_zhi.setChecked(false);
            }
        });

        ll_test1= (LinearLayout) findViewById(R.id.ll_test1);
        ll_test1.setOnClickListener(this);
        tv_trade_youhui.setOnClickListener(this);
    }
    protected void DataTask(){
        if (getAddressSharedPreference().getName().equals("")){
            tv_truename.setVisibility(View.GONE);
            tv_phone.setText("点击添加地址");
            tv_address.setVisibility(View.GONE);
        }else {
            tv_truename.setText(getAddressSharedPreference().getName().toString());
            tv_phone.setText(getAddressSharedPreference().getPhone().toString());
            tv_address.setText(getAddressSharedPreference().getAddress().toString());
        }
        tv_trade_title.setText(product.getTitle());
        tv_trade_price.setText("单价:¥ "+String.valueOf(product.getPrice()));
        tv_trade_count.setText("× "+String.valueOf(pop_count));
        if (!v1.equals("")){
            tv_trade_color.setText(product.getN1()+":"+v1);
        }else {
            tv_trade_color.setVisibility(View.GONE);
        }
        if (!v2.equals("")){
            tv_trade_size.setText(product.getN2()+":"+v2);
        }else {
            tv_trade_size.setVisibility(View.GONE);
        }
        if (!v3.equals("")){
            tv_trade_notknow.setText(product.getN3()+":"+v3);
        }else {
            tv_trade_notknow.setVisibility(View.GONE);
        }
        String carPath= product.getThumb();
        if(null!=carPath) {
            Picasso.with(context).load(carPath).into(iv_trade_image);
        }
        else{
            iv_trade_image.setImageResource(R.mipmap.ic_phone);
        }
        if (type.equals("order")){
            if (!product.getNote().equals(""))
                et_trade_note.setText(product.getNote());
        }
        amount=product.getPrice()*pop_count;
        tv_trade_amount.setText(String.valueOf(amount - cost));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_test1:
                Intent intent=new Intent();
                intent.setClass(TransactionActivity.this,AddressActivity.class);
                intent.putExtra("type","product");
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_trade_youhui:
                Intent intent2=new Intent();
                intent2.setClass(TransactionActivity.this,YouhuiActivity.class);
                intent2.putExtra("type","youhui");
                intent2.putExtra("term",product.getPrice());
                startActivityForResult(intent2, 3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        address= (Address) data.getSerializableExtra("address");
        coupon= (Coupon) data.getSerializableExtra("youhui");
        if(resultCode==2)
        {
            if(requestCode==1){
                tv_truename.setText(address.getName());
                tv_phone.setText(address.getPhone());
                tv_address.setText(address.getAreaname()+address.getAddress());
                tv_truename.setVisibility(View.VISIBLE);
                tv_address.setVisibility(View.VISIBLE);
            }
        }
        if(resultCode==4)
        {
            if(requestCode==3){
                cost=coupon.getPromoPrice();
                tv_trade_youhui.setText("已使用抵用券 抵用¥"+cost);
                tv_trade_amount.setText(String.valueOf(amount - cost));
            }
        }
    }
    public void pay(View v) {
        if (rb_trade_zhi.isChecked()){
            DoForAlipay();
        }
        if (rb_trade_yu.isChecked()){
            if (getMemberSharedPreference().getMoney()>=amount){
                if (type.equals("order"))
                    PayFromOrder();
                if (type.equals("product"))
                    PayFromProduct();
            }else {
                Toast.makeText(context,"余额不足",Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void PayFromProduct() {
        new TradeNoTask(context,urlGetTrade,product).execute();
    }

    private void PayFromOrder() {
        Map<String,String> map=new HashMap<>();
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        map.put("money", String.valueOf(amount));
        map.put("tradeNo",product.getTrade_no());
        new MoneyPayTask(context,map,urlYu).execute();
        Intent intent=new Intent();
        intent.setClass(TransactionActivity.this,MyOderActivity.class);
        intent.putExtra("type","orderPay");
        startActivity(intent);
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
                    map.put("number", String.valueOf(pop_count));
                    map.put("amount", String.valueOf(product.getPrice()*pop_count));
                    map.put("fee", String.valueOf(0));
                    map.put("feeName","");
                    map.put("buyerName", getAddressSharedPreference().getName());
                    map.put("buyerAddress",getAddressSharedPreference().getAreaname()+getAddressSharedPreference().getAddress());
                    map.put("buyerPostcode",getAddressSharedPreference().getPostcode());
                    map.put("buyerPhone",getAddressSharedPreference().getPhone());
                    map.put("buyerMobile","");
                    map.put("addTime", String.valueOf(0));
                    map.put("tradeNo", result);
                    String note="";
                    if (!v1.equals("")){
                        note=note+product.getN1()+":"+v1+" ";
                    }
                    if (!v2.equals("")){
                        note=note+product.getN2()+":"+v2+" ";
                    }
                    if (!v3.equals("")){
                        note=note+product.getN3()+":"+v3;
                    }
                    map.put("note", note);
                    new EditAddressTask(context,map,urlSave).execute();
                    if (type.equals("order"))
                        OrderStatus(result);
                    if (type.equals("product"))
                        ProductStatus(result);
                }
            }
        }

        private void ProductStatus(String result) {
            Map<String,String> map=new HashMap<>();
            map.put("tradeNo",result);
            map.put("status", String.valueOf(2));
            new OrderStatusTask(context,urlOrderStatus,map).execute();
            Intent intent=new Intent();
            intent.setClass(context,MyOderActivity.class);
            startActivity(intent);
        }

        private void OrderStatus(String result) {
            Map<String,String> map=new HashMap<>();
            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            map.put("money", String.valueOf(amount));
            map.put("tradeNo",result);
            new MoneyPayTask(context,map,urlYu).execute();
            Intent intent=new Intent();
            intent.setClass(TransactionActivity.this,MyOderActivity.class);
            intent.putExtra("type","orderPay");
            startActivity(intent);
        }

    }
    private void DoForAlipay() {
        String orderInfo = getOrderInfo("蓝车世界", "该测试商品的详细描述", String.valueOf(amount));//String.valueOf(amount)

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(TransactionActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private String getOrderInfo(String subject, String body, String price) {
        Log.i("susu","order");
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }
    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        Log.i("susu","out");
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
