package aizulove.com.fxxt.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.ProductActivity;
import aizulove.com.fxxt.adapter.ProductAdapter;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.AddAttentionTask;
import aizulove.com.fxxt.task.CancelHotAttention;
import aizulove.com.fxxt.task.HotTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;

/**
 * Created by joker on 2016/10/9.
 */
public class ProductFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener,ProductAdapter.Callback {
    private  View view;
    private GridView listView;
    private ProductAdapter adapter;
    private MaterialRefreshLayout mlayout;
    private String url= VariablesOfUrl.GETHOTMALLLIST;
    private List<Product> listMessage = new ArrayList<Product>();
    private String type,isGuanzhu;
    private SharedPreferences sharedPreferences;
    private List<String> temp=new ArrayList<>();
    private int count=0,i=2;
    private static final String SEP1 = ",";
    private boolean isLoadMore = true;
    private TextView guanzhu;
    private int positions;

    //private Context context;
    //private BaseActivity baseActivity=new BaseActivity();
    public static Fragment instance(String type,String isGuanzhu){
        ProductFragment fragment = new ProductFragment(type,isGuanzhu) ;
        return fragment ;
    }
    @SuppressLint("ValidFragment")
    public ProductFragment(String type,String isGuanzhu){
        this.isGuanzhu=isGuanzhu;
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_product,null);//activity_qishiquan
        sharedPreferences=getActivity().getSharedPreferences("member", Context.MODE_WORLD_READABLE);
        String test=sharedPreferences.getString("attentionids","");
        String[] p=test.split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }
        removeDuplicate(temp);
        //Log.i("susu", String.valueOf(temp));
        findViews();
        DataTaskTest();
        initRefresh();
        return view;
    }
    public static List<String> removeDuplicate(List<String> list)

    {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }



    private void DataTaskTest() {
        Log.i("susu",type);
        //Log.i("susu", getActivity().getMemberSharedPreference().getAttentionids() + "这是测试");
        adapter=new ProductAdapter(getContext(),listMessage,isGuanzhu,this);
        listView.setAdapter(adapter);
        Map<String, String> map = new HashMap<String, String>();
        map.put("page","1");
        map.put("num", "10");
        map.put("classify",type);
        new HotTask(getContext(),listMessage,adapter,mlayout,map,url).execute();

    }

    private void findViews() {
        mlayout= (MaterialRefreshLayout) view.findViewById(R.id.srl);
        listView=(GridView)view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    private void initRefresh() {
        mlayout.setLoadMore(isLoadMore);
        mlayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("page","1");
                map.put("num", "10");
                map.put("classify",type);
                new HotTask(getContext(),listMessage,adapter,mlayout,map,url).execute();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore=false;
                Map<String, String> map = new HashMap<String, String>();
                map.put("page", String.valueOf(i));
                map.put("num", "10");
                map.put("classify",type);
                new HotTask(getContext(),listMessage,adapter,mlayout,map,url).execute();
                i++;
                Log.i("susu", String.valueOf(i));
                mlayout.finishRefreshLoadMore();
            }
        });

    }
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append(SEP1);
                }  else {
                    sb.append(list.get(i));
                    sb.append(SEP1);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i("susu", String.valueOf(temp.size()));
//
//        guanzhu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < temp.size(); i++) {
//                    if (temp.get(i).equals(listMessage.get(position).getItemid().toString())) {
//                        guanzhu.setText("关注+");
//                        Log.i("susu", "hava");
//                        count = 1;
//                        temp.remove(i);
//                        String attentionids = ListToString(temp);
//                        Log.i("susu", attentionids);
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
//                        map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
//                        new CancelHotAttention(getActivity(), map).execute();
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("attentionids", attentionids);
//                        editor.commit();
//                        break;
//                    } else {
//                        //Log.i("susu", "not have");
//                        //Log.i("susu", String.valueOf(listMessage.get(position).getItemid()));
//                        count = 0;
//                    }
//                }
//                String test = ListToString(temp);
//                Log.i("susu", test);
//                Log.i("susu", String.valueOf(temp));
//                if (count == 0) {
//                    guanzhu.setText("取消关注");
//                    temp.add(listMessage.get(position).getItemid().toString());
//                    String attentionids = ListToString(temp);
//                    Map<String, String> map = new HashMap<String, String>();
//                    map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
//                    map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
//                    new AddAttentionTask(getActivity(), map).execute();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("attentionids", attentionids);
//                    editor.commit();
//                }
//                count = 0;
//            }
//        });

        Intent intent= new Intent();
        intent.setClass(getContext(),ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",listMessage.get(position));
        bundle.putSerializable("type","hot");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void click(View v,int position,View convertView) {
        guanzhu= (TextView) v.findViewById(R.id.tv_guanzhu);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).equals(listMessage.get(position).getItemid().toString())) {
                guanzhu.setText("关注+");
                Log.i("susu", "hava");
                count = 1;
                temp.remove(i);
                String attentionids = ListToString(temp);
                Log.i("susu", attentionids);
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
                map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
                new CancelHotAttention(getActivity(), map).execute();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("attentionids", attentionids);
                editor.commit();
                break;
            } else {
                //Log.i("susu", "not have");
                //Log.i("susu", String.valueOf(listMessage.get(position).getItemid()));
                count = 0;
            }
        }
        String test = ListToString(temp);
        Log.i("susu", test);
        Log.i("susu", String.valueOf(temp));
        if (count == 0) {
            guanzhu.setText("已关注");
            temp.add(listMessage.get(position).getItemid().toString());
            String attentionids = ListToString(temp);
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
            map.put("hotId", String.valueOf(listMessage.get(position).getItemid()));
            new AddAttentionTask(getActivity(), map).execute();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("attentionids", attentionids);
            editor.commit();
        }
        count = 0;
    }
}
