package com.seunggabi.mju_success_network.view.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.model.bean.Schedule;
import com.seunggabi.mju_success_network.view.chatting.ChattingActivity;
import com.seunggabi.mju_success_network.view.group.GroupData;
import com.seunggabi.mju_success_network.view.map.MapActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//약속 뷰
public class ScheduleActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButtonDate;
    private RadioButton radioButtonTime;
    private RadioButton radioButtonLocation;
    private CalendarView calendarView;
    private TimePicker timePicker;
    private RelativeLayout locationLayout;
    private Date selectedDate;
    private Date currentDate;
    private EditText locationName;
    private EditText name;
    private EditText content;
    private TextView locationText;
    private String gps_location;
    private String gps_longitude;
    private String gps_latitude;
    private ArrayList<String> location;
    private Intent intent;
    private GroupData groupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Init();
        intent = getIntent();
        groupData = (GroupData)intent.getSerializableExtra("GroupData");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            location = intent.getStringArrayListExtra("location");
            gps_longitude = location.get(0);
            gps_latitude = location.get(1);
            gps_location = location.get(2);
            locationText.setText(gps_location);

            radioButtonLocation.setChecked(true);
            calendarView.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            locationLayout.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onResume();
    }

    protected void Init() {
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButtonDate = (RadioButton)findViewById(R.id.radioDate);
        radioButtonTime = (RadioButton)findViewById(R.id.radioTime);
        radioButtonLocation = (RadioButton)findViewById(R.id.radioLocation);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        locationLayout = (RelativeLayout)findViewById(R.id.locationLayout);
        locationText = (TextView)findViewById(R.id.locationText);
        locationName = (EditText)findViewById(R.id.locationName);
        name = (EditText)findViewById(R.id.name);
        content = (EditText)findViewById(R.id.content);

        location = new ArrayList<String>();
        gps_longitude = "";
        gps_latitude = "";
        gps_location = "";

        radioButtonDate.setChecked(true);
        rdCheck(null);
    }

    protected void rdCheck(View view) {
        if(radioButtonDate.isChecked()) {
            calendarView.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            locationLayout.setVisibility(View.INVISIBLE);
        }else if(radioButtonTime.isChecked()) {
            calendarView.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.VISIBLE);
            locationLayout.setVisibility(View.INVISIBLE);
        }else if(radioButtonLocation.isChecked()) {
            calendarView.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            locationLayout.setVisibility(View.VISIBLE);
        }
    }

    public void selectLocation(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, 0);
    }

    public void finishLocation(View view) {
        selectedDate = new Date(calendarView.getDate());

        long now = System.currentTimeMillis();
        currentDate = new Date(now);

        if(selectedDate.after(currentDate)) {
            if(locationName.getText().length() > 0
                    && name.getText().length() > 0
                    && content.getText().length() > 0
                    && gps_latitude.length() > 0
                    && gps_longitude.length() > 0
                    && gps_location.length() > 0) {
                Intent intent = new Intent(this, ChattingActivity.class);
                selectedDate.setHours(timePicker.getCurrentHour());
                selectedDate.setMinutes(timePicker.getCurrentMinute());
                selectedDate.setSeconds(0);

                Schedule schedule = new Schedule();
                schedule.setDatetime(selectedDate);
                schedule.setGps_latitude(gps_latitude);
                schedule.setGps_logitude(gps_longitude);
                schedule.setGps_location(gps_location);
                schedule.setGps_name(locationName.getText().toString());
                schedule.setName(name.getText().toString());
                schedule.setContent(content.getText().toString());
                schedule.setU_id(Constants.user.getId());
                schedule.setG_id(groupData.getG_id());
                intent.putExtra("GroupData", groupData);

                uploadSchedule(schedule);
                startActivity(intent);
            } else {
                Tool.getInstance().toast("장소와 내용을 입력해주세요.", this);
            }
        }else {
            Tool.getInstance().toast("약속일은 내일부터 설정이 가능합니다.", this);
        }
    }

    public void uploadSchedule(Schedule schedule) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("g_id", String.valueOf(schedule.getG_id()));
        data.put("s_name", schedule.getName());
        data.put("s_content", schedule.getContent());
        data.put("s_datetime", Tool.getInstance().dateToString(schedule.getDatetime()));
        data.put("s_gps_logitude", schedule.getGps_logitude());
        data.put("s_gps_latitude", schedule.getGps_latitude());
        data.put("s_gps_location", schedule.getGps_location());
        data.put("s_gps_name", schedule.getGps_name());
        String url = "http://"+ Constants.IP+"/fcm/schedule.php?mode=add";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);
    }
}
