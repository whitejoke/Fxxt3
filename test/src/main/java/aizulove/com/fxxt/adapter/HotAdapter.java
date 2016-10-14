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
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.utils.ImageLoadOptions;

/**
 * Created by joker on 2016/7/26.
 */
public class HotAdapter extends BaseAdapter {
    private Context context;
    private List<Product> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;

    public HotAdapter(Context context, List<Product> list) {
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
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.actiity_product_item, null);
            holder.cuxiaoImage=(ImageView)convertView.findViewById(R.id.Image);
            holder.cuxiaoTitle = (TextView) convertView.findViewById(R.id.Title);
            holder.cuxiaoPrice = (TextView) convertView.findViewById(R.id.Price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.cuxiaoTitle.setText(list.get(position).getTitle());
        holder.cuxiaoPrice.setText("￥"+String.valueOf(list.get(position).getPrice()));
        String cuxiaopath= list.get(position).getThumb();//
        if(null!=cuxiaopath) {
            Picasso.with(context).load(cuxiaopath).into(holder.cuxiaoImage);
        }
        else{
            holder.cuxiaoImage.setImageResource(R.mipmap.ic_phone);
        }
        return convertView;
    }
    public class ViewHolder{
        private ImageView cuxiaoImage;
        private TextView cuxiaoTitle;
        private TextView cuxiaoPrice;
    }
}
