package com.seunggabi.mju_success_network.view.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.seunggabi.mju_success_network.Constants;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.helper.Tool;
import com.seunggabi.mju_success_network.model.DBTool;
import com.seunggabi.mju_success_network.model.bean.Dept;
import com.seunggabi.mju_success_network.model.bean.Pos;
import com.seunggabi.mju_success_network.model.dao.DeptDAO;
import com.seunggabi.mju_success_network.model.dao.PosDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ParentActivity {
    TextView email;
    TextView name;
    TextView phone;
    TextView intro;
    Spinner deptSpinner;
    Spinner posSpinner;
    int deptPos;
    int posPos;
    Activity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("study");

        DBTool.getInstance().setContentResolver(getContentResolver());
        email = (TextView)findViewById(R.id.email);
        name = (TextView)findViewById(R.id.name);
        phone = (TextView)findViewById(R.id.intro);
        intro = (TextView)findViewById(R.id.phone);

        ArrayList<Dept> deptList = DeptDAO.getInstance().getList();
        ArrayList<String> deptNameList = new ArrayList<String>();
        for(Dept d : deptList) {
            deptNameList.add(d.getName());
        }
        deptSpinner = (Spinner)findViewById(R.id.dept);
        ArrayAdapter<String> deptAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, deptNameList);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptSpinner.setAdapter(deptAdapter);

        ArrayList<Pos> posList = PosDAO.getInstance().getList();
        ArrayList<String> posNameList = new ArrayList<String>();
        for(Pos p : posList) {
            posNameList.add(p.getName());
        }
        posSpinner = (Spinner)findViewById(R.id.pos);
        ArrayAdapter<String> posAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, posNameList);
        posAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posSpinner.setAdapter(posAdapter);

        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                deptPos = pos+1;
            }
        });

        posSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                posPos = pos+1;
            }
        });

        deptSpinner.setSelection(2);
        posSpinner.setSelection(0);
    }

    @Override
    public void onResume() {
        getUser();
        super.onResume();
    }

    public void editUser(View view) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("u_email", email.getText().toString());
        data.put("u_name", name.getText().toString());
        data.put("u_phone", phone.getText().toString());
        data.put("u_intro", intro.getText().toString());
        data.put("d_id", String.valueOf(deptPos));
        data.put("p_id", String.valueOf(posPos));

        data.put("token", FirebaseInstanceId.getInstance().getToken());

        String url = "http://"+ Constants.IP+"/fcm/user.php?mode=add";
        Tool.getInstance().sendToServer(data, url);
        Tool.getInstance().toast("저장되었습니다.", this);
    }

    public void getUser() {
        Tool.getInstance().getUser(this);
        if(Constants.user != null) {
            email.setText(Constants.user.getEmail());
            name.setText(Constants.user.getName());
            phone.setText(Constants.user.getPhone());
            intro.setText(Constants.user.getIntro());
            deptSpinner.setSelection(Constants.user.getD_id() - 1);
            posSpinner.setSelection(Constants.user.getP_id() - 1);
        }
    }
}
