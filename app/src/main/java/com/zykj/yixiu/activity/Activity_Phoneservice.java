package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.MobileBean;
import com.zykj.yixiu.bean.Phone;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import java.io.File;
import java.util.ArrayList;
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
 * Created by zykj on 2017/4/15.
 */

public class Activity_Phoneservice extends Activity {
    @Bind(R.id.phoneservice_brand)
    TextView phoneserviceBrand;
    @Bind(R.id.phoneservice_model)
    TextView phoneserviceModel;
    @Bind(R.id.phoneservice_fault)
    TextView phoneserviceFault;
    @Bind(R.id.phone_ll_brand)
    LinearLayout phoneLlBrand;
    @Bind(R.id.phone_ll_model)
    LinearLayout phoneLlModel;
    @Bind(R.id.phone_ll_fault)
    LinearLayout phoneLlFault;
    @Bind(R.id.bt_phone_okcall)
    Button btPhoneOkcall;
    @Bind(R.id.ll_phone_add)
    LinearLayout llPhoneAdd;
    @Bind(R.id.iv_phone_img)
    ImageView ivPhoneImg;
    @Bind(R.id.et_miaoshu)
    EditText etMiaoshu;
    private List<MobileBean> lists;
    private List<MobileBean> lists1;
    private List<ImageView> list;
    private int index = -1;
    private int index2 = -1;
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    private OptionsPickerView pvOptions;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_phone_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.phone_ll_brand, R.id.phone_ll_model, R.id.phone_ll_fault, R.id.bt_phone_okcall
            , R.id.ll_phone_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_ll_brand:
                Y.get(YURL.FIND_PHONE_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //条件选择器
                            //返回的分别是三个级别的选中位置
                            pvOptions = new OptionsPickerView.Builder(Activity_Phoneservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    phoneserviceBrand.setText(lists.get(options1).getName());
                                    index = options1;
                                    pvOptions = null;
                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (MobileBean str : lists) {
                                list.add(str.getName());
                            }
                            pvOptions.setPicker(list, null, null);
                            pvOptions.show();

                        } else {
                            Y.t("服务器解析异常");
                        }
                    }
                });
                break;
            case R.id.phone_ll_model:
                //检测是否选择了品牌
                if (index == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map<String, String> map = new HashMap<>();
                    map.put("pid", lists.get(index).getId() + "");
                    Y.get(YURL.FIND_PHONE_MODEL, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                //成功
                                lists1 = JSON.parseArray(Y.getData(result), MobileBean.class);

                                //创建选择器
                                pvOptions = new OptionsPickerView.Builder(Activity_Phoneservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        phoneserviceModel.setText(lists1.get(options1).getName());
                                        index2 = options1;
                                        pvOptions = null;
                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (MobileBean mb : lists1) {
                                    strs.add(mb.getName());
                                }

                                //添加数据
                                pvOptions.setPicker(strs, null, null);
                                //显示选择器
                                pvOptions.show();


                            } else {
                                //失败
                                Y.t("数据解析失败");

                            }
                        }
                    });


                }

                break;
            case R.id.phone_ll_fault:
                Y.get(YURL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //条件选择器
                            OptionsPickerView pvOptions = new OptionsPickerView.Builder(Activity_Phoneservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    phoneserviceFault.setText(lists.get(options1).getName());

                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (MobileBean str : lists) {
                                list.add(str.getName());
                            }
                            pvOptions.setPicker(list, null, null);
                            pvOptions.show();

                        } else {
                            Y.t("服务器解析异常");
                        }
                    }
                });
                break;
            case R.id.bt_phone_okcall:
                String brand = phoneserviceBrand.getText().toString().trim();
                String model = phoneserviceModel.getText().toString().trim();
                String fault = phoneserviceFault.getText().toString().trim();
                String miaoshu = etMiaoshu.getText().toString().trim();

                if (TextUtils.isEmpty(brand)) {
                    Y.t("请选择您的手机品牌");
                    return;
                }

                if (TextUtils.isEmpty(model)) {
                    Y.t("请选择您的手机型号");
                    return;
                }
                if (TextUtils.isEmpty(fault)) {
                    Y.t("请选择您的手机故障点");
                    return;
                }
                if (TextUtils.isEmpty(miaoshu)) {
                    Y.t("请选择您的手机故障点进行描述");
                    return;
                }
                final Phone phone=new Phone();
                phone.setBrand(brand);
                phone.setModle(model);
                phone.setFault(fault);
                phone.setDescribe(miaoshu);
                phone.setImage1(photoPath);
                Intent intent = new Intent(Activity_Phoneservice.this, Activity_Callservice.class);
                intent.putExtra("order_type","1");
                intent.putExtra("bean",phone);
                startActivity(intent);
                break;
            case R.id.ll_phone_add:

                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        //ivPhoneImg
                        if (reqeustCode == 1001) {
                            photoPath = resultList.get(0).getPhotoPath();
                            Glide.with(Activity_Phoneservice.this).load(photoPath).into(ivPhoneImg);
                            Luban.get(Activity_Phoneservice.this)
                                    .load(new File(photoPath))                     //传人要压缩的图片
                                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                                    .setCompressListener(new OnCompressListener() { //设置回调

                                        @Override
                                        public void onStart() {
                                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                                        }
                                        @Override
                                        public void onSuccess(File file) {
                                            Y.t("压缩成功");
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
