package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Integration;

/**
 * Created by joker on 2016/8/3.
 */
public class IntegrationAdapter extends BaseAdapter {
    private Context context;
    private List<Integration> list;
    private LayoutInflater mInflater;

    public IntegrationAdapter(Context context, List<Integration> list) {
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
            convertView = mInflater.inflate(R.layout.activity_inte_item, null);
            holder.addtime = (TextView) convertView.findViewById(R.id.tv_inte_date);
            holder.balance = (TextView) convertView.findViewById(R.id.tv_inte_count);
            holder.reason= (TextView) convertView.findViewById(R.id.tv_inte_award);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(list.get(position).getAddtime());
        re_StrTime = sdf.format(new Date(lcc_time*1000L));

        holder.addtime.setText(re_StrTime);

        holder.balance.setText(String.valueOf(list.get(position).getBalance())+"积分");
        holder.reason.setText(list.get(position).getReason());
        return convertView;
    }
    public class ViewHolder{
        private TextView addtime;
        private TextView balance;
        private TextView reason;
    }
}
