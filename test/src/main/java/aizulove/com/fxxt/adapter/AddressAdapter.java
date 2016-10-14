package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Address;

/**
 * Created by joker on 2016/8/3.
 */
public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<Address> list;
    private LayoutInflater mInflater;

    public AddressAdapter(Context context, List<Address> list) {
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
            convertView = mInflater.inflate(R.layout.activity_address_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.tv_address_name);
            holder.phone = (TextView) convertView.findViewById(R.id.tv_address_phone);
            holder.address_deta= (TextView) convertView.findViewById(R.id.tv_address_deta);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.phone.setText(list.get(position).getPhone());
        holder.address_deta.setText(list.get(position).getAreaname()+list.get(position).getCityname()+list.get(position).getAddress());
        return convertView;
    }
    public class ViewHolder{
        private TextView name;
        private TextView phone;
        private TextView address_deta;
        private TextView postcode;
    }
}
