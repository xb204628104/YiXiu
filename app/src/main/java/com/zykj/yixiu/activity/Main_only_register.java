package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.utils.Utils;

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
                //获取手机号和验证码
                String num = etRegisterNum.getText().toString().trim();
                String getyan = etRegisterYan.getText().toString().trim();
                //判断手机号是否为空
                if (TextUtils.isEmpty(num)){
                    Y.t("手机号不能为空");
                    return;
                }
                //判断用户输入的手机号是否为合法的手机号
                if (!Y.isMobileNO(num)){
                    Y.t("请输入正确的手机号");
                    return;
                }
                //判断用户的输入的验证码是否为空
                if (TextUtils.isEmpty(getyan)){
                    Y.t("验证码不能为空");
                    return;
                }
                //判断用户输入的验证的长度是否大于或者小于4
                if (getyan.length()!=4){
                    Y.t("请输入正确的验证码");
                    return;
                }
                //map集合 把所需要的key valus 传上去
                Map<String,String> map=new HashMap<String, String>();
                map.put("phone",num);//phone 电话号码
                map.put("vcode",getyan);//vcode 验证码
                map.put("type","0");//type 固定值，0
                Y.get(YURL.REGISTER,map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //如果成功后吧dialog关闭
                        StyledDialog.dismissLoading();
                        //把返回的message取出来
                        String message = JSON.parseObject(result).getString("message");
                        if (Y.getRespCode(result)){
                            Y.t("注册成功"+message);
                            //把成功后的data 取出来
                            String data = Y.getData(result);
                            //成功之后跳转带密码的页面
                            Intent intent=new Intent(Main_only_register.this,Main_only_register_pwd.class);
                            //把返回的data传到密码的页面
                            intent.putExtra("data",data);
                            //跳转
                            startActivity(intent);
                        }else {
                            //失败
                            Y.t("--11"+message);
                        }

                    }
                });

                break;
        }
    }

}
