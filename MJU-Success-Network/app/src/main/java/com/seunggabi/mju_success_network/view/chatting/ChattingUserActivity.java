package com.seunggabi.mju_success_network.view.chatting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.group.GroupAddActivity;
import com.seunggabi.mju_success_network.view.group.GroupData;
import com.seunggabi.mju_success_network.view.group.GroupInfoActivity;
import com.seunggabi.mju_success_network.view.group.GroupViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

public class ChattingUserActivity extends AppCompatActivity {
    private GroupData groupData;
    private ListView listView;
    private ChattingUserViewAdapter adapter;
    private Context me = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_user);

        Intent intent = getIntent();
        groupData = (GroupData)intent.getSerializableExtra("GroupData");

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ChattingUserViewAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            }
        });
    }

    @Override
    protected void onResume() {
        reload();
        super.onResume();
    }

    public void reload() {
        adapter.clear();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(groupData.getG_id()));
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        String url = "http://"+ Constants.IP+"/api/join.php?mode=getUserList";
        JSONArray array = null;

        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    ChattingUserData chattingUserData = new ChattingUserData();
                    chattingUserData.setJ_id(Integer.parseInt(obj.getString("j_id")));
                    chattingUserData.setU_id(Integer.parseInt(obj.getString("u_id")));
                    chattingUserData.setG_id(Integer.parseInt(obj.getString("g_id")));
                    chattingUserData.setU_name(obj.getString("u_name"));
                    chattingUserData.setJ_status(obj.getString("j_status").charAt(0));
                    chattingUserData.setJ_time(obj.getString("j_time"));

                    if(Constants.user.getId() != chattingUserData.getU_id())
                        adapter.addItem(chattingUserData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        findViewById(R.id.layout).requestLayout();
    }

    public void groupAdd(View view) {
        Intent intent = new Intent(this, GroupAddActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        reload();
    }
}
