package com.pillapp.views.home.fragments.patients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pillapp.R;

public class MenuPatientFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_add_patient_reminder, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");
        return view;
    }

}
