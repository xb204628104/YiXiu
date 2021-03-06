package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.zykj.yixiu.R;
import com.zykj.yixiu.adapter.MyBaseAdapter;
import com.zykj.yixiu.bean.Oreder;
import com.zykj.yixiu.utils.Y;
import com.zykj.yixiu.utils.YURL;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Bind(R.id.ll_nyordle_wei)
    LinearLayout llNyordleWei;
    @Bind(R.id.ll_nyordle_yi)
    LinearLayout llNyordleYi;
    @Bind(R.id.ll_nyordle_qu)
    LinearLayout llNyordleQu;
    @Bind(R.id.lv)
    ListView lv;

    private List<Oreder> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_myorder);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Y.i(Y.USER.getUser_id()+"");

        if (intent != null) {
            String wei = intent.getStringExtra("Wei");
            Y.i(wei);
            switch (wei) {
                case "1":
                    tvNyordleWei.setTextColor(LAN);
                    tvNyordleYi.setTextColor(HEI);
                    tvNyordleQuxiao.setTextColor(HEI);
                    ivNyordleWei.setVisibility(View.VISIBLE);
                    ivNyordleYi.setVisibility(View.INVISIBLE);
                    ivNyordleQu.setVisibility(View.INVISIBLE);
                    RequestParams requestParams=new RequestParams(YURL.FIND_ORDERBYSTATE);
                    requestParams.addBodyParameter("custom_id",Y.USER.getUser_id()+"");
                    requestParams.addBodyParameter("order_state",1+"");
                    x.http().post(requestParams,  new Y.MyCommonCall<String>() {

                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)){
                                List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                                MyBaseAdapter myBaseAdapter=new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,0);
                                lv.setAdapter(myBaseAdapter);
                            }else {
                                Y.t("失败了");
                            }
                        }
                    });
                   /* Map map = new HashMap();
                    map.put("custom_id",Y.USER.getUser_id()+"");
                    map.put("order_state",1+"");
                    Y.get(YURL.FIND_ORDERBYSTATE, map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {
                                List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                                Y.i(oreders.toString());
                                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                                lv.setAdapter(myBaseAdapter);
                            } else {
                                Y.t("失败了");
                            }

                        }
                    });
*/
                    break;
                case "2":
                    tvNyordleWei.setTextColor(HEI);
                    tvNyordleYi.setTextColor(LAN);
                    tvNyordleQuxiao.setTextColor(HEI);
                    ivNyordleWei.setVisibility(View.INVISIBLE);
                    ivNyordleYi.setVisibility(View.VISIBLE);
                    ivNyordleQu.setVisibility(View.INVISIBLE);
                    Map map2 = new HashMap();
                    map2.put("custom_id", Y.USER.getUser_id()+"");
                    map2.put("order_state",2+"");
                    Y.get(YURL.FIND_ORDERBYSTATE, map2, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {

                                List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                                Y.i(oreders.toString());
                                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                                lv.setAdapter(myBaseAdapter);
                                if (oreders.size()==0){
                                    Y.t("请耐心等待接单");
                                }
                            } else {
                                Y.t("失败了");
                            }

                        }
                    });
                    break;
                case "3":
                    tvNyordleWei.setTextColor(HEI);
                    tvNyordleYi.setTextColor(HEI);
                    tvNyordleQuxiao.setTextColor(LAN);
                    ivNyordleWei.setVisibility(View.INVISIBLE);
                    ivNyordleYi.setVisibility(View.INVISIBLE);
                    ivNyordleQu.setVisibility(View.VISIBLE);
                    Map map1= new HashMap();
                    map1.put("custom_id", Y.USER.getUser_id()+"");
                    map1.put("order_state",3+"");
                    Y.get(YURL.FIND_ORDERBYSTATE, map1, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading();
                            if (Y.getRespCode(result)) {

                                List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                                Y.i(oreders.toString());
                                MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                                lv.setAdapter(myBaseAdapter);
                            } else {
                                Y.t("失败了");
                            }

                        }
                    });
                    break;
            }

        }
    }

    @OnClick({R.id.tv_nyordle_wei, R.id.tv_nyordle_yi, R.id.tv_nyordle_quxiao
            , R.id.ll_nyordle_qu, R.id.ll_nyordle_wei, R.id.ll_nyordle_yi})
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
                Map map4 = new HashMap();
                map4.put("custom_id", Y.USER.getUser_id()+"");
                map4.put("order_state",2+"");
                Y.post(YURL.FIND_ORDERBYSTATE, map4, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {

                            List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                            Y.i(oreders.toString());
                            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                            lv.setAdapter(myBaseAdapter);
                            if (oreders.size()==0){
                                Y.t("请耐心等待接单");
                            }
                        } else {
                            Y.t("失败了");
                        }

                    }
                });
                break;
            case R.id.tv_nyordle_quxiao:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(LAN);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.VISIBLE);
                Map map5= new HashMap();
                map5.put("custom_id", Y.USER.getUser_id()+"");
                map5.put("order_state",3+"");
                Y.post(YURL.FIND_ORDERBYSTATE, map5, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {

                            List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                            Y.i(oreders.toString());
                            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                            lv.setAdapter(myBaseAdapter);
                        } else {
                            Y.t("失败了");
                        }

                    }
                });
                break;
            case R.id.ll_nyordle_wei:
                tvNyordleWei.setTextColor(LAN);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.VISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                Map map = new HashMap();
                map.put("custom_id", Y.USER.getUser_id()+"");
                map.put("order_state",1+"");
                Y.post(YURL.FIND_ORDERBYSTATE, map, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {

                            List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                            Y.i(oreders.toString());
                            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,0);
                            lv.setAdapter(myBaseAdapter);
                        } else {
                            Y.t("失败了");
                        }

                    }
                });
                break;
            case R.id.ll_nyordle_yi:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(LAN);
                tvNyordleQuxiao.setTextColor(HEI);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.VISIBLE);
                ivNyordleQu.setVisibility(View.INVISIBLE);
                Map map2 = new HashMap();
                map2.put("custom_id", Y.USER.getUser_id()+"");
                map2.put("order_state",2+"");
                Y.post(YURL.FIND_ORDERBYSTATE, map2, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {
                            List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                            Y.i(oreders.toString());
                            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                            lv.setAdapter(myBaseAdapter);
                            if (oreders.size()==0){
                                Y.t("请耐心等待接单");
                            }
                        } else {
                            Y.t("失败了");
                        }

                    }
                });
                break;
            case R.id.ll_nyordle_qu:
                tvNyordleWei.setTextColor(HEI);
                tvNyordleYi.setTextColor(HEI);
                tvNyordleQuxiao.setTextColor(LAN);
                ivNyordleWei.setVisibility(View.INVISIBLE);
                ivNyordleYi.setVisibility(View.INVISIBLE);
                ivNyordleQu.setVisibility(View.VISIBLE);
                Map map1= new HashMap();
                map1.put("custom_id", Y.USER.getUser_id()+"");
                map1.put("order_state",3+"");
                Y.post(YURL.FIND_ORDERBYSTATE, map1, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading();
                        if (Y.getRespCode(result)) {

                            List<Oreder> oreders = JSON.parseArray(Y.getData(result), Oreder.class);
                            Y.i(oreders.toString());
                            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(Activity_Prcenter_myorder.this,oreders,1);
                            lv.setAdapter(myBaseAdapter);
                        } else {
                            Y.t("失败了");
                        }

                    }
                });
                break;
        }
    }
}
