package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/19.
 */

public class Activity_Prcenter_myorder extends Activity {
    public static final int LAN = 0xFF18d6d6;
    public static final int HEI = 0xFF979797;
    @Bind(R.id.tv_nyordle_wei)
    TextView tvNyordleWei;
    @Bind(R.id.tv_nyordle_yi)
    TextView tvNyordleYi;
    @Bind(R.id.tv_nyordle_quxiao)
    TextView tvNyordleQuxiao;
    @Bind(R.id.iv_nyordle_wei)
    ImageView ivNyordleWei;
    @Bind(R.id.iv_nyordle_yi)
    ImageView ivNyordleYi;
    @Bind(R.id.iv_nyordle_qu)
    ImageView ivNyordleQu;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.ll_nyordle_wei)
    LinearLayout llNyordleWei;
    @Bind(R.id.ll_nyordle_yi)
    LinearLayout llNyordleYi;
    @Bind(R.id.ll_nyordle_qu)
    LinearLayout llNyordleQu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_myorder);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            String wei = intent.getStringExtra("Wei");
            Y.i(wei);
            switch (wei){
                case "1":
                    tvNyordleWei.setTextColor(LAN);
                    tvNyordleYi.setTextColor(HEI);
                    tvNyordleQuxiao.setTextColor(HEI);
                    ivNyordleWei.setVisibility(View.VISIBLE);
                    ivNyordleYi.setVisibility(View.INVISIBLE);
                    ivNyordleQu.setVisibility(View.INVISIBLE);

                    break;
                case "2":
                    tvNyordleWei.setTextColor(HEI);
                    tvNyordleYi.setTextColor(LAN);
                    tvNyordleQuxiao.setTextColor(HEI);
                    ivNyordleWei.setVisibility(View.INVISIBLE);
                    ivNyordleYi.setVisibility(View.VISIBLE);
                    ivNyordleQu.setVisibility(View.INVISIBLE);
                    break;
                case "3":
                    tvNyordleWei.setTextColor(HEI);
                    tvNyordleYi.setTextColor(HEI);
                    tvNyordleQuxiao.setTextColor(LAN);
                    ivNyordleWei.setVisibility(View.INVISIBLE);
                    ivNyordleYi.setVisibility(View.INVISIBLE);
                    ivNyordleQu.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }

    @OnClick({R.id.tv_nyordle_wei, R.id.tv_nyordle_yi, R.id.tv_nyordle_quxiao
    ,R.id.ll_nyordle_qu,R.id.ll_nyordle_wei,R.id.ll_nyordle_yi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_nyordle_wei:
                tvNyordleWei.setTextColor(LAN);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.VISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_nyordle_yi:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(LAN);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.VISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_nyordle_quxiao:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(LAN);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_nyordle_wei:
                tvNyordleWei.setTextColor(LAN);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.VISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_nyordle_yi:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(LAN);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.VISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_nyordle_qu:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(LAN);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.VISIBLE);
                break;
        }
    }
}
