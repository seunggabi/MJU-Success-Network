package com.seunggabi.mju_success_network.view.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.bluetoothchat.WhisperActivity;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.video.VideoActivity;

import java.util.HashMap;

/**
 * Created by USER on 2016-11-20.
 */

public class SettingActivity extends ParentActivity {
    private Button alarmONButton;
    private Button alarmOFFButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        alarmONButton = (Button) findViewById(R.id.alarmON);
        alarmOFFButton = (Button) findViewById(R.id.alarmOFF);
        checkAlarm();
    }

    @Override
    protected void onResume() {
        checkAlarm();
        super.onResume();
    }

    public void checkAlarm() {
        if(Constants.user.getAlarm() == 'N') {
            alarmONButton.setVisibility(View.VISIBLE);
            alarmOFFButton.setVisibility(View.INVISIBLE);
        } else {
            alarmONButton.setVisibility(View.INVISIBLE);
            alarmOFFButton.setVisibility(View.VISIBLE);
        }
    }

    public void changeAlarm(View view) {
        char alarmStatus = Constants.user.getAlarm();
        alarmStatus = Tool.getInstance().toggleYN(alarmStatus);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("u_alarm", String.valueOf(alarmStatus));
        String url = "http://"+ Constants.IP+"/fcm/user.php?mode=changeAlarm";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        Constants.user.setAlarm(alarmStatus);
        checkAlarm();
    }

    public void goWhisper(View view) {
        Tool.getInstance().goActivity(this, WhisperActivity.class);
    }

    public void goVideo(View view) {
        Tool.getInstance().goActivity(this, VideoActivity.class);
    }
}
