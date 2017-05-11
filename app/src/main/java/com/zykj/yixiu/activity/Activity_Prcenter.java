package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.common.Callback;
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
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);
        button= (Button) findViewById(R.id.bt_pc_data);
        Map map=new HashMap();
        map.put("custom_id",Y.USER.getUser_id()+"");
        Y.post(YURL.FIND_UNFINISH_COUNT, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    String data = Y.getData(result);
                    if ("0".equals(data)){
                        button.setVisibility(View.INVISIBLE);
                    }
                    button.setText(data);
                    Y.i(data);
                }else {
                    Y.t("服务器异常");
                }
            }
        });


        Y.i(Y.USER.toString());
        if (!TextUtils.isEmpty(Y.USER.getIcon())) {
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();

            x.image().bind(ivPeMytou, YURL.HOST + Y.USER.getIcon(), options);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map map=new HashMap();
        map.put("custom_id",Y.USER.getUser_id()+"");
        Y.post(YURL.FIND_UNFINISH_COUNT, map, new Y.MyCommonCall<String>() {
            @Override
            public void onSuccess(String result) {
                StyledDialog.dismissLoading();
                if (Y.getRespCode(result)){
                    String data = Y.getData(result);
                    if ("0".equals(data)){
                        button.setVisibility(View.INVISIBLE);
                    }
                    button.setText(data);
                    Y.i(data);
                }else {
                    Y.t("服务器异常");
                }
            }
        });
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
                            final File file=new File(photoPath);
                            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
                            x.image().bind(ivPeMytou,photoPath,options);
                            Luban.get(Activity_Prcenter.this)
                                    .load(file)                     //传人要压缩的图片
                                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                                    .setCompressListener(new OnCompressListener() { //设置回调
                                        @Override
                                        public void onStart() {
                                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                                        }
                                        @Override
                                        public void onSuccess(File file) {
                                            Y.t("压缩成功");
                                            RequestParams requestParams=new RequestParams(YURL.UP_LOAD_ICON);
                                            requestParams.addBodyParameter("icon",file);
                                            Y.i("--------"+file);
                                            requestParams.addBodyParameter("token",Y.USER.getToken());
                                            Y.i(""+Y.USER.getToken());
                                            x.http().post(requestParams,new Callback.CommonCallback<String>() {
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
                                                @Override
                                                public void onError(Throwable ex, boolean isOnCallback) {
                                                }
                                                @Override
                                                public void onCancelled(CancelledException cex) {
                                                }
                                                @Override
                                                public void onFinished() {
                                                }
                                            });
                                            //TODO 压缩成功后调用，返回压缩后的图片文件
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            //TODO 当压缩过去出现问题时调用
                                        }
                                    }).launch();    //启动压缩

                            // Glide.with(Activity_Prcenter.this).load(photoPath).into(ivPeMytou);
                                   /* Map<String, String> map = new HashMap<String, String>();
                                    File file=new File(photoPath);
                                    map.put("icon",file+"");//icon: 头像文件
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
                                                x.image().bind(ivPeMytou,"file://"+photoPath,imageOptions);
                                            } else {
                                                Y.t("失败" + message);
                                            }
                                        }
                                    });
*/

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
                Intent intent2 = new Intent(Activity_Prcenter.this,Activity_Callservice_Adress.class);
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
