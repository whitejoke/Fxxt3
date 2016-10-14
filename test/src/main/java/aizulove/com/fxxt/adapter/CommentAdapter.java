package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Pinglun;

/**
 * Created by joker on 2016/8/19.
 */
public class CommentAdapter extends BaseAdapter{
    private Context context;
    private List<Pinglun> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;

    public CommentAdapter(Context context, List<Pinglun> list) {
        this.context = context;
        this.list = list;
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
            convertView = mInflater.inflate(R.layout.comment, null);
            holder.tv_buyer = (TextView) convertView.findViewById(R.id.tv_buyer);
            holder.tv_comment_buyer = (TextView) convertView.findViewById(R.id.tv_comment_buyer);
            holder.tv_seller = (TextView) convertView.findViewById(R.id.tv_seller);
            holder.tv_comment_seller = (TextView) convertView.findViewById(R.id.tv_comment_seller);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        if (list.get(position).getBuyer_comment().equals("")){
            holder.tv_seller.setVisibility(View.GONE);
            holder.tv_comment_seller.setVisibility(View.GONE);
        }else {
            holder.tv_seller.setVisibility(View.VISIBLE);
            holder.tv_comment_seller.setVisibility(View.VISIBLE);
            holder.tv_seller.setText(list.get(position).getBuyer()+"回复:");
            holder.tv_comment_seller.setText(list.get(position).getBuyer_comment());
        }
        if (list.get(position).getSeller_comment().equals("")){
            holder.tv_buyer.setVisibility(View.GONE);
            holder.tv_comment_buyer.setVisibility(View.GONE);
        }else {
            holder.tv_buyer.setVisibility(View.VISIBLE);
            holder.tv_comment_buyer.setVisibility(View.VISIBLE);
            holder.tv_buyer.setText(list.get(position).getSeller()+"说:");
            holder.tv_comment_buyer.setText(list.get(position).getSeller_comment());
        }
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_buyer;
        private TextView tv_comment_buyer;
        private TextView tv_seller;
        private TextView tv_comment_seller;
    }
}
