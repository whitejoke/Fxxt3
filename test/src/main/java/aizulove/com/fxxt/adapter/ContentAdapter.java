package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Comment;

/**
 * Created by joker on 2016/9/7.
 */
public class ContentAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> list;
    private LayoutInflater mInflater;

    public ContentAdapter(Context context, List<Comment> list){
        this.context = context;
        this.list=list;
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
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_comment, null);
            holder.avatarUrl = (ImageView) convertView.findViewById(R.id.iv_head);
            holder.title = (TextView) convertView.findViewById(R.id.commentusername);
            holder.content = (TextView) convertView.findViewById(R.id.commentcontent);
            holder.creatTime = (TextView) convertView.findViewById(R.id.commentdate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(postion).getMember().getUsername());
        holder.content.setText(list.get(postion).getContent());
        holder.creatTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getCreatTime()));

        if (null != list.get(postion).getMember().getAvatarUrl() && "http".contains(list.get(postion).getMember().getAvatarUrl())) {
            Picasso.with(context).load(list.get(postion).getMember().getAvatarUrl()).into(holder.avatarUrl);
            //ImageLoader.getInstance().displayImage(list.get(postion).getMember().getAvatarUrl(), holder.avatarUrl, options, null);
        } else {
            holder.avatarUrl.setImageResource(R.mipmap.ic_zt);
        }
        return convertView;
    }

    public class ViewHolder{
        private ImageView avatarUrl;
        private TextView title;
        private TextView content;
        private TextView creatTime;
    }
}
