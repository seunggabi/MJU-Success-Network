package com.seunggabi.mju_success_network.view.map;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.seunggabi.mju_success_network.R;

public class MapParentActivity extends NMapActivity{
    protected static final String KEY_ZOOM_LEVEL = "NMapViewer.zoomLevel";
    protected static final String KEY_CENTER_LONGITUDE = "NMapViewer.centerLongitudeE6";
    protected static final String KEY_CENTER_LATITUDE = "NMapViewer.centerLatitudeE6";
    protected static final String KEY_VIEW_MODE = "NMapViewer.viewMode";
    protected static final String CLIENT_ID = "FhBFoQsusVSmtukzs0UO";

    protected static final NGeoPoint NMAP_LOCATION_DEFAULT = new NGeoPoint(126.978371, 37.5666091);
    protected static final int NMAP_ZOOMLEVEL_DEFAULT = 11;
    protected static final int NMAP_VIEW_MODE_DEFAULT = NMapView.VIEW_MODE_VECTOR;
    protected static boolean mIsMapEnlared = false;

    protected NMapView mMapView;
    protected NMapController mMapController;
    protected NMapLocationManager mMapLocationManager;
    protected SharedPreferences mPreferences;
    protected NMapViewerResourceProvider mMapViewerResourceProvider;
    protected NMapOverlayManager mOverlayManager;
    protected NGeoPoint saveLocation;
    protected NMapView.LayoutParams lp;
    protected int markerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPermission();
        lp = new NMapView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, NMapView.LayoutParams.BOTTOM_RIGHT);

        mMapLocationManager = new NMapLocationManager(this);
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        markerId = NMapPOIflagType.PIN;    }

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

    // Map 터치시 발생하는 이벤트 핸들러 달아놓은 함수
    protected final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
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
    protected final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

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
            Toast.makeText(MapParentActivity.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
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
    protected void restoreInstanceState() {
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
    protected void saveInstanceState() {
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

    protected void setMapView() {
        mMapView = (NMapView)findViewById(R.id.mapView);
        mMapView.setClientId(CLIENT_ID);
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
        mMapView.setBuiltInZoomControls(true, lp);

        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);
        mMapController = mMapView.getMapController();
        mMapController.setMapCenter(NMAP_LOCATION_DEFAULT, 11);
    }
}