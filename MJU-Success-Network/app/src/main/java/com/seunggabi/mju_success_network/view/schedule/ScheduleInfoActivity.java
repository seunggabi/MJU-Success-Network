package com.seunggabi.mju_success_network.view.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

public class ScheduleInfoActivity extends AppCompatActivity {
    ScheduleData scheduleData;
    Intent intent;
    TextView name;
    TextView content;
    TextView datetime;
    TextView gps_location;
    TextView gps_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_info);

        intent = getIntent();
        scheduleData = (ScheduleData) intent.getSerializableExtra("ScheduleData");

        name = (TextView) findViewById(R.id.name);
        content = (TextView) findViewById(R.id.content);
        datetime = (TextView) findViewById(R.id.datetime);
        gps_location = (TextView) findViewById(R.id.gps_location);
        gps_name = (TextView) findViewById(R.id.gps_name);

        name.setText(scheduleData.getS_name());
        content.setText(scheduleData.getS_content());
        datetime.setText(Tool.getInstance().dateToString(scheduleData.getS_datetime()));
        gps_location.setText(scheduleData.getS_gps_location());
        gps_name.setText(scheduleData.getS_gps_name());
    }
}
