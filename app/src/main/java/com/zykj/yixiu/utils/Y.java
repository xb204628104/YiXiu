package com.zykj.yixiu.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.orhanobut.logger.Logger;
import com.zykj.yixiu.bean.Address;
import com.zykj.yixiu.bean.Oreder;
import com.zykj.yixiu.bean.User;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 工具类
 * Created by zykj on 2017/4/8.
 */

public  class Y {
    public static Context context; //全局上下文

    public  static  boolean isLog=true; //控制日志打印的开关


    //user的类
    public static User USER=new User();//用户信息
    public static Address ADDRESS=new Address();//地址信息
    public static Oreder OREDER=new Oreder();//订单信息
    public static String TOKEN;//用户的标识






    /**
     * 吐司功能只需要传入一个 字符串
     * @param str
     */
    public static  void t(String  str){
        Toast.makeText(context,str, Toast.LENGTH_LONG).show();
    }

    /**
     * 输出log日志
     * @param str
     */
    public static  void i(String  str){
        if(isLog)
         Logger.i(str);
    }

    /**
     * 检测请求返回的数据是否正确
     */
    public static boolean getRespCode(String  result){
        if("0".equals(JSON.parseObject(result).getString("resp_code"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 如果成功获取数据
     */
    public static String getData(String  result){
        return JSON.parseObject(result).getString("data");
    }





    /**
     * get请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable get(String url, Map<String,String> params, MyCommonCall<String> call){


        StyledDialog.buildLoading().show();
        //请求的对象
        RequestParams rp  =new RequestParams(url);

        //检测外部是否传入了参数
        if(params!=null){
            //把参数取出来这是到rp
            for (Map.Entry<String,String> entry :params.entrySet()) {
                rp.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }
        i(rp.toString());
        // 只要发起Get请求就开启对话框

        return   x.http().get(rp, call);
    }
    /**
     * post请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable post(String url, Map<String,String> params, MyCommonCall<String> call){
        StyledDialog.buildLoading().show();
        //请求的对象
        RequestParams rp  =new RequestParams(url);

        //检测外部是否传入了参数
        if(params!=null){
            //把参数取出来这是到rp
            for (Map.Entry<String,String> entry :params.entrySet()) {
                rp.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }
        i(rp.toString());
        // 只要发起Post请求就开启对话框
        return   x.http().post(rp, call);
    }
    /**
     * postfile请求  压缩图片
     * @param params
     * @param call
     * @return
     */
    public static void postFile(final RequestParams params, final MyCommonCall<String> call){
        StyledDialog.buildLoading().show();
        //请求的对象
        List<KeyValue> fileParams = params.getFileParams();
        for (final KeyValue value:fileParams) {
            File file = (File)value.value;
            i("之前的file"+file);
            //params.addBodyParameter(value.key,file);
            Luban.get(context).load(file).putGear(Luban.THIRD_GEAR)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }
                        @Override
                        public void onSuccess(File file) {
                            t("压缩成功");
                            i("之后的file"+file);
                            //检测外部是否传入了参数
                            params.addBodyParameter(value.key,file);
                            params.setMultipart(true);
                            i(params.toString());
                            // 只要发起Post请求就开启对话框
                            x.http().post(params,call);
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                    })
                    .launch();
        }


    }
    /**
       实现不需要外部完成的两个函数
     */
    public abstract  static class  MyCommonCall<String> implements Callback.CommonCallback<String>{
        @Override
        public void onFinished() {}

        @Override
        public void onCancelled(CancelledException cex) {}

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            t("服务器异常");
            ex.printStackTrace();
            StyledDialog.dismissLoading();
        }
    }
    /*
    * 手机号正则
    * */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }





}
