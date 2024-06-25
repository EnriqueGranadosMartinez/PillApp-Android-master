package com.pillapp.views.home.rvpatient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pillapp.R;
import com.pillapp.models.Patient;
import com.pillapp.views.home.recyclerinterfaces.RvPatientInterface;

import java.util.ArrayList;

public class PatientRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Patient> patientData;
    LayoutInflater layoutInflater;
    Context context;
    private final RvPatientInterface rvPatientInterface;
    public boolean holderPatient = true;

    public PatientRvAdapter(Context context, ArrayList<Patient> patientData, RvPatientInterface rvPatientInterface) {
        this.patientData = patientData;
        layoutInflater = layoutInflater.from(context);
        this.context = context;
        this.rvPatientInterface = rvPatientInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (holderPatient ==  true){
            View view = layoutInflater.inflate(R.layout.activity_rv_patient_item, parent, false);
            return new PatientRvHolder(view, rvPatientInterface);
        }else{
            View view = layoutInflater.inflate(R.layout.fragment_rv_patient_item, parent, false);
            return new PatientRvHolder2(view, rvPatientInterface);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String firstLetter = String.valueOf(patientData.get(position).fullName.charAt(0));

        if (holderPatient == true) {
            PatientRvHolder patientRvHolder = (PatientRvHolder) holder;
            patientRvHolder.tvPatientName.setText(patientData.get(position).fullName);
            patientRvHolder.btnPatient.setText(firstLetter);
        }else {
            PatientRvHolder2 patientRvHolder2 = (PatientRvHolder2) holder;
            patientRvHolder2.tvFPatientName.setText(patientData.get(position).fullName);
            patientRvHolder2.btnFPatient.setText(firstLetter);

        }
    }

    @Override
    public int getItemCount() {
        return patientData.size();
    }
}
