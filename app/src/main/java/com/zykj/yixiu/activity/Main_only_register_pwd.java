package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.User;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/19.
 */

public class Main_only_register_pwd extends Activity {
    @Bind(R.id.et_repwd)
    EditText etRepwd;
    //第一次的密码
    @Bind(R.id.et_re_onebyon)
    EditText etReOnebyon;
    //第二次的密码
    @Bind(R.id.iv_pwd_xianshi)
    ImageView ivPwdXianshi;
    //显示密码
    @Bind(R.id.bt_pwd_ok)
    Button btPwdOk;
    private String data;
    //确定按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yixiu_register_pwd);
        ButterKnife.bind(this);
        //把上个页面的data取回来
        //返回的用户令牌
        Intent intent = getIntent();
        if (intent!=null){
            data = intent.getStringExtra("data");
        }
    }

    @OnClick({R.id.iv_pwd_xianshi, R.id.bt_pwd_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pwd_xianshi:
                //按下按钮密码位显示的状态
                etRepwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                etReOnebyon.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.bt_pwd_ok:
                //把密码和再次输入的密码取出来
                String onepwd = etRepwd.getText().toString().trim();
                String twopwd = etReOnebyon.getText().toString().trim();
                //判断密码是否为空
                if (TextUtils.isEmpty(onepwd)){
                    Y.t("密码不能为空");
                    return;
                }
                //判断再次输入的密码是否为空
                if (TextUtils.isEmpty(twopwd)){
                    Y.t("请再次输入密码");
                    return;
                }
                //判断俩次输入的密码是否一样
                if (!onepwd.equals(twopwd)){
                    Y.t("两次输入的密码不一致");
                    return;
                }
                Map<String,String> map=new HashMap<String, String>();
                map.put("password",onepwd);
                map.put("token",data);
                //情求设置密码的接口
                Y.get(YURL.SET_PASSWORD, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //在成功后关闭dialog
                        StyledDialog.dismissLoading();
                        //判断返回的resp_code是否为 0
                        if (Y.getRespCode(result)){
                            Y.t("密码设置成功");
                            //成功之后跳转到登录页面
                            Intent intent=new Intent(Main_only_register_pwd.this,Main_only.class);
                            intent.putExtra("token",data);
                            startActivity(intent);
                        }else {
                            //返回的resp-code为 1
                            Y.t("密码设置失败");
                        }

                    }
                });

                break;
        }
    }
}
