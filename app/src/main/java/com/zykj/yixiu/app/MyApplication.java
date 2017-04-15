package com.zykj.yixiu.app;

import android.app.Application;

import com.zykj.yixiu.utils.Y;

import org.xutils.x;

/**
 * Created by zykj on 2017/4/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        Y.context=this;
    }
}
