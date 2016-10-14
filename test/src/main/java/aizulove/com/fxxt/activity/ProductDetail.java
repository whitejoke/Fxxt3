package aizulove.com.fxxt.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/7/26.
 */
public class ProductDetail extends BaseActivity{
    private TextView title,price,amount,brand,yuanjia,v1;
    private String thumb;
    private String thumb1;
    private String thumb2;
    Product product=new Product();
    private DisplayImageOptions options;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_product_info, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }



    protected void findViews() {
        title=(TextView)findViewById(R.id.P_title);
        price= (TextView) findViewById(R.id.P_price);
        amount= (TextView) findViewById(R.id.P_amount);
        brand= (TextView) findViewById(R.id.P_brand);
        //price= (TextView) findViewById(R.id.P_price);
//        img=(ImageView)findViewById(R.id.img);
//        posts=(LinearLayout)findViewById(R.id.posts);
//        jlbgz=(ImageView)findViewById(R.id.jlbgz);
//        jlbgz.setOnClickListener(this);
        ((TextView)findViewById(R.id.head_title)).setText("商品详情");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        rightimageView=(ImageView)findViewById(R.id.rightimageView);
//        rightimageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_add));
//        rightimageView.setOnClickListener(this);
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("logoId", product.getItemid().toString());
        map.put("userId","13");
        map.put("num","10");
        map.put("page","1");
        new JulebuDetailTask(context,map).execute();
    }

    private void initData() {
        product = (Product) getIntent().getSerializableExtra("product");
    }


    class JulebuDetailTask extends AsyncTask<Void, Void,Logo> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag = true;
        private Map<String, String> map;

        public JulebuDetailTask(Context context, Map<String, String> map) {
            super();
            this.map = map;
            this.context = context;
        }

        @Override
        protected Logo doInBackground(Void... params) {
            Logo result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETPOSTLISTBYLOGOID;
                    System.out.println("url==" + url);
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getPostListByLogoId(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }
    }

}
