package com.macom.medicationapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM!! ALARM!!", Toast.LENGTH_SHORT).show();

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmNotificationService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));


        context.startService(new Intent(context, AlarmSoundService.class));

    }


}




