package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Coupon;

/**
 * Created by Administrator on 2016/4/29.
 */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<Coupon> list;
    private LayoutInflater mInflater;
    private  int term;

    public CouponAdapter(Context context, List<Coupon> list,int term){
        this.context = context;
        this.list = list;
        this.term=term;
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
    public View getView(int postion, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_youhui_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.promoPrice = (TextView) convertView.findViewById(R.id.promoPrice);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.judge = (TextView) convertView.findViewById(R.id.tv_judge);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String    date    =    sDateFormat.format(new java.util.Date());
        String last=getTime(new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss").format(list.get(postion).getOverTime()));
        String tolast=getTime(date);
        //Log.i("susu",last+"+++++++++++++"+tolast);
        int i= Integer.parseInt(last);
        int j= Integer.parseInt(tolast);
        if (i<j){
            holder.judge.setText("优惠券已过期");
        }else {
            if (term<list.get(postion).getTerm()){
                holder.judge.setText("优惠券未满足要求");
            }else {
                holder.judge.setVisibility(View.GONE);
            }

        }

        holder.title.setText(list.get(postion).getTitle());
        holder.promoPrice.setText("￥"+list.get(postion).getPromoPrice());
        holder.date.setText("使用期限:"+new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getStartTime())+" "+new SimpleDateFormat("yyyy-MM-dd").format(list.get(postion).getOverTime()));
        return convertView;
    }
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    public class ViewHolder{
        private TextView title;
        private TextView promoPrice;
        private TextView date;
        private TextView judge;
    }

}
