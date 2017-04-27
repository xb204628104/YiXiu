package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zykj.yixiu.R;
import com.zykj.yixiu.adapter.AdressBaseAdapter;
import com.zykj.yixiu.bean.Address;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_Callservice_Adress extends Activity {
    @Bind(R.id.ll_add_adress)
    LinearLayout llAddAdress;
    @Bind(R.id.lisetview)
    ListView lisetview;
    private List<Address> lists = new ArrayList<Address>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_address_service);
        ButterKnife.bind(this);
        Map<String,String> map=new HashMap<String, String>();
        map.put("user_id",Y.USER.getUser_id()+"");
        Y.post(YURL.SELECT_ADDRESS, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                if (Y.getRespCode(result)){
                    Y.t("成功");
                    String data = Y.getData(result);
                    lists = JSON.parseArray(data, Address.class);
                    AdressBaseAdapter adressBaseAdapter=new AdressBaseAdapter(lists,Activity_Callservice_Adress.this);
                    lisetview.setAdapter(adressBaseAdapter);
                }else {
                    Y.t("失败了");
                }

            }
        });




    }

    @OnClick(R.id.ll_add_adress)
    public void onClick() {
        Intent intent = new Intent(Activity_Callservice_Adress.this, Activity_Callservice_Adress_Edit.class);
        startActivity(intent);
    }
}
