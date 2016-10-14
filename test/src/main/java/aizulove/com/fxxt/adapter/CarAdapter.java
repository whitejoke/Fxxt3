package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.Product;

/**
 * Created by joker on 2016/8/2.
 */
public class CarAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Product> list;
    private LayoutInflater mInflater;
    private Callback mCallBack;
    private int weizhi;

    public CarAdapter(Context context, List<Product> list,Callback callback) {
        this.context = context;
        this.list = list;
        this.mCallBack=callback;
    }
    public interface Callback{
        public void clickToCar(View view,String type,int position);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
//        Log.i("susu", String.valueOf(position));
//        Log.i("susu", String.valueOf(list.get(position).getItemid()));
        ViewHolder holder;
        if (null == convertView) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_car_item, null);
            holder.carImage=(ImageView)convertView.findViewById(R.id.iv_car);
            holder.carTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.carPrice = (TextView) convertView.findViewById(R.id.tv_car_price);
            holder.carColor= (TextView) convertView.findViewById(R.id.tv_color);
            holder.carSize= (TextView) convertView.findViewById(R.id.tv_size);
            holder.carNotknow= (TextView) convertView.findViewById(R.id.tv_notknow);
            holder.carCount= (TextView) convertView.findViewById(R.id.tv_i);
            holder.car_n1= (TextView) convertView.findViewById(R.id.tv_n1);
            holder.car_n2= (TextView) convertView.findViewById(R.id.tv_n2);
            holder.car_n3= (TextView) convertView.findViewById(R.id.tv_n3);
            holder.add= (TextView) convertView.findViewById(R.id.add);
            holder.cut= (TextView) convertView.findViewById(R.id.cut);
            holder.ll_car_color= (LinearLayout) convertView.findViewById(R.id.ll_car_color);
            holder.ll_car_size= (LinearLayout) convertView.findViewById(R.id.ll_car_size);
            holder.ll_car_notknow= (LinearLayout) convertView.findViewById(R.id.ll_car_notknow);
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.clickToCar(v,"add",position);
                }
            });
            holder.cut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.clickToCar(v,"cut",position);
                }
            });
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.carTitle.setText(list.get(position).getTitle());
        holder.carPrice.setText("单价:¥ "+String.valueOf(list.get(position).getPrice()));
        holder.carCount.setText("× "+list.get(position).getI());
        String[] temp=list.get(position).getV1().toString().split("\\|");
        String[] temp2=list.get(position).getV2().toString().split("\\|");
        String[] temp3=list.get(position).getV3().toString().split("\\|");
        String[] temp4=list.get(position).getOut_v3().toString().split(";");
        if (!list.get(position).getN1().equals("")){
            holder.ll_car_color.setVisibility(View.VISIBLE);
            holder.car_n1.setText(list.get(position).getN1()+":");
            holder.carColor.setText(temp[Integer.parseInt(list.get(position).getOut_v1())]);
        }
        if (!list.get(position).getN2().equals("")){
            holder.ll_car_size.setVisibility(View.VISIBLE);
            holder.car_n2.setText(list.get(position).getN2()+":");
            holder.carSize.setText(temp2[Integer.parseInt(list.get(position).getOut_v2())]);
        }
        if (!list.get(position).getN3().equals("")){
            holder.ll_car_notknow.setVisibility(View.VISIBLE);
            holder.car_n3.setText(list.get(position).getN3()+":");
            holder.carNotknow.setText(temp3[Integer.parseInt(temp4[0])]);
        }
        String carPath= list.get(position).getThumb();//
        if(null!=carPath) {
            Picasso.with(context).load(carPath).into(holder.carImage);
        }
        else{
            holder.carImage.setImageResource(R.mipmap.ic_phone);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                Log.i("susu","add");
                Log.i("susu", String.valueOf(weizhi));
                mCallBack.clickToCar(v,"add",weizhi);
                break;
            case R.id.cut:
                Log.i("susu","cut");
                mCallBack.clickToCar(v,"cut",weizhi);
                break;
        }
    }

    public class ViewHolder{
        private ImageView carImage;
        private TextView carTitle;
        private TextView carPrice;
        private TextView carColor;
        private TextView carSize;
        private TextView carNotknow;
        private TextView carCount;
        private TextView car_n1;
        private TextView car_n2;
        private TextView car_n3;
        private TextView add;
        private TextView cut;
        private LinearLayout ll_car_color;
        private LinearLayout ll_car_size;
        private LinearLayout ll_car_notknow;


    }
}
