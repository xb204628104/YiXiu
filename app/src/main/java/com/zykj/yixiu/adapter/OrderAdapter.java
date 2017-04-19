package com.zykj.yixiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zykj.yixiu.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by zykj on 2017/4/19.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHoder>{
    private Context context;
    private List<String> list;

    public OrderAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        MyViewHoder myViewHoder=new MyViewHoder(view);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.btfa.setText(list.get(position));
        holder.btqu.setText(list.get(position));
        holder.tvjie.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder{
        private TextView tvjie;
        private Button btfa,btqu;
        public MyViewHoder(View itemView) {
            super(itemView);
            tvjie= (TextView) itemView.findViewById(R.id.tv_item_jiedan);
            btfa= (Button) itemView.findViewById(R.id.bt_item_fabu);
            btqu= (Button) itemView.findViewById(R.id.bt_item_quxiao);
        }
    }
}
