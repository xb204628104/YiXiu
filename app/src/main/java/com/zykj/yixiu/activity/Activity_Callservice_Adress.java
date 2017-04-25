package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_Callservice_Adress extends Activity {
    @Bind(R.id.ll_add_adress)
    LinearLayout llAddAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_address_service);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_add_adress)
    public void onClick() {
        Intent intent=new Intent(Activity_Callservice_Adress.this,Activity_Callservice_Adress.class);
        startActivity(intent);
    }
}
