package com.macom.medicationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.macom.medicationapp.Adapter.MedicineAdapter;
import com.macom.medicationapp.Models.MedicinReminderModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;
    List<MedicinReminderModel> modelArrayList;
    DatabaseHelper dbHelper;
    BottomNavigationView bottomNavigationView;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        modelArrayList = new ArrayList<>();
button = findViewById(R.id.a)
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.inflateMenu(R.menu.menu);
b
        bottomNavigationView.setOnClickListener(v -> {
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

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.bottomNavigationAlarmMenuId:
//
//                Intent intent = new Intent(MainActivity.this, SecondPage.class);
//                startActivity(intent);
//
//        }
//        return super.onOptionsItemSelected(item);
//git status

//    }
}