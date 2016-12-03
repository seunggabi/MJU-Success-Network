package com.seunggabi.mju_success_network.view.attend;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.view.schedule.ScheduleData;
import com.seunggabi.mju_success_network.view.schedule.ScheduleInfoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//출석체크 리스트 뷰
public class AttendActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback {
    private Intent intent;
    private ScheduleData scheduleData;
    private ListView listView;
    private AttendViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
        intent = getIntent();
        scheduleData = (ScheduleData) intent.getSerializableExtra("ScheduleData");

        if(scheduleData.getU_id() != Constants.user.getId()) {
            NfcAdapter mAdapter = NfcAdapter.getDefaultAdapter(this);
            if (mAdapter == null) {
                Tool.getInstance().toast("NFC 기능이 없습니다.", this);
                return;
            }
            if (!mAdapter.isEnabled()) {
                Tool.getInstance().toast("NFC를 P2P모드로 설정해주세요.", this);
            }
            mAdapter.setNdefPushMessageCallback(this, this);
            mAdapter.setOnNdefPushCompleteCallback(this, this);
            Tool.getInstance().toast("[참석자] 핸드폰을 맞대고 화면을 터치해주세요!",this);
        }
        else {
           Tool.getInstance().toast("[주최자] 핸드폰을 맞대고 기다려주세요!",this);
        }

        listView = (ListView)findViewById(R.id.listView);
        adapter = new AttendViewAdapter(this);
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
        String url = "http://"+ Constants.IP+"/api/attend.php";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("s_id", String.valueOf(scheduleData.getS_id()));
        JSONArray array = null;
        if(Tool.getInstance().isNetwork(this))
            array = Tool.getInstance().getToServer(data, url);

        if(array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject obj = array.getJSONObject(i);
                    AttendData attendData = new AttendData();
                    attendData.setA_id(Integer.parseInt(obj.getString("a_id")));
                    attendData.setU_id(Integer.parseInt(obj.getString("u_id")));
                    attendData.setS_id(Integer.parseInt(obj.getString("s_id")));
                    attendData.setU_name(obj.getString("u_name"));
                    attendData.setA_time(Tool.getInstance().stringToDate(obj.getString("a_time")));
                    adapter.addItem(attendData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        findViewById(R.id.layout).requestLayout();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        if(scheduleData.getU_id() == Constants.user.getId()) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
                Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                        NfcAdapter.EXTRA_NDEF_MESSAGES);

                NdefMessage message = (NdefMessage) rawMessages[0];
                int u_id = Integer.parseInt(new String(message.getRecords()[0].getPayload()));
                checkAttend(u_id);

            } else
                Tool.getInstance().toast("NFC태그를 기다리고 있는 중입니다.", this);
        }
        reload();
    }

    public void checkAttend(int u_id) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("token", FirebaseInstanceId.getInstance().getToken());
        data.put("s_id", String.valueOf(scheduleData.getS_id()));
        data.put("g_id", String.valueOf(scheduleData.getG_id()));
        data.put("u_id", String.valueOf(u_id));
        String url = "http://"+ Constants.IP+"/fcm/attend.php?mode=check";

        if(Tool.getInstance().isNetwork(this))
            Tool.getInstance().getToServer(data, url);

        Tool.getInstance().toast("출석 체크 되었습니다.", this);
    }

    /**
     * Ndef Record that will be sent over via NFC
     * @param nfcEvent
     * @return
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        NdefRecord ndefRecord = NdefRecord.createMime("text/plain", String.valueOf(Constants.user.getId()).getBytes());
        NdefMessage ndefMessage = new NdefMessage(ndefRecord);
        return ndefMessage;
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        intent = new Intent(this, ScheduleInfoActivity.class);
        startActivity(intent);
    }
}
