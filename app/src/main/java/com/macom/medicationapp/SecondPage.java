package com.macom.medicationapp;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.macom.medicationapp.Models.MedicinReminderModel;
import com.macom.medicationapp.ServicesAndRecievers.AlarmNotificationService;
import com.macom.medicationapp.ServicesAndRecievers.AlarmReciever;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import ca.antonious.materialdaypicker.MaterialDayPicker;

public class SecondPage extends AppCompatActivity {
    @BindView(R.id.editTitle)
    EditText editTitle;
    @BindView(R.id.save)
    Button button;
    @BindView(R.id.day_picker)
    MaterialDayPicker materialDayPicker;
    @BindView(R.id.timePicker)
    TimePicker timePicker;
    private static final int ALARM_REQUEST_CODE = 133;
    PendingIntent pendingIntent;
    AlarmNotificationService alarmNotificationService;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        alarmNotificationService = new AlarmNotificationService();
        ButterKnife.bind(this);


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
        calendar.set( Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute() - 10);
        manager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }
}



