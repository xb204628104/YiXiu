package com.zykj.yixiu.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Stroke;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.Computer;
import com.zykj.yixiu.bean.House;
import com.zykj.yixiu.bean.Phone;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static cn.finalteam.toolsfinal.DateUtils.getTime;

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
    String add;
    private Button bt_ok;

    private PopupWindow popupWindow;
    private String order_type;
    private Phone phone;
    private Computer computer;
    private House house;
    private int address_id;
    private int user_id;
    private String name;
    private String num;
    private Button button;
    private String tvcallTime;
    private String shijian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_call_service);
        ButterKnife.bind(this);
        bt_ok= (Button) findViewById(R.id.bt_ok);
        button= (Button) findViewById(R.id.bt_ok2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Y.i(Y.ADDRESS.getPhone()+"点化石");
                Y.i(Y.ADDRESS.getName()+"明星是");
                Y.i(tvcallTime);
            }
        });
        Intent intent = getIntent();

        if (intent!=null){
            order_type = intent.getStringExtra("order_type");
            Y.i("--"+order_type);
            switch (order_type){
                case "1":
                    phone = (Phone) intent.getSerializableExtra("bean");
                    break;
                case "2":
                    computer = (Computer) intent.getSerializableExtra("bean");
                    break;
                case "3":
                    house = (House)intent.getSerializableExtra("bean");
                    break;
            }
        }

        final String tvcallMap = tvCallMap.getText().toString().trim();
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (order_type){
                    case "1":
                        RequestParams requestParams =new RequestParams(YURL.ADD_ORDER);
                        requestParams.setMultipart(true);
                        requestParams.addBodyParameter("order_type","1");
                        requestParams.addBodyParameter("brand",phone.getBrand());
                        requestParams.addBodyParameter("model",phone.getModle());
                        requestParams.addBodyParameter("fault",phone.getFault());
                        requestParams.addBodyParameter("fault_desc",phone.getDescribe());
                        requestParams.addBodyParameter("category","");
                        requestParams.addBodyParameter("image1",new File(phone.getImage1()));
                        requestParams.addBodyParameter("service_time",tvcallTime);
                        requestParams.addBodyParameter("service_address",add);
                        requestParams.addBodyParameter("custom_phone",Y.ADDRESS.getPhone());
                        requestParams.addBodyParameter("custom_name",Y.ADDRESS.getName());
                        requestParams.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                        requestParams.addBodyParameter("address_id",Y.ADDRESS.getAddress_id()+"");
                        x.http().post(requestParams, new Y.MyCommonCall<String>() {
                            @Override
                            public void onSuccess(String result) {
                                if (Y.getRespCode(result)){
                                    Intent intent1=new Intent(Activity_Callservice.this,Mainactivity.class);
                                    startActivity(intent1);
                                    Y.t("手机上传成功");
                                    Y.i(""+phone.getBrand());
                                    Y.i(""+new File(phone.getImage1()));
                                    Y.i(""+phone.getModle());
                                    Y.i(""+phone.getFault());
                                    Y.i(""+phone.getDescribe());
                                    Y.i(""+tvcallTime);
                                    Y.i(""+add);
                                    Y.i(""+Y.ADDRESS.getPhone());
                                    Y.i(""+Y.ADDRESS.getName());
                                    Y.i(""+Y.USER.getUser_id());
                                    Y.i("第一个"+Y.ADDRESS.getAddress_id());
                                    Y.i(""+address_id);
                                    Y.OREDER.setBrand(phone.getBrand());
                                    Y.OREDER.setService_time(tvcallTime);
                                    Y.OREDER.setService_address(add);
                                    Y.OREDER.setCustom_name(Y.ADDRESS.getName());
                                    Y.OREDER.setCustom_phone(Y.ADDRESS.getPhone());
                                }else {
                                    Y.t("失败");
                                }
                            }
                        });
                        break;
                    case "2":
                        RequestParams requestParams1 =new RequestParams(YURL.ADD_ORDER);
                        requestParams1.setMultipart(true);
                        requestParams1.addBodyParameter("order_type","2");
                        requestParams1.addBodyParameter("brand",computer.getBrand());
                        requestParams1.addBodyParameter("model",computer.getModle());
                        requestParams1.addBodyParameter("fault",computer.getFault());
                        requestParams1.addBodyParameter("fault_desc",computer.getDescribe());
                        requestParams1.addBodyParameter("category",computer.getCategory());
                        requestParams1.addBodyParameter("image1",new File(computer.getImage1()));
                        requestParams1.addBodyParameter("service_time",tvcallTime);
                        requestParams1.addBodyParameter("service_address",add);
                        requestParams1.addBodyParameter("custom_phone",Y.ADDRESS.getPhone());
                        requestParams1.addBodyParameter("custom_name",Y.ADDRESS.getName());
                        requestParams1.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                        requestParams1.addBodyParameter("address_id",Y.ADDRESS.getAddress_id()+"");
                        x.http().post(requestParams1, new Y.MyCommonCall<String>() {
                            @Override
                            public void onSuccess(String result) {
                                if (Y.getRespCode(result)){
                                    Intent intent2=new Intent(Activity_Callservice.this,Mainactivity.class);
                                    startActivity(intent2);
                                    Y.t("电脑上传成功");
                                    Y.i(""+computer.getBrand());
                                    Y.i(""+new File(computer.getImage1()));
                                    Y.i(""+computer.getModle());
                                    Y.i(""+computer.getFault());
                                    Y.i(""+tvcallTime);
                                    Y.i(""+add);
                                    Y.i(""+Y.ADDRESS.getName());
                                    Y.i(""+Y.ADDRESS.getUser_id());
                                    Y.i(""+address_id);
                                    Y.OREDER.setService_time(tvcallTime);
                                    Y.OREDER.setService_address(add);
                                    Y.OREDER.setCustom_name(Y.ADDRESS.getName());
                                    Y.OREDER.setCustom_phone(Y.ADDRESS.getPhone());
                                }else {
                                    Y.t("失败");
                                }
                            }
                        });
                        break;
                    case "3":
                        RequestParams requestParams2 =new RequestParams(YURL.ADD_ORDER);
                        requestParams2.setMultipart(true);
                        requestParams2.addBodyParameter("order_type","3");
                        requestParams2.addBodyParameter("brand",house.getBrand());
                        requestParams2.addBodyParameter("model",house.getModle());
                        requestParams2.addBodyParameter("fault",house.getFault());
                        requestParams2.addBodyParameter("fault_desc",house.getDescribe());
                        requestParams2.addBodyParameter("category",house.getCategory());
                        requestParams2.addBodyParameter("image1",new File(house.getImage1()));
                        requestParams2.addBodyParameter("service_time",tvcallTime);
                        requestParams2.addBodyParameter("service_address",add);
                        requestParams2.addBodyParameter("custom_phone",Y.ADDRESS.getPhone());
                        requestParams2.addBodyParameter("custom_name",Y.ADDRESS.getName());
                        requestParams2.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                        requestParams2.addBodyParameter("address_id",Y.ADDRESS.getAddress_id()+"");
                        x.http().post(requestParams2, new Y.MyCommonCall<String>() {
                            @Override
                            public void onSuccess(String result) {
                                if (Y.getRespCode(result)){
                                    Intent intent1=new Intent(Activity_Callservice.this,Mainactivity.class);
                                    startActivity(intent1);
                                    Y.t("家电上传成功");
                                    Y.i(""+house.getBrand());
                                    Y.i(""+new File(house.getImage1()));
                                    Y.i(""+house.getModle());
                                    Y.i(""+house.getFault());
                                    Y.i(""+tvcallTime);
                                    Y.i(""+add);
                                    Y.i(""+Y.ADDRESS.getName());
                                    Y.i(""+Y.ADDRESS.getUser_id());
                                    Y.i(""+address_id);
                                    Y.OREDER.setService_time(tvcallTime);
                                    Y.OREDER.setService_address(add);
                                    Y.OREDER.setCustom_name(Y.ADDRESS.getName());
                                    Y.OREDER.setCustom_phone(Y.ADDRESS.getPhone());
                                }else {
                                    Y.t("失败");
                                }
                            }
                        });
                        break;
                }

