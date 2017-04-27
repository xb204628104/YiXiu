package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_Callservice_Adress_Edit extends Activity {
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_edit_name)
    EditText etEditName;
    @Bind(R.id.ll_edit_adress)
    LinearLayout llEditAdress;
    @Bind(R.id.sw_edie_moren)
    Switch swEdieMoren;
    @Bind(R.id.tv_finsh)
    TextView tvFinsh;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.ll_change_num)
    LinearLayout llChangeNum;
    int index = 0;
    @Bind(R.id.et_edit_adress)
    TextView etEditAdress;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_address_edit);
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(Y.USER.getPhone())) {
            tvNum.setText(Y.USER.getPhone());
        }
        if (!TextUtils.isEmpty(Y.USER.getUsername())) {
            etEditName.setText(Y.USER.getUsername());
        }
        Intent intent = getIntent();
        if (intent != null) {
            String address = intent.getStringExtra("adress");//地址
            String num = intent.getStringExtra("num");//手机号
            tvNum.setText(num);
            etEditAdress.setText(address);
        }
        if (swEdieMoren.isChecked()) {
            index = 1;
        }
    }

    @OnClick({R.id.tv_finsh, R.id.ll_edit_adress, R.id.et_edit_adress, R.id.sw_edie_moren, R.id.ll_change_num})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_finsh:
                final String name = etEditName.getText().toString().trim();//名字
                String address = etEditAdress.getText().toString().trim();//地址
                final String num = tvNum.getText().toString().trim();//手机号
                final Map<String, String> map = new HashMap<String, String>();
                map.put("name", name);
                map.put("address", address);
                map.put("phone", num);
                map.put("user_id", Y.USER.getUser_id() + "");
                map.put("lat", Y.ADDRESS.getLat());
                map.put("lon", Y.ADDRESS.getLon());
                map.put("city_name", Y.ADDRESS.getCity_name());
                map.put("city_code", Y.ADDRESS.getCity_code());
                map.put("isdefault", index + "");
                Y.post(YURL.ADD_ADDRESS, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            Y.t("成功");
                            String name1 = map.get("name");
                            String address1 = map.get("address");
                            String phone = map.get("phone");
                            String user_id = map.get("user_id");
                            String lat = map.get("lat");
                            String lon = map.get("lon");
                            String city_name = map.get("city_name");
                            String city_code = map.get("city_code");
                            String isdefault = map.get("isdefault");
                            Y.ADDRESS.setPhone(phone);
                            Y.ADDRESS.setName(name1);
                            Y.ADDRESS.setAddress(address1);
                            Y.i(name1 + "--名字是");
                            Y.i(address1 + "--地址是");
                            Y.i(phone + "--电话号码是");
                            Y.i(user_id + "--用户id");
                            Y.i(lat + "--精度");
                            Y.i(lon + "--维度");
                            Y.i(city_code + "--城市编码");
                            Y.i(city_name + "--城市名称");
                            Y.i(isdefault + "--woshi");
                            finish();
                        } else {
                            Y.t("失败");
                        }
                    }
                });
                break;
            case R.id.et_edit_adress:
                Intent intent = new Intent(Activity_Callservice_Adress_Edit.this, Activity_Baidu_Map.class);
                startActivity(intent);
                break;
            case R.id.sw_edie_moren:
                break;
            case R.id.ll_change_num:
                Intent intent2 = new Intent(Activity_Callservice_Adress_Edit.this, Activity_Change_Num.class);
                startActivityForResult(intent2, 100);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            Bundle extras = data.getExtras();
            num = extras.getString("num");
            tvNum.setText(num);
        }
    }
}
