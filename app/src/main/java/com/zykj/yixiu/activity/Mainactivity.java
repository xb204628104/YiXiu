package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zykj.yixiu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/11.
 */

public class Mainactivity extends Activity {
    @Bind(R.id.main_set_phone)
    FrameLayout mainSetPhone;
    @Bind(R.id.main_set_computer)
    FrameLayout mainSetComputer;
    @Bind(R.id.main_set_house)
    FrameLayout mainSetHouse;
    @Bind(R.id.honepage_banner)
    Banner honepageBanner;
    @Bind(R.id.tv_home_city)
    TextView tvHomeCity;
    private String[] imggs = {"http://enjoy.eastday.com/images/thumbnailimg/month_1704/201704111456291136.jpg",
            "http://fun.youth.cn/yl24xs/201704/W020170407308982716757.png",
            "http://fun.youth.cn/yl24xs/201703/W020170307332125513690.png",
            "http://img3.imgtn.bdimg.com/it/u=2327585548,1525749689&fm=21&gp=0.jpg",
            "http://cms-bucket.nosdn.127.net/1636acc956d5400e918998fac591299120170407084959.jpg"};
    //用于储存图片路径
    private List<String> strs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        honepageBanner.setImageLoader(new MyImageLoader());
        for (int i = 0; i < imggs.length; i++) {
            strs.add(imggs[i]);
        }
        honepageBanner.setImages(strs);
        //指示器样式
        honepageBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        honepageBanner.start();
    }

    @OnClick({R.id.main_set_phone, R.id.main_set_computer, R.id.main_set_house, R.id.honepage_banner
    ,R.id.tv_home_city,R.id.main_geren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_set_phone:
                Intent intent = new Intent(Mainactivity.this, Activity_Phoneservice.class);
                startActivity(intent);
                break;
            case R.id.main_set_computer:
                Intent intent1 = new Intent(Mainactivity.this, Activity_Computereservice.class);
                startActivity(intent1);
                break;
            case R.id.main_set_house:
                Intent intent2 = new Intent(Mainactivity.this, Activity_Houseereservice.class);
                startActivity(intent2);
                break;
            case R.id.honepage_banner:
                break;
            case R.id.tv_home_city:
                break;
            case R.id.main_geren:
                Intent intent3=new Intent(Mainactivity.this,Activity_Prcenter.class);
                startActivity(intent3);
                break;

        }
    }
}
