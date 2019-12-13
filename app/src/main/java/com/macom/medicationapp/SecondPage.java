package com.macom.medicationapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import ca.antonious.materialdaypicker.MaterialDayPicker;


public class SecondPage extends AppCompatActivity {
    TextView dateView;
    EditText editDescrption, editTitle;
    MaterialDayPicker materialDayPicker;
    Button button;
TimePicker timePicker;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        button = findViewById(R.id.save);


        editTitle = findViewById(R.id.editTitle);
        editDescrption = findViewById(R.id.des);
materialDayPicker = findViewById(R.id.day_picker);
timePicker = findViewById(R.id.timePicker);



        button.setOnClickListener(v -> {
            if(materialDayPicker.getSelectedDays()!=null){
int timeHour = timePicker.getHour();
            int timeMinute = timePicker.getMinute();
            String time;
if(timeMinute<10){
    time = timeHour+ " : 0"+timeMinute;

}
   else{
    time = timeHour+" : "+timeMinute;

}

            String title = editTitle.getText().toString();
           int  days = materialDayPicker.getSelectedDays().size();
            StringBuffer stringBuffer = new StringBuffer();

           for(int i =0;i<days-1;i++){

               stringBuffer.append(materialDayPicker.getSelectedDays().get(i)+", ");
           }
            stringBuffer.append(materialDayPicker.getSelectedDays().get(days-1));

                scheduleNotification(getNotification("test"),3000);


                databaseHelper.addReminder(new MedicinReminderModel(title, stringBuffer.toString(),time));
            Intent i = new Intent(SecondPage.this, MainActivity.class);
            startActivity(i);

        }
        else {


            }


        }

        );
        }


        private void scheduleNotification(Notification notification, int timeDelay){
Intent notificationIntent = new Intent(getApplicationContext(),NotificationPublisher.class);

notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID,1);
notificationIntent.putExtra(NotificationPublisher.NOTIFICATION,notification);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
long futureTimeInMillis = timeDelay;
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP,futureTimeInMillis,pendingIntent);

        }

        private Notification getNotification(String title){

NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
builder.setContentTitle("Test Alarm");
builder.setContentText("Test-01");
builder.setSmallIcon(R.drawable.circle_toggle_button);
return builder.build();

        }

    }
