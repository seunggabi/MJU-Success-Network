package com.seunggabi.mju_success_network.view.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.group.GroupAddActivity;
import com.seunggabi.mju_success_network.view.notice.NoticeData;
import com.seunggabi.mju_success_network.view.notice.NoticeViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USER on 2016-11-20.
 */

public class NoticeActivity extends ParentActivity {
    private TextView search;
    private ListView listView;
    private NoticeViewAdapter adapter;
    private Context me = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        search = (TextView)findViewById(R.id.search);
        listView = (ListView)findViewById(R.id.listView);
        adapter = new NoticeViewAdapter(this);
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
        String url = "http://"+ Constants.IP+"/api/notice.php?search="+search.getText();
        JSONArray array = null;
        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(null, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    NoticeData noticeData = new NoticeData();
                    noticeData.setN_id(Integer.parseInt(obj.getString("n_id")));
                    noticeData.setM_id(Integer.parseInt(obj.getString("m_id")));
                    noticeData.setM_name(obj.getString("m_name"));
                    noticeData.setN_content(obj.getString("n_content"));
                    noticeData.setN_time(obj.getString("n_time"));
                    adapter.addItem(noticeData);
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
