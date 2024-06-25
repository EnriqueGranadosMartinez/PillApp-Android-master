package com.pillapp.views.home.rvpatient;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.views.home.recyclerinterfaces.RvPatientInterface;

public class PatientRvHolder extends RecyclerView.ViewHolder{
    Button btnPatient;
    TextView tvPatientName;
    private final RvPatientInterface rvPatientInterface;
    public PatientRvHolder(@NonNull View itemView, RvPatientInterface rvPatientInterface) {
        super(itemView);
        btnPatient = itemView.findViewById(R.id.btnPatient);
        tvPatientName = itemView.findViewById(R.id.tvPatientNameInCharge);
        this.rvPatientInterface = rvPatientInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAbsoluteAdapterPosition();
               PatientRvHolder.this.rvPatientInterface.onItemClickPatient(pos);
            }
        });
    }
}
