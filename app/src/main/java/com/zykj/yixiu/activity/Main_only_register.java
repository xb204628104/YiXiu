package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/20.
 */

public class Main_only_register extends Activity {
    @Bind(R.id.et_register_num)
    EditText etRegisterNum;
    @Bind(R.id.et_register_yan)
    EditText etRegisterYan;
    @Bind(R.id.bt_register_getyan)
    Button btRegisterGetyan;
    @Bind(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yixiu_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_register_getyan, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register_getyan:
                break;
            case R.id.bt_register:
                break;
        }
    }
}
