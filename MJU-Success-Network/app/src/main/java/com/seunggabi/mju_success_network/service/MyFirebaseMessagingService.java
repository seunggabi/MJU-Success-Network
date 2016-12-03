package com.seunggabi.mju_success_network.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.seunggabi.mju_success_network.R;
import com.seunggabi.mju_success_network.view.chatting.ChattingActivity;
import com.seunggabi.mju_success_network.view.group.GroupData;
import com.seunggabi.mju_success_network.view.menu.MainActivity;
import com.seunggabi.mju_success_network.view.menu.NoticeActivity;

/**
 * Created by seunggabi on 2016-11-06.
 */

//FCM 서비스 클래스
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    public static final String CHATTING_SERVICE_ACTTION = "com.seunggabi.mju_success_network.service.chatting";
    public static final String CHATTING_ACTTION = "com.seunggabi.mju_success_network.chatting";
    private int g_id;
    private String name;
    private String type;
    private String g_name;
    private String u_alarm;
    private String j_alarm;
    private String message;

    private Uri defaultSoundUri;
    private Ringtone ring;

    @Override
    public void onCreate() {
        super.onCreate();

        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ring = RingtoneManager.getRingtone(this, defaultSoundUri);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        name = remoteMessage.getData().get("name");
        type = remoteMessage.getData().get("type");
        message = remoteMessage.getData().get("message");
        u_alarm = remoteMessage.getData().get("u_alarm");
        j_alarm = remoteMessage.getData().get("j_alarm");

        if(type.equals("chatting")) {
            g_id = Integer.parseInt(remoteMessage.getData().get("g_id"));
            g_name = remoteMessage.getData().get("g_name");
        }
        if(u_alarm.equals("Y") && j_alarm.equals("Y")) {
            sendNotification();
            ring.play();
        }

        refreshChatting();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        if(type.equals("notice")) {
            intent = new Intent(this, NoticeActivity.class);
        }
        else if(type.equals("chatting")) {
            intent = new Intent(this, ChattingActivity.class);
            GroupData groupListData = new GroupData();
            groupListData.setG_id(g_id);
            groupListData.setG_name(g_name);
            intent.putExtra("GroupData", groupListData);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(name)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public void refreshChatting() {
        Intent intent = new Intent();
        intent.setAction(CHATTING_SERVICE_ACTTION);
        sendBroadcast(intent);
    }
}