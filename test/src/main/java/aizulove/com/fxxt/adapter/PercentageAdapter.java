package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Percentage;

/**
 * Created by Administrator on 2016/4/29.
 */
public class PercentageAdapter extends BaseAdapter {
    private Context context;
    private List<Percentage> list;
    private LayoutInflater mInflater;

    public PercentageAdapter(Context context, List<Percentage> list){
        this.context = context;
        this.list = list;
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
            convertView = mInflater.inflate(R.layout.activity_percentage_item, null);
            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.leve = (TextView) convertView.findViewById(R.id.leve);
            holder.productname = (TextView) convertView.findViewById(R.id.product_name);
            holder.yj = (TextView) convertView.findViewById(R.id.yj);
            holder.yjbl = (TextView) convertView.findViewById(R.id.yjbl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.username.setText(list.get(postion).getTruename());
        holder.leve.setText("等级:"+list.get(postion).getLevel());
       holder.productname.setText(list.get(postion).getTitle());
        holder.yj.setText(String.valueOf(list.get(postion).getAmont()));
        holder.yjbl.setText(list.get(postion).getProportion()+"%");
        return convertView;
    }

    public class ViewHolder{
        private TextView username;
        private TextView leve;
        private TextView productname;
        private TextView yj;
        private TextView yjbl;
    }

}
