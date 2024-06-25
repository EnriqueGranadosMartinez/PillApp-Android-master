package com.pillapp.views.home.fragments.patients;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.models.Vital;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

public class VitalHolder extends RecyclerView.ViewHolder {
    View sideBar;
    TextView tvReminderDate, tvReminderDrug;
    Button btnPatient;
    private final RvReminderInterface rvReminderInterface;

    public VitalHolder(@NonNull View itemView, RvReminderInterface rvReminderInterface) {
        super(itemView);
        tvReminderDate = itemView.findViewById(R.id.tvReminderDate);
        tvReminderDrug = itemView.findViewById(R.id.tvReminderDrug);
        btnPatient = itemView.findViewById(R.id.btnPatient);
        sideBar = itemView.findViewById(R.id.sideBar2);

        this.rvReminderInterface = rvReminderInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAbsoluteAdapterPosition();

                VitalHolder.this.rvReminderInterface.onItemClickReminder(pos);
            }
        });
    }
}