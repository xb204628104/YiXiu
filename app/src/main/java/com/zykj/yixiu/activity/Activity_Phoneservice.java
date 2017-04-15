package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.MobileBean;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private List<MobileBean> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_phone_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.phoneservice_brand, R.id.phoneservice_model, R.id.phoneservice_fault})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phoneservice_brand:
                Y.get(new RequestParams(YURL.FIND_PHONE_BRAND), new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (Y.getRespCode(result)){
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);
                            //条件选择器
                            OptionsPickerView pvOptions = new  OptionsPickerView.Builder(Activity_Phoneservice.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                                    //返回的分别是三个级别的选中位置
                                    phoneserviceBrand.setText(lists.get(options1).getName());
                                }
                            }).build();
                            List<String> list=new ArrayList<String>();
                            for (MobileBean str :lists) {
                                list.add(str.getName());
                            }
                            pvOptions.setPicker(list, null, null);
                            pvOptions.show();

                        }else {
                            Y.t("服务器解析异常");
                        }
                    }
                });
                break;
            case R.id.phoneservice_model:
                break;
            case R.id.phoneservice_fault:
                break;
        }
    }
}
