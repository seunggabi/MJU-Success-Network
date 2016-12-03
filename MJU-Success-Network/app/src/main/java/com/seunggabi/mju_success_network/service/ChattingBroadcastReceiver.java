package com.seunggabi.mju_success_network.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.seunggabi.mju_success_network.view.chatting.ChattingActivity;

/**
 * Created by seunggabi on 2016-12-02.
 */

//채팅방 리로드 BroadcastReceiver
public class ChattingBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ChattingActivity act = (ChattingActivity) context;
        if (intent.getAction().equals(MyFirebaseMessagingService.CHATTING_ACTTION)) {
            act.reload();
        }
    }
}
