package com.zykj.yixiu.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
