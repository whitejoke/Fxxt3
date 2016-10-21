package aizulove.com.fxxt.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.CommentAdapter;
import aizulove.com.fxxt.adapter.PageAdapter;
import aizulove.com.fxxt.modle.entity.Pinglun;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.EditAddressTask;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.ToastSingle;

public class ProductActivity extends BaseActivity {

    private TextView title,price,amount,brand,yuanjia ,v1,v2,v3,n1,n2,n3,title2,brand2;
    private TextView tv_pop_tile,tv_pop_price,tv_count;
    private WebView webView;

    private LinearLayout ll_size,ll_color,ll_notknow,ll_rg_first;
    private ViewPager vp_ad;
    private ImageView[] pointViews_ad;
    private ImageView imageView;
    private ViewGroup pointGroup_ad;
    private List<View> adViews=new ArrayList<View>();
    Product product=new Product();
    private RadioGroup radioGroup,rg_pop_color,rg_pop_size,rg_pop_notknow;
    private LinearLayout layout,ll_v1,ll_v2,ll_v3,ll_comment;
    private String urlAdd=VariablesOfUrl.SAVEMALLORDER;

    private int count=0;
    protected SharedPreferences sharedPreferences;
    private RadioButton radioButton,rb_pop_color,rb_pop_size;
    private DisplayImageOptions options;

    private Button btn_add,btn_buy,btn_left,btn_right,btn_recognize;
    private int pop_count=1;
    private double pop_amount;
    private String url=VariablesOfUrl.ADDCART;
    private String v1String="",v2String="",v3String="";
    private String[] temp,temp3,temp2;
    private ListView lv_bottom_comment;
    private CommentAdapter adapter;
    private List<Pinglun> listMessage=new ArrayList<Pinglun>();

