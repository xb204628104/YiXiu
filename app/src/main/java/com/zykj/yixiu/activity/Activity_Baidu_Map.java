package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zykj.yixiu.R;
import com.zykj.yixiu.utils.Y;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_Baidu_Map extends Activity {
    @Bind(R.id.baidu)
    MapView baidu;
    @Bind(R.id.et_baidu_adress)
    EditText etBaiduAdress;
    @Bind(R.id.bt_baidu_ok)
    Button btBaiduOk;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    boolean isFristLoc=true;
    private String adress;
    private LatLng latLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);
        ButterKnife.bind(this);
        baiduMap = baidu.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMyLocationEnabled(true);

        initLocationClient();
        seletMap();
    }



    @OnClick(R.id.bt_baidu_ok)
    public void onClick() {
        adress = etBaiduAdress.getText().toString().trim();
        if (TextUtils.isEmpty(adress)){//判断是否地址为空
            Y.t("请输入您的地址");
        }else {
            //发起地理编码
            GeoCoder geoCoder=GeoCoder.newInstance();
            //地理编码监听器
            geoCoder.geocode(new GeoCodeOption().city("哈尔滨").address(adress));
            //地理编码监听器
            geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    if (geoCodeResult==null||geoCodeResult.error!=GeoCodeResult.ERRORNO.NO_ERROR){
                        Y.t("请输入合法地址");
                    }else {
                        Y.t("添加成功");

                        LatLng location = geoCodeResult.getLocation();
                        baiduMap.clear();
                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromResource(R.mipmap.ic_launcher);
                        //构建MarkerOption，用于在地图上添加Marker
                        OverlayOptions overlayOptions=new MarkerOptions()
                                .icon(bitmap)
                                .position(geoCodeResult.getLocation());
                        //在地图上添加Marker，并显示
                        baiduMap.addOverlay(overlayOptions);
                        MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLng(location);
                        baiduMap.animateMapStatus(mapStatusUpdate);
                        Intent intent=new Intent(Activity_Baidu_Map.this,Activity_Callservice_Adress_Edit.class);
                        intent.putExtra("adress",adress);
                        startActivity(intent);
                    }

                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                }
            });


        }

    }
    public void jieMa(LatLng latLng1) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng1));
        baiduMap.clear();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        OverlayOptions overlayOptions = new MarkerOptions().icon(bitmapDescriptor).position(latLng1);
        baiduMap.addOverlay(overlayOptions);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult != null) {
                    Toast.makeText(Activity_Baidu_Map.this, reverseGeoCodeResult.getAddress(), Toast.LENGTH_SHORT).show();
                    etBaiduAdress.setText(reverseGeoCodeResult.getAddress());
                    ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                    Y.ADDRESS.setAddress(reverseGeoCodeResult.getAddress());
                    Y.ADDRESS.setRegion(addressDetail.district);
                    Y.ADDRESS.setCity_name(addressDetail.city);
                    Y.ADDRESS.setLon(reverseGeoCodeResult.getLocation().longitude+"");
                    Y.ADDRESS.setLat(reverseGeoCodeResult.getLocation().latitude+"");

                }
            }
        });
    }
    private void seletMap() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng1) {
                jieMa(latLng1);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                Toast.makeText(Activity_Baidu_Map.this, mapPoi.getName(), Toast.LENGTH_SHORT).show();
                jieMa(mapPoi.getPosition());
                return false;
            }
        });
    }
    public void initLocationClient(){
        locationClient = new LocationClient(this);
        LocationClientOption clientOption=new LocationClientOption();
        clientOption.setCoorType("db0911");//经纬度为百度经纬度
        clientOption.setOpenGps(true);// 开启GPS
        clientOption.setScanSpan(50000000);//定位间隔5000毫秒
        clientOption.setIsNeedAddress(true);//定位成功后是否返回地址信息
        locationClient.setLocOption(clientOption);//配置信息交给LocationClient对象
        baiduMap.setMyLocationEnabled(true);
        locationClient.start();
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(final BDLocation bdLocation) {
                if (bdLocation==null||baiduMap==null){
                    Y.t("定位失败");

                }else {

                    Y.t(bdLocation.getAddrStr());
                    MyLocationData myLocationData=new MyLocationData.Builder()
                            .accuracy(bdLocation.getRadius())//设置半径
                            .latitude(bdLocation.getLatitude())//设置经纬度
                            .longitude(bdLocation.getLongitude())//设置经纬度
                            .build();
                    // 把定位信息交给百度地图
                    baiduMap.setMyLocationData(myLocationData);

                    //获取经纬度
                    latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                    if (isFristLoc){
                        Y.ADDRESS.setCity_code(bdLocation.getAddress().cityCode);
                        Y.ADDRESS.setAddress(bdLocation.getAddrStr());
                        Y.ADDRESS.setRegion(bdLocation.getDistrict());
                        Y.ADDRESS.setCity_name(bdLocation.getAddress().city);
                        Y.ADDRESS.setLon(bdLocation.getLatitude()+"");
                        Y.ADDRESS.setLat(bdLocation.getLongitude()+"");
                        //不是第一次了
                        isFristLoc=false;
                        //更新百度地图对象
                        MapStatusUpdate mapStatusUpdate1=MapStatusUpdateFactory.newLatLng(latLng);
                        //吧更新的对象给百度
                        baiduMap.animateMapStatus(mapStatusUpdate1);
                    }//开启线程
                    etBaiduAdress.post(new Runnable() {
                        @Override
                        public void run() {
                            etBaiduAdress.setText(bdLocation.getAddrStr());
                        }
                    });
                    baiduMap.clear();
                    //在定位的地方标一个标记
                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                            .fromResource(R.mipmap.ic_launcher);
                    //构建MarkerOption，用于在地图上添加Marker
                    OverlayOptions overlayOptions=new MarkerOptions()
                            .icon(bitmap)
                            .position(latLng);
                    //在地图上添加Marker，并显示
                    baiduMap.addOverlay(overlayOptions);

                }
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        baiduMap.setMyLocationEnabled(true);
        if (!locationClient.isStarted()) {
            locationClient.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        baiduMap.setMyLocationEnabled(false);
        locationClient.stop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        baidu.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        baidu.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        baidu.onPause();
    }

}
