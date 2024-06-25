package com.pillapp.views.home.rvreminder;
//TODO cambiar nombre a la clase

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.Drugs;
import com.pillapp.models.Meeting;
import com.pillapp.models.Reminder;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

public class ReminderRvAdapter<Private> extends RecyclerView.Adapter<ReminderRvHolder> {
    ArrayList<Reminder> listReminder;
    ArrayList<Reminder> listReminderHome;
    LayoutInflater layoutInflater;
    Context context;
    private final RvReminderInterface rvReminderInterface;


    public ReminderRvAdapter(Context context, ArrayList<Reminder> listReminder, RvReminderInterface rvReminderInterface) {
        this.listReminder = listReminder;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.rvReminderInterface = rvReminderInterface;
    }

    @NonNull
    @Override
    public ReminderRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item, parent, false);
        return new ReminderRvHolder(view, rvReminderInterface);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ReminderRvHolder holder, int position) {


        Reminder reminder = listReminder.get(position);
        if (reminder.getType().equals("drug")) {
            holder.tvReminderDate.setText(reminder.getDateText());
            holder.tvReminderDrug.setText(reminder.getTitleText());
            holder.btnPatient.setText(reminder.getPatientText());
            holder.sideBar.setBackgroundResource(R.color.terciario_color2);
        }
        if (reminder.getType().equals("meeting")) {
            holder.tvReminderDate.setText(reminder.getDateText());
            holder.tvReminderDrug.setText(reminder.getTitleText());
            holder.btnPatient.setText(reminder.getPatientText());
            holder.sideBar.setBackgroundResource(R.color.terciario_color4);
        }
        if (reminder.getType().equals("drugAction")) {
            holder.tvReminderDate.setText(reminder.getDateText());
            holder.tvReminderDrug.setText(reminder.getTitleText());
            holder.btnPatient.setText(reminder.getPatientText());
            holder.sideBar.setBackgroundResource(R.color.secundario_color2);
        }
        if (reminder.getType().equals("vitalAction")) {
            holder.tvReminderDate.setText(reminder.getDateText());
            holder.tvReminderDrug.setText(reminder.getTitleText());
            holder.btnPatient.setText(reminder.getPatientText());
            holder.sideBar.setBackgroundResource(R.color.terciario_color1);
        }
        if (reminder.getType().equals("vital")) {
            holder.tvReminderDate.setText(reminder.getDateText());
            holder.tvReminderDrug.setText(reminder.getTitleText());
            holder.btnPatient.setText(reminder.getPatientText());
            holder.sideBar.setBackgroundResource(R.color.terciario_color1);
        }
    }

    @Override
    public int getItemCount() {
        listReminderHome = new ArrayList<>();
        for (int i = 0; i <= listReminder.size() - 1; i++) {
            if (listReminder.get(i).getType().equals("meeting")) {
                listReminderHome.add((Meeting) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("drug")) {
                listReminderHome.add((Drugs) listReminder.get(i));
            }
        }
        return listReminderHome.size();
    }
}
