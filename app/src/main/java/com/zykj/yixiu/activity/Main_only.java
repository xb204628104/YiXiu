package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class Main_only extends Activity {
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_yixiu_zhuce)
    TextView tvYixiuZhuce;
    @Bind(R.id.tv_yixiu_forget)
    TextView tvYixiuForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yixiu_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.tv_yixiu_zhuce, R.id.tv_yixiu_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                Intent intent=new Intent(Main_only.this,Mainactivity.class);
                startActivity(intent);
                break;
            case R.id.tv_yixiu_zhuce:
                Intent intent1=new Intent(Main_only.this,Main_only_register.class);
                startActivity(intent1);
                break;
            case R.id.tv_yixiu_forget:
                Intent intent2=new Intent(Main_only.this,Main_only_register_forgetpwd.class);
                startActivity(intent2);
                break;
        }
    }
}
