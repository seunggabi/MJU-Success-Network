package com.seunggabi.mju_success_network.view.group;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;

import java.util.HashMap;

//그룹 수정 뷰
public class GroupEditActivity extends AppCompatActivity {
    TextView name;
    TextView intro;
    TextView tag;
    RadioGroup hidden;
    RadioButton hiddenY;
    RadioButton hiddenN;
    String hiddenValue;
    GroupData GroupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_edit);
        Intent intent = getIntent();

        name = (TextView)findViewById(R.id.name);
        intro = (TextView)findViewById(R.id.phone);
        tag = (TextView)findViewById(R.id.tag);
        hidden = (RadioGroup)findViewById(R.id.hidden);
        hiddenY = (RadioButton)findViewById(R.id.hiddenY);
        hiddenN = (RadioButton)findViewById(R.id.hiddenN);

        GroupData = (GroupData) intent.getSerializableExtra("GroupData");
        name.setText(GroupData.getG_name());
        intro.setText(GroupData.getG_intro());
        tag.setText(GroupData.getG_tag());
        hiddenValue = GroupData.getG_hidden();

        if(hiddenValue.equals("Y")) {
            hiddenY.setChecked(true);
        } else if(hiddenValue.equals("N")) {
            hiddenN.setChecked(true);
        }

        hidden.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.hiddenY:
                        hiddenValue = "Y";
                        break;
                    case R.id.hiddenN:
                        hiddenValue = "N";
                        break;
                }
            }
        });

    }

    public void editGroup(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("g_id", String.valueOf(GroupData.getG_id()));
        data.put("g_name", name.getText().toString());
        data.put("g_intro", intro.getText().toString());
        data.put("g_tag", tag.getText().toString());
        data.put("g_hidden", hiddenValue);

        data.put("token", FirebaseInstanceId.getInstance().getToken());

        String url = "http://"+ Constants.IP+"/fcm/group.php?mode=edit";
        Tool.getInstance().sendToServer(data, url);
        Tool.getInstance().toast("수정되었습니다.", this);
    }
}
