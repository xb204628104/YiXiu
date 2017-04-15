package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/11.
 */

public class Mainactivity extends Activity {
    @Bind(R.id.main_set_phone)
    FrameLayout mainSetPhone;
    @Bind(R.id.main_set_computer)
    FrameLayout mainSetComputer;
    @Bind(R.id.main_set_house)
    FrameLayout mainSetHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.main_set_phone, R.id.main_set_computer, R.id.main_set_house})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_set_phone:
                Intent intent=new Intent(Mainactivity.this,Activity_Phoneservice.class);
                startActivity(intent);
                break;
            case R.id.main_set_computer:
                break;
            case R.id.main_set_house:
                break;
        }
    }
}
