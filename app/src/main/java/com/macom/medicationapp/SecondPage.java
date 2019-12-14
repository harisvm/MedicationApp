package com.macom.medicationapp;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import ca.antonious.materialdaypicker.MaterialDayPicker;


public class SecondPage extends AppCompatActivity {
	private static final int ALARM_REQUEST_CODE = 133;
	EditText editDescrption, editTitle;
	MaterialDayPicker materialDayPicker;
	Button button;
	TimePicker timePicker;
	PendingIntent pendingIntent;
	DatePicker datePicker;
	AlarmNotificationService alarmNotificationService;


	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_page);

		DatabaseHelper databaseHelper = new DatabaseHelper(this);

		alarmNotificationService = new AlarmNotificationService();
		button = findViewById(R.id.save);
		datePicker = new DatePicker(this);

		editTitle = findViewById(R.id.editTitle);
		editDescrption = findViewById(R.id.des);
		materialDayPicker = findViewById(R.id.day_picker);
		timePicker = findViewById(R.id.timePicker);


		button.setOnClickListener(v -> {
					if (!materialDayPicker.getSelectedDays().isEmpty()) {
						Intent alarmIntent = new Intent(getApplicationContext(), AlarmReciever.class);

						pendingIntent = PendingIntent.getBroadcast(SecondPage.this, ALARM_REQUEST_CODE, alarmIntent, 0);
						int timeHour = timePicker.getHour();
						int timeMinute = timePicker.getMinute();


						String time;
						triggerAlarm();


						if (timeMinute < 10) {
							time = timeHour + " : 0" + timeMinute;

						} else {
							time = timeHour + " : " + timeMinute;

						}

						String title = editTitle.getText().toString();
						int days = materialDayPicker.getSelectedDays().size();
						StringBuffer stringBuffer = new StringBuffer();

						for (int i = 0; i < days - 1; i++) {

							stringBuffer.append(materialDayPicker.getSelectedDays().get(i) + ", ");
						}
						stringBuffer.append(materialDayPicker.getSelectedDays().get(days - 1));

						databaseHelper.addReminder(new MedicinReminderModel(title, stringBuffer.toString(), time));
						Intent i = new Intent(SecondPage.this, MainActivity.class);
						startActivity(i);


					} else {

						Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_LONG).show();
					}
				}


		);


	}


	public void triggerAlarm() {

		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
		calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute() - 10);

		manager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
	}



    }


//    public void stopAlarmManager() {
//
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        manager.cancel(pendingIntent);
//        stopService(new Intent(SecondPage.this, AlarmSoundService.class));
//        NotificationManager notificationManager = (NotificationManager) this
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(AlarmNotificationService.NOTIFICATION_ID);
//        Toast.makeText(this, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show();
//    }


