package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.Vital;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

public class VitalAdapter extends RecyclerView.Adapter<VitalHolder> {
    ArrayList<Vital> listVital;
    LayoutInflater layoutInflater;
    Context context;
    private final RvReminderInterface rvReminderInterface;

    public VitalAdapter(Context context, ArrayList<Vital> listVital, RvReminderInterface rvReminderInterface){
        this.listVital = listVital;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.rvReminderInterface = rvReminderInterface;
    }

    @NonNull
    @Override
    public VitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item2,parent,false);
        return new VitalHolder(view, rvReminderInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull VitalHolder holder, int position) {
        //holder.btnPatient.setText(listVital.get(position).patient.fullName);
        holder.tvReminderDate.setText(listVital.get(position).getDateText());
        holder.tvReminderDrug.setText(listVital.get(position).description);
        holder.sideBar.setBackgroundResource(R.color.terciario_color1);
    }



    @Override
    public int getItemCount() {
        return listVital.size();
    }
}