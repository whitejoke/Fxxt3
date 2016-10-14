package aizulove.com.fxxt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import java.util.List;

import aizulove.com.fxxt.R;
import aizulove.com.fxxt.modle.entity.GridViewItem;

/**
 * Created by joker on 2016/8/1.
 */
public class CategoryAdapter extends BaseAdapter{
    private Context context;
    private int gridViewSelectPosition;
    private List<GridViewItem> list; // 记录哪一个Item被选中，初始化的时候使用，在本例中并未用到
    public CategoryAdapter(Context context,List<GridViewItem> list,int gridViewSelectPosition ) {
        this.context=context;
        this.list = list;
        this.gridViewSelectPosition=gridViewSelectPosition;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) { return list.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
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
        RadioButton rb_item;
    }
}
