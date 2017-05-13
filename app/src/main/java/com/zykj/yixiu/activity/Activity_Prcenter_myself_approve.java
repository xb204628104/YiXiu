package com.zykj.yixiu.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.IdCard;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;
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

public class Activity_Prcenter_myself_approve extends Activity {
    @Bind(R.id.fm_app_addzheng)
    FrameLayout fmAppAddzheng;
    @Bind(R.id.ll_app_beijzheng)
    LinearLayout llAppBeijzheng;
    @Bind(R.id.ll_app_zheng)
    LinearLayout llAppZheng;
    @Bind(R.id.fm_app_addfan)
    FrameLayout fmAppAddfan;
    @Bind(R.id.ll_app_beijfan)
    LinearLayout llAppBeijfan;
    @Bind(R.id.ll_app_fan)
    LinearLayout llAppFan;
    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.bt_app_ok)
    Button btAppOk;
    @Bind(R.id.iv2)
    ImageView iv2;
    private String path;
    private String path1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_approve);
        ButterKnife.bind(this);
        btAppOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams requestParams=new RequestParams(YURL.UP_LOADID_CARD);
                File file=new File(path);
                File file1=new File(path1);
                requestParams.addBodyParameter("idcard_image1",file);
                requestParams.addBodyParameter("idcard_image2",file1);
                requestParams.addBodyParameter("token",Y.USER.getToken());
                x.http().post(requestParams, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        JSONObject jsonObject = JSON.parseObject(result);
                        String message = jsonObject.getString("message");
                        if (Y.getRespCode(result)){
                            Y.i("chenggong------"+message);
                            String data = Y.getData(result);
                            Y.i(data.toString()+"..............");
                            finish();
                        }else{
                            Y.i("------"+message);
                        }
                    }
                });
               /* final Map<String,String> map=new HashMap<String, String>();
                map.put("idcard_image1",path);
                map.put("idcard_image2",path1);
                map.put("token",Y.TOKEN);
                Y.post(YURL.UP_LOADID_CARD, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        String message = JSON.parseObject(result).getString("message");
                        if (Y.getRespCode(result)){
                            List<IdCard> idCards = JSON.parseArray(Y.getData(result), IdCard.class);
                            for (IdCard card:idCards) {
                                String idcard_image1 = card.getIdcard_image1();
                                String idcard_image2 = card.getIdcard_image2();
                                Y.i(idcard_image1+"0000............");
                                Y.i(idcard_image2+"0000............");
                            }
                            Y.t(message+"成功");
                        }else {
                            Y.t(message+"失败");
                        }
                    }
                });*/
            }
        });
    }

    @OnClick({R.id.ll_app_beijzheng, R.id.ll_app_beijfan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_app_beijzheng:
                GalleryFinal.openGallerySingle(1000, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 1000) {
                            path = resultList.get(0).getPhotoPath();
                            File file=new File(path);
                            if (resultList != null) {
                                Glide.with(Activity_Prcenter_myself_approve.this).load(file).into(iv1);
                                Y.i(path);
                                Luban.get(Activity_Prcenter_myself_approve.this)
                                        .load(new File(path))                     //传人要压缩的图片
                                        .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                                        .setCompressListener(new OnCompressListener() { //设置回调

                                            @Override
                                            public void onStart() {
                                                //TODO 压缩开始前调用，可以在方法内启动 loading UI
                                            }
                                            @Override
                                            public void onSuccess(File file) {
                                                //TODO 压缩成功后调用，返回压缩后的图片文件
                                               // Y.t("压缩成功");
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                //TODO 当压缩过去出现问题时调用
                                            }
                                        }).launch();    //启动压缩
                                //llAppZheng.setBackgroundResource(R.mipmap.add_camera);

                            }
                        } else {

                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
            case R.id.ll_app_beijfan:
                GalleryFinal.openGallerySingle(1000, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 1000) {
                            path1 = resultList.get(0).getPhotoPath();
                            if (resultList != null) {
                                Y.i(path1);
                                Glide.with(Activity_Prcenter_myself_approve.this).load(path).into(iv2);
                                iv2.setScaleType(ImageView.ScaleType.FIT_XY);
                                Luban.get(Activity_Prcenter_myself_approve.this)
                                        .load(new File(path1))                     //传人要压缩的图片
                                        .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                                        .setCompressListener(new OnCompressListener() { //设置回调

                                            @Override
                                            public void onStart() {
                                                //TODO 压缩开始前调用，可以在方法内启动 loading UI
                                            }
                                            @Override
                                            public void onSuccess(File file) {
                                                //TODO 压缩成功后调用，返回压缩后的图片文件
                                               // Y.t("path1压缩成功");
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                //TODO 当压缩过去出现问题时调用
                                            }
                                        }).launch();    //启动压缩
                            }
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
