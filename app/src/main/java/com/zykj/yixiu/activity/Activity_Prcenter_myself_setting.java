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

public class Activity_Prcenter_myself_setting extends Activity {
    @Bind(R.id.ll_set_pwd)
    LinearLayout llSetPwd;
    @Bind(R.id.ll_set_image)
    LinearLayout llSetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_set_pwd, R.id.ll_set_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_set_pwd:
                Intent intent=new Intent(Activity_Prcenter_myself_setting.this,Activity_Prcenter_myself_setting_pwd.class);
                startActivity(intent);
                break;
            case R.id.ll_set_image:
                Intent intent1=new Intent(Activity_Prcenter_myself_setting.this,Activity_Prcenter_myself_setting_image.class);
                startActivity(intent1);
                break;
        }
    }
}
