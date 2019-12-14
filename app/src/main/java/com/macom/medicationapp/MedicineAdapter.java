package com.macom.medicationapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewholder> {

    List<MedicinReminderModel> medicineList;
    Context context;

    public MedicineAdapter(List<MedicinReminderModel> medicineList, Context context) {
        this.medicineList = medicineList;
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicine_card, parent, false);

        return new MedicineViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewholder holder, int position) {


        MedicinReminderModel medicine = medicineList.get(position);

        holder.button.setOnClickListener(v -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.deleteContact(medicine);
            medicineList.remove(position);
            notifyDataSetChanged();


        });

        holder.title.setText(medicine.getTitle());
        holder.description.setText(medicine.getDescription());
        holder.imageView.setBackgroundResource(R.drawable.medicine);
        holder.time.setText(medicine.getTime());
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


    public class MedicineViewholder extends RecyclerView.ViewHolder {
        TextView title, description, time;
        ImageView imageView;
        CardView cardView;
        Button button;

        public MedicineViewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.title1);
            description = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time1);
            imageView = itemView.findViewById(R.id.imgView);
            button = itemView.findViewById(R.id.delete);

        }
    }
}
