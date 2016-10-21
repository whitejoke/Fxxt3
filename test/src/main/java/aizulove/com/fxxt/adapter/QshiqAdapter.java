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

import java.text.SimpleDateFormat;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Post;
import aizulove.com.fxxt.utils.ImageLoadOptions;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by Administrator on 2016/4/29.
 */
public class QshiqAdapter extends BaseAdapter {
    private Context context;
    private List<Post> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;


    public QshiqAdapter(Context context, List<Post> list){
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
        //Log.i("susu", String.valueOf(list.size()));
        if (null == convertView) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_qishiquan, null);
            holder.avatarUrl = (ImageView) convertView.findViewById(R.id.avatarUrl);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.img0 = (ImageView) convertView.findViewById(R.id.img0);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
            holder.img2 = (ImageView) convertView.findViewById(R.id.img2);
            holder.creatTime = (TextView) convertView.findViewById(R.id.creatTime);
            holder.logozts = (TextView) convertView.findViewById(R.id.logozts);
            holder.logodianz = (TextView) convertView.findViewById(R.id.logodianz);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(postion).getSource());
        holder.content.setText(list.get(postion).getContent());
        String[] imgs = list.get(postion).getImg().split(",");
        holder.creatTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getCreatTime()));
        //holder.logozts.setText(String.valueOf(list.get(postion).getCommentList() == null ? 0 : list.get(postion).getCommentList().size()));
        holder.logozts.setText(String.valueOf(list.get(postion).getCount()));
        holder.logodianz.setText(String.valueOf(list.get(postion).getZan()));

        if (null != list.get(postion).getMember().getAvatarUrl() && "http".contains(list.get(postion).getMember().getAvatarUrl())) {
            Picasso.with(context).load(list.get(postion).getMember().getAvatarUrl()).into(holder.avatarUrl);
            //ImageLoader.getInstance().displayImage(list.get(postion).getMember().getAvatarUrl(), holder.avatarUrl, options, null);
        } else {
            holder.avatarUrl.setImageResource(R.mipmap.ic_zt);
        }

        for (int i = 0; i < imgs.length; i++){
            String imgpath= VariablesOfUrl.imagePath+"/file/post/"+imgs[i];//
            if (i==0) {
                if (null != imgs[i]&&!imgs[i].equals("")) {
                    Picasso.with(context).load(imgpath).into(holder.img0);
                    //ImageLoader.getInstance().displayImage(imgpath, holder.img0, options, null);
                }else {
                    holder.img0.setVisibility(View.GONE);
                }
            }else if (i==1){
                if (null != imgs[i]&&!imgs[i].equals("")) {
                    Picasso.with(context).load(imgpath).into(holder.img1);
                    //ImageLoader.getInstance().displayImage(imgpath, holder.img1, options, null);
                }else {
                    holder.img1.setVisibility(View.GONE);
                }
            }else if(i==2){
                if (null != imgs[i]&&!imgs[i].equals("")) {
                    Picasso.with(context).load(imgpath).into(holder.img2);
                    //ImageLoader.getInstance().displayImage(imgpath, holder.img2, options, null);
                }else {
                    holder.img2.setVisibility(View.GONE);
                }
            }
         }
        return convertView;
    }

    public class ViewHolder{
        private ImageView avatarUrl;
        private TextView title;
        private TextView content;
        private ImageView img0;
        private ImageView img1;
        private ImageView img2;
        private TextView creatTime;
        private TextView logozts;
        private TextView logodianz;
    }

}
