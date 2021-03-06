package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

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
    @Bind(R.id.tvnum)
    TextView tvnum;
    @Bind(R.id.ivback)
    ImageView ivback;
    private String data;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_myself);
        ButterKnife.bind(this);
        ok = (Button) findViewById(R.id.ok);
        tvnum.setText(Y.USER.getPhone());
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();

            x.image().bind(ivMyTouxiang, YURL.HOST + Y.USER.getIcon(), options);
            ivMyBeijing.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(Y.USER.getUsername())) {
            etMyName.setText(Y.USER.getUsername());
        }
        if (!TextUtils.isEmpty(Y.USER.getSex())) {
            if ("男".equals(Y.USER.getSex())) {
                nan.setChecked(true);
            } else if ("女".equals(Y.USER.getSex())) {
                nv.setChecked(true);
            }
        }
        if (!TextUtils.isEmpty(Y.USER.getProvince())) {
            tvMySheng.setText(Y.USER.getProvince());
        }
        if (!TextUtils.isEmpty(Y.USER.getCity())) {
            tvMyShi.setText(Y.USER.getCity());
        }
        rgMySex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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


    @OnClick({R.id.iv_my_name, R.id.tv_id_sex, R.id.iv_my_diqu, R.id.ll_my_diqu, R.id.iv_my_touxiang,
            R.id.ok,R.id.ivback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivback:
                Intent intent=new Intent(Activity_Prcenter_myself.this,Activity_Prcenter.class);
                startActivity(intent);
                break;
            case R.id.ok:
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
                        if (Y.getRespCode(result)) {
                            Y.t("成功了");
                            String username = map.get("username");
                            String sex = map.get("sex");
                            String province = map.get("province");
                            String city = map.get("city");
                            String user_id = map.get("user_id");
                            String token = map.get("token");
                            Y.i(username + "-------");
                            Y.i(sex + "-------");
                            Y.i(province + "-------");
                            Y.i(city + "-------");
                            Y.i(user_id + "-------");
                            Y.i(token + "-------");
                            Y.USER.setUsername(username);
                            Y.USER.setSex(sex);
                            Y.USER.setProvince(province);
                            Y.USER.setCity(city);
                            finish();
                        } else {
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
                            final String photoPath = resultList.get(0).getPhotoPath();
                            File file=new File(photoPath);
                            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                            x.image().bind(ivMyTouxiang,photoPath,options);
                            Luban.get(Activity_Prcenter_myself.this)
                                    .load(file)                     //传人要压缩的图片
                                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                                    .setCompressListener(new OnCompressListener() { //设置回调
                                        @Override
                                        public void onStart() {
                                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                                        }
                                        @Override
                                        public void onSuccess(File file) {
                                            RequestParams requestParams=new RequestParams(YURL.UP_LOAD_ICON);
                                            requestParams.addBodyParameter("icon",file);
                                            Y.i("--------"+file);
                                            requestParams.addBodyParameter("token",Y.USER.getToken());
                                            Y.i(""+Y.USER.getToken());
                                            x.http().post(requestParams, new Y.MyCommonCall<String>() {
                                                @Override
                                                public void onSuccess(String result) {
                                                    String message = JSON.parseObject(result).getString("message");
                                                    if (Y.getRespCode(result)){
                                                        Y.t("成功"+message);
                                                        String data = Y.getData(result);
                                                        Y.i(""+data);
                                                        Y.USER.setIcon(data);
                                                        Y.i(""+Y.USER.getIcon());
                                                    }else {
                                                        Y.t(""+message);
                                                    }
                                                }
                                            });
                                            //TODO 压缩成功后调用，返回压缩后的图片文件
                                        }
                                        @Override
                                        public void onError(Throwable e) {
                                            //TODO 当压缩过去出现问题时调用
                                        }
                                    }).launch();    //启动压缩

                        } else {
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
        }
    }
}
