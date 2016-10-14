package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Product;

/**
 * Created by joker on 2016/8/3.
 */
public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Product> list;
    private LayoutInflater mInflater;
    private String[] temp={"待确认","待付款","待发货","已发货",
            "交易成功","申请退款","已退款","货到付款",
            "买家关闭","卖家关闭"};

    public OrderAdapter(Context context, List<Product> list) {
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
            convertView = mInflater.inflate(R.layout.activity_order_id, null);
            holder.orderImage=(ImageView)convertView.findViewById(R.id.iv_order);
            holder.orderNote = (TextView) convertView.findViewById(R.id.tv_order_note);
            holder.orderAmount = (TextView) convertView.findViewById(R.id.tv_order_amount);
            holder.orderNum= (TextView) convertView.findViewById(R.id.tv_order_num);
            holder.orderNum2= (TextView) convertView.findViewById(R.id.tv_order_num2);
            holder.orderPrice= (TextView) convertView.findViewById(R.id.tv_order_price);
            holder.orderTitle= (TextView) convertView.findViewById(R.id.tv_order_title);
            holder.orderTrade_no= (TextView) convertView.findViewById(R.id.tv_order_trade_no);
            holder.orderPay= (TextView) convertView.findViewById(R.id.tv_order_pay);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.orderTitle.setText(list.get(position).getTitle());
        holder.orderPrice.setText("单价: ¥ "+String.valueOf(list.get(position).getPrice()));
        if (list.get(position).getNote().equals("")){
            holder.orderNote.setVisibility(View.INVISIBLE);
        }else {
            holder.orderNote.setText(list.get(position).getNote());
        }
        holder.orderNum.setText("数量:"+String.valueOf(list.get(position).getNumber()));
        holder.orderNum2.setText("共计"+String.valueOf(list.get(position).getNumber())+"件商品");
        holder.orderAmount.setText("合计: ¥ "+String.valueOf(list.get(position).getAmount()));
        holder.orderTrade_no.setText("订单号 "+list.get(position).getTrade_no());
        holder.orderPay.setText(temp[list.get(position).getStatus()]);
        String orderPath= list.get(position).getThumb();//
        if(null!=orderPath) {
            Picasso.with(context).load(orderPath).into(holder.orderImage);
        }
        else{
            holder.orderImage.setImageResource(R.mipmap.ic_phone);
        }
        return convertView;
    }
    public class ViewHolder{
        private ImageView orderImage;
        private TextView orderTitle;
        private TextView orderPrice;
        private TextView orderAmount;
        private TextView orderNum;
        private TextView orderNum2;
        private TextView orderNote;
        private TextView orderTrade_no;
        private TextView orderPay;


    }
}
