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
import com.zykj.yixiu.bean.Computer;
import com.zykj.yixiu.bean.ComputerBean;
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

/**
 * Created by zykj on 2017/4/18.
 */

public class Activity_Computereservice extends Activity {

    @Bind(R.id.ll_computer_brand)
    LinearLayout llComputerBrand;
    @Bind(R.id.ll_computer_model)
    LinearLayout llComputerModel;
    @Bind(R.id.ll_computer_type)
    LinearLayout llComputerType;
    @Bind(R.id.ll_computer_fault)
    LinearLayout llComputerFault;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.tv_computer_brand)
    TextView tvComputerBrand;
    @Bind(R.id.tv_computer_type)
    TextView tvComputerType;
    @Bind(R.id.tv_computer_fault)
    TextView tvComputerFault;
    @Bind(R.id.tv_computer_category)
    TextView tvComputerCategory;
    @Bind(R.id.bt_computer_okcall)
    Button btComputerOkcall;
    @Bind(R.id.ll_computer_add)
    LinearLayout llComputerAdd;
    @Bind(R.id.iv_computer_img)
    ImageView ivPhoneImg;
    @Bind(R.id.et_computer_miaoshu)
    EditText etComputerMiaoshu;
    private List<ComputerBean> lists;
    private List<ComputerBean> listsmodel;
    private List<ComputerBean> listsmodel2;
    private int index = -1;
    private int indexmodel = -1;
    private int indexmodel2 = -1;
    private OptionsPickerView pvOptions;
    private String photoPath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_computer_service);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.ll_computer_brand, R.id.ll_computer_model, R.id.ll_computer_type, R.id.ll_computer_fault
            , R.id.bt_computer_okcall, R.id.ll_computer_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_computer_brand:
                Y.get(YURL.FIND_COMPUTER_BRAND, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            //条件选择器
                            //返回的分别是三个级别的选中位置
                            if (pvOptions == null)
                                pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //返回的分别是三个级别的选中位置

                                        tvComputerBrand.setText(lists.get(options1).getName());
                                        index = options1;
                                        pvOptions = null;
                                    }
                                }).build();
                            List<String> list = new ArrayList<String>();
                            for (ComputerBean str : lists) {
                                list.add(str.getName());
                            }
                            pvOptions.setPicker(list, null, null);
                            if (!pvOptions.isShowing())
                                pvOptions.show();

                        } else {
                            Y.t("服务器解析异常");
                        }
                    }
                });
                break;
            case R.id.ll_computer_model:
                if (index == -1) {
                    Y.t("请您先选择电脑品牌");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("pid", lists.get(index).getId() + "");
                    Y.get(YURL.FIND_COMPUTER_CATEGORY, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listsmodel = JSON.parseArray(Y.getData(result), ComputerBean.class);
                                //条件选择器
                                pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //返回的分别是三个级别的选中位置
                                        tvComputerCategory.setText(listsmodel.get(options1).getName());
                                        indexmodel = options1;
                                        pvOptions = null;
                                    }
                                }).build();
                                List<String> list = new ArrayList<String>();
                                for (ComputerBean str : listsmodel) {
                                    list.add(str.getName());
                                }
                                pvOptions.setPicker(list, null, null);
                                if (!pvOptions.isShowing())
                                    pvOptions.show();

                            } else {
                                Y.t("服务器解析异常");
                            }
                        }
                    });
                }


                break;
            case R.id.ll_computer_type:
                if (index == -1) {
                    Y.t("请您先选择电脑品牌");
                    return;
                }
                if (indexmodel == -1) {
                    Y.t("请您先选择电脑型号");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("pid", lists.get(index).getId() + "");
                map.put("category", listsmodel.get(indexmodel).getId() + "");
                Y.get(YURL.FIND_BYCOMPUTER_MODEL, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            listsmodel2 = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            //条件选择器
                            pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    tvComputerType.setText(listsmodel2.get(options1).getName());
                                    indexmodel2 = options1;
                                    pvOptions = null;

                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (ComputerBean str : listsmodel2) {
                                list.add(str.getName());
                            }
                            pvOptions.setPicker(list, null, null);
                            if (!pvOptions.isShowing())
                                pvOptions.show();

                        } else {
                            Y.t("服务器解析异常");
                        }
                    }
                });


                break;
            case R.id.ll_computer_fault:
                Y.get(YURL.FIND_PHONE_FAULT, null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            //条件选择器
                            OptionsPickerView pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    tvComputerFault.setText(lists.get(options1).getName());

                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (ComputerBean str : lists) {
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
            case R.id.bt_computer_okcall:
                String tvcomputerBrand = tvComputerBrand.getText().toString().trim();
                String tvcomputerType = tvComputerType.getText().toString().trim();
                String tvcomputerFault = tvComputerFault.getText().toString().trim();
                String etcomputerMiaoshu = etComputerMiaoshu.getText().toString().trim();
                String tvcomputerCategory = tvComputerCategory.getText().toString().trim();
                if (TextUtils.isEmpty(tvcomputerBrand)){
                    Y.t("请选择您的电脑品牌");
                    return;

                }
                if (TextUtils.isEmpty(tvcomputerType)){
                    Y.t("请选择您的电脑型号");
                    return;
                }
                if (TextUtils.isEmpty(tvcomputerFault)){
                    Y.t("请选择您的电脑故障");
                    return;
                }
                if (TextUtils.isEmpty(etcomputerMiaoshu)){
                    Y.t("请对您的电脑故障进行描述");
                    return;
                }
                Computer computer=new Computer();
                computer.setBrand(tvcomputerBrand);
                computer.setModle(tvcomputerType);
                computer.setFault(tvcomputerFault);
                computer.setDescribe(etcomputerMiaoshu);
                computer.setImage1(photoPath);
                computer.setCategory(tvcomputerCategory);
                Intent intent = new Intent(Activity_Computereservice.this, Activity_Callservice.class);
                intent.putExtra("order_type","2");
                intent.putExtra("bean",computer);
                startActivity(intent);
                break;
            case R.id.ll_computer_add:
                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (reqeustCode == 1001) {
                            photoPath = resultList.get(0).getPhotoPath();
                            Glide.with(Activity_Computereservice.this).load(photoPath).into(ivPhoneImg);

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
