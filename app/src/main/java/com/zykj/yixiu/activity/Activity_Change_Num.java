package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/27.
 */

public class Activity_Change_Num extends Activity {
    @Bind(R.id.et_change_num)
    EditText etChangeNum;
    @Bind(R.id.bt_ok)
    Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_num);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_ok)
    public void onClick() {
        String num = etChangeNum.getText().toString().trim();
        if (TextUtils.isEmpty(num)){
            Y.t("请输入手机号");
        }
        Intent intent=getIntent();

        intent.putExtra("num",num);
        setResult(100,intent);
        finish();
    }
}
