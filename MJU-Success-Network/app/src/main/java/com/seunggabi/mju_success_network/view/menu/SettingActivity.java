package com.seunggabi.mju_success_network.view.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

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
    }

    @Override
    protected void onResume() {
        if(Constants.user.getAlarm() == 'Y') {
            alarmONButton.setVisibility(View.VISIBLE);
            alarmOFFButton.setVisibility(View.INVISIBLE);
        } else {
            alarmONButton.setVisibility(View.INVISIBLE);
            alarmOFFButton.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    public void alarmChange(View view) {
        char alarmStatus = Constants.user.getAlarm();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("u_alarm", String.valueOf(alarmStatus));
        String url = "http://"+ Constants.IP+"/fcm/user.php?mode=changeAlarm";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        Constants.user.setAlarm(Tool.getInstance().toggleYN(alarmStatus));
        Tool.getInstance().reload(this);
    }
}
