package com.seunggabi.mju_success_network.view.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.chatting.ChattingActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

//그룹 정보 뷰
public class GroupInfoActivity extends AppCompatActivity {
    GroupData GroupData;
    TextView name;
    TextView intro;
    Button editButton;
    Button joinButton;
    Button exitButton;
    Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        Intent intent = getIntent();
        GroupData = (GroupData)intent.getSerializableExtra("GroupData");

        String url = "http://"+ Constants.IP+"/api/join.php?mode=check";
        HashMap<String, String> data = new HashMap<String, String>();
        JSONArray array = null;

        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("g_id", String.valueOf(GroupData.getG_id()));
        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        name = (TextView)findViewById(R.id.name);
        intro = (TextView)findViewById(R.id.phone);
        editButton = (Button)findViewById(R.id.editButton);
        joinButton = (Button)findViewById(R.id.joinButton);
        exitButton = (Button)findViewById(R.id.exitButton);
        enterButton = (Button)findViewById(R.id.enterButton);

        name.setText(GroupData.getG_name());
        intro.setText(GroupData.getG_intro());

        try {
            int check = array.getJSONObject(0).getInt("check");
            if(Constants.user.getId() != GroupData.getU_id()) {
                editButton.setVisibility(View.INVISIBLE);
            }
            switch (check) {
                case 0: exitButton.setVisibility(View.INVISIBLE); enterButton.setVisibility(View.INVISIBLE); break;
                case 1: joinButton.setVisibility(View.INVISIBLE); break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void join(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(GroupData.getG_id()));
        data.put("token", FirebaseInstanceId.getInstance().getToken());

        String url = "http://"+ Constants.IP+"/fcm/group.php?mode=join";
        Tool.getInstance().sendToServer(data, url);
        Tool.getInstance().toast("그룹에 참가했습니다.", this);
        Tool.getInstance().reload(this);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, GroupEditActivity.class);
        intent.putExtra("GroupData", GroupData);
        startActivity(intent);
    }
    public void exit(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(GroupData.getG_id()));
        data.put("token", FirebaseInstanceId.getInstance().getToken());

        String url = "http://"+ Constants.IP+"/fcm/group.php?mode=exit";
        Tool.getInstance().sendToServer(data, url);
        Tool.getInstance().toast("그룹에서 나왔습니다.", this);
        Tool.getInstance().reload(this);
    }

    public void enter(View view) {
        Intent intent = new Intent(this, ChattingActivity.class);
        intent.putExtra("GroupData", GroupData);
        startActivity(intent);
    }
}
