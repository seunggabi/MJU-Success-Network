package com.seunggabi.mju_success_network.view.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seunggabi.mju_success_network.R;

/**
 * Created by USER on 2016-11-25.
 */

//부모 메뉴 뷰, 다른 메뉴 뷰가 상속
public class ParentActivity extends AppCompatActivity {
    protected Button my;
    protected Button group;
    protected Button chatting;
    protected Button notice;
    protected Button setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        this.my = (Button)findViewById(R.id.my);
        this.group = (Button)findViewById(R.id.gr);
        this.chatting = (Button)findViewById(R.id.chat);
        this.notice = (Button)findViewById(R.id.notice);
        this.setting = (Button)findViewById(R.id.set);
    }

    public void goProfile(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goGroup(View view){
        Intent intent = new Intent(this, GroupActivity.class);
        startActivity(intent);
    }

    public void goChatting(View view){
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }

    public void goNotice(View view){
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }

    public void goSetting(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
