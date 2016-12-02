package com.seunggabi.mju_success_network.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import static com.seunggabi.mju_success_network.service.MyFirebaseMessagingService.CHATTING_ACTTION;
import static com.seunggabi.mju_success_network.service.MyFirebaseMessagingService.CHATTING_SERVICE_ACTTION;

public class ChattingService extends Service {
    private final IBinder binder;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    public class ChattingBinder extends Binder {
        public ChattingService getService() {
            return  ChattingService.this;
        }
    }

    public ChattingService() {
        binder = new ChattingBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadcastReceiver = new ChattingServiceBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(CHATTING_SERVICE_ACTTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void refresh() {
        Intent intent = new Intent();
        intent.setAction(CHATTING_ACTTION);
        sendBroadcast(intent);
    }
}
