package com.zykj.yixiu.utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/15.
 */

public class YURL {


    // 服务器地址
    public static   final  String BASE_HOST="http://221.207.184.124:7071/";


    //项目名称
    public  static   final  String HOST=BASE_HOST+"yxg/";

  //  http://221.207.184.124:7071/yxg/robOrder?order_id=397&engineer_id=20
  //  http://221.207.184.124:7071/yxg/finishOrder?order_id=397&engineer_id=20
  //  http://221.207.184.124:7071/yxg/findOrderByState?custom_id=150&order_state=1

    //查找手机品牌
    public static   final  String  FIND_PHONE_BRAND=HOST+"findPhoneBrand";

    //根据品牌查找型号
    public static   final  String  FIND_PHONE_MODEL=HOST+"findPhoneModel";

    //查询手机故障
    public static   final  String  FIND_PHONE_FAULT=HOST+"findPhoneFault";

    //查询电脑型号
    public static   final  String  FIND_BYCOMPUTER_MODEL=HOST+"findByComputerModel";

    //查找电脑品牌
    public static   final  String  FIND_COMPUTER_BRAND=HOST+"findComputerBrand";

    //查找电脑下的分类
    public static   final  String  FIND_COMPUTER_CATEGORY=HOST+"findComputerCategory";

    //查找家电下的分类
    public static   final  String  FIND_APPLIANCE_CATEGORY=HOST+"findApplianceCategory";

    //查找家电下的品牌
    public static   final  String  FIND_BYAPPLIANCE_BRAND=HOST+"findByApplianceBrand";

    //查找家电下的分类
    public static   final  String  FIND_BYAPPLIANCE_MODEL=HOST+"findByApplianceModel";


    //注册页面的接口
    public static   final  String  REGISTER=HOST+"register";

    //设置密码的接口
    public static   final  String  SET_PASSWORD=HOST+"setpassword";

    //设置登录的接口
    public static   final  String  LOGIN=HOST+"login";

    //设置上传头像的接口
    public static   final  String  UP_LOAD_ICON=HOST+"uploadIcon";

    //设置用户信息的接口
    public static   final  String  SET_USER_INFO=HOST+"setUserInfo";

    //设置上传身份证的接口
    public static   final  String   UP_LOADID_CARD=HOST+"uploadIdCard";

    //设置发布订单的接口
    public static   final  String   ADD_ORDER=HOST+"addOrder";

    //设置添加地址的接口
    public static   final  String   ADD_ADDRESS=HOST+"addaddress";

    //设置查询地址的接口
    public static   final  String   SELECT_ADDRESS=HOST+"selectAddress";

    //设置删除地址的接口
    public static   final  String   DELA_DDRESS=HOST+"delAddress";

    //设置删除地址的接口
    public static   final  String   DEF_ADDRESS=HOST+"defAddress";
    //未完成的订单
    public static   final  String   FIND_ORDERBYSTATE=HOST+"findOrderByState";
    //未完成的订单数量
    public static   final  String   FIND_UNFINISH_COUNT=HOST+"findUnFinishCount";
    //取消订单
    public static   final  String   CAN_CELORDER=HOST+"cancelOrder";

    //删除订单
    public static   final  String   DEL_ORDER=HOST+"delOrder";
}
