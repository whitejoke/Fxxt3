package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Information;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by Administrator on 2016/4/29.
 */
public class InformationAdapter extends BaseAdapter {
    private Context context;
    private List<Information> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;


    public InformationAdapter(Context context, List<Information> list){
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
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_qipa, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.visit = (TextView) convertView.findViewById(R.id.visit);
            holder.crdate = (TextView) convertView.findViewById(R.id.crdate);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            ViewGroup.LayoutParams margin = new ViewGroup.LayoutParams(holder.img.getLayoutParams());
            LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(margin);
            layoutParams.height = width;//设置图片的高度
            //layoutParams.width = width; //设置图片的宽度
            holder.img.setLayoutParams(layoutParams);
            holder.img.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.title.setText(list.get(postion).getCreatName());
        holder.description.setText(list.get(postion).getDescription());
        holder.visit.setText(String.valueOf(list.get(postion).getVisit()));
        holder.crdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getCreatTime()));
        String img= VariablesOfUrl.imagePath+"/file/information/"+list.get(postion).getImg();//activity
        if(null!=img) {

            ImageLoader.getInstance().displayImage(img, holder.img, options, null);
            //Picasso.with(context).load(img).into(holder.img);
        }else{
            holder.img.setImageResource(R.mipmap.qishou_xq);
        }
        return convertView;
    }

    public class ViewHolder{
        private TextView title;
        private TextView creatName;
        private TextView creatUser;
        private TextView description;
        private TextView source;
        private TextView visit;
        private ImageView img;
        private TextView crdate;
    }

}
