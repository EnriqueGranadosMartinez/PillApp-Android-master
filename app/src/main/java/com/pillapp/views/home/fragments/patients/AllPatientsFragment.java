package com.pillapp.views.home.fragments.patients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pillapp.R;
import com.pillapp.models.Patient;
import com.pillapp.views.home.HomeActivity;
import com.pillapp.views.home.rvpatient.PatientRvAdapter;

import java.util.ArrayList;


public class AllPatientsFragment extends Fragment {

    FloatingActionButton crearFichasBT;
    RecyclerView recyclerViewPatient;
    ArrayList<Patient> listPatient;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AllPatientsFragment() {

    }

    public static AllPatientsFragment newInstance(String param1, String param2) {
        AllPatientsFragment fragment = new AllPatientsFragment();
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
        View view =inflater.inflate(R.layout.fragment_fichas, container, false);
        findView(view);
        crearFichasBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).goToCreateFicha(view);
                crearFichasBT.setVisibility(View.GONE);
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("A tu cargo");
        listPatient = (ArrayList<Patient>) getArguments().getSerializable("ARRAYPATIENTS");
        recyclerViewPatient.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        PatientRvAdapter patientRvAdapter = ((HomeActivity)getActivity()).patientRvAdapter;
        recyclerViewPatient.setAdapter(patientRvAdapter);
        return view;

    }

    private void findView(View view) {
        crearFichasBT = view.findViewById(R.id.crearFichasBT);
        recyclerViewPatient = view.findViewById(R.id.RVFichas);
    }

}