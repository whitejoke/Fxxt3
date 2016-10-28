package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Withdraw;

/**
 * Created by Administrator on 2016/4/29.
 */
public class WithdrawAdapter extends BaseAdapter {
    private Context context;
    private List<Withdraw> list;
    private LayoutInflater mInflater;

    public WithdrawAdapter(Context context, List<Withdraw> list){
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
            convertView = mInflater.inflate(R.layout.activity_withdraw_item, null);
            holder.t_ordersn = (TextView) convertView.findViewById(R.id.t_ordersn);
            holder.zhanhao = (TextView) convertView.findViewById(R.id.zhanhao);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.create = (TextView) convertView.findViewById(R.id.create);
            holder.reason = (TextView) convertView.findViewById(R.id.reason);
            holder.ll_reason= (LinearLayout) convertView.findViewById(R.id.ll_reason);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.t_ordersn.setText("提现单号:"+list.get(postion).getWithdrawId());
        holder.zhanhao.setText("提现帐号:"+list.get(postion).getTrade());
        holder.create.setText(new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getCreateDate()));
        holder.money.setText("提现金额:￥"+list.get(postion).getAmont());
        if (list.get(postion).getStatus()==0) {
            holder.status.setText("审核中");
            holder.status.setTextColor(context.getResources().getColor(R.color.yellow));
        }else if (list.get(postion).getStatus()==1){
            holder.status.setText("审核通过");
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
        }else if (list.get(postion).getStatus()==2){
            holder.status.setText("审核拒绝");
            holder.ll_reason.setVisibility(View.VISIBLE);
            holder.reason.setText("拒绝理由:"+list.get(postion).getReason());
            holder.reason.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.status.setTextColor(context.getResources().getColor(R.color.colorAccent));

        }
        return convertView;
    }

    public class ViewHolder{
        private TextView t_ordersn;
        private TextView zhanhao;
        private TextView money;
        private TextView status;
        private TextView create;
        private TextView reason;
        private LinearLayout ll_reason;
    }

}
