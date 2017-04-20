package com.zykj.yixiu.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.orhanobut.logger.Logger;
import com.youth.banner.loader.ImageLoader;
import com.zykj.yixiu.R;
import com.zykj.yixiu.activity.Activity_Phoneservice;
import com.zykj.yixiu.utils.Y;

import org.xutils.x;

import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by zykj on 2017/4/11.
 */


public class MyApplication extends Application {
    private final int REQUEST_CODE_CAMERA = 1000; //相机表示
    private final int REQUEST_CODE_GALLERY = 1001; //相册标示
    private final int REQUEST_CODE_CROP = 1002;    //裁剪表示
    private final int REQUEST_CODE_EDIT = 1003;       //编辑表示
    @Override
    public void onCreate() {
        super.onCreate();
        //SDKInitializer.initialize(getApplicationContext());
        //SDKInitializer.initialize(getApplicationContext());
        StyledDialog.init(this);
        x.Ext.init(this);
        Y.context=this;
        ThemeConfig themeConfig = new ThemeConfig.Builder().build();
        FunctionConfig functionConfig = new FunctionConfig.Builder().setEnableCamera(true)
                .setEnableEdit(true).setEnableCrop(true).setEnableRotate(true)
                .setCropSquare(true).setEnablePreview(true)
                .build();
        myImageLoader myImageLoader=new myImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, myImageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                //在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
    public class myImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Glide.with(activity).load("file://" + path)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(false)
                    .into(imageView);

        }

        @Override
        public void clearMemoryCache() {

        }
    }



}

