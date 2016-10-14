package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.util.Log;
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
 * Created by Administrator on 2016/4/29.
 */
public class ProductAdapter extends BaseAdapter  {
    private Context context;
    private List<Product> list;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private String isGuanzhu;
    private Callback mCallBack;
    private View layoutview;

    public interface Callback {
        public void click(View v,int position,View view);
    }
    public ProductAdapter(Context context, List<Product> list,String isGuanzhu,Callback callback){
        this.context = context;
        ImageLoadOptions.initImageLoader(context);
        ImageLoadOptions.getOptions();
        this.list = list;
        this.isGuanzhu=isGuanzhu;
        this.mCallBack=callback;
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
    public View getView(final int postion, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_guanzhu_item, null);
            holder.img=(ImageView)convertView.findViewById(R.id.img);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.guanzhu= (TextView) convertView.findViewById(R.id.tv_guanzhu);
            holder.guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.click(v,postion,layoutview);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        layoutview=convertView;
        String[] temp=isGuanzhu.split(",");
        for (int i=0;i<temp.length;i++){
            if (temp[i].equals(list.get(postion).getItemid().toString())){
                holder.guanzhu.setText("取消关注");
            }
        }
        Log.i("susu", String.valueOf(list.get(postion).getItemid()));
        holder.title.setText(list.get(postion).getTitle());
        holder.price.setText("￥"+list.get(postion).getPrice());
        if(null!=list.get(postion).getThumb()) {
            Picasso.with(context).load(list.get(postion).getThumb()).into(holder.img);
            //ImageLoader.getInstance().displayImage(list.get(postion).getThumb(), holder.img, options, null);
        }
        return convertView;
    }
    /**
     * mLists集合的get方法  通过他可以取得数据的size();
     * @return
     */
    public List<Product> getLists() {
        return list;
    }

    /**
     * 对以下方法的复用
     * @param lists
     */
    public void addLists(List<Product> lists) {
        addLists(0, lists);
    }


    /**
     * 添加数据
     * @param position  添加的位置
     * @param lists   添加的数据
     */
    public void addLists(int position, List<Product> lists) {
        //mLists = lists;
        if (lists != null && lists.size() > 0) {
            list.addAll(lists);
            /**
             * Notify any registered observers that the <code>itemCount</code> items starting at
             * position <code>positionStart</code> have changed.
             *
             * 通知item是从哪个地方到哪个地方已经改变了
             */

        }
    }

    public class ViewHolder{
        private ImageView img;
        private TextView title;
        private TextView price;
        private TextView guanzhu;
    }

}
