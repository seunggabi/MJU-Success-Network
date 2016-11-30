package com.seunggabi.mju_success_network.view.schedule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.model.bean.Schedule;
import com.seunggabi.mju_success_network.view.menu.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapActivity extends NMapActivity {
    private static final String LOG_TAG = "MapActivity";
    private static final String KEY_ZOOM_LEVEL = "NMapViewer.zoomLevel";
    private static final String KEY_CENTER_LONGITUDE = "NMapViewer.centerLongitudeE6";
    private static final String KEY_CENTER_LATITUDE = "NMapViewer.centerLatitudeE6";
    private static final String KEY_VIEW_MODE = "NMapViewer.viewMode";
    private static final String CLIENT_ID = "FhBFoQsusVSmtukzs0UO";

    private static final NGeoPoint NMAP_LOCATION_DEFAULT = new NGeoPoint(126.978371, 37.5666091);
    private static final int NMAP_ZOOMLEVEL_DEFAULT = 11;
    private static final int NMAP_VIEW_MODE_DEFAULT = NMapView.VIEW_MODE_VECTOR;
    private static boolean mIsMapEnlared = false;

    private NMapView mMapView;
    private NMapController mMapController;
    private NMapLocationManager mMapLocationManager;
    private SharedPreferences mPreferences;
    private NMapViewerResourceProvider mMapViewerResourceProvider;
    private NMapOverlayManager mOverlayManager;
    private NGeoPoint saveLocation;
    private EditText editText;
    private int markerId;
    private String addrline;
    private Activity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        getPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        NMapView.LayoutParams lp = new NMapView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, NMapView.LayoutParams.BOTTOM_RIGHT);
        mMapView = (NMapView)findViewById(R.id.mapView);
        mMapView.setClientId(CLIENT_ID);
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
        mMapView.setBuiltInZoomControls(true, lp);
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

        mMapLocationManager = new NMapLocationManager(this);
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
        mMapController = mMapView.getMapController();
        mMapController.setMapCenter(NMAP_LOCATION_DEFAULT, 11);

        markerId = 0;

        startMyLocation();
    }

    public void search(View v) {
        editText = (EditText)findViewById(R.id.editText);
        String data = editText.getText().toString();

        Geocoder geo = new Geocoder(this, Locale.KOREAN);
        List<Address> addr = null;
        double latitude = 37.2266173;
        double longitude = 127.187609;
        NGeoPoint location = null;
        try {
            addr = geo.getFromLocationName(data, 2);
            longitude = addr.get(0).getLongitude();
            latitude = addr.get(0).getLatitude();
            if(longitude >= 124 && longitude <= 132 && latitude >=33 && latitude <= 43){ // 우리나라 위도 경도
                if (mMapController != null) {
                    location = new NGeoPoint(longitude, latitude);
                    mMapController.setMapCenter(location);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // android 6.0 이상에서 권한 체크하는 함수
    public void getPermission() {
        String[] permissions = new String[]{android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION};
        if(Build.VERSION.SDK_INT>=23) {
            for(String permission:permissions) {
                int result = PermissionChecker.checkSelfPermission(this, permission);
                if(result==PermissionChecker.PERMISSION_GRANTED) ;
                else {
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }
            }
        }

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
    }

    private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

        @Override
        public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
            try {
                Geocoder geo = new Geocoder(MapActivity.this, Locale.KOREAN);
                List<Address> addr = geo.getFromLocation(saveLocation.getLatitude(), saveLocation.getLongitude(), 2);
                addrline = addr.get(0).getAddressLine(0);
            }catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MapActivity.this, ScheduleActivity.class);
            ArrayList<String> location =  new ArrayList<String>();
            location.add(0, String.valueOf(saveLocation.getLongitude()));
            location.add(1, String.valueOf(saveLocation.getLatitude()));
            location.add(2, addrline);
            intent.putStringArrayListExtra("location", location);
            startActivity(intent);
        }

        @Override
        public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {}
    };

    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {

        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

            if (errorInfo == null) { // success
                restoreInstanceState();
            } else { // fail
                Log.e(LOG_TAG, "onMapInitHandler: error=" + errorInfo.toString());
            }
        }

        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
            saveLocation = nGeoPoint;
            if(markerId == 0){
                markerId = NMapPOIflagType.PIN;
            }else {
                mOverlayManager.clearOverlays();
            }

            NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
            poiData.beginPOIdata(1);
            poiData.addPOIitem(nGeoPoint.getLongitude(), nGeoPoint.getLatitude(), "모임장소 선택", markerId, 0);
            poiData.endPOIdata();

            NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
            poiDataOverlay.showAllPOIdata(0);
            poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);
        }
        @Override
        public void onMapCenterChangeFine(NMapView nMapView) {}
        @Override
        public void onZoomLevelChange(NMapView nMapView, int i) {}
        @Override
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {}
    };

    // Map 터치시 발생하는 이벤트 핸들러 달아놓은 함수
    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {}
        @Override
        public void onLongPressCanceled(NMapView mapView) {}
        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {}
        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {}
        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {}
        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {}
    };

    // 현재 위치 변할 때 발생하는 이벤트 핸들러 달아놓은 함수
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {
            if (mMapController != null) {
                mMapController.animateTo(nGeoPoint);
                stopMyLocation();
            }
            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager nMapLocationManager) {
            Toast.makeText(MapActivity.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager nMapLocationManager, NGeoPoint nGeoPoint) {
            stopMyLocation();
        }
    };

    // 현재 위치 찾기 시작 함수
    public void startMyLocation() {
        try {
            if(mMapLocationManager.enableMyLocation(true)) { // 현재 위치 찾기 시작
                if(mMapLocationManager.isMyLocationFixed()) { // 현재 위치 찾기 성공
                    mMapController.setMapCenter(mMapLocationManager.getMyLocation());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 현재 위치 찾기 종료 함수
    public void stopMyLocation() {
        mMapLocationManager.disableMyLocation();
    }

    // 가장 최근 위치 불러오는 함수
    private void restoreInstanceState() {
        mPreferences = getPreferences(MODE_PRIVATE);

        int longitudeE6 = mPreferences.getInt(KEY_CENTER_LONGITUDE, NMAP_LOCATION_DEFAULT.getLongitudeE6());
        int latitudeE6 = mPreferences.getInt(KEY_CENTER_LATITUDE, NMAP_LOCATION_DEFAULT.getLatitudeE6());
        int level = mPreferences.getInt(KEY_ZOOM_LEVEL, NMAP_ZOOMLEVEL_DEFAULT);
        int viewMode = mPreferences.getInt(KEY_VIEW_MODE, NMAP_VIEW_MODE_DEFAULT);

        mMapController.setMapViewMode(viewMode);
        mMapController.setMapCenter(new NGeoPoint(longitudeE6, latitudeE6), level);

        if (mIsMapEnlared) {
            mMapView.setScalingFactor(2.0F);
        } else {
            mMapView.setScalingFactor(1.0F);
        }
    }

    // 가장 최근 위치 저장하는 함수
    private void saveInstanceState() {
        if (mPreferences == null) {
            return;
        }

        NGeoPoint center = mMapController.getMapCenter();
        int level = mMapController.getZoomLevel();
        int viewMode = mMapController.getMapViewMode();

        SharedPreferences.Editor edit = mPreferences.edit();

        edit.putInt(KEY_CENTER_LONGITUDE, center.getLongitudeE6());
        edit.putInt(KEY_CENTER_LATITUDE, center.getLatitudeE6());
        edit.putInt(KEY_ZOOM_LEVEL, level);
        edit.putInt(KEY_VIEW_MODE, viewMode);

        edit.commit();
    }
}