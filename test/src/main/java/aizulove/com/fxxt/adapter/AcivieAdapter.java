package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Activity;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by Administrator on 2016/4/29.
 */
public class AcivieAdapter extends BaseAdapter {
    private Context context;
    private List<Activity> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;


    public AcivieAdapter(Context context, List<Activity> list){
        this.context = context;
        this.list = list;
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int postion) {
        return list.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_active_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.info = (TextView) convertView.findViewById(R.id.tv_info);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String    date    =    sDateFormat.format(new java.util.Date());
        String last=getTime(new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss").format(list.get(postion).getEnddate()));
        String tolast=getTime(date);
        //Log.i("susu",last+"+++++++++++++"+tolast);
        int i= Integer.parseInt(last);
        int j= Integer.parseInt(tolast);
        if (i<j){
            holder.info.setText("活动结束");
            holder.info.setTextColor(context.getResources().getColor(R.color.info));

        }
        holder.title.setText(list.get(postion).getTitle());
        String img= VariablesOfUrl.imagePath+"/file/activity/"+list.get(postion).getImg();//
        System.out.println("img==="+img);
            if(null!=list.get(postion).getImg()) {
                Picasso.with(context).load(img).into(holder.img);
            //ImageLoader.getInstance().displayImage(img, holder.img, options, null);
        }else{
            holder.img.setImageResource(R.mipmap.active_img);
        }
        return convertView;
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

    public class ViewHolder{
        private TextView title;
        private TextView info;
        private ImageView img;
    }

}
