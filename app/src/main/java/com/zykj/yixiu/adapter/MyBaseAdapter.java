package com.zykj.yixiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.Address;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/20.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Address> lists;

    public MyBaseAdapter(Context context, List<Address> lists) {
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
        ViewHoder2 viewHoder2;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.order_item,null);
            viewHoder2=new ViewHoder2();
            viewHoder2.tv_item_jiedan= (TextView) convertView.findViewById(R.id.tv_item_jiedan);
            viewHoder2.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            viewHoder2.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            viewHoder2.bt_item_chakan= (Button) convertView.findViewById(R.id.bt_item_chakan);
            viewHoder2.bt_item_quxiao= (Button) convertView.findViewById(R.id.bt_item_quxiao);
            convertView.setTag(viewHoder2);
        }else {
            viewHoder2= (ViewHoder2) convertView.getTag();
        }

        return convertView;
    }

}
class ViewHoder2{
    TextView tv_item_jiedan,tv_time,tv_address;
    Button bt_item_chakan,bt_item_quxiao;
}
