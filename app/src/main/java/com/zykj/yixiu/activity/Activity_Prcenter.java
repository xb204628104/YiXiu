package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class Activity_Prcenter extends Activity {
    @Bind(R.id.ll_pc_undone)
    LinearLayout llPcUndone;
    @Bind(R.id.ll_pc_offthestocks)
    LinearLayout llPcOffthestocks;
    @Bind(R.id.ll_pc_canceled)
    LinearLayout llPcCanceled;
    @Bind(R.id.ll_pc_mymeans)
    LinearLayout llPcMymeans;
    @Bind(R.id.ll_pc_mywallet)
    LinearLayout llPcMywallet;
    @Bind(R.id.ll_pc_address)
    LinearLayout llPcAddress;
    @Bind(R.id.ll_pc_approve)
    LinearLayout llPcApprove;
    @Bind(R.id.ll_pc_terrace)
    LinearLayout llPcTerrace;
    @Bind(R.id.ll_pc_aboutus)
    LinearLayout llPcAboutus;
    @Bind(R.id.ll_pc_setting)
    LinearLayout llPcSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_pc_undone, R.id.ll_pc_offthestocks, R.id.ll_pc_canceled, R.id.ll_pc_mymeans, R.id.ll_pc_mywallet, R.id.ll_pc_address, R.id.ll_pc_approve, R.id.ll_pc_terrace, R.id.ll_pc_aboutus, R.id.ll_pc_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_pc_undone:
                Intent intent7=new Intent(Activity_Prcenter.this,null);
                startActivity(intent7);
                break;
            case R.id.ll_pc_offthestocks:
                break;
            case R.id.ll_pc_canceled:
                break;
            case R.id.ll_pc_mymeans:
                Intent intent=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself.class);

                startActivity(intent);
                break;
            case R.id.ll_pc_mywallet:
                Intent intent1=new Intent(Activity_Prcenter.this,Activity_Prcenter_mywallet.class);
                startActivity(intent1);
                break;
            case R.id.ll_pc_address:
                Intent intent2=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself_address.class);
                startActivity(intent2);
                break;
            case R.id.ll_pc_approve:
                Intent intent3=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself_approve.class);
                startActivity(intent3);
                break;
            case R.id.ll_pc_terrace:
                Intent intent4=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself_service.class);
                startActivity(intent4);
                break;
            case R.id.ll_pc_aboutus:
                Intent intent5=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself_aboutus.class);
                startActivity(intent5);
                break;
            case R.id.ll_pc_setting:
                Intent intent6=new Intent(Activity_Prcenter.this,Activity_Prcenter_myself_setting.class);
                startActivity(intent6);
                break;
        }
    }
}
