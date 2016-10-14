package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.ChongZhiActivity;
import aizulove.com.fxxt.fragment.RechargeFragment;
import aizulove.com.fxxt.modle.entity.Recharge;

/**
 * Created by Administrator on 2016/4/29.
 */
public class RechargeAdapter extends BaseAdapter {
    private Context context;
    private List<Recharge> list;
    private  RechargeFragment fr;
    private LayoutInflater mInflater;

    public RechargeAdapter(Context context, List<Recharge> list,RechargeFragment fr){
        this.context = context;
        this.list = list;
        this.fr=fr;
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
    public View getView( int postion, View convertView, ViewGroup parent) {
        final int index=postion;
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_recharge_item, null);
            holder.ordersn = (TextView) convertView.findViewById(R.id.ordersn);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            holder.createdate = (TextView) convertView.findViewById(R.id.createdate);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ordersn.setText("订单号:"+list.get(postion).getRechargeCode());
        holder.money.setText("充值金额:￥"+String.valueOf(list.get(postion).getMoney()));
        holder.createdate.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(list.get(postion).getCreateDate()));
        //holder.logozts.setText(String.valueOf(list.get(postion).getCommentList() == null ? 0 : list.get(postion).getCommentList().size()));
        if (list.get(postion).getStatus()==0){
            holder.status.setText("未支付");
            holder.status.setTextColor(context.getResources().getColor(R.color.colorAccent));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    fr.startActivitys(list.get(index).getRechargeCode(),String.valueOf(list.get(index).getMoney()));
                }
            });
        }else{
            holder.status.setText("已支付");
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
        }

        return convertView;
    }

    public class ViewHolder{
        private TextView ordersn;
        private TextView money;
        private TextView createdate;
        private TextView status;
    }
}