    final String data = "<p class=\"MsoNormal\" style=\"text-align: center\"><b><span style=\"font-family: 宋体; font-size: 14pt\">上海凤凰自行车新品 酷速866</span></b></p> <p style=\"text-align: center\"><img width=\"750\" height=\"500\" alt=\"TB2VwQ3cFXXXXazXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093139941.jpg\" /><br /> <img width=\"800\" height=\"600\" alt=\"未标题1-1\" src=\"http://www.laiworld.com/file/upload/201603/28/100547571.png\" /><br /> <img width=\"790\" height=\"56\" alt=\"未标题-1\" src=\"http://www.laiworld.com/file/upload/201603/28/100614671.png\" /><br /> <strong>消光蓝黄橙<br /> <img width=\"750\" height=\"485\" alt=\"TB2KV.ZcFXXXXbeXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093217761.jpg\" /></strong></p> <p style=\"text-align: center\"> <strong>白红黑</strong><br /> <img width=\"750\" height=\"493\" alt=\"TB2E5.VcFXXXXbRXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093232101.jpg\" /></p> <p style=\"text-align: center\"><strong>白绿</strong><br /> <img width=\"750\" height=\"490\" alt=\"TB2LKoQcFXXXXX9XpXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093242591.jpg\" /></p> <p style=\"text-align: center\"><strong>亮黑蓝橙</strong><br /> <img width=\"750\" align=\"absMiddle\" height=\"482\" alt=\"TB26rsYcFXXXXboXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093312541.jpg\" /></p> <div style=\"text-align: center\"><strong>黑黄蓝</strong><br /> <img width=\"750\" align=\"absMiddle\" height=\"505\" alt=\"TB26cZZcFXXXXbcXXXXXXXXXXXX_!!801328478\" style=\"text-indent: 2em\" src=\"http://www.laiworld.com/file/upload/201604/22/093332701.jpg\" /><br /> <img width=\"790\" height=\"132\" alt=\"未标题-2\" src=\"http://www.laiworld.com/file/upload/201603/28/100518481.png\" /><br /> <img width=\"750\" height=\"750\" alt=\"TB2SbcRcFXXXXXoXpXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093429571.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2TLA6cFXXXXXWXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093434381.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2keZVcFXXXXbPXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093443411.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2nToWcFXXXXbjXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093449661.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2Q0w5cFXXXXabXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093454941.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2I9.6cFXXXXXTXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093458371.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2XJZ9cFXXXXXeXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093504651.jpg\" /><img width=\"750\" height=\"750\" alt=\"TB2ttMScFXXXXcxXXXXXXXXXXXX_!!801328478\" src=\"http://www.laiworld.com/file/upload/201604/22/093511911.jpg\" /><br />  </div>";
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_product_info, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        setListenerForView();
       // setListenerForButton();
        DataTaskTest();
        HiddenMeun();
    }

    private void setListenerForButton(final Product result) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data().equals("")) {
                    Intent intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    String type="add";
                    initBottomLayout(result,type);
                }

            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data().equals("")) {
                    Intent intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    String type="buy";
                    initBottomLayout(result,type);
                }

            }
        });
    }

    private void initBottomLayout(final Product result, final String type) {
        final Dialog dialog = new Dialog(ProductActivity.this, R.style.MyDialogStyleBottom);
        final View dialogView = LayoutInflater.from(ProductActivity.this).inflate(R.layout.car_dialog,null);
        tv_pop_tile= (TextView) dialogView.findViewById(R.id.tv_pop_title);
        tv_pop_price= (TextView) dialogView.findViewById(R.id.tv_pop_price);
        tv_count= (TextView) dialogView.findViewById(R.id.tv_count);
        btn_left= (Button) dialogView.findViewById(R.id.btn_left);
        btn_right= (Button) dialogView.findViewById(R.id.btn_right);
        rg_pop_color= (RadioGroup) dialogView.findViewById(R.id.rg_pop_color);
        rg_pop_size= (RadioGroup) dialogView.findViewById(R.id.rg_pop_size);
        rg_pop_notknow= (RadioGroup) dialogView.findViewById(R.id.rg_pop_notknow);
        View layout=LayoutInflater.from(context).inflate(R.layout.pop_item,null);
        rb_pop_color= (RadioButton) layout.findViewById(R.id.rb_pop_item);
        rb_pop_size= (RadioButton) layout.findViewById(R.id.rb_pop_item);
        ll_color= (LinearLayout) dialogView.findViewById(R.id.ll_color);
        ll_size= (LinearLayout) dialogView.findViewById(R.id.ll_size);
        ll_notknow= (LinearLayout) dialogView.findViewById(R.id.ll_notknow);
        btn_recognize= (Button) dialogView.findViewById(R.id.btn_recognize);
        tv_pop_tile.setText(title.getText().toString());
        //ll_rg_first= (LinearLayout) dialogView.findViewById(R.id.ll_rg_first);

        final RelativeLayout rl_layout= (RelativeLayout) dialogView.findViewById(R.id.rl_layout);
        LinearLayout pop_layout= (LinearLayout) dialogView.findViewById(R.id.pop_layout);

        //获得dialog的window窗口
        Window window = dialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.PopupAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog.setContentView(dialogView);
        dialog.show();
        rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        pop_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop_count<2){
                    pop_count=1;
                    tv_count.setText(String.valueOf(pop_count));
                    pop_amount=Double.parseDouble(price.getText().toString())*pop_count;
                    tv_pop_price.setText("总价:¥ "+String.valueOf(pop_amount));
                }else {
                    pop_count--;
                    tv_count.setText(String.valueOf(pop_count));
                    pop_amount=Double.parseDouble(price.getText().toString())*pop_count;
                    tv_pop_price.setText("总价:¥ "+String.valueOf(pop_amount));
                }
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop_count<10){
                    pop_count++;
                    tv_count.setText(String.valueOf(pop_count));
                    pop_amount=Double.parseDouble(price.getText().toString())*pop_count;
                    tv_pop_price.setText("总价:¥ "+String.valueOf(pop_amount));
                }else {
                    pop_count=10;
                    tv_count.setText(String.valueOf(pop_count));
                    pop_amount=Double.parseDouble(price.getText().toString())*pop_count;
                    tv_pop_price.setText("总价:¥ "+String.valueOf(pop_amount));
                }
            }
        });
        pop_amount=Double.parseDouble(price.getText().toString())*pop_count;
        tv_pop_price.setText("总价:¥ "+String.valueOf(pop_amount));
        tv_count.setText(String.valueOf(pop_count));

        temp=result.getV1().toString().split("\\|");
        temp2=result.getV2().toString().split("\\|");
        temp3=result.getV3().toString().split("\\|");
        if (!result.getN1().equals("")){
            ll_color.setVisibility(View.VISIBLE);
            v1String=temp[0];
            for (int i=0;i<temp.length;i++){
                RadioButton tempButton = new RadioButton(context);
                tempButton.setBackgroundResource(R.drawable.pop_background);	// 设置RadioButton的背景图片
                tempButton.setButtonDrawable(android.R.color.transparent);			// 设置按钮的样式
                tempButton.setTextColor(context.getResources().getColorStateList(R.color.pop_textcolor));
                tempButton.setPadding(5, 5, 5, 5);           		// 设置文字距离按钮四周的距离
                tempButton.setText(temp[i]);
                //rg_pop_color.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                rg_pop_color.addView(tempButton,LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i==0){
                    //rg_pop_color.check(tempButton.getId());
                    rg_pop_color.check(tempButton.getId());
                }
            }
            rg_pop_color.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton tempButton = (RadioButton) dialogView.findViewById(checkedId);
                    v1String = tempButton.getText().toString();
                }
            });
            //ll_rg_first.addView(radioGroup);
        }
        if (!result.getN2().equals("")){
            ll_size.setVisibility(View.VISIBLE);
            v2String=temp2[0];
            for (int i=0;i<temp2.length;i++){
                //Log.i("susu",temp2[i]);
                RadioButton tempButton = new RadioButton(context);
                tempButton.setBackgroundResource(R.drawable.pop_background);	// 设置RadioButton的背景图片
                tempButton.setButtonDrawable(android.R.color.transparent);			// 设置按钮的样式
                tempButton.setTextColor(context.getResources().getColorStateList(R.color.pop_textcolor));
                tempButton.setPadding(5, 5, 5, 5);           		// 设置文字距离按钮四周的距离
                tempButton.setText(temp2[i]);
                rg_pop_size.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i==0){
                    rg_pop_size.check(tempButton.getId());
                }
            }
            rg_pop_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton tempButton = (RadioButton)dialogView.findViewById(checkedId);
                    if (tempButton.isChecked()){
                        v2String=tempButton.getText().toString();
                    }
                }
            });
        }
        if (!result.getN3().equals("")){
            ll_notknow.setVisibility(View.VISIBLE);
            v3String=temp3[0];
            for (int i=0;i<temp3.length;i++){
                RadioButton tempButton = new RadioButton(context);
                tempButton.setBackgroundResource(R.drawable.pop_background);	// 设置RadioButton的背景图片
                tempButton.setButtonDrawable(android.R.color.transparent);			// 设置按钮的样式
                tempButton.setTextColor(context.getResources().getColorStateList(R.color.pop_textcolor));
                tempButton.setPadding(5, 5, 5, 5);           		// 设置文字距离按钮四周的距离
                tempButton.setText(temp3[i]);
                rg_pop_notknow.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (i==0){
                    rg_pop_notknow.check(tempButton.getId());
                }
            }
            rg_pop_notknow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton tempButton = (RadioButton)dialogView.findViewById(checkedId);
                    v3String=tempButton.getText().toString();
                }
            });
        }
        btn_recognize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("add")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                    builder.setMessage("确认加入购物车吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AddToOrder(result);
//                            Intent intent=new Intent();
//                            intent.setClass(context,MyCartActivity.class);
//                            intent.putExtra("v1",v1String);
//                            intent.putExtra("v2",v2String);
//                            intent.putExtra("v3",v3String);
//                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }else if (type.equals("buy")){
                    Intent intent=new Intent();
                    intent.setClass(ProductActivity.this,TransactionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("product",result);
                    bundle.putInt("count",pop_count);
                    bundle.putString("v1",v1String);
                    bundle.putString("v2",v2String);
                    bundle.putString("v3",v3String);
                    bundle.putString("type","product");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
    }

    private void AddToOrder(Product result) {
        int s1 = 0,s2=0,s3=0;
        for (int i=0;i<temp.length;i++){
            if (temp[i].equals(v1String)){
                s1=i;
                break;
            }
        }
        for (int i=0;i<temp2.length;i++){
            if (temp2[i].equals(v2String)){
                s2=i;
                break;
            }
        }
        for (int i=0;i<temp3.length;i++){
            if (temp3[i].equals(v3String)){
                s3=i;
                break;
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        map.put("mallId", String.valueOf(result.getItemid()));
        map.put("v1", String.valueOf(s1));
        map.put("v2", String.valueOf(s2));
        map.put("v3", String.valueOf(s3));
        map.put("i", String.valueOf(pop_count));
        new EditAddressTask(getApplicationContext(),map,url).execute();
    }

    private void setListenerForView() {
        if (radioButton.getText().toString().equals("商品信息")){
            layout.setVisibility(View.VISIBLE);
            count++;
        }
        if (radioButton.getText().toString().equals("商品评价")){
            ll_comment.setVisibility(View.VISIBLE);
            count++;
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (count%2==0){
                    layout.setVisibility(View.VISIBLE);
                    ll_comment.setVisibility(View.GONE);
                    count--;
                    //Log.d("susu", String.valueOf(radioButton.getId()));
                }else{
                    layout.setVisibility(View.GONE);
                    ll_comment.setVisibility(View.VISIBLE);
                    count++;
                }
            }
        });
    }

    private void initData() {

        product = (Product) getIntent().getSerializableExtra("product");
        //type= (String) getIntent().getSerializableExtra("type");
    }
    protected void findViews() {
        title=(TextView)findViewById(R.id.P_title);
        title2=(TextView)findViewById(R.id.P_title2);
        price= (TextView) findViewById(R.id.P_price);
        amount= (TextView) findViewById(R.id.P_amount);
        brand= (TextView) findViewById(R.id.P_brand);
        brand2= (TextView) findViewById(R.id.P_brand2);
        yuanjia= (TextView) findViewById(R.id.P_yuanjia);
        v1= (TextView) findViewById(R.id.P_V1);
        v2= (TextView) findViewById(R.id.P_V2);
        v3= (TextView) findViewById(R.id.P_V2);
        n1= (TextView) findViewById(R.id.P_N1);
        n2= (TextView) findViewById(R.id.P_N2);
        n3= (TextView) findViewById(R.id.P_N3);
        webView= (WebView) findViewById(R.id.wb_show);
        ll_v1= (LinearLayout) findViewById(R.id.ll_v1);
        ll_v2= (LinearLayout) findViewById(R.id.ll_v2);
        ll_v3= (LinearLayout) findViewById(R.id.ll_v3);

        lv_bottom_comment= (ListView) findViewById(R.id.lv_bottom_comment);
        vp_ad=(android.support.v4.view.ViewPager)findViewById(R.id.p_ad);
        layout= (LinearLayout) findViewById(R.id.ll_bottom);
        ll_comment= (LinearLayout) findViewById(R.id.ll_bottom_comment);

        radioGroup= (RadioGroup) findViewById(R.id.myRadioGroup);
        radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());

        btn_add= (Button) findViewById(R.id.btn_add);
        btn_buy= (Button) findViewById(R.id.btn_buy);

        pointGroup_ad= (ViewGroup) findViewById(R.id.ll_point_p_ad);
        ((TextView)findViewById(R.id.head_title)).setText("商品详情");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     *
     * 逻辑处理方法
     */


    protected void DataTaskTest(){
        adapter=new CommentAdapter(context,listMessage);
        lv_bottom_comment.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("mallId", product.getItemid().toString());
        new ProductDetailTask(context,map).execute();
        new PinglunTask(context,listMessage,adapter,map).execute();
    }
    private class CategroyWebChromeViewClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title){
            super.onReceivedTitle(view, title);
        }
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    public class PinglunTask extends AsyncTask<Void,Void,List<Pinglun>> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private List<Pinglun> listMessage;
        private CommentAdapter adapter;
        private Map<String, String> map;

        public PinglunTask(Context context, List<Pinglun> listMessage, CommentAdapter adapter, Map<String, String> map) {
            this.context = context;
            this.listMessage = listMessage;
            this.adapter = adapter;
            this.map = map;
        }

        @Override
        protected List<Pinglun> doInBackground(Void... params) {
            List<Pinglun> result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL +VariablesOfUrl.GETCOMMENTLISTBYMALLID;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getCommentListByMallId(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Pinglun> result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        listMessage.clear();
                        listMessage.addAll(result);
                        adapter.notifyDataSetChanged();
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

    private class ProductDetailTask extends AsyncTask<Void,Void,Product>{
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String,String> map;
        public ProductDetailTask(Context context, Map<String, String> map) {
            this.context=context;
            this.map=map;
        }

        @Override
        protected Product doInBackground(Void... params) {
            Product result=null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETMALLBYMALLID;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    Log.i("susu", String.valueOf(jsonStr));
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getJsonCuXiaoById(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Product result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        adViews.clear();
                        title.setText(result.getTitle());
                        title2.setText(result.getTitle());
                        amount.setText("库存:"+String.valueOf(result.getAmount()));
                        brand.setText("品牌:"+result.getBrand());
                        brand2.setText(result.getBrand());
                        if (result.getN1().equals("")){
                            ll_v1.setVisibility(View.GONE);
                        }else {
                            n1.setText(result.getN1());
                            v1.setText(result.getV1());
                        }
                        if (result.getN2().equals("")){
                            ll_v2.setVisibility(View.GONE);
                        }else {
                            n2.setText(result.getN2());
                            v2.setText(result.getV2());
                        }
                        if (result.getN3().equals("")){
                            ll_v3.setVisibility(View.GONE);
                        }else {
                            v3.setText(result.getN3());
                            v3.setText(result.getV3());
                        }
                        if (result.getYuanjia()!=0) {
                            //Log.i("susu", String.valueOf(result.getYuanjia()));
                            yuanjia.setText("¥ "+String.valueOf(result.getYuanjia()));
                        }else {
                            yuanjia.setVisibility(View.INVISIBLE);
                        }
                        price.setText(String.valueOf(result.getPrice()));
                        String path= result.getThumb();//
                        String path1= result.getThumb1();//
                        String path2= result.getThumb2();//
                        //Log.i("susu",path2.toString());
                        if (null != path) {
//                            ImageLoader.getInstance().displayImage(path,thumb, options, null);
//                            Picasso.with(context).load(path).into(thumb);
                            ImageView iv = new ImageView(ProductActivity.this);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            ImageLoader.getInstance().displayImage(path, iv, options, null);
                            adViews.add(iv);
                        }
                        if (null!=path1){
                            ImageView iv = new ImageView(ProductActivity.this);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            ImageLoader.getInstance().displayImage(path1,iv, options, null);
                            adViews.add(iv);

                        }
                        if (null!=path2&&!path2.equals("")){
                            ImageView iv = new ImageView(ProductActivity.this);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            ImageLoader.getInstance().displayImage(path2,iv, options, null);
                            adViews.add(iv);
                        }
                        //webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                        //webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                        webView.getSettings().setSupportZoom(false);
                        webView.getSettings().setBuiltInZoomControls(false);
                        webView.getSettings().setUseWideViewPort(true);
                        webView.getSettings().setDisplayZoomControls(false);
                        webView.getSettings().setLoadWithOverviewMode(true);
                        webView.setEnabled(false);
                        webView.setVerticalScrollBarEnabled(false);
                        webView.setVerticalScrollbarOverlay(false);
                        webView.setHorizontalScrollBarEnabled(false);
                        webView.setHorizontalScrollbarOverlay(false);
                        webView.setWebChromeClient(new CategroyWebChromeViewClient());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
                        } else {
                            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                        }
                        webView.loadDataWithBaseURL(null,getHtmlData(result.getContent()),"text/html","utf-8",null);


                        pointViews_ad = new ImageView[adViews.size()];

                        pointGroup_ad.removeAllViews();

                        for (int i = 0; i < adViews.size(); i++) {
                            imageView = new ImageView(ProductActivity.this);
                            android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(2, 0, 2, 0);
                            imageView.setLayoutParams(params);
                            pointViews_ad[i] = imageView;
                            if (i == 0){
                                //默认选中第一张图片,加入焦点
                                pointViews_ad[i].setBackgroundResource(R.mipmap.ic_dot_focused_3);
                            }else{
                                pointViews_ad[i].setBackgroundResource(R.mipmap.ic_point);
                            }
                            //把每一个导航小圆点都加入到ViewGroup中
                            pointGroup_ad.addView(pointViews_ad[i]);
                        }
                        vp_ad.setAdapter(new PageAdapter(adViews));
                        vp_ad.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
                            @Override
                            public void onPageSelected(int arg0){
                                pointViews_ad[arg0].setBackgroundResource(R.mipmap.ic_dot_focused_3);
                                for (int i = 0; i < pointViews_ad.length; i++) {
                                    if (arg0 != i) {
                                        pointViews_ad[i].setBackgroundResource(R.mipmap.ic_point);
                                    }
                                }
                                vp_ad.setCurrentItem(arg0);
                            }
                            @Override
                            public void onPageScrolled(int arg0, float arg1, int arg2){

                            }
                            @Override
                            public void onPageScrollStateChanged(int arg0){

                            }
                        });
                        setListenerForButton(result);
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

}
