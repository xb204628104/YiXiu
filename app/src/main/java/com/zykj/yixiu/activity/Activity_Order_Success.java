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

public class Activity_Order_Success extends Activity {
    @Bind(R.id.tv_succ_typ)
    TextView tvSuccTyp;
    @Bind(R.id.tv_succ_name)
    TextView tvSuccName;
    @Bind(R.id.tv_succ_num)
    TextView tvSuccNum;
    @Bind(R.id.tv_succ_time)
    TextView tvSuccTime;
    @Bind(R.id.tv_succ_address)
    TextView tvSuccAddress;
    @Bind(R.id.iv_succ_image)
    ImageView ivSuccImage;
    @Bind(R.id.tv_succ_brand)
    TextView tvSuccBrand;
    @Bind(R.id.tv_succ_model)
    TextView tvSuccModel;
    @Bind(R.id.tv_succ_fault)
    TextView tvSuccFault;
    @Bind(R.id.tv_succ_des)
    TextView tvSuccDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_myorder_issue);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            Oreder oreder=(Oreder)intent.getSerializableExtra("Bean");
            int order_type = oreder.getOrder_type();
            switch (order_type){
                case 1:
                    tvSuccTyp.setText("手机");
                    break;
                case 2:
                    tvSuccTyp.setText("电脑");
                    break;
                case 3:
                    tvSuccTyp.setText("家电");
                    break;
            }
            tvSuccName.setText(oreder.getCustom_name());
            tvSuccNum.setText(oreder.getCustom_phone());
            tvSuccTime.setText(oreder.getAddtime());
            tvSuccAddress.setText(oreder.getService_address());
            tvSuccBrand.setText(oreder.getBrand());
            tvSuccModel.setText(oreder.getModel());
            tvSuccFault.setText(oreder.getFault());
            tvSuccDes.setText(oreder.getFault_desc());
            String image1 = oreder.getImage1();
            ImageOptions options = new ImageOptions.Builder().setCircular(true).build();
            x.image().bind(ivSuccImage, YURL.HOST+image1,options);
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
