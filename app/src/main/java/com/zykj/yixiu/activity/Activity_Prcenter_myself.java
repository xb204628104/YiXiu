package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.OptionsPicke;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by zykj on 2017/4/19.
 */

public class Activity_Prcenter_myself extends Activity {
    //头像的id
    @Bind(R.id.fm_my_touxiang)
    FrameLayout fmMyTouxiang;
    @Bind(R.id.ll_my_touxianh)
    LinearLayout llMyTouxianh;
    @Bind(R.id.ll_my_xingming)
    LinearLayout llMyXingming;
    @Bind(R.id.ll_my_xingbie)
    LinearLayout llMyXingbie;
    @Bind(R.id.ll_my_shouji)
    LinearLayout llMyShouji;
    @Bind(R.id.ll_my_diqu)
    LinearLayout llMyDiqu;
    @Bind(R.id.iv_my_touxiang)
    ImageView ivMyTouxiang;
    @Bind(R.id.iv_my_beijing)
    ImageView ivMyBeijing;
    @Bind(R.id.et_my_name)
    EditText etMyName;
    @Bind(R.id.rg_my_sex)
    RadioGroup rgMySex;
    @Bind(R.id.tv_my_sheng)
    TextView tvMySheng;
    @Bind(R.id.tv_my_shi)
    TextView tvMyShi;
    @Bind(R.id.iv_my_name)
    ImageView ivMyName;
    @Bind(R.id.tv_id_sex)
    TextView tvIdSex;
    @Bind(R.id.iv_my_diqu)
    ImageView ivMyDiqu;
    @Bind(R.id.nan)
    RadioButton nan;
    @Bind(R.id.nv)
    RadioButton nv;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_myself);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            data = intent.getStringExtra("data");
            Glide.with(Activity_Prcenter_myself.this).load(data).into(ivMyTouxiang);
            ivMyBeijing.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(Y.USER.getIcon())){
            Glide.with(Activity_Prcenter_myself.this).load(Y.USER.getIcon()).into(ivMyTouxiang);
        }
        if (!TextUtils.isEmpty(Y.USER.getUsername())){
            etMyName.setText(Y.USER.getUsername());
        }
        if (!TextUtils.isEmpty(Y.USER.getSex())){
            if ("男".equals(Y.USER.getSex())){
                nan.setChecked(true);
            }else if ("女".equals(Y.USER.getSex())){
                nv.setChecked(true);
            }
        }
        if (!TextUtils.isEmpty(Y.USER.getProvince())){
            tvMySheng.setText(Y.USER.getProvince());
        }
        if (!TextUtils.isEmpty(Y.USER.getCity())){
            tvMyShi.setText(Y.USER.getCity());
        }
        rgMySex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.nan:
                        Y.USER.setSex("男");
                        break;
                    case R.id.nv:
                        Y.USER.setSex("女");
                        break;
                }
            }
        });

    }


    @OnClick({R.id.iv_my_name, R.id.tv_id_sex, R.id.iv_my_diqu, R.id.ll_my_diqu,R.id.iv_my_touxiang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_my_name:
                String name = etMyName.getText().toString().trim();
                String sheng = tvMySheng.getText().toString().trim();
                String shi = tvMyShi.getText().toString().trim();
                final Map<String, String> map = new HashMap<String, String>();
                map.put("username", name);
                map.put("sex", Y.USER.getSex());
                map.put("province", sheng);
                map.put("city", shi);
                map.put("user_id", Y.USER.getUser_id() + "");
                map.put("token", Y.USER.getToken());
                Y.get(YURL.SET_USER_INFO, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)){
                            Y.t("成功了");
                            String username = map.get("username");
                            String sex = map.get("sex");
                            String province = map.get("province");
                            String city = map.get("city");
                            String user_id = map.get("user_id");
                            String token = map.get("token");
                            Y.i(username+"-------");
                            Y.i(sex+"-------");
                            Y.i(province+"-------");
                            Y.i(city+"-------");
                            Y.i(user_id+"-------");
                            Y.i(token+"-------");
                            Y.USER.setUsername(username);
                            Y.USER.setSex(sex);
                            Y.USER.setProvince(province);
                            Y.USER.setCity(city);
                        }else {
                            Y.t("woshi");
                            Y.t("失败了");
                        }
                    }
                });
                break;
            case R.id.tv_id_sex:
                break;
            case R.id.iv_my_diqu:
                break;
            case R.id.ll_my_diqu:
                OptionsPicke optionsPicke = new OptionsPicke();
                optionsPicke.showOptionsPicke(Activity_Prcenter_myself.this, new OptionsPicke.OptionsSelectListener() {
                    @Override
                    public void selectListener(String province, String city, String district) {
                        tvMySheng.setText(province);
                        tvMyShi.setText(city);
                    }
                });
                break;
            case R.id.iv_my_touxiang:
                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 1001) {
                            String photoPath = resultList.get(0).getPhotoPath();
                            for (PhotoInfo info:resultList) {
                                ImageOptions imageOptions=new ImageOptions.Builder().setUseMemCache(true).setCircular(true).build();
                                x.image().bind(ivMyBeijing,info.getPhotoPath(),imageOptions);
                                // Glide.with(Activity_Prcenter.this).load(photoPath).into(ivPeMytou);
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("icon", photoPath);//icon: 头像文件
                                map.put("token", Y.TOKEN);//token: 用户令牌
                                Y.post(YURL.UP_LOAD_ICON, map, new Y.MyCommonCall<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        StyledDialog.dismissLoading();
                                        String message = JSON.parseObject(result).getString("message");
                                        if (Y.getRespCode(result)) {
                                            Y.t("成功" + message);
                                            //data = Y.getData(result);
                                            Y.USER.setIcon(Y.getData(result));
                                            //Y.i(data);
                                        } else {
                                            Y.t("失败" + message);
                                        }
                                    }
                                });
                            }


                        }else {}
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
        }
    }
}
