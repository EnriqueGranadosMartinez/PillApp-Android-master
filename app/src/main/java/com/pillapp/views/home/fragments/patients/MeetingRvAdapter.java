package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.Meeting;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

public class MeetingRvAdapter extends RecyclerView.Adapter<MeetingRvHolder> {
ArrayList<Meeting> listMeeting;
LayoutInflater layoutInflater;
Context context;
private final RvReminderInterface rvReminderInterface;

public MeetingRvAdapter(Context context, ArrayList<Meeting> listMeeting, RvReminderInterface rvReminderInterface){
    this.listMeeting = listMeeting;
    layoutInflater = LayoutInflater.from(context);
    this.context = context;
    this.rvReminderInterface = rvReminderInterface;
}
    @NonNull
    @Override
    public MeetingRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item2, parent, false);

        return new MeetingRvHolder(view, rvReminderInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingRvHolder holder, int position) {
        //holder.btnPatient.setText(listMeeting.get(position).patient.fullName);
        holder.tvReminderDate.setText(listMeeting.get(position).getDateText());
        holder.tvReminderDrug.setText(listMeeting.get(position).description);
        holder.sideBar.setBackgroundResource(R.color.terciario_color4);
    }

    @Override
    public int getItemCount() {
        return listMeeting.size();
    }
}
