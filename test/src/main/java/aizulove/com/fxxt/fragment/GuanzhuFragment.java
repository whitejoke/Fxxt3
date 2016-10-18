package aizulove.com.fxxt.fragment;

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
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.activity.BaseActivity;
import aizulove.com.fxxt.activity.JulbdetailActivity;
import aizulove.com.fxxt.activity.ProductActivity;
import aizulove.com.fxxt.adapter.GuanzhuAdapter;
import aizulove.com.fxxt.adapter.GuanzhuTwoAdapter;
import aizulove.com.fxxt.modle.entity.Logo;
import aizulove.com.fxxt.modle.entity.Product;
import aizulove.com.fxxt.task.CancelHotAttention;
import aizulove.com.fxxt.task.GuanzhuDataTask;
import aizulove.com.fxxt.task.GuanzhuProductTask;
import aizulove.com.fxxt.utils.VariablesOfUrl;
import aizulove.com.fxxt.view.GridListView;

/**
 * Created by moon.zhong on 2015/3/9.
 */
public class GuanzhuFragment extends Fragment implements  AdapterView.OnItemClickListener,GuanzhuTwoAdapter.Callback {

    private String urlString = VariablesOfUrl.GETHOTATTENTIONLIST;
    private String url=VariablesOfUrl.GETATTENTIONLIST;
    private GridListView listView;
    private GuanzhuAdapter adapter;
    private GuanzhuTwoAdapter pAdapter;
    private  View view;
    private List<Logo> listMessage = new ArrayList<Logo>();
    private List<Product> list=new ArrayList<>();
    private BaseActivity activity=new BaseActivity();
    private String type;
    private int id;
    private List<String> temp=new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private TextView guanzhu;
    private int count=0;
    private static final String SEP1 = ",";
    public static Fragment instance(String type,int id){
        GuanzhuFragment fragment = new GuanzhuFragment(type,id) ;
        return fragment ;
    }
    public GuanzhuFragment(String type,int id){
        this.type=type;
        this.id=id;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_guanzhu,null);
        sharedPreferences=getActivity().getSharedPreferences("member", Context.MODE_WORLD_READABLE);
        //sharedPreferences=getActivity().getSharedPreferences("member", Context.MODE_WORLD_READABLE);
        String test=sharedPreferences.getString("attentionids","");
        String[] p=test.split(",");
        for (int i=0;i<p.length;i++){
            temp.add(p[i]);
        }
        removeDuplicate(temp);
        findViews();
        DataTaskTest();
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

    protected void findViews() {
        listView=(GridListView)view.findViewById(R.id.guanzhulistview);
        listView.setOnItemClickListener(this);
    }


    /**
     *
     * 逻辑处理方法
     */
    protected void DataTaskTest(){
        if (type.equals("俱乐部")){
            Log.i("susu",type);
            adapter=new GuanzhuAdapter(getContext(),listMessage);
            listView.setAdapter(adapter);
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(id));
            new GuanzhuDataTask(getContext(),listMessage,map,adapter,listView,url).execute();
        }else {
            Log.i("susu",sharedPreferences.getString("attentionids",""));
            pAdapter=new GuanzhuTwoAdapter(getContext(),list,sharedPreferences.getString("attentionids",""),this);
            listView.setAdapter(pAdapter);
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", String.valueOf(id));
            map.put("keyword",type);
            new GuanzhuProductTask(getContext(),map,list,pAdapter,urlString).execute();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("susu","跳转");
        if (type.equals("俱乐部")){
            Intent intent= new Intent();
            intent.setClass(getContext(),JulbdetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("logo",(Serializable) listMessage.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            Intent intent= new Intent();
            intent.setClass(getContext(),ProductActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("product",list.get(position));
            bundle.putSerializable("type","hot");
            intent.putExtras(bundle);
            startActivity(intent);
        }

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
    public void click(View v,int position,View convertView) {
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).equals(list.get(position).getItemid().toString())) {
                temp.remove(i);
                String attentionids = ListToString(temp);
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", String.valueOf(sharedPreferences.getString("userid", "")));
                map.put("hotId", String.valueOf(list.get(position).getItemid()));
                new CancelHotAttention(getActivity(), map).execute();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("attentionids", attentionids);
                editor.commit();
                onRefresh();
                break;
            }
        }
    }

    private void onRefresh() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", String.valueOf(id));
        map.put("keyword",type);
        new GuanzhuProductTask(getContext(),map,list,pAdapter,urlString).execute();
    }
}
