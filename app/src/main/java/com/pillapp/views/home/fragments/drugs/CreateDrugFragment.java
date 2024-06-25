package com.pillapp.views.home.fragments.drugs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pillapp.R;
import com.pillapp.views.home.HomeActivity;

public class CreateDrugFragment extends Fragment implements View.OnClickListener {

    EditText nameDrugET, doseDrugET, durationDrugET, timeDrugET;
    Button createDrugBT, cancelDrugBT;
    String token;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CreateDrugFragment() {
    }

    public static CreateDrugFragment newInstance(String param1, String param2) {
        CreateDrugFragment fragment = new CreateDrugFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_drug, container, false);
        findView(view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Nueva prescripción de medicamento");
        createDrugBT.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String nameDrug, doseDrug, durationDrug, timeDrug;

        if (nameDrugET.getText().toString().isEmpty() ) {
            nameDrugET.setError("introduzca un medicamento");
        }else if (doseDrugET.getText().toString().isEmpty()) {
            doseDrugET.setError("introduzca dosis");
        }else if (durationDrugET.getText().toString().isEmpty()) {
            durationDrugET.setError("introduzca duración ");
        } else if (timeDrugET.getText().toString().isEmpty()) {
            timeDrugET.setError("introduzca tiempo ");
        }else {
            //TODO Llamar API
            goToBackDetailPatient(view);
        }
    }

    public void token(){
        token = getArguments().getString ("TOKEN");

    }

    public void goToBackDetailPatient (View view){
        ((HomeActivity)getActivity()).backToDetailPatientDrug(view);
    }

    private void findView(View view) {
        nameDrugET = view.findViewById(R.id.nameDrugET);
        doseDrugET = view.findViewById(R.id.doseDrugET);
        durationDrugET = view.findViewById(R.id.durationDrugET);
        timeDrugET = view.findViewById(R.id.timeDrugET);
        createDrugBT = view.findViewById(R.id.createDrugBT);
        cancelDrugBT = view.findViewById(R.id.cancelDrugBT);
    }

}