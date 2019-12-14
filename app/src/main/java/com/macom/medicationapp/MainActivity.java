package com.macom.medicationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.macom.medicationapp.Adapter.MedicineAdapter;
import com.macom.medicationapp.Models.MedicinReminderModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    MedicineAdapter medicineAdapter;
    List<MedicinReminderModel> modelArrayList;
    DatabaseHelper dbHelper;
    @BindView(R.id.addRem)
    Button button;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        modelArrayList = new ArrayList<>();
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