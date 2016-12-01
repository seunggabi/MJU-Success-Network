package com.seunggabi.mju_success_network.view.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.model.bean.Schedule;
import com.seunggabi.mju_success_network.view.chatting.ChattingUserActivity;
import com.seunggabi.mju_success_network.view.group.GroupData;
import com.seunggabi.mju_success_network.view.map.MapParentActivity;

import java.util.HashMap;

public class ScheduleInfoActivity extends MapParentActivity {
    ScheduleData scheduleData;
    GroupData groupData;
    Intent intent;
    TextView name;
    TextView content;
    TextView datetime;
    TextView gps_location;
    TextView gps_name;
    Toolbar toolbar;
    Activity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_info);

        intent = getIntent();
        groupData = (GroupData) intent.getSerializableExtra("GroupData");
        scheduleData = (ScheduleData) intent.getSerializableExtra("ScheduleData");

        name = (TextView) findViewById(R.id.name);
        content = (TextView) findViewById(R.id.content);
        datetime = (TextView) findViewById(R.id.datetime);
        gps_location = (TextView) findViewById(R.id.gps_location);
        gps_name = (TextView) findViewById(R.id.gps_name);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        name.setText(scheduleData.getS_name());
        content.setText(scheduleData.getS_content());
        datetime.setText(Tool.getInstance().dateToString(scheduleData.getS_datetime()));
        gps_location.setText(scheduleData.getS_gps_location());
        gps_name.setText(scheduleData.getS_gps_name());

        setMapView();
        NGeoPoint nGeoPoint = new NGeoPoint(scheduleData.getS_gps_logitude(), scheduleData.getS_gps_latitude());
        NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
        poiData.beginPOIdata(1);
        poiData.addPOIitem(nGeoPoint.getLongitude(), nGeoPoint.getLatitude(), scheduleData.getS_gps_name(), markerId, 0);
        poiData.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        poiDataOverlay.showAllPOIdata(0);

        mMapController.setMapCenter(nGeoPoint);

        setToolbar();
    }

    public void setToolbar() {
        toolbar.inflateMenu(R.menu.schedule_menu);

        if(scheduleData.getU_id() == Constants.user.getId()){
            toolbar.getMenu().getItem(1).setVisible(true);
        } else {
            toolbar.getMenu().getItem(1).setVisible(false);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            Intent intent;

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch(id) {
                    case R.id.attend:
                        intent = new Intent(me, AttendActivity.class);
                        intent.putExtra("ScheduleData", scheduleData);
                        startActivity(intent);
                        break;
                    case R.id.remove:
                        removeSchedule(scheduleData);
                        break;
                }
                return false;
            }
        });
    }

    public void removeSchedule(ScheduleData schedule) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("s_id", String.valueOf(schedule.getS_id()));
        String url = "http://"+ Constants.IP+"/fcm/schedule.php?mode=remove";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        intent = new Intent(this, ScheduleListActivity.class);
        intent.putExtra("GroupData", groupData);
        intent.putExtra("ScheduleData", scheduleData);
        startActivity(intent);
    }
}
