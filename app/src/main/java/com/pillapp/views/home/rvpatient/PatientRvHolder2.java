package com.pillapp.views.home.rvpatient;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;
import com.pillapp.views.home.recyclerinterfaces.RvPatientInterface;

public class PatientRvHolder2 extends RecyclerView.ViewHolder {

    public Button btnFPatient;
    public TextView tvFPatientName;
    private final RvPatientInterface rvPatientInterface;

    public PatientRvHolder2(@NonNull View itemView, RvPatientInterface rvPatientInterface) {
        super(itemView);
        btnFPatient = itemView.findViewById(R.id.btnFPatient);
        tvFPatientName = itemView.findViewById(R.id.tvFPatientName);
        this.rvPatientInterface = rvPatientInterface;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAbsoluteAdapterPosition();
                PatientRvHolder2.this.rvPatientInterface.onItemClickPatient(pos);
            }
        });
    }
}
