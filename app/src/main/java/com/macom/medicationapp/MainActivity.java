package com.macom.medicationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;
    List<MedicinReminderModel> modelArrayList;
    DatabaseHelper dbHelper;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        modelArrayList = new ArrayList<>();
        button = findViewById(R.id.addRem);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, SecondPage.class);
            startActivity(intent);

        });

        getDatabaseValues();
    }


    public void getDatabaseValues() {


        dbHelper = new DatabaseHelper(this);

        modelArrayList = new ArrayList<>();
        modelArrayList = dbHelper.getAllReminders();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        medicineAdapter = new MedicineAdapter(modelArrayList, MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(medicineAdapter);
        medicineAdapter.notifyDataSetChanged();


    }
}
