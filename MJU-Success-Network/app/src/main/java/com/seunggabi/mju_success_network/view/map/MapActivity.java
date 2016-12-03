package com.seunggabi.mju_success_network.view.map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.view.schedule.ScheduleActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//약속 지도 설정 뷰
public class MapActivity extends MapParentActivity {
    private EditText editText;
    private String addrline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setMapView();
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

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
            }
        }

        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
            saveLocation = nGeoPoint;
            mOverlayManager.clearOverlays();

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
}