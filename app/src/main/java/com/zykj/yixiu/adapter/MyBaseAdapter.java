package com.zykj.yixiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zykj.yixiu.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/20.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<String> lists;

    public MyBaseAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.order_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_item_jiedan)
        TextView tvItemJiedan;
        @Bind(R.id.bt_item_chakan)
        Button btItemChakan;
        @Bind(R.id.bt_item_quxiao)
        Button btItemQuxiao;
        @Bind(R.id.tv_item_time)
        TextView tvItemTime;
        @Bind(R.id.bt_item_fabu)
        Button btItemFabu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
