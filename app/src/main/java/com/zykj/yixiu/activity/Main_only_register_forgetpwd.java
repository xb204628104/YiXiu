package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/19.
 */

public class Main_only_register_forgetpwd extends Activity {
    @Bind(R.id.et_forget_num)
    EditText etForgetNum;
    @Bind(R.id.et_forget_yan)
    EditText etForgetYan;
    @Bind(R.id.bt_forget_getyan)
    Button btForgetGetyan;
    @Bind(R.id.bt_forget_next)
    Button btForgetNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yixiu_register_forgetpwd);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_forget_getyan, R.id.bt_forget_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_forget_getyan:
                break;
            case R.id.bt_forget_next:
                Intent intent=new Intent(Main_only_register_forgetpwd.this,Main_only_register_pwd.class);
                startActivity(intent);
                break;
        }
    }
}