//                // 步骤1： 创建UI视图
//                View  view =getLayoutInflater().inflate(R.layout.item_fabu,null);
//                // 步骤2： 创建PopupWindow
//                popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT,true);
//                // 步骤3； 设置动画(可选)
//                popupWindow.setAnimationStyle(android.R.style.Animation_Toast); //使用系统的
//
//
//                //步骤4;  显示window 并且指定位置
//                popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_home_call_service,null), Gravity.CENTER_HORIZONTAL,0,0);
//                //Gravity
//                //BOTTOM  下
//                // Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL     显示在下并且水平居中
//
//                //步骤5： 设置背景window半透明
//                WindowManager.LayoutParams attributes = getWindow().getAttributes(); //获取程序的WINDOW属性
//                attributes.alpha=1.0f;  //0.1-1.0
//                getWindow().setAttributes(attributes);// 在把所有属性重新设置回去
//
//                //步骤6：  popupWindow 关闭的时候恢复正常透明度
//                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                        WindowManager.LayoutParams attributes = getWindow().getAttributes(); //获取程序的WINDOW属性
//                        attributes.alpha=1.0F;  //0.1-1.0
//                        getWindow().setAttributes(attributes);// 在把所有属性重新设置回去
//                    }
//                });

               /* if (TextUtils.isEmpty(tvcallTime)){
                    Y.t("请选择您的服务时间");
                    return;
                }
                if (TextUtils.isEmpty(tvcallMap)){
                    Y.t("请选择您的服务地址");
                    return;
                }*/
               /* Map map=new HashMap();
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
                });*/

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
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");//利用占位符来格式化时间
                        shijian = format.format(date).toString();
                        Y.i(date + "");
                        tvCallTime.setText(shijian);
                        tvcallTime= tvCallTime.getText().toString().trim();
                    }
                })
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
            case R.id.ll_call_map:
                Intent intent=new Intent(Activity_Callservice.this,Activity_Callservice_Adress.class);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==100){
         add= data.getStringExtra("add");
         name= data.getStringExtra("name");
         num=data.getStringExtra("phone");
            address_id=data.getIntExtra("address_id",0);
            user_id=data.getIntExtra("user_id",0);
            tvCallMap.setText(add);
        }
    }
}
