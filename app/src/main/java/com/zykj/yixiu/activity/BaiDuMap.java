package com.zykj.yixiu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zykj on 2017/4/18.
 */

public class BaiDuMap extends Activity {
    @Bind(R.id.map)
    MapView map;
    private BaiduMap mmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_map);
        ButterKnife.bind(this);
        mmap = map.getMap();
        mmap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }
}
