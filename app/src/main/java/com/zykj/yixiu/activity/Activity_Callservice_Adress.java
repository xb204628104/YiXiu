package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
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
    private AdressBaseAdapter adressBaseAdapter;
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
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    Y.t("成功");
                    String data = Y.getData(result);
                   lists = JSON.parseArray(data, Address.class);
                    adressBaseAdapter=new AdressBaseAdapter(lists,Activity_Callservice_Adress.this);
                    lisetview.setAdapter(adressBaseAdapter);
                }else {
                    Y.t("失败了");
                }

            }
        });
        lisetview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Y.t(lists.get(position).getAddress().toString());
                String address=lists.get(position).getAddress().toString();
                String name=lists.get(position).getName().toString();
                String phine=lists.get(position).getPhone().toString();
                Y.i(name+"---");
                Y.i(phine+"---");
                int user_id=lists.get(position).getUser_id();
                Y.i(user_id+"---user_id");
                int address_id=lists.get(position).getAddress_id();
                Y.i("Address---"+address_id);
//                Intent intent=new Intent(Activity_Callservice_Adress.this,Activity_Callservice.class);
//                intent.putExtra("address",lists.get(position).getAddress().toString());
//                startActivityForResult(intent,100);
//                finish();
                Y.ADDRESS.setName(name);
                Y.ADDRESS.setPhone(phine);
                Y.ADDRESS.setAddress_id(address_id);
                Y.ADDRESS.setUser_id(user_id);
                Intent intent=new Intent(Activity_Callservice_Adress.this,Activity_Callservice.class);
                intent.putExtra("add",address);
                intent.putExtra("address_id",address_id);
                intent.putExtra("name",name);
                intent.putExtra("phone",phine);
                intent.putExtra("user_id",user_id);
                setResult(100,intent);
                finish();
            }
        });

    }

    @OnClick(R.id.ll_add_adress)
    public void onClick() {
        Intent intent = new Intent(Activity_Callservice_Adress.this, Activity_Callservice_Adress_Edit.class);
        startActivity(intent);
    }
}
