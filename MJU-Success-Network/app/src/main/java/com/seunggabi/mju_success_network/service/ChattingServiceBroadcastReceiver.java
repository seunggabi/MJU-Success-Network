package com.seunggabi.mju_success_network.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by seunggabi on 2016-12-02.
 */


//ChattingService에서 사용하는 BroadcastReceiver
public class ChattingServiceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ChattingService service = (ChattingService) context;
        if (intent.getAction().equals(MyFirebaseMessagingService.CHATTING_SERVICE_ACTTION)) {
            service.refresh();
        }
    }
}
