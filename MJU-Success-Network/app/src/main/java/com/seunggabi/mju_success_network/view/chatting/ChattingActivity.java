package com.seunggabi.mju_success_network.view.chatting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private GroupData groupData;
    private SharedPreferences prefs;
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
        prefs = getSharedPreferences("chattingPrefs", MODE_PRIVATE);

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
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("send", send.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        reload();
        this.send.setText(prefs.getString("send", ""));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chatting_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        Log.d("test", "onPrepareOptionsMenu - 옵션메뉴가 " +
//                "화면에 보여질때 마다 호출됨");
//        if(bLog){ // 로그인 한 상태: 로그인은 안보이게, 로그아웃은 보이게
//            menu.getItem(0).setEnabled(true);
//            menu.getItem(1).setEnabled(false);
//        }else{ // 로그 아웃 한 상태 : 로그인 보이게, 로그아웃은 안보이게
//            menu.getItem(0).setEnabled(false);
//            menu.getItem(1).setEnabled(true);
//        }
//
//        bLog = !bLog;   // 값을 반대로 바꿈

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.user: break;
            case R.id.make: break;
            case R.id.view: break;
        }
        return super.onOptionsItemSelected(item);
    }
}
