package com.pillapp.views.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pillapp.R;
import com.pillapp.dto.DataDto;
import com.pillapp.dto.LoginOkDto;
import com.pillapp.dto.RespuestaGetDto;
import com.pillapp.dto.UpdateDto;
import com.pillapp.models.DrugAction;
import com.pillapp.models.Drugs;
import com.pillapp.models.InCharge;
import com.pillapp.models.Meeting;
import com.pillapp.models.Patient;
import com.pillapp.models.Reminder;
import com.pillapp.models.Vital;
import com.pillapp.models.VitalAction;
import com.pillapp.services.RetrofitMethods;
import com.pillapp.views.home.fragments.patients.CreatePatientFragment;
import com.pillapp.views.home.fragments.patients.AllPatientsFragment;
import com.pillapp.views.home.fragments.patients.DetailPatientFragment;
import com.pillapp.views.home.fragments.drugs.CreateDrugFragment;
import com.pillapp.views.home.fragments.meetings.CreateMeetingFragment;
import com.pillapp.views.home.fragments.patients.MenuPatientFragment;
import com.pillapp.views.home.recyclerinterfaces.RvPatientInterface;
import com.pillapp.views.home.recyclerinterfaces.RvReminderInterface;
import com.pillapp.views.home.rvpatient.PatientRvAdapter;
import com.pillapp.views.home.rvreminder.ReminderRvAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends AppCompatActivity implements RvReminderInterface, RvPatientInterface {

    RecyclerView recyclerViewReminder;
    RecyclerView recyclerViewPatient;
    ArrayList<Meeting> listMeeting;
    ArrayList<Patient> listPatient;
    ArrayList<Reminder> listReminder;
    ArrayList<Drugs> listDrugs;
    ArrayList<DrugAction> listDrugAction;
    ArrayList<VitalAction> listVitalAction;
    ArrayList<InCharge> listInCharge;
    private LoginOkDto login;
    TextView userNameTV;
    Button informesBT;
    AllPatientsFragment allPatientsFragment;
    CreatePatientFragment createPatientFragment;
    CreateMeetingFragment createMeetingFragment;
    CreateDrugFragment createDrugFragment;
    public PatientRvAdapter patientRvAdapter;
    String namePatientSelect;
    FloatingActionButton floatingActionButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("");

        findView();

        login = (LoginOkDto) getIntent().getSerializableExtra("LOGIN");

        userNameTV.setText("Hola " + login.profile.userName + " !");

        listReminder = new ArrayList<>();

        listDrugs = new ArrayList<>();
        listDrugAction = new ArrayList<>();
        listVitalAction = new ArrayList<>();
        listInCharge = new ArrayList<>();

        recyclerViewReminder = findViewById(R.id.Rvreminder);
        listMeeting = new ArrayList<>();
        recyclerViewReminder.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ReminderRvAdapter reminderRvAdapter = new ReminderRvAdapter(getApplicationContext(), listReminder, this);
        recyclerViewReminder.setAdapter(reminderRvAdapter);

        listPatient = new ArrayList<>();
        recyclerViewPatient = findViewById(R.id.RvPatient);
        recyclerViewPatient.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        patientRvAdapter = new PatientRvAdapter(getApplicationContext(), listPatient, this);
        recyclerViewPatient.setAdapter(patientRvAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitMethods retrofitMethods = retrofit.create(RetrofitMethods.class);

        //TODO encapsular esta lógica
        retrofitMethods.GetData(login.token);
        Call<RespuestaGetDto> call = retrofitMethods.GetData("Bearer " + login.token);

        call.enqueue(new Callback<RespuestaGetDto>() {
            @Override
            public void onResponse(Call<RespuestaGetDto> call, Response<RespuestaGetDto> response) {
                for (int i = 0; i <= response.body().updates.size() - 1; i++) {
                    UpdateDto update = response.body().updates.get(i);
                    DataDto data = update.data;
                    if (update.type.equals("patient")) {
                        listPatient.add(new Patient(
                                data.id,
                                data.fullName,
                                data.notes,
                                data.kg));
                    }
                    if (update.type.equals("meeting")) {
                        for (Patient patient : listPatient) {
                            if (patient.id == data.patientId) {
                                listReminder.add(new Meeting(
                                        patient,
                                        data.description,
                                        data.appointmentTimestamp,
                                        data.reminderTimestamp
                                ));
                            }
                        }
                    }
                    if (update.type.equals("drug")) {
                        for (Patient patient : listPatient) {
                            if (patient.id == data.patientId) {
                                listReminder.add(new Drugs(
                                        patient,
                                        data.description,
                                        data.reminderTimestamp
                                ));
                            }
                        }
                    }
                }

                //TODO Future Call Api
                if (listPatient.size() <= 0 ){

                }else{
                listReminder.add(new DrugAction( 1669464398L,1669464398L,listPatient.get(0),"Apiretal 100"));
                listReminder.add(new DrugAction(1667145544L, 1667145544L, listPatient.get(0),"Dalsy 40 mg"));
                listReminder.add(new VitalAction(1668427598L, 1668427598L, listPatient.get(0),"90 ppm"));
                listReminder.add(new VitalAction(1668427598L, 1668427598L, listPatient.get(0),"37º"));
                listReminder.add(new VitalAction(1668427598L, 1668427598L, listPatient.get(0),"12 / 7"));
                listReminder.add(new Vital(1668427598L, 1668427598L, listPatient.get(0),"R.Cardiaco"));
                listReminder.add(new Vital(1668427598L, 1668427598L, listPatient.get(0),"Temperatura"));
                listReminder.add(new Vital(1668427598L, 1668427598L, listPatient.get(0),"Tensión"));
                listReminder.add(new InCharge(1642161998L,listPatient.get(0), "Carlos"));
                listReminder.add(new InCharge(1647605198L,listPatient.get(0), "Susana"));

                listReminder.add(new DrugAction(1662894398L, 1662894398L, listPatient.get(0),"Dalsy 40 mg"));
                listReminder.add(new VitalAction(1662980798L, 1662980798L, listPatient.get(0),"38º"));
                listReminder.add(new Vital(1662980798L, 1662980798L, listPatient.get(0),"Temperatura"));
                listReminder.add(new InCharge(1642161998L,listPatient.get(0), "Juan"));
                listReminder.add(new InCharge(1647605198L,listPatient.get(0), "Marta"));

                listReminder.add(new DrugAction(1667145544L, 1667145544L, listPatient.get(0),"Dalsy 20 mg"));
                listReminder.add(new VitalAction(1666436798L, 1666436798L, listPatient.get(0),"37º"));
                listReminder.add(new Vital(1666436798L, 1666436798L, listPatient.get(0),"Temperatura"));
                listReminder.add(new InCharge(1642161998L,listPatient.get(0), "Mario"));
                listReminder.add(new InCharge(1647605198L,listPatient.get(0), "Nuria"));
                }


                recyclerViewPatient.setAdapter(patientRvAdapter);
                patientRvAdapter.notifyDataSetChanged();
                recyclerViewReminder.setAdapter(reminderRvAdapter);
                reminderRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RespuestaGetDto> call, Throwable t) {
                //TODO Toast
                Log.d("api", "FALLO ");
            }
        });
    }

    private void findView() {
        userNameTV = findViewById(R.id.userNameTV);
        informesBT = findViewById(R.id.informesBT);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    @Override
    public void onItemClickReminder(int position) {
    }

    @Override
    public void onItemClickPatient(int position) {
        Patient patient = listPatient.get(position);
        goToDetailPatient(patient);
    }

    //TODO programarlo por popBackStack(String, int)
    @Override
    public void onBackPressed(){

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportFragmentManager().popBackStack();
            patientRvAdapter.holderPatient = true;
            recyclerViewPatient.setAdapter(patientRvAdapter);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            informesBT.setVisibility(View.VISIBLE);
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
            getSupportFragmentManager().popBackStack();
            patientRvAdapter.holderPatient = false;
            recyclerViewPatient.setAdapter(patientRvAdapter);
            getSupportActionBar().setTitle("");
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    public void goToFicha(View view){
        patientRvAdapter.holderPatient = false;
        informesBT.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putSerializable("ARRAYPATIENTS", listPatient);
        bundle.putSerializable("TOKEN", login.token);

        allPatientsFragment = new AllPatientsFragment();
        allPatientsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionFicha = getSupportFragmentManager().beginTransaction();
        fragmentTransactionFicha.replace(R.id.home_layout, allPatientsFragment).addToBackStack("allPatientsFragment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("A tu cargo");
        fragmentTransactionFicha.commit();
    }

    public void goToCreateFicha(View view){
        patientRvAdapter.holderPatient = false;

        Bundle bundle = new Bundle();
        bundle.putString("TOKEN", login.token);
        createPatientFragment = new CreatePatientFragment();
        createPatientFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionCreateFicha = getSupportFragmentManager().beginTransaction();
        fragmentTransactionCreateFicha.replace(R.id.fichas_layout, createPatientFragment).addToBackStack("createPatientFragment");
        getSupportActionBar().setTitle("Añadir persona a mi cargo");
        fragmentTransactionCreateFicha.commit();
    }

    public void goToDetailPatient(Patient patient){
        informesBT.setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailPatientFragment fragment = new DetailPatientFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Patient", patient);
        bundle.putSerializable("Reminder", listReminder);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_layout, fragment).addToBackStack("detailPatientFragment");
        getSupportActionBar().setTitle(patient.fullName);
        namePatientSelect = patient.fullName;
        transaction.commit();
    }


    public void goToCreateMeeting (View view){
        createMeetingFragment = new CreateMeetingFragment();
        FragmentTransaction fragmentTransactionMeeting = getSupportFragmentManager().beginTransaction();
        fragmentTransactionMeeting.replace(R.id.detail_patient_layout, createMeetingFragment).addToBackStack("createMeetingFragment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nueva cita");
        fragmentTransactionMeeting.commit();
    }

    public void goToCreateDrug (View view){
        createDrugFragment = new CreateDrugFragment();
        FragmentTransaction fragmentTransactionMeeting = getSupportFragmentManager().beginTransaction();
        fragmentTransactionMeeting.replace(R.id.detail_patient_layout, createDrugFragment).addToBackStack("createDrugFragment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nueva prescripción de medicamento");
        fragmentTransactionMeeting.commit();
    }

    public void backToFicha(View view){
        patientRvAdapter.holderPatient = false;
        recyclerViewPatient.setAdapter(patientRvAdapter);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(createPatientFragment);
        getSupportActionBar().setTitle("A tu cargo");
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.commit();
    }

    public void backToDetailPatientMeeting(View view){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(createMeetingFragment);
        getSupportActionBar().setTitle(namePatientSelect);
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.commit();
    }

    public void backToDetailPatientDrug(View view){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(createDrugFragment);
        getSupportActionBar().setTitle(namePatientSelect);
        getSupportFragmentManager().popBackStack();
        fragmentTransaction.commit();
    }

    public void recibirDatosPatient(Patient patient){
        listPatient.add(patient);
        patientRvAdapter.holderPatient = true;
        recyclerViewPatient.setAdapter(patientRvAdapter);
    }

    public void goToAddReminder(){
        MenuPatientFragment menuPatientFragment = new MenuPatientFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.detail_patient_layout, menuPatientFragment).addToBackStack("menuPatientFragment");
        transaction.commit();
    }
}