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

    }


    @OnClick({R.id.ll_call_time, R.id.ll_call_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_call_time:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        Y.i(date + "");
                        tvCallTime.setText(date+"");
                    }
                })
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.ll_call_map:
                Intent intent=new Intent(Activity_Callservice.this,Activity_Callservice_Adress.class);
                startActivity(intent);

                break;
        }
    }

}
