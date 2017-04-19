package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/19.
 */

public class Main_only_register_pwd extends Activity {
    @Bind(R.id.et_repwd)
    EditText etRepwd;
    @Bind(R.id.et_re_onebyon)
    EditText etReOnebyon;
    @Bind(R.id.bt_pwd_ok)
    Button btPwdOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yixiu_register_pwd);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_pwd_ok)
    public void onClick() {
        Intent intent=new Intent(Main_only_register_pwd.this,Main_only.class);
        startActivity(intent);
    }
}
