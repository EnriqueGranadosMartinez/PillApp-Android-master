package com.pillapp.views.home.rvreminder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

public class ReminderRvHolder2 extends RecyclerView.ViewHolder {
    TextView tvReminderDate, tvReminderDrug;
    Button btnPatient;
    private final RvReminderInterface rvReminderInterface;

    public ReminderRvHolder2(@NonNull View itemView, RvReminderInterface rvReminderInterface) {
        super(itemView);
        tvReminderDate = itemView.findViewById(R.id.tvReminderDate);
        tvReminderDrug = itemView.findViewById(R.id.tvReminderDrug);
        btnPatient = itemView.findViewById(R.id.btnPatient);
        this.rvReminderInterface = rvReminderInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAbsoluteAdapterPosition();

                ReminderRvHolder2.this.rvReminderInterface.onItemClickReminder(pos);
            }
        });
    }
}
