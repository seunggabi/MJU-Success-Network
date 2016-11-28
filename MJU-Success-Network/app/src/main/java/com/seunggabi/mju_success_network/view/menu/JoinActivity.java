package com.seunggabi.mju_success_network.view.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.group.GroupData;
import com.seunggabi.mju_success_network.view.group.GroupInfoActivity;
import com.seunggabi.mju_success_network.view.group.GroupViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by USER on 2016-11-20.
 */

public class JoinActivity extends ParentActivity {
    private TextView search;
    private ListView listView;
    private GroupViewAdapter adapter;
    private Context me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        search = (TextView)findViewById(R.id.search);
        listView = (ListView)findViewById(R.id.listView);
        adapter = new GroupViewAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                GroupData data = adapter.dataList.get(position);
                Intent intent = new Intent(me, GroupInfoActivity.class);
                intent.putExtra("GroupData", (Serializable) data);
                startActivity(intent);
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
        String url = "http://"+ Constants.IP+"/api/join.php?mode=get";
        HashMap<String, String> data = new HashMap<String, String>();
        JSONArray array = null;

        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("search", search.getText().toString());
        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    GroupData groupData = new GroupData();
                    groupData.setU_id(Integer.parseInt(obj.getString("u_id")));
                    groupData.setG_id(Integer.parseInt(obj.getString("g_id")));
                    groupData.setU_name(obj.getString("u_name"));
                    groupData.setG_name(obj.getString("g_name"));
                    groupData.setG_intro(obj.getString("g_intro"));
                    groupData.setG_time(obj.getString("g_time"));
                    groupData.setG_status(obj.getString("g_status"));
                    groupData.setG_hidden(obj.getString("g_hidden"));
                    groupData.setG_tag(obj.getString("g_tag"));
                    adapter.addItem(groupData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        findViewById(R.id.layout).requestLayout();
    }

    public void search(View view) {
        reload();
    }
}
