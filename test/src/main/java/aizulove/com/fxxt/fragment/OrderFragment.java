package aizulove.com.fxxt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.TransactionActivity;
import aizulove.com.fxxt.adapter.OrderAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.OrderTaSK;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/10/9.
 */
public class OrderFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View view;
    private TextView titleview;
    private GridView listView;
    private OrderAdapter adapter;
    private String type;
    private String name;
    private SwipeRefreshLayout srl;
    private String url= VariablesOfUrl.GETORDERLISTBYUSERNAME;
    private List<Product> listMessage = new ArrayList<Product>();
    public static Fragment instance(String type,String name){
        OrderFragment fragment = new OrderFragment(type,name) ;
        return fragment ;
    }
    public OrderFragment(String type,String name){
        this.type=type;
        this.name=name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_order,null);
        findViews();
        DataTask();
        return view;
    }

    protected void findViews() {
        listView=(GridView)view.findViewById(R.id.gv_order);
        listView.setOnItemClickListener(this);
    }


    /**
     *
     * 逻辑处理方法
     */
    protected void DataTask(){
        adapter=new OrderAdapter(getContext(),listMessage);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "222");
        map.put("username", name);
        //Log.i("susu",getMemberSharedPreference().getUsername().toString());
        new OrderTaSK(getContext(),listMessage,adapter,map,url).execute();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.setClass(getContext(),TransactionActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("product",listMessage.get(position));
        bundle.putString("type","order");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
