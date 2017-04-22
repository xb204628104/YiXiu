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
 * Created by zykj on 2017/4/19.
 */

public class Activity_Prcenter_myself_service extends Activity {
    @Bind(R.id.ll_yixiuzixun)
    LinearLayout llYixiuzixun;
    @Bind(R.id.ll_chuangjian)
    LinearLayout llChuangjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_yixiuzixun, R.id.ll_chuangjian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_yixiuzixun:
                Intent intent=new Intent(Activity_Prcenter_myself_service.this,Activity_Prcenter_myself_service_message.class);
                startActivity(intent);
                break;
            case R.id.ll_chuangjian:
                Intent intent1=new Intent(Activity_Prcenter_myself_service.this,Activity_Prcenter_myself_service_common.class);
                startActivity(intent1);
                break;
        }
    }
}
