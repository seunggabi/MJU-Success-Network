package com.seunggabi.mju_success_network.view.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.group.GroupData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//약속 리스트 뷰
public class ScheduleListActivity extends AppCompatActivity {
    private TextView search;
    private ListView listView;
    private ScheduleViewAdapter adapter;
    private Context me = this;
    private GroupData groupData;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        intent = getIntent();
        groupData = (GroupData)intent.getSerializableExtra("GroupData");
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ScheduleViewAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ScheduleData data = adapter.dataList.get(position);
                Intent intent = new Intent(me, ScheduleInfoActivity.class);
                intent.putExtra("GroupData", groupData);
                intent.putExtra("ScheduleData", data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        reload();
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Tool.getInstance().reload(this);

        onResume();
    }

    public void reload() {
        adapter.clear();
        String url = "http://"+ Constants.IP+"/api/schedule.php?mode=get";
        HashMap<String, String> data = new HashMap<String, String>();
        JSONArray array = null;

        data.put("g_id", String.valueOf(groupData.getG_id()));
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    ScheduleData scheduleData = new ScheduleData();
                    scheduleData.setS_id(Integer.parseInt(obj.getString("s_id")));
                    scheduleData.setU_id(Integer.parseInt(obj.getString("u_id")));
                    scheduleData.setG_id(Integer.parseInt(obj.getString("g_id")));
                    scheduleData.setS_name(obj.getString("s_name"));
                    scheduleData.setS_content(obj.getString("s_content"));
                    scheduleData.setS_datetime(Tool.getInstance().stringToDate(obj.getString("s_datetime")));
                    scheduleData.setS_time(Tool.getInstance().stringToDate(obj.getString("s_time")));
                    scheduleData.setS_gps_logitude(Float.parseFloat(obj.getString("s_gps_logitude")));
                    scheduleData.setS_gps_latitude(Float.parseFloat(obj.getString("s_gps_latitude")));
                    scheduleData.setS_gps_location(obj.getString("s_gps_location"));
                    scheduleData.setS_gps_name(obj.getString("s_gps_name"));
                    adapter.addItem(scheduleData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        findViewById(R.id.layout).requestLayout();
    }
}
