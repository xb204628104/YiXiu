package com.zykj.yixiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zykj on 2017/4/27.
 */

public class AdressBaseAdapter extends BaseAdapter {
    private List<Address> lists=new ArrayList<Address>();
    private Context context;

    public AdressBaseAdapter(List<Address> lists, Context context) {
        this.lists = lists;
        this.context = context;
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
        ViewHoder viewHoder=null;
        if (convertView==null){
            viewHoder=new ViewHoder();
            convertView=View.inflate(context, R.layout.item_adress,null);
            viewHoder.item_name= (TextView) convertView.findViewById(R.id.item_name);
            viewHoder.item_num= (TextView) convertView.findViewById(R.id.item_num);
            viewHoder.item_adress= (TextView) convertView.findViewById(R.id.item_adress);
            viewHoder.bt_bianji= (Button) convertView.findViewById(R.id.bt_bianji);
            viewHoder.bt_delete= (Button) convertView.findViewById(R.id.bt_delete);
            viewHoder.item_adress_rb= (RadioButton) convertView.findViewById(R.id.item_adress_rb);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        Address address = lists.get(position);
        viewHoder.item_name.setText(address.getName());
        viewHoder.item_adress.setText(address.getAddress());
        viewHoder.item_num.setText(address.getPhone());
        int isdefault = address.getIsdefault();
        if (isdefault==0){
            viewHoder.item_adress_rb.setChecked(false);
        }else {
            viewHoder.item_adress_rb.setChecked(true);
        }

        return convertView;
    }
}
class ViewHoder{
    TextView item_name,item_num,item_adress;
    Button bt_bianji,bt_delete;
    RadioButton item_adress_rb;
}