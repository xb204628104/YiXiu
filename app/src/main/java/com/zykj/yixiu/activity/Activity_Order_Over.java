package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ObbInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.bean.Oreder;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/5/6.
 */

public class Activity_Order_Over extends Activity {
    @Bind(R.id.tv_over_typ)
    TextView tvOverTyp;
    @Bind(R.id.tv_over_name)
    TextView tvOverName;
    @Bind(R.id.tv_over_num)
    TextView tvOverNum;
    @Bind(R.id.tv_over_time)
    TextView tvOverTime;
    @Bind(R.id.tv_over_address)
    TextView tvOverAddress;
    @Bind(R.id.tv_over_brand)
    TextView tvOverBrand;
    @Bind(R.id.tv_over_model)
    TextView tvOverModel;
    @Bind(R.id.tv_over_fault)
    TextView tvOverFault;
    @Bind(R.id.tv_over_des)
    TextView tvOverDes;
    @Bind(R.id.iv_over_image)
    ImageView ivOverImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_myorder_over);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            Oreder oreder=(Oreder)intent.getSerializableExtra("Bean");
            int order_type = oreder.getOrder_type();
            switch (order_type){
                case 1:
                    tvOverTyp.setText("手机");
                    break;
                case 2:
                    tvOverTyp.setText("电脑");
                    break;
                case 3:
                    tvOverTyp.setText("家电");
                    break;
            }
            tvOverName.setText(oreder.getCustom_name());
            tvOverNum.setText(oreder.getCustom_phone());
            tvOverTime.setText(oreder.getAddtime());
            tvOverAddress.setText(oreder.getService_address());
            tvOverBrand.setText(oreder.getBrand());
            tvOverModel.setText(oreder.getModel());
            tvOverFault.setText(oreder.getFault());
            tvOverDes.setText(oreder.getFault_desc());
            String image1 = oreder.getImage1();
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(ivOverImage, YURL.HOST+image1,options);
            Y.i(oreder.getOrder_type()+"");
            Y.i(oreder.getModel());
            Y.i(oreder.getCustom_phone());
            Y.i(oreder.getAddtime());
            Y.i(oreder.getService_address());
            Y.i(oreder.getImage1());
            Y.i(oreder.getModel());
            Y.i(oreder.getBrand());
            Y.i(oreder.getFault());
            Y.i(oreder.getFault_desc());

        }
    }
}
