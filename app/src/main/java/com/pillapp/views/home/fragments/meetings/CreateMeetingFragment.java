package com.pillapp.views.home.fragments.meetings;

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

public class CreateMeetingFragment extends Fragment implements View.OnClickListener{

    EditText nombreCitaET, fechaET, horaET;
    Button createMeetingBT, cancelMeetingBT;
    String token;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CreateMeetingFragment() {

    }

    public static CreateMeetingFragment newInstance(String param1, String param2) {
        CreateMeetingFragment fragment = new CreateMeetingFragment();
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
        View view =inflater.inflate(R.layout.fragment_create_meeting, container, false);
        findView(view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Nueva cita");
        createMeetingBT.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (nombreCitaET.getText().toString().isEmpty() ) {
            nombreCitaET.setError("introduzca una cita");
        }else if (fechaET.getText().toString().isEmpty()) {
            fechaET.setError("introduzca fecha");
        }else if (horaET.getText().toString().isEmpty()) {
            horaET.setError("introduzca hora");
        } else {
            //TODO Llamar API
            goToBackDetailPatient(view);
        }
    }


    public void token(){
        token = getArguments().getString ("TOKEN");
    }

    public void goToBackDetailPatient (View view){
        ((HomeActivity)getActivity()).backToDetailPatientMeeting(view);
    }

    private void findView(View view) {
        nombreCitaET = view.findViewById(R.id.nombreCitaET);
        fechaET = view.findViewById(R.id.fechaET);
        horaET = view.findViewById(R.id.horaET);
        createMeetingBT = view.findViewById(R.id.createMeetingBT);
        cancelMeetingBT = view.findViewById(R.id.cancelMeetingBT);
    }

}