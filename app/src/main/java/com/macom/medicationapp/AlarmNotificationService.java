package com.macom.medicationapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.Nullable;

public class AlarmNotificationService extends IntentService {
    public static final int NOTIFICATION_ID = 0;


    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        sendNotification();

    }


    private void sendNotification() {
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 1,
                new Intent(getApplicationContext(),MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "my_channel_id";
        CharSequence channelName = "My Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.enableLights(true);

        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{1000, 2000});
        notificationManager.createNotificationChannel(notificationChannel);

        int notifyId = 1;

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("its time to have medicine")
                .setContentText("Please check your pillbox!")
                .setAutoCancel(true)
                .setOngoing(true)
                .setSmallIcon(R.drawable.medicine)
                .setChannelId(channelId)
                .setFullScreenIntent(contentIntent,true)
                .build();

        notificationManager.notify(notifyId, notification);


    }


}
