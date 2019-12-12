package com.macom.medicationapp;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmNotificationService extends IntentService {
    private NotificationManager alarmNotificationManager;
    public static final int NOTIFICATION_ID = 1;
    public AlarmNotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private void sendNotification(String message){

        alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,SecondPage.class),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder alarmNotifiacationBuilder = new NotificationCompat.Builder(this).setContentText("ALARM").setSmallIcon(R.drawable.medicine).setContentText(message).setAutoCancel(true);
alarmNotifiacationBuilder.setContentIntent(pendingIntent);


alarmNotificationManager.notify(NOTIFICATION_ID,alarmNotifiacationBuilder.build());

    }
}
