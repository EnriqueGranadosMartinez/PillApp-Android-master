package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.InCharge;

import java.util.ArrayList;

public class InChargeRvAdapter extends RecyclerView.Adapter<InChargeRvHolder>{
    ArrayList<InCharge> listInCharge;
    LayoutInflater layoutInflater;
    Context context;


    public InChargeRvAdapter(Context context, ArrayList<InCharge> listInCharge){
        this.listInCharge = listInCharge;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @NonNull
    @Override
    public InChargeRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_rv_patient_item_in_charge, parent,false);
        return new InChargeRvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InChargeRvHolder holder, int position) {
        String firstLetter = String.valueOf(listInCharge.get(position).fullName.charAt(0));
        holder.btnInCharge.setText(firstLetter);
        holder.tvInCharge.setText(listInCharge.get(position).fullName);

    }

    @Override
    public int getItemCount() {
        return listInCharge.size();
    }
}
