package com.macom.medicationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewholder> {

    List<ItemListModel> medicineList;
    Context context;

    public MedicineAdapter(List<ItemListModel> medicineList, Context context) {
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


        ItemListModel movie = medicineList.get(position);
        holder.cardView.setOnClickListener(v -> {

            String title = movie.getTitle();
            String des = movie.getDescription();
            Intent intent = new Intent(context, SecondPage.class);
            Bundle mBundle = new Bundle();

            mBundle.putString("title", title);
            mBundle.putString("des", des);

            intent.putExtras(mBundle);
            v.getContext().startActivity(intent);
        });
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getDescription());
        holder.imageView.setBackgroundResource(R.drawable.medicine);

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }


    public class MedicineViewholder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imageView;
        CardView cardView;

        public MedicineViewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.title1);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
