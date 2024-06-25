package com.pillapp.views.home.rvreminder;
//TODO cambiar el nombre a la clase
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

public class ReminderRvHolder extends RecyclerView.ViewHolder {
    TextView tvReminderDate, tvReminderDrug;
    Button btnPatient;
    View sideBar;
   private final RvReminderInterface rvReminderInterface;
    public ReminderRvHolder(@NonNull View itemView, RvReminderInterface rvReminderInterface) {
        super(itemView);
        tvReminderDate = itemView.findViewById(R.id.tvReminderDate);
        tvReminderDrug = itemView.findViewById(R.id.tvReminderDrug);
        btnPatient = itemView.findViewById(R.id.btnPatient);
        sideBar = itemView.findViewById(R.id.sideBar);
        this.rvReminderInterface = rvReminderInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAbsoluteAdapterPosition();

                ReminderRvHolder.this.rvReminderInterface.onItemClickReminder(pos);
            }
        });
    }
}
