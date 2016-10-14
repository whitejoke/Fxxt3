package aizulove.com.fxxt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.adapter.ContentAdapter;
import aizulove.com.fxxt.modle.entity.Comment;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.task.CommentTask;
import aizulove.com.fxxt.task.ZanPostTask;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.JsonParserFactory;
import aizulove.com.fxxt.utils.NetWork;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;
import aizulove.com.fxxt.view.ToastSingle;

public class FindDetailActivity extends BaseActivity implements View.OnClickListener{

    private ImageView avatarUrl;
    private TextView title;
    private TextView content;
    private ImageView img;
    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private TextView creatTime;
    private TextView logozts;
    private TextView logodianz;
    private ImageView addzan;
    private ImageView comment;
    private LinearLayout commentlist;
    private  View line;
    private Post post;
    private DisplayImageOptions options;
    private MaterialRefreshLayout mlayout;
    private GridListView gridListView;
    private List<Comment> list=new ArrayList<>();
    private ContentAdapter adapter;
    private String head;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_qishiquandetail, null);
        layoutContent.addView(convertView);
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
        initData();
        findViews();
        DataTaskTest();
        initRefresh();
        HiddenMeun();
    }
    private void initRefresh() {
        //materialRefreshLayout.setLoadMore(true);
        mlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("postId",post.getId().toString());
                new ContentTask(context,map,list,adapter).execute();
            }

        });
    }



    protected void findViews() {
        gridListView= (GridListView) findViewById(R.id.gdVllistview);
        mlayout= (MaterialRefreshLayout) findViewById(R.id.srl);
        line=findViewById(R.id.line);
       ((TextView)findViewById(R.id.head_title)).setText(head);
       avatarUrl=(ImageView)findViewById(R.id.avatarUrl);
       title = (TextView)findViewById(R.id.title);
       content = (TextView)findViewById(R.id.content);
        img0 = (ImageView)findViewById(R.id.img0);
       img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        creatTime = (TextView)findViewById(R.id.creatTime);
        logozts = (TextView)findViewById(R.id.logozts);
        logodianz = (TextView)findViewById(R.id.logodianz);
        addzan=(ImageView)findViewById(R.id.addzan);
        comment=(ImageView)findViewById(R.id.comment);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addzan.setOnClickListener(this);
        comment.setOnClickListener(this);
    }

    /**
     *
     * 逻辑处理方法
     */
    public void DataTaskTest(){
        adapter=new ContentAdapter(context,list);
        gridListView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("postId",post.getId().toString());
        new ContentTask(context,map,list,adapter).execute();

    }
    private void initData() {
        head=getIntent().getStringExtra("title");
        post = (Post) getIntent().getSerializableExtra("post");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.addzan:
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", getMemberSharedPreference().getUsername());
            map.put("logintimes",String.valueOf(getMemberSharedPreference().getLogintimes()));
            map.put("userId", getMemberSharedPreference().getUserid()+"");
            map.put("postId", post.getId().toString());
            new ZanPostTask(this, map).execute();
            break;
            case  R.id.comment:
                Intent intent;
                if(Data().equals("")) {
                    intent = new Intent();
                    intent.setClass(context, HostActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    View layout=dialog(R.layout.activity_message);
                    Button qd=(Button) layout.findViewById(R.id.qd);
                    final EditText content=(EditText)layout.findViewById(R.id.content);
                    qd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("content",content.getText().toString());
                            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                            map.put("postId", post.getId().toString());
                            new CommentTask(FindDetailActivity.this, map).execute();
                            dialog.dismiss();
                            Map<String, String> prase = new HashMap<String, String>();
                            prase.put("postId",post.getId().toString());
                            new ContentTask(context,prase,list,adapter).execute();
                        }
                    });
                }
            break;
        }
    }
    class ContentTask extends AsyncTask<Void, Void,Post> {
        private Context context;
        private boolean judgeInternet;
        private boolean typeFlag=true;
        private  Map<String,String> map;
        private List<Comment> listMessage;
        private ContentAdapter adapter;
        public ContentTask(Context context,Map<String,String> map,List<Comment> listMessage,ContentAdapter adapter){
            super();
            this.adapter=adapter;
            this.map=map;
            this.context=context;
            this.listMessage=listMessage;
        }

        @Override
        protected Post doInBackground(Void... params) {
            Post result = null;
            judgeInternet = NetWork.checkNetWorkStatus(context);
            try {
                if (judgeInternet) {
                    String url = VariablesOfUrl.APP_BASE_URL + VariablesOfUrl.GETPOSTBYID;
                    StringBuilder jsonStr = NetWork.postStringFromUrl(url, map);
                    if (jsonStr.toString().equals("[]")) {
                        typeFlag = false;
                    }
                    result = JsonParserFactory.getPostById(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }
        @Override
        protected void onPostExecute(Post result) {
            super.onPostExecute(result);
            if (judgeInternet) {
                if (typeFlag) {// 返回有数据
                    if (result != null) {
                        List<Comment> list=result.getCommentList();
                        listMessage.clear();
                        listMessage.addAll(list);
                        adapter.notifyDataSetChanged();
                        mlayout.finishRefresh();

                        int commentCount=null==result.getCommentList()?0:result.getCommentList().size();
                        if (commentCount>0){
                            line.setVisibility(View.VISIBLE);
                        }
                        title.setText(result.getSource());
                        content.setText(result.getContent());
                        String[] imgs = result.getImg().split(",");
                        creatTime.setText(dateFormat.format(result.getCreatTime()));
                        logozts.setText(String.valueOf(result.getVisit() == null ? 0 : result.getVisit()));
                        logodianz.setText(String.valueOf(result.getZan()));
                        if (null != result.getMember().getAvatarUrl() && "http".contains(result.getMember().getAvatarUrl())) {
                            Picasso.with(context).load(post.getMember().getAvatarUrl()).into(avatarUrl);
                            //ImageLoader.getInstance().displayImage(post.getMember().getAvatarUrl(),avatarUrl, options, null);
                        } else {
                            avatarUrl.setImageResource(R.mipmap.ic_zt);
                        }

                        for (int i = 0; i < imgs.length; i++){
                            String imgpath= VariablesOfUrl.imagePath+"/file/post/"+imgs[i];//activity
                            if (i==0) {
                                if (null != imgs[i]&&!imgs[i].equals("")) {
                                    Picasso.with(context).load(imgpath).into(img0);
                                    //ImageLoader.getInstance().displayImage(imgpath,img0, options, null);
                                }else {
                                    img0.setVisibility(View.GONE);
                                }
                            }else if (i==1){
                                if (null != imgs[i]&&!imgs[i].equals("")) {
                                    Picasso.with(context).load(imgpath).into(img1);
                                    //ImageLoader.getInstance().displayImage(imgpath, img1, options, null);
                                }else {
                                    img1.setVisibility(View.GONE);
                                }
                            }else if(i==2){
                                if (null != imgs[i]&&!imgs[i].equals("")) {
                                    Picasso.with(context).load(imgpath).into(img2);
                                    //ImageLoader.getInstance().displayImage(imgpath,img2, options, null);
                                }else {
                                    img2.setVisibility(View.GONE);
                                }
                            }
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
}
