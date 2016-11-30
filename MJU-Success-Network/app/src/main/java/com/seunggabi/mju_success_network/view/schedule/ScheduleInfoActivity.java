package com.seunggabi.mju_success_network.view.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.seunggabi.mju_success_network.R;

public class ScheduleInfoActivity extends AppCompatActivity {
    ScheduleData scheduleData;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_info);

        intent = getIntent();
        scheduleData = (ScheduleData) intent.getSerializableExtra("ScheduleData");
    }
}
