package com.macom.medicationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;


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
int timeHour = timePicker.getHour();
            int timeMinute = timePicker.getMinute();
            String time;
if(timeMinute<10){
    time = timeHour+ " : 0"+timeMinute;

}
   else{
    time = timeHour+""+timeMinute;

}

            String title = editTitle.getText().toString();
            String des = editDescrption.getText().toString();
            databaseHelper.addReminder(new MedicinReminderModel(title, des,time));
            Intent i = new Intent(SecondPage.this, MainActivity.class);
            startActivity(i);

        });


    }
}
