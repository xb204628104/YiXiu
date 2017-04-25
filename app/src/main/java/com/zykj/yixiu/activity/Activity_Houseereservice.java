package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.HouseBean;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by zykj on 2017/4/18.
 */

public class Activity_Houseereservice extends Activity {
    @Bind(R.id.tv_house_brand)
    TextView tvHouseBrand;
    @Bind(R.id.ll_house_brand)
    LinearLayout llHouseBrand;
    @Bind(R.id.tv_house_typ)
    TextView tvHouseTyp;
    @Bind(R.id.ll_house_typ)
    LinearLayout llHouseTyp;
    @Bind(R.id.tv_house_model)
    TextView tvHouseModel;
    @Bind(R.id.ll_house_nodel)
    LinearLayout llHouseNodel;
    @Bind(R.id.tv_house_fault)
    TextView tvHouseFault;
    @Bind(R.id.ll_house_fault)
    LinearLayout llHouseFault;
    @Bind(R.id.bt_house_okcall)
    Button btHouseOkcall;
    @Bind(R.id.ll_house_add)
    LinearLayout llHouseAdd;
    private List<HouseBean> lists;
    private List<HouseBean> listsmodle;
    private List<HouseBean> listsmodle2;
    private int index = -1;
    private int modleindex = -1;
    private int modleindex2 = -1;
    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_house_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_house_brand, R.id.ll_house_typ, R.id.ll_house_nodel, R.id.ll_house_fault
            , R.id.bt_house_okcall,R.id.ll_house_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_house_brand:
                Y.get(YURL.FIND_BYAPPLIANCE_BRAND,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), HouseBean.class);
                            //条件选择器
                            //返回的分别是三个级别的选中位置
                            if (pvOptions==null)
                            pvOptions = new OptionsPickerView.Builder(Activity_Houseereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    tvHouseBrand.setText(lists.get(options1).getName());
                                    index = options1;
                                    pvOptions=null;
                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (HouseBean str : lists) {
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
            //类型
            case R.id.ll_house_typ:
                if (index == -1) {
                    Y.t("请您先选择电脑品牌");
                    return;
                } else {
                    Map<String,String> map=new HashMap<>();
                    map.put("pid",lists.get(index).getId() + "");
                    Y.get(YURL.FIND_APPLIANCE_CATEGORY,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listsmodle = JSON.parseArray(Y.getData(result), HouseBean.class);
                                //条件选择器
                                if (pvOptions==null)
                               pvOptions = new OptionsPickerView.Builder(Activity_Houseereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //返回的分别是三个级别的选中位置
                                        tvHouseTyp.setText(listsmodle.get(options1).getName());
                                        modleindex=options1;

                                        pvOptions=null;

                                    }
                                }).build();
                                List<String> list = new ArrayList<String>();
                                for (HouseBean str : listsmodle) {
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
            case R.id.ll_house_nodel:
                if (index == -1) {
                    Y.t("请您先选择家电品牌");
                    return;
                }
                if (modleindex == -1) {
                Y.t("请您先选择家电型号");
                return;
            }
                Map<String,String> map=new HashMap<>();
                    map.put("pid", lists.get(index).getId() + "");
                    map.put("category", listsmodle.get(modleindex).getId()+"");
                    Y.get(YURL.FIND_BYAPPLIANCE_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                listsmodle2 = JSON.parseArray(Y.getData(result), HouseBean.class);
                                //条件选择器
                                pvOptions = new OptionsPickerView.Builder(Activity_Houseereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        //返回的分别是三个级别的选中位置
                                        tvHouseModel.setText(listsmodle2.get(options1).getName());
                                        modleindex2=options1;
                                        pvOptions=null;

                                    }
                                }).build();
                                List<String> list = new ArrayList<String>();
                                for (HouseBean str : listsmodle2) {
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
            case R.id.ll_house_fault:
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            lists = JSON.parseArray(Y.getData(result), HouseBean.class);
                            //条件选择器
                            OptionsPickerView pvOptions = new OptionsPickerView.Builder(Activity_Houseereservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    tvHouseFault.setText(lists.get(options1).getName());
                                }
                            }).build();
                            List<String> list = new ArrayList<String>();
                            for (HouseBean str : lists) {
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
            case R.id.bt_house_okcall:
                Intent intent = new Intent(Activity_Houseereservice.this, Activity_Callservice.class);
                startActivity(intent);
                break;
            case R.id.ll_house_add:
                GalleryFinal.openGallerySingle(1001, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                       // Glide.with(Activity_Houseereservice.this).load(resultList.get(0).getPhotoPath());
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
                break;
        }
    }

}
