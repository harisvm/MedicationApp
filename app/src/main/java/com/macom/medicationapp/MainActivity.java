package com.macom.medicationapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;
    ArrayList<ItemListModel> modelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        modelArrayList = new ArrayList<>();


        modelArrayList.add(new ItemListModel("Paracitamol", "51-02-2019"));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        medicineAdapter = new MedicineAdapter(modelArrayList,MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(medicineAdapter);


    }
}
