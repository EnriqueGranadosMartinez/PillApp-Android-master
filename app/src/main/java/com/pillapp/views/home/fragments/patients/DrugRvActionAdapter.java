package com.pillapp.views.home.fragments.patients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.DrugAction;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;

public class DrugRvActionAdapter extends RecyclerView.Adapter<DrugActionRvHolder>{
   ArrayList<DrugAction> listDrugAction;
   LayoutInflater layoutInflater;
   Context context;
   private final RvReminderInterface rvReminderInterface;

   public DrugRvActionAdapter(Context context, ArrayList<DrugAction> listDrugAction, RvReminderInterface rvReminderInterface) {
      this.listDrugAction = listDrugAction;
      this.layoutInflater = LayoutInflater.from(context);
      this.context = context;
      this.rvReminderInterface = rvReminderInterface;
   }

   @NonNull
   @Override
   public DrugActionRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = layoutInflater.inflate(R.layout.activity_rv_reminder_item2,parent,false);
      return new DrugActionRvHolder(view, rvReminderInterface);
   }

   @Override
   public void onBindViewHolder(@NonNull DrugActionRvHolder holder, int position) {
      //holder.btnPatient.setText(listDrugAction.get(position).patient.fullName);
      holder.tvReminderDate.setText(listDrugAction.get(position).getDateText());
      holder.tvReminderDrug.setText(listDrugAction.get(position).description);
      holder.sideBar.setBackgroundResource(R.color.secundario_color2);
   }

   @Override
   public int getItemCount() {
      return listDrugAction.size();
   }
}
