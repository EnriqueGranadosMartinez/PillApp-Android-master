package com.pillapp.views.home.fragments.patients;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pillapp.R;
import com.pillapp.models.DrugAction;
import com.pillapp.models.Drugs;
import com.pillapp.models.InCharge;
import com.pillapp.models.Meeting;
import com.pillapp.models.Patient;
import com.pillapp.models.Reminder;
import com.pillapp.models.Vital;
import com.pillapp.models.VitalAction;
import com.pillapp.views.home.HomeActivity;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;

import java.util.ArrayList;


public class DetailPatientFragment extends Fragment implements RvReminderInterface {

    RecyclerView rvMeeting;
    RecyclerView rvDrug;
    RecyclerView rvDrugAction;
    RecyclerView rvVital;
    RecyclerView rvVitalAction;
    RecyclerView rvInCharge;
    ArrayList<Meeting> listMeeting;
    ArrayList<Drugs> listDrug;
    ArrayList<DrugAction> listDrugAction;
    ArrayList<Vital> listVital;
    ArrayList<VitalAction> listVitalAction;
    ArrayList<InCharge> listInCharge;
    Button button;
    FloatingActionButton floatingActionButton;
    TextView nameText, weightText, notesText;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_patient, container, false);
        button = view.findViewById(R.id.button5);
        nameText = view.findViewById(R.id.textView7);
        weightText = view.findViewById(R.id.textView9);
        notesText = view.findViewById(R.id.textView10);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);


        //TODO cambiar a paciente onClick
        assert getArguments() != null;
        Patient patient = (Patient) getArguments().getSerializable("Patient");
        ArrayList<Reminder> listReminder = (ArrayList<Reminder>) getArguments().getSerializable("Reminder");
        String firstLetter = String.valueOf(patient.fullName.charAt(0));
        String weight = String.valueOf(patient.kg);
        button.setText(firstLetter);
        nameText.setText(patient.fullName);
        weightText.setText(weight + " kg");
        notesText.setText(patient.notes);

        listMeeting = new ArrayList<>();
        listDrug = new ArrayList<>();
        listDrugAction = new ArrayList<>();
        listVital = new ArrayList<>();
        listVitalAction = new ArrayList<>();
        listInCharge = new ArrayList<>();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).goToAddReminder();
                floatingActionButton.setVisibility(View.GONE);
            }
        });

        //TODO buscar lógica para sacar solo los meetings y solo del paciente en el que estamos
        for (int i = 0; i <= listReminder.size() - 1; i++) {
            if (listReminder.get(i).getType().equals("meeting") && (((Meeting) listReminder.get(i)).patient.id == patient.id)) {
                listMeeting.add((Meeting) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("drug") && (((Drugs) listReminder.get(i)).patient.id == patient.id)) {
                listDrug.add((Drugs) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("drugAction") && (((DrugAction) listReminder.get(i)).patient.id == patient.id)) {
                listDrugAction.add((DrugAction) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("vitalAction") && (((VitalAction) listReminder.get(i)).patient.id == patient.id)) {
                listVitalAction.add((VitalAction) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("vital") && (((Vital) listReminder.get(i)).patient.id == patient.id)) {
                listVital.add((Vital) listReminder.get(i));
            }
            if (listReminder.get(i).getType().equals("inCharge") && (((InCharge) listReminder.get(i)).patient.id == patient.id)) {
                listInCharge.add((InCharge) listReminder.get(i));
            }
        }

        rvMeeting = (RecyclerView) view.findViewById(R.id.RvMeetings);
        rvMeeting.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        MeetingRvAdapter reminderRvAdapter = new MeetingRvAdapter(getActivity(), listMeeting, this);
        rvMeeting.setAdapter(reminderRvAdapter);
        reminderRvAdapter.notifyDataSetChanged();

        rvDrug = (RecyclerView) view.findViewById(R.id.RvDrugs);
        rvDrug.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        DrugRvAdapter drugRvAdapter = new DrugRvAdapter(getActivity(), listDrug, this);
        rvDrug.setAdapter(drugRvAdapter);
        drugRvAdapter.notifyDataSetChanged();

        rvDrugAction = (RecyclerView) view.findViewById(R.id.RvDrugAction);
        rvDrugAction.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        DrugRvActionAdapter drugRvActionAdapter = new DrugRvActionAdapter(getActivity(), listDrugAction, this);
        rvDrugAction.setAdapter(drugRvActionAdapter);
        drugRvActionAdapter.notifyDataSetChanged();

        rvVital = (RecyclerView) view.findViewById(R.id.RvVital);
        rvVital.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        VitalAdapter vitalAdapter = new VitalAdapter(getActivity(), listVital, this);
        rvVital.setAdapter(vitalAdapter);
        reminderRvAdapter.notifyDataSetChanged();

        rvVitalAction = (RecyclerView) view.findViewById(R.id.RvVitalAction);
        rvVitalAction.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        VitalActionAdapter vitalActionAdapter = new VitalActionAdapter(getActivity(), listVitalAction, this);
        rvVitalAction.setAdapter(vitalActionAdapter);
        reminderRvAdapter.notifyDataSetChanged();

        rvInCharge = (RecyclerView) view.findViewById(R.id.RvInCharge);
        rvInCharge.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        InChargeRvAdapter inChargeRvAdapter = new InChargeRvAdapter(getActivity(), listInCharge);
        rvInCharge.setAdapter(inChargeRvAdapter);
        inChargeRvAdapter.notifyDataSetChanged();
        reminderRvAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onItemClickReminder(int position) {

    }
}