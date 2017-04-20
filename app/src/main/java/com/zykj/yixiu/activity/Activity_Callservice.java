package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class Activity_Callservice extends Activity {


    @Bind(R.id.ll_call_time)
    LinearLayout llCallTime;

    @Bind(R.id.ll_call_map)
    LinearLayout llCallMap;
    @Bind(R.id.tv_call_time)
    TextView tvCallTime;
    @Bind(R.id.tv_call_map)
    TextView tvCallMap;
    private MapView map;
    private BaiduMap mymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_call_service);
        ButterKnife.bind(this);
        map= (MapView) findViewById(R.id.baidu);

    }


    @OnClick({R.id.ll_call_time, R.id.ll_call_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_call_time:
                ArrayList<String> item1=new ArrayList<String>();
                item1.add("今天");
                item1.add("名天");
                item1.add("后天");
                ArrayList<ArrayList<String>>item2=new ArrayList<ArrayList<String>>();
                ArrayList<String> item2_1=new ArrayList<String>();
                ArrayList<String> item2_2=new ArrayList<String>();
                ArrayList<String> item2_3=new ArrayList<String>();
                for (int i = 0; i <=24 ; i++) {
                    item2_1.add(i+"点");
                    item2_2.add(i+"点");
                    item2_3.add(i+"点");
                }
                item2.add(item2_1);
                item2.add(item2_2);
                item2.add(item2_3);
                ArrayList<ArrayList<ArrayList<String>>> item3=new ArrayList<ArrayList<ArrayList<String>>>();
                ArrayList<ArrayList<String>> item3_1=new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> item3_2=new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> item3_3=new ArrayList<ArrayList<String>>();
                ArrayList<String> item3_1_1=new ArrayList<String>();
                ArrayList<String> item3_1_2=new ArrayList<String>();
                ArrayList<String> item3_1_3=new ArrayList<String>();
                item3_1_1.add("10分");
                item3_1_1.add("20分");
                item3_1_1.add("30分");
                item3_1_1.add("40分");
                item3_1_1.add("50分");
                item3_1_1.add("60分");
                item3_1_2.add("10分");
                item3_1_2.add("20分");
                item3_1_2.add("30分");
                item3_1_2.add("40分");
                item3_1_2.add("50分");
                item3_1_2.add("60分");
                item3_1_3.add("10分");
                item3_1_3.add("20分");
                item3_1_3.add("30分");
                item3_1_3.add("40分");
                item3_1_3.add("50分");
                item3_1_3.add("60分");
                item3_1.add(item3_1_1);
                item3_2.add(item3_1_2);
                item3_3.add(item3_1_3);
                item3.add(item3_1);
                item3.add(item3_2);
                item3.add(item3_3);
                OptionsPickerView optionsPickerView=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                    }
                }).build();
                optionsPickerView.setPicker(item1,item2,item3);
                optionsPickerView.show();
                /*TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        Y.i(date + "");
                        tvCallTime.setText(date+"");
                    }
                })
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();*/
                break;
            case R.id.ll_call_map:
               /* mymap = map.getMap();
                mymap.setMapType(BaiduMap.MAP_TYPE_NORMAL);*/
                break;
        }
    }
   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        map.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        map.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        map.onPause();
    }*/
}
