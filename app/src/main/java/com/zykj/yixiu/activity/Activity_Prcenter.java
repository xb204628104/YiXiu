package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
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
 * Created by zykj on 2017/4/18.
 */

public class Activity_Prcenter extends Activity {
    @Bind(R.id.ll_pc_undone)
    LinearLayout llPcUndone;
    @Bind(R.id.ll_pc_offthestocks)
    LinearLayout llPcOffthestocks;
    @Bind(R.id.ll_pc_canceled)
    LinearLayout llPcCanceled;
    @Bind(R.id.ll_pc_mymeans)
    LinearLayout llPcMymeans;
    @Bind(R.id.ll_pc_mywallet)
    LinearLayout llPcMywallet;
    @Bind(R.id.ll_pc_address)
    LinearLayout llPcAddress;
    @Bind(R.id.ll_pc_approve)
    LinearLayout llPcApprove;
    @Bind(R.id.ll_pc_terrace)
    LinearLayout llPcTerrace;
    @Bind(R.id.ll_pc_aboutus)
    LinearLayout llPcAboutus;
    @Bind(R.id.ll_pc_setting)
    LinearLayout llPcSetting;
    //头像id
    @Bind(R.id.fm_pe_touxiang)
    FrameLayout fmPeTouxiang;
    @Bind(R.id.iv_pe_mytou)
    ImageView ivPeMytou;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(Y.USER.getIcon())){
            ImageOptions imageOptions=new ImageOptions.Builder().setUseMemCache(true).setCircular(true).build();
            x.image().bind(ivPeMytou,YURL.HOST+Y.USER.getIcon(),imageOptions);
        }
    }

    @OnClick({R.id.ll_pc_undone, R.id.ll_pc_offthestocks, R.id.ll_pc_canceled, R.id.ll_pc_mymeans,
            R.id.ll_pc_mywallet, R.id.ll_pc_address, R.id.ll_pc_approve, R.id.ll_pc_terrace,
            R.id.ll_pc_aboutus, R.id.ll_pc_setting, R.id.fm_pe_touxiang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fm_pe_touxiang:
                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 1001) {
                            final String photoPath = resultList.get(0).getPhotoPath();//本地路径

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
                                                data = Y.getData(result);//上传成功后返回的网络路径
                                                Y.USER.setIcon(data);
                                                Y.i(data);
                                                ImageOptions imageOptions=new ImageOptions.Builder().setUseMemCache(true).setCircular(true).build();
                                                x.image().bind(ivPeMytou,photoPath,imageOptions);
                                            } else {
                                                Y.t("失败" + message);
                                            }
                                        }
                                    });



                        }else {}
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
            case R.id.ll_pc_undone:
                Intent intent7 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myorder.class);
                intent7.putExtra("Wei", "1");
                startActivity(intent7);
                break;
            case R.id.ll_pc_offthestocks:
                Intent intent8 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myorder.class);
                intent8.putExtra("Wei", "2");
                startActivity(intent8);
                break;
            case R.id.ll_pc_canceled:
                Intent intent9 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myorder.class);
                intent9.putExtra("Wei", "3");
                startActivity(intent9);
                break;
            case R.id.ll_pc_mymeans:
                Intent intent = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself.class);
                intent.putExtra("data",data);
                startActivity(intent);
                break;
            case R.id.ll_pc_mywallet:
                Intent intent1 = new Intent(Activity_Prcenter.this, Activity_Prcenter_mywallet.class);
                startActivity(intent1);
                break;
            case R.id.ll_pc_address:
                Intent intent2 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself_address.class);
                startActivity(intent2);
                break;
            case R.id.ll_pc_approve:
                Intent intent3 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself_approve.class);
                startActivity(intent3);
                break;
            case R.id.ll_pc_terrace:
                Intent intent4 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself_service.class);
                startActivity(intent4);
                break;
            case R.id.ll_pc_aboutus:
                Intent intent5 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself_aboutus.class);
                startActivity(intent5);
                break;
            case R.id.ll_pc_setting:
                Intent intent6 = new Intent(Activity_Prcenter.this, Activity_Prcenter_myself_setting.class);
                startActivity(intent6);
                break;
        }
    }
}
