package com.pillapp.views.home.fragments.patients;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillapp.R;

public class InChargeRvHolder extends RecyclerView.ViewHolder {
    Button btnInCharge;
    TextView tvInCharge;

    public InChargeRvHolder(@NonNull View itemView) {
        super(itemView);
        btnInCharge = itemView.findViewById(R.id.btnPatientInCharge);
        tvInCharge = itemView.findViewById(R.id.tvPatientNameInCharge);
    }
}
