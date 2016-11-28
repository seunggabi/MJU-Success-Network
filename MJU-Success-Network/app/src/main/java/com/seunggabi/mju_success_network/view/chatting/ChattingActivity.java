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
import com.seunggabi.mju_success_network.Constans;
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
        setContentView(R.layout.activity_chat_list);
        Tool.getInstance().getUser(this);

        Intent intent = getIntent();
        groupData = (GroupData)intent.getSerializableExtra("GroupListData");

        send = (TextView)findViewById(R.id.send);
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

        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(adapter.getCount()-1);
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
        String url = "http://"+ Constans.IP+"/api/chat.php";
        JSONArray array = null;

        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    ChattingData ChatListdata = new ChattingData();
                    ChatListdata.setU_id(Integer.parseInt(obj.getString("u_id")));
                    ChatListdata.setU_name(obj.getString("u_name"));
                    ChatListdata.setL_time(obj.getString("l_time"));
                    ChatListdata.setL_content(obj.getString("l_content"));
                    adapter.addItem(ChatListdata);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void send(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(groupData.getG_id()));
        data.put("content", String.valueOf(send.getText()));
        data.put("u_name", Constans.user.getName());
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        String url = "http://"+ Constans.IP+"/fcm/send.php";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        Tool.getInstance().reload(this);
    }
}
