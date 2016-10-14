package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.JulbdetailAdapter;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.task.AttentionLogoTask;
import aizulove.com.fxxt.task.CancelAttentionTask;
import aizulove.com.fxxt.task.ZanLogoTask;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

public class JulbdetailActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView title;
    private  ImageView img;
    private TextView quality;
    private TextView zan;
    private Logo logo;
    private TextView content;
    private TextView logoZan;
    private ImageView jlbgz;
    private LinearLayout posts;
    private Integer isAttention;
    private View line;
    private DisplayImageOptions options;
    private GridListView listView;
    private JulbdetailAdapter adapter;
    private List<Post> listMessage = new ArrayList<Post>();
    private MaterialRefreshLayout materialRefreshLayout;
    public void setContentViews(){

        View convertView = mInflater.inflate(R.layout.activity_julebudetail, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
        initRefresh();
    }
    private void initRefresh() {
        //materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("logoId",logo.getId().toString());
                map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                map.put("num","999");
                map.put("page", "1");
                new JulebuDetailTask(context,map,listMessage,adapter,listView).execute();
            }

        });
    }


    protected void findViews() {
        materialRefreshLayout= (MaterialRefreshLayout) findViewById(R.id.srl);
        title=(TextView)findViewById(R.id.title);
        quality=(TextView)findViewById(R.id.quality);
        img=(ImageView)findViewById(R.id.img);
        zan=(TextView)findViewById(R.id.zan);
        content=(TextView)findViewById(R.id.content);
        line=(View)findViewById(R.id.line);
        logoZan=(TextView)findViewById(R.id.logozan);
        logoZan.setOnClickListener(this);
        jlbgz=(ImageView)findViewById(R.id.jlbgz);
        jlbgz.setOnClickListener(this);
       ((TextView)findViewById(R.id.head_title)).setText("俱乐部详情");
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rightimageView=(ImageView)findViewById(R.id.rightimageView);
        rightimageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_add));
        rightimageView.setVisibility(View.VISIBLE);
        rightimageView.setOnClickListener(this);
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
        listView= (GridListView) findViewById(R.id.gdVllistview);
    }

    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new JulbdetailAdapter(context,listMessage);
        listView.setAdapter(adapter);
        //new QishiJulebuDataTask(context,listMessage,adapter,listView).execute();
        Map<String, String> map = new HashMap<String, String>();
        map.put("logoId",logo.getId().toString());
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        map.put("num","999");
        map.put("page", "1");
        new JulebuDetailTask(context,map,listMessage,adapter,listView).execute();
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        logo = (Logo) getIntent().getSerializableExtra("logo");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(context,FindDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("post",(Serializable) listMessage.get(position));
        intent.putExtras(bundle);
        intent.putExtra("title","车友俱乐部");
        startActivity(intent);
    }


    class JulebuDetailTask extends AsyncTask<Void, Void,Logo> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String,String> map;
        private List<Post> listMessage;
        private JulbdetailAdapter adapter;
        private  GridListView listView;
        public JulebuDetailTask(Context context,Map<String,String> map,List<Post> listMessage,JulbdetailAdapter adapter,GridListView listView){
            super();
            this.listMessage=listMessage;
            this.adapter=adapter;
            this.listView=listView;
            this.map=map;
            this.context=context;
        }

        @Override
        protected Logo doInBackground(Void... params) {
            Logo result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETPOSTLISTBYLOGOID;
                    System.out.println("url=="+url);
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
        @Override
        protected void onPostExecute(Logo result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        List<Post> list=result.getPostList();
                        int postCount=null==list?0:list.size();
                        listMessage.clear();
                        listMessage.addAll(list);
                        adapter.notifyDataSetChanged();
                        materialRefreshLayout.finishRefresh();


                        isAttention=result.getIsAttention();
                        title.setText(result.getTitle());
                        quality.setText(String.valueOf(postCount));
                        zan.setText(String.valueOf(result.getZan()));
                        content.setText(result.getContent());
                        String logopath= VariablesOfUrl.imagePath+"/file/logo/"+result.getLogoImg();//
                        if (null != logopath) {
                            Picasso.with(context).load(logopath).into(img);
                            //ImageLoader.getInstance().displayImage(logopath,img, options, null);
                        }else {
                            img.setImageResource(R.mipmap.ic_phone);
                        }

                        if (postCount>0){
                            line.setVisibility(View.VISIBLE);
                        }

                        if(isAttention==0){
                         jlbgz.setImageResource(R.mipmap.gzjlb);
                        }else{
                         jlbgz.setImageResource(R.mipmap.jlbgz_s);
                        }
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
    public void onClick(View v){
        switch (v.getId()){
            case  R.id.logozan:
            Map<String, String> map = new HashMap<String, String>();
            map.put("username",getMemberSharedPreference().getUsername());
            map.put("logintimes",String.valueOf(getMemberSharedPreference().getLogintimes()));
            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            map.put("logoId", logo.getId().toString());
            new ZanLogoTask(this, map).execute();
            break;
            case R.id.jlbgz:
            Map<String, String> gzmap = new HashMap<String, String>();
            if (isAttention==0){
            gzmap.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            gzmap.put("logoId", logo.getId().toString());
             new AttentionLogoTask(this, gzmap).execute();
            }else{
            gzmap.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
            gzmap.put("logoId", logo.getId().toString());
            new CancelAttentionTask(this, gzmap).execute();
            }
            break;
            case R.id.rightimageView:
                Intent intent;
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    intent= new Intent();
                    intent.putExtra("logoId",logo.getId().toString());
                    intent.putExtra("type","2");
                    intent.setClass(context, FindActivity.class);
                    startActivityForResult(intent, 1001);
                }
            break;

        }

        Map<String, String> detailMap = new HashMap<String, String>();
        detailMap.put("logoId",logo.getId().toString());
        detailMap.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        detailMap.put("num", "10");
        detailMap.put("page", "1");
        new JulebuDetailTask(context,detailMap,listMessage,adapter,listView).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> map = new HashMap<String, String>();
        map.put("logoId",logo.getId().toString());
        map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
        map.put("num","999");
        map.put("page", "1");
        new JulebuDetailTask(context,map,listMessage,adapter,listView).execute();
    }

    protected  void setSelecttab(){
        this.SELECTTAB=2;
    }


}
