package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.VitalAction;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

    public class VitalActionAdapter extends RecyclerView.Adapter<VitalActionHolder> {
        ArrayList<VitalAction> listVitalAction;
        LayoutInflater layoutInflater;
        Context context;
        private final RvReminderInterface rvReminderInterface;

        public VitalActionAdapter(Context context, ArrayList<VitalAction> listVitalAction, RvReminderInterface rvReminderInterface){
            this.listVitalAction = listVitalAction;
            this.layoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.rvReminderInterface = rvReminderInterface;
        }


        @NonNull
        @Override
        public VitalActionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item2,parent, false);
            return new VitalActionHolder(view, rvReminderInterface);
        }

        @Override
        public void onBindViewHolder(@NonNull VitalActionHolder holder, int position) {
            //holder.btnPatient.setText(listVitalAction.get(position).patient.fullName);
            holder.tvReminderDate.setText(listVitalAction.get(position).getDateText());
            holder.tvReminderDrug.setText(listVitalAction.get(position).description);
            holder.sideBar.setBackgroundResource(R.color.terciario_color1);
        }

        @Override
        public int getItemCount() {
            return listVitalAction.size();
        }
    }

