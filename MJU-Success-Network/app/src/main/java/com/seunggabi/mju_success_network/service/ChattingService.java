package com.seunggabi.mju_success_network.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import static com.seunggabi.mju_success_network.service.MyFirebaseMessagingService.CHATTING_ACTTION;
import static com.seunggabi.mju_success_network.service.MyFirebaseMessagingService.CHATTING_SERVICE_ACTTION;

//FCM 왔을때 Chatting방에 대한 정보일 경우 클래스
public class ChattingService extends Service {
    private final IBinder binder;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    //혹시 몰라서 서비스 Bind
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

    //채팅방 리로드 이벤트 발생
    public void refresh() {
        Intent intent = new Intent();
        intent.setAction(CHATTING_ACTTION);
        sendBroadcast(intent);
    }
}
