package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.ComputerBean;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

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
    private List<ComputerBean> lists;
    private int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_computer_service);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.ll_computer_brand, R.id.ll_computer_model, R.id.ll_computer_type, R.id.ll_computer_fault
            , R.id.bt_computer_okcall,R.id.ll_computer_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_computer_brand:
                Y.get(new RequestParams(YURL.FIND_COMPUTER_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), ComputerBean.class);
                            //条件选择器
                            OptionsPickerView pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    tvComputerBrand.setText(lists.get(options1).getName());
                                    index = options1;
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
            case R.id.ll_computer_model:
                if (index == -1) {
                    Y.t("请您先选择电脑品牌");
                } else {
                    RequestParams rp = new RequestParams(YURL.FIND_COMPUTER_CATEGORY);
                    rp.addBodyParameter("pid", lists.get(index).getId() + "");
                    Y.i(rp + "");
                    Y.get(rp, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                lists = JSON.parseArray(Y.getData(result), ComputerBean.class);
                                //条件选择器
                                OptionsPickerView pvOptions = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //返回的分别是三个级别的选中位置
                                        tvComputerCategory.setText(lists.get(options1).getName());

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
                }


                break;
            case R.id.ll_computer_type:
                //检测是否选择了品牌
                if (index == -1) {
                    Y.t("请您先选择电脑品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    RequestParams rp1 = new RequestParams(YURL.FIND_BYCOMPUTER_MODEL);
                    rp1.addBodyParameter("pid", lists.get(index).getId() + "");
                    rp1.addBodyParameter("category", "1");
                    Y.get(rp1, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), ComputerBean.class);

                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(Activity_Computereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        tvComputerType.setText(lists.get(options1).getName());

                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (ComputerBean cb : lists) {
                                    strs.add(cb.getName());
                                }

                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();

                            } else {
                                //失败
                                Y.t("服务器解析异常");

                            }
                        }
                    });

                }

                break;
            case R.id.ll_computer_fault:
                Y.get(new RequestParams(YURL.FIND_PHONE_FAULT), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
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

                Intent intent = new Intent(Activity_Computereservice.this, Activity_Callservice.class);
                startActivity(intent);
                break;
            case R.id.ll_computer_add:
                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
        }
    }
}
