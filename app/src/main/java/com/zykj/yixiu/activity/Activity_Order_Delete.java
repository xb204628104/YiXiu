package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
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

public class Activity_Order_Delete extends Activity {
    @Bind(R.id.tv_dele_typ)
    TextView tvDeleTyp;
    @Bind(R.id.tv_dele_name)
    TextView tvDeleName;
    @Bind(R.id.tv_dele_num)
    TextView tvDeleNum;
    @Bind(R.id.tv_dele_time)
    TextView tvDeleTime;
    @Bind(R.id.tv_dele_address)
    TextView tvDeleAddress;
    @Bind(R.id.iv_dele_image)
    ImageView ivDeleImage;
    @Bind(R.id.tv_dele_brand)
    TextView tvDeleBrand;
    @Bind(R.id.tv_dele_model)
    TextView tvDeleModel;
    @Bind(R.id.tv_dele_fault)
    TextView tvDeleFault;
    @Bind(R.id.tv_dele_des)
    TextView tvDeleDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_myorder_callof);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            Oreder oreder=(Oreder)intent.getSerializableExtra("Bean");
            int order_type = oreder.getOrder_type();
            switch (order_type){
                case 1:
                    tvDeleTyp.setText("手机");
                    break;
                case 2:
                    tvDeleTyp.setText("电脑");
                    break;
                case 3:
                    tvDeleTyp.setText("家电");
                    break;
            }
            tvDeleName.setText(oreder.getCustom_name());
            tvDeleNum.setText(oreder.getCustom_phone());
            tvDeleTime.setText(oreder.getAddtime());
            tvDeleAddress.setText(oreder.getService_address());
            tvDeleBrand.setText(oreder.getBrand());
            tvDeleModel.setText(oreder.getModel());
            tvDeleFault.setText(oreder.getFault());
            tvDeleDes.setText(oreder.getFault_desc());
            String image1 = oreder.getImage1();
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(ivDeleImage, YURL.HOST+image1,options);
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
