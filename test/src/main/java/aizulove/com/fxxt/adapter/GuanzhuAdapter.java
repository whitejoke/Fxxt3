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

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by Administrator on 2016/4/29.
 */
public class GuanzhuAdapter extends BaseAdapter {
    private Context context;
    private List<Logo> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;

    public GuanzhuAdapter(Context context, List<Logo> list){
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
    public View getView(int postion, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_qulbu_item, null);
            holder.logoImage=(ImageView)convertView.findViewById(R.id.logoImage);
            holder.logoTitle = (TextView) convertView.findViewById(R.id.logoTitle);
            holder.logozts = (TextView) convertView.findViewById(R.id.logozts);
            holder.logodianz = (TextView) convertView.findViewById(R.id.logodianz);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.logoTitle.setText(list.get(postion).getTitle());
        holder.logozts.setText(String.valueOf(list.get(postion).getQuality()));
        holder.logodianz.setText(String.valueOf(list.get(postion).getZan()));
        String logopath= VariablesOfUrl.imagePath+"/file/logo/"+list.get(postion).getLogoImg();//
        //Log.i("susu",logopath);
        if(null!=logopath) {
            Picasso.with(context).load(logopath).into(holder.logoImage);
            //ImageLoader.getInstance().displayImage(logopath, holder.logoImage, options, null);
        }else{
            holder.logoImage.setImageResource(R.mipmap.ic_phone);
        }
        return convertView;
    }

    public class ViewHolder{
        private ImageView logoImage;
        private TextView logoTitle;
        private TextView logozts;
        private TextView logodianz;
    }
}
