package aizulove.com.fxxt.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Activity;
import aizulove.com.fxxt.task.AddActivieTask;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class ActivieDetailActivity extends BaseActivity implements View.OnClickListener{


    private Activity activity;
    private  TextView title;
    private  TextView userName;
    private TextView createTime;
    private ImageView img;
    private  TextView content;
    private TextView name;
    private TextView date;
    private TextView address;
    private TextView lishi;
    private Button qd;
    private DisplayImageOptions options;
    private int i,j;

    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_active_xq, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }



    protected void findViews() {
       ((TextView)findViewById(R.id.head_title)).setText("活动详情");
        title=(TextView)findViewById(R.id.title);
        userName=(TextView)findViewById(R.id.userName);
        createTime=(TextView)findViewById(R.id.createTime);
        img=(ImageView)findViewById(R.id.img);
        content=(TextView)findViewById(R.id.content);
        name=(TextView)findViewById(R.id.name);
        date=(TextView)findViewById(R.id.date);
        address=(TextView)findViewById(R.id.address);
        lishi=(TextView)findViewById(R.id.lishi);
        qd=(Button)findViewById(R.id.qd);
        qd.setOnClickListener(this);
        blakimageView= ((ImageView)findViewById(R.id.blakimageView));
        blakimageView .setVisibility(View.VISIBLE);
        blakimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
    }

    protected  void setSelecttab() {
        SELECTTAB=2;
    }
    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        title.setText(activity.getTitle());
        userName.setText("来源:"+activity.getUserName());
        createTime.setText(dateFormat.format(activity.getCreateTime()));
        content.setText(activity.getContent());
        name.setText(activity.getTitle());
        date.setText(dateFormat.format(activity.getStartdate())+" "+dateFormat.format(activity.getEnddate()));
        address.setText(activity.getAddress());
        lishi.setText(activity.getLishi()+"天");
        String imgpath= VariablesOfUrl.imagePath+"/file/activity/"+activity.getImg();//
        if(null!=imgpath) {
            Picasso.with(context).load(imgpath).into(img);
            //ImageLoader.getInstance().displayImage(imgpath, img, options, null);
        }else{
            img.setImageResource(R.mipmap.active_img);
        }
        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String    date    =    sDateFormat.format(new java.util.Date());
        String last=getTime(new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss").format(activity.getEnddate()));
        String tolast=getTime(date);
        //Log.i("susu",last+"+++++++++++++"+tolast);
        i= Integer.parseInt(last);
        j= Integer.parseInt(tolast);
        if (i<j){
            qd.setText("活动结束");
            qd.setBackgroundResource(R.color.info);
        }
    }
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    private void initData() {
        activity = (Activity) getIntent().getSerializableExtra("activity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.qd:
                if (i>j){
                    View layout=dialog(R.layout.activity_addactive);
                    Button qd=(Button) layout.findViewById(R.id.save);
                    final EditText phone=(EditText)layout.findViewById(R.id.phone);
                    final EditText name=(EditText)layout.findViewById(R.id.name);
                    phone.setText(getMemberSharedPreference().getMobile());
                    name.setText(getMemberSharedPreference().getTruename());
                    qd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("name",name.getText().toString());
                            map.put("phone",phone.getText().toString());
                            map.put("userId", String.valueOf(getMemberSharedPreference().getUserid()));
                            map.put("activityId", activity.getId().toString());
                            new AddActivieTask(ActivieDetailActivity.this, map).execute();
                            dialog.dismiss();
                        }
                    });
                }
                break;
        }
    }
}
