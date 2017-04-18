package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            /*    final ArrayList<String> list=new ArrayList<String>();
                list.add("今天");
                list.add("明天");
                list.add("后天");
                ArrayList<ArrayList<String>> list1=new ArrayList<ArrayList<String>>();
                ArrayList<String> list1_1=new ArrayList<String>();
                list1_1.add("1点");
                list1_1.add("2点");
                list1_1.add("3点");
                list1.add(list1_1);
                ArrayList<String> list1_2=new ArrayList<String>();
                list1_2.add("1点");
                list1_2.add("2点");
                list1_2.add("3点");
                list1.add(list1_2);
                OptionsPickerView  pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        tvCallTime.setText(list.get(options1));
                    }
                })
                        .setSubmitText("确定")//确定按钮文字
                        .setCancelText("取消")//取消按钮文字
                        .setTitleText("城市选择")//标题
                        .setSubCalSize(18)//确定和取消文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                        .setContentTextSize(18)//滚轮文字大小
                        .setLinkage(false)//设置是否联动，默认true
                        .setLabels("省", "市", "区")//设置选择的三级单位
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .setCyclic(false, false, false)//循环与否
                        .setSelectOptions(1, 1, 1)  //设置默认选中项
                        .setOutSideCancelable(false)//点击外部dismiss default true
                        .isDialog(true)//是否显示为对话框样式
                        .build();

                pvOptions.setPicker(list,list1,null);//添加数据源*/
                //时间选择器
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
                Intent intent=new Intent(Activity_Callservice.this,BaiDuMap.class);
                startActivity(intent);
                break;
        }
    }
}
