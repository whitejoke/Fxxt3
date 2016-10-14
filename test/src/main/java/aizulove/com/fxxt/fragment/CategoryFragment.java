package aizulove.com.fxxt.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.GridViewItem;

/**
 * Created by joker on 2016/7/29.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CategoryFragment extends Fragment {
    private Context context;
    private GridView gridView;
    private GridItemAdapter adapter;
    private List<GridViewItem> data;
    int gridViewSelectPosition = 0;
    private String[] rbTextArray = null;
    private String title=null;
    private TextView tv_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gridview,container,false);
        gridView = (GridView) view.findViewById(R.id.gridview);
        tv_title= (TextView) view.findViewById(R.id.tv_gridtitle);

        context = getActivity();
        data=new ArrayList<GridViewItem>();
        for (int i = 0; i < rbTextArray.length; i++) {
            GridViewItem gridViewItem = new GridViewItem();
            gridViewItem.setBtn_text(rbTextArray[i]);
            data.add(gridViewItem);
        }
        //准备数据源
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_title.setText(title);
        //定义适配器
        adapter=new GridItemAdapter(context,data,gridViewSelectPosition);
        //绑定适配器
        gridView.setAdapter(adapter);
    }

    private class GridItemAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private int gridViewSelectPosition;
        private List<GridViewItem> list; // 记录哪一个Item被选中，初始化的时候使用，在本例中并未用到
        public GridItemAdapter(Context context, List<GridViewItem> list,int gridViewSelectPosition ) {
            inflater = LayoutInflater.from(context);
            this.list = list;
            this.gridViewSelectPosition=gridViewSelectPosition;
        }
        @Override
        public int getCount() { return list.size(); }
        @Override
        public Object getItem(int position) { return list.get(position); }
        @Override
        public long getItemId(int position) { return position; }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.grid_item, null);
                viewHolder.rb_item = (RadioButton) convertView.findViewById(R.id.rb_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            GridViewItem gridViewItem = list.get(position);
            viewHolder.rb_item.setText(gridViewItem.getBtn_text());
            if (position==gridViewSelectPosition){
                viewHolder.rb_item.setChecked(true);
            }else {
                viewHolder.rb_item.setChecked(false);
            }
            return convertView;
        }
        private class ViewHolder {
            RadioButton rb_item; }
        }

}
