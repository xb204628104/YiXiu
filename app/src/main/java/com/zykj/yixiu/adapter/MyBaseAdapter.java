package com.zykj.yixiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.activity.Activity_Order_Delete;
import com.zykj.yixiu.activity.Activity_Order_Have;
import com.zykj.yixiu.activity.Activity_Order_Over;
import com.zykj.yixiu.activity.Activity_Order_Success;
import com.zykj.yixiu.bean.Address;
import com.zykj.yixiu.bean.Oreder;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/20.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Oreder> lists;
    private int index=-1;

    public MyBaseAdapter(Context context, List<Oreder> lists, int index) {
        this.context = context;
        this.lists = lists;
        this.index = index;
    }

    public List<Oreder> getLists() {
        return lists;
    }

    public void setLists(List<Oreder> lists) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder2 viewHoder2=null;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.order_item,null);
            viewHoder2=new ViewHoder2();
            viewHoder2.tv_item_jiedan= (TextView) convertView.findViewById(R.id.tv_item_jiedan);
            viewHoder2.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            viewHoder2.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            viewHoder2.tv_typ= (TextView) convertView.findViewById(R.id.tv_typ);
            viewHoder2.bt_item_chakan= (Button) convertView.findViewById(R.id.bt_item_chakan);
            viewHoder2.bt_item_quxiao= (Button) convertView.findViewById(R.id.bt_item_quxiao);
            viewHoder2.bt_fabu= (Button) convertView.findViewById(R.id.bt_item_fabu);
            viewHoder2.iv_image= (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHoder2);
        }else {
            viewHoder2= (ViewHoder2) convertView.getTag();
        }
        /*"model": "",  //型号
                "engineer_id": "",//工程师ID
                "order_state": 1,   //订单状态:1,4,5,6为未完成,2为已完成,3为已取消//1刚发布的订单 ,4确认订单,5已支付,6已接单
                "class": "",    //类型
                "service_address": "", //服务地址
                "fault": "",    //故障点
                "custom_id": 2,     //客户id
                "custom_name": "",  //客户名
                "id": 1,            //订单ID
                "service_time": "", //服务时间
                "addtime": "",      //添加时间
                "image2": "",       //图片二
                "price": "",        //维修金额
                "image1": "",       //图片一
                "pay_type": "",     //支付类型
                "address_id": "",   //客户关联地址id
                "isdel": 0,         //是否已删除
                "brand": "",        //品牌
                "fault_desc": "",   //故障描述
                "image3": "",       //图片三
                "custom_phone": "", //客户电话
                "order_type": 1     //订单类型
        "region":"香坊区"             //区
        "lat":"45.111"        //纬度
        "lon":"经度"        //经度
        "city_name":"哈尔滨市"//城市名
        "city_code":"48"    //城市编码*/
        final Oreder oreder = lists.get(position);
        viewHoder2.bt_item_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (index==0){
                   Map map=new HashMap();
                   map.put("id",oreder.getId()+"");
                   map.put("custom_id",Y.USER.getUser_id()+"");
                   Y.get(YURL.CAN_CELORDER, map, new Y.MyCommonCall<String>() {
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
               }else {
                   Map map=new HashMap();
                   map.put("id",oreder.getId()+"");
                   map.put("custom_id",Y.USER.getUser_id()+"");
                   Y.get(YURL.DEL_ORDER, map, new Y.MyCommonCall<String>() {
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
            }
        });
        Y.i(oreder.toString());
        final int order_state = oreder.getOrder_state();
        int order_type = oreder.getOrder_type();
        String service_address = oreder.getService_address();
        String service_time = oreder.getService_time();
        String image1 = oreder.getImage1();
        String custom_phone = oreder.getCustom_phone();
        if (index==0){
            viewHoder2.bt_item_quxiao.setText("取消订单");
        }else {
            viewHoder2.bt_item_quxiao.setText("删除订单");
        }
        switch (order_type){
            case 1:
                viewHoder2.tv_typ.setText("手机"+oreder.getId());
                break;
            case 2:
                viewHoder2.tv_typ.setText("电脑"+oreder.getId());
                break;
            case 3:
                viewHoder2.tv_typ.setText("家电"+oreder.getId());
                break;
        }
        //订单状态:1,4,5,6为未完成,2为已完成,3为已取消//1刚发布的订单 ,4确认订单,5已支付,6已接单
        switch (order_state){
            case 1:
                viewHoder2.tv_item_jiedan.setText("等待接单");
                viewHoder2.bt_fabu.setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHoder2.tv_item_jiedan.setText("已完成");
                break;
            case 3:
                viewHoder2.tv_item_jiedan.setText("已取消");
                break;
            case 4:
                viewHoder2.tv_item_jiedan.setText("确认订单");
                break;
            case 5:
                viewHoder2.tv_item_jiedan.setText("已支付");
                break;
            case 6:
                viewHoder2.tv_item_jiedan.setText("已接单");
                break;
        }
        viewHoder2.bt_item_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            //订单状态:1,4,5,6为未完成,2为已完成,3为已取消//1刚发布的订单 ,4确认订单,5已支付,6已接单
            public void onClick(View v) {
                if (order_state==1){
                    Intent intent=new Intent(context,Activity_Order_Success.class);
                    intent.putExtra("Bean",oreder);
                    context.startActivity(intent);
                }else if (order_state==2){
                    Intent intent=new Intent(context,Activity_Order_Over.class);
                    intent.putExtra("Bean",oreder);
                    context.startActivity(intent);
                }else if (order_state==3){
                    Intent intent=new Intent(context,Activity_Order_Delete.class);
                    intent.putExtra("Bean",oreder);
                    context.startActivity(intent);
                }else if (order_state==6){
                    Intent intent=new Intent(context,Activity_Order_Have.class);
                    intent.putExtra("Bean",oreder);
                    context.startActivity(intent);
                }
            }
        });
        viewHoder2.tv_address.setText(oreder.getService_address()+"---");
        viewHoder2.tv_time.setText(oreder.getAddtime());
        ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
        x.image().bind(viewHoder2.iv_image, YURL.HOST+image1,options);
        return convertView;
    }

}
class ViewHoder2{
    TextView tv_item_jiedan,tv_time,tv_address,tv_typ;
    Button bt_item_chakan,bt_item_quxiao,bt_fabu;
    ImageView iv_image;
}
