package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.User;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.Map;

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
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.et_pwd)
    EditText etPwd;

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
                //把手机号和密码取出来
                final String num = etNum.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                //判断手机号是否为空
                if (TextUtils.isEmpty(num)){
                    Y.t("请输入手机号");
                    return;
                }
                //判断手机号是否为合法的手机号
                if (!Y.isMobileNO(num)){
                    Y.t("请输入正确的手机号");
                    return;
                }
                //判断验证码是否为空
                if (TextUtils.isEmpty(pwd)){
                    Y.t("请输入密码");
                    return;
                }
                //创建map的集合 把需要的数据传上去
                Map<String,String> map=new HashMap<String, String>();
                map.put("phone",num);//phone 电话号码
                map.put("password",pwd);// password  密码
                Y.get(YURL.LOGIN,map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //成功后关闭dialog
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("登录成功");
                            User user = JSON.parseObject(Y.getData(result), User.class);
                            Y.USER=user;
                            user.setIcon(getIntent().getStringExtra("token"));
                            Y.TOKEN=user.getToken();
                            Y.USER.setPhone(num);
                            Y.i(Y.USER.getUser_id()+"");
                            Y.i(Y.TOKEN);
                            //成功之后跳转页面 调到主页面
                            Intent intent = new Intent(Main_only.this, Mainactivity.class);
                            startActivity(intent);
                        }else {
                            //失败
                           Y.t("登录失败");
                        }
                    }
                });

                break;
            case R.id.tv_yixiu_zhuce:
                // 跳到注册页面
                Intent intent1 = new Intent(Main_only.this, Main_only_register.class);
                startActivity(intent1);
                break;
            case R.id.tv_yixiu_forget:
                // 跳到忘记密码页面
                Intent intent2 = new Intent(Main_only.this, Main_only_register_forgetpwd.class);
                startActivity(intent2);
                break;
        }
    }
}
