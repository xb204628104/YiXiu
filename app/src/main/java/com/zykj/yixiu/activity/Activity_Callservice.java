package com.zykj.yixiu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private Button bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_call_service);
        ButterKnife.bind(this);
        bt_ok= (Button) findViewById(R.id.bt_ok);
        Intent intent = getIntent();
        if (intent!=null){

        }
        final String tvcallTime = tvCallTime.getText().toString().trim();
        final String tvcallMap = tvCallMap.getText().toString().trim();
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvcallTime)){
                    Y.t("请选择您的服务时间");
                    return;
                }
                if (TextUtils.isEmpty(tvcallMap)){
                    Y.t("请选择您的服务地址");
                    return;
                }
                Map map=new HashMap();
                Y.post(YURL.ADD_ORDER,map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("成功");
                            //Dialog dialog=new Dialog(getApplicationContext());
                        }else {
                            Y.t("失败");
                        }
                    }
                });

            }
        });


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
