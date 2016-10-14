package aizulove.com.fxxt.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Information;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

public class QbxqActivity extends BaseActivity {


    private  Information information;
    private  TextView title;
    private  TextView source;
    private TextView creatTime;
    private  ImageView img;
    private TextView description;
    private  TextView visit;
    private DisplayImageOptions options;
    public void setContentViews(){
        View convertView = mInflater.inflate(R.layout.activity_qipa_xq, null);
        layoutContent.addView(convertView);
        initData();
        findViews();
        DataTask();
        HiddenMeun();
    }



    protected void findViews() {
       ((TextView)findViewById(R.id.head_title)).setText("奇葩详情");
        title=(TextView)findViewById(R.id.title);
        source=(TextView)findViewById(R.id.source);
        creatTime=(TextView)findViewById(R.id.creatTime);
        img=(ImageView)findViewById(R.id.img);
        description=(TextView)findViewById(R.id.description);
        visit=(TextView)findViewById(R.id.visit);
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
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        title.setText(information.getCreatName());
        source.setText("来源:"+information.getSource());
        creatTime.setText("日期:" + new SimpleDateFormat("yyyy-MM-dd").format(information.getCreatTime()));
        description.setText(information.getDescription());
        visit.setText(String.valueOf(information.getVisit()));
        String imgpasth= VariablesOfUrl.imagePath+"/file/information/"+information.getImg();//activity
        if(null!=imgpasth) {

            ImageLoader.getInstance().displayImage(imgpasth,img, options, null);
            //Picasso.with(context).load(imgpasth).into(img);
        }else{
            img.setImageResource(R.mipmap.qishou_xq);
        }
        ViewGroup.LayoutParams margin = new ViewGroup.LayoutParams(img.getLayoutParams());
        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(margin);
        layoutParams.height = width;//设置图片的高度
        //layoutParams.width = width; //设置图片的宽度
        img.setLayoutParams(layoutParams);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void initData() {
        information = (Information) getIntent().getSerializableExtra("information");
    }
}
