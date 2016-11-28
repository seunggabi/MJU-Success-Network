package com.seunggabi.mju_success_network.view.group;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constans;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

import java.util.HashMap;

public class GroupAddActivity extends AppCompatActivity {
    TextView name;
    TextView intro;
    TextView tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);

        name = (TextView)findViewById(R.id.name);
        intro = (TextView)findViewById(R.id.phone);
        tag = (TextView)findViewById(R.id.tag);
    }

    public void addGroup(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_name", name.getText().toString());
        data.put("g_intro", intro.getText().toString());
        data.put("g_tag", tag.getText().toString());

        data.put("token", FirebaseInstanceId.getInstance().getToken());

        String url = "http://"+ Constans.IP+"/fcm/group.php?mode=add";
        Tool.getInstance().sendToServer(data, url);
        Tool.getInstance().toast("생성되었습니다.", this);
    }
}
