package com.seunggabi.mju_success_network.view.chatting;

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

public class ChattingActivity extends AppCompatActivity {
    GroupData groupData;

    private TextView send;
    private ListView listView;
    private ChattingViewAdapter adapter;
    private Context me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Tool.getInstance().getUser(this);

        Intent intent = getIntent();
        groupData = (GroupData)intent.getSerializableExtra("GroupData");

        send = (TextView)findViewById(R.id.search);
        listView = (ListView)findViewById(R.id.listView);
        adapter = new ChattingViewAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                ChatListData data = adapter.dataList.get(position);
//                Intent intent = new Intent(me, GroupInfoActivity.class);
//                intent.putExtra("ChatListData", (Serializable) data);
//                startActivity(intent);
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
        String url = "http://"+ Constants.IP+"/api/chatting.php";
        JSONArray array = null;

        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    ChattingData chattingData = new ChattingData();
                    chattingData.setU_id(Integer.parseInt(obj.getString("u_id")));
                    chattingData.setU_name(obj.getString("u_name"));
                    chattingData.setL_time(obj.getString("l_time"));
                    chattingData.setL_content(obj.getString("l_content"));
                    adapter.addItem(chattingData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        findViewById(R.id.layout).requestLayout();
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(adapter.getCount()-1);
            }
        });
    }

    public void send(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(groupData.getG_id()));
        data.put("content", String.valueOf(send.getText()));
        data.put("u_name", Constants.user.getName());
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        String url = "http://"+ Constants.IP+"/fcm/send.php";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        reload();
    }
}
