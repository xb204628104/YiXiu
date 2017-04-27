package com.zykj.yixiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.activity.Activity_Callservice_Adress_Edit;
import com.zykj.yixiu.bean.Address;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHoder viewHoder=null;
        if (convertView==null){
            viewHoder=new ViewHoder();
            convertView=View.inflate(context, R.layout.item_adress,null);
            viewHoder.item_name= (TextView) convertView.findViewById(R.id.item_name);
            viewHoder.tv_text= (TextView) convertView.findViewById(R.id.tv_text);
            viewHoder.item_num= (TextView) convertView.findViewById(R.id.item_num);
            viewHoder.item_adress= (TextView) convertView.findViewById(R.id.item_adress);
            viewHoder.bt_bianji= (Button) convertView.findViewById(R.id.bt_bianji);
            viewHoder.bt_delete= (Button) convertView.findViewById(R.id.bt_delete);
            viewHoder.iv= (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        Address address = lists.get(position);
        viewHoder.item_name.setText(address.getName());
        viewHoder.item_adress.setText(address.getAddress());
        viewHoder.item_num.setText(address.getPhone());
        viewHoder.bt_delete.setTag(address.getAddress_id());
        viewHoder.bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map=new HashMap();
                map.put("user_id",Y.USER.getUser_id()+"");
                map.put("address_id",v.getTag()+"");
                Y.get(YURL.DELA_DDRESS, map, new Y.MyCommonCall<String>() {

                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("删除成功");
                            lists.remove(position);
                            notifyDataSetChanged();
                        }else {
                            Y.t("删除失败");
                        }
                    }
                });
            }
        });
        viewHoder.bt_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Activity_Callservice_Adress_Edit.class);

            }
        });
        int isdefault = address.getIsdefault();
        if (isdefault==1){
            viewHoder.tv_text.setText("默认");
           viewHoder.tv_text.setTextColor(Color.BLUE);
            viewHoder.iv.setImageResource(R.mipmap.nanxuan);
        }else {
            viewHoder.tv_text.setText("设置");
           viewHoder.tv_text.setTextColor(Color.BLACK);
            viewHoder.iv.setImageResource(R.mipmap.nan);
        }

        return convertView;
    }
}
class ViewHoder{
    TextView item_name,item_num,item_adress,tv_text;
    Button bt_bianji,bt_delete;
    ImageView iv;
}