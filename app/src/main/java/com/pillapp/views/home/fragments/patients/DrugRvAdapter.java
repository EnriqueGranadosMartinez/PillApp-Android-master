package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.Drugs;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

public class DrugRvAdapter extends RecyclerView.Adapter<MeetingRvHolder> {

    ArrayList<Drugs> listDrug;
    LayoutInflater layoutInflater;
    Context context;
    private final RvReminderInterface rvReminderInterface;

    public DrugRvAdapter(Context context, ArrayList<Drugs> listDrug, RvReminderInterface rvReminderInterface) {
        this.listDrug = listDrug;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.rvReminderInterface = rvReminderInterface;
    }

    @NonNull
    @Override
    public MeetingRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item2,parent,false);

        return new MeetingRvHolder(view, rvReminderInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingRvHolder holder, int position) {
        //holder.btnPatient.setText(listDrug.get(position).patient.fullName);
        holder.tvReminderDate.setText(listDrug.get(position).getDateText());
        holder.tvReminderDrug.setText(listDrug.get(position).description);
        holder.sideBar.setBackgroundResource(R.color.terciario_color2);
    }

    @Override
    public int getItemCount() {
        return listDrug.size();
    }
}
