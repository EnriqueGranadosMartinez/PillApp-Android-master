package com.pillapp.views.home.fragments.patients;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pillapp.R;
import com.pillapp.models.Patient;
import com.pillapp.services.RetrofitMethods;
import com.pillapp.views.home.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreatePatientFragment extends Fragment implements View.OnClickListener{


    EditText nombreET, pesoET, notasET;
    Button crearPatientBT, cancelarPatientBT;
    String token;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CreatePatientFragment() {

    }


    public static CreatePatientFragment newInstance(String param1, String param2) {
        CreatePatientFragment fragment = new CreatePatientFragment();
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
        View view =inflater.inflate(R.layout.fragment_create_fichas, container, false);
        findView(view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Añadir persona a mi cargo");
        token();
        crearPatientBT.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {

        if (nombreET.getText().toString().isEmpty() ) {
            nombreET.setError("introduzca un nombre");
        }else if (pesoET.getText().toString().isEmpty()) {
            pesoET.setError("introduzca peso");
        }else if (notasET.getText().toString().isEmpty()) {
            notasET.setError("introduzca nota");

        } else {
            callApiPatient(view);
        }

    }

    public void callApiPatient(View view){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitMethods retrofitMethods = retrofit.create(RetrofitMethods.class);

            Patient patient = new Patient(nombreET.getText().toString(), notasET.getText().toString(), Integer.parseInt(pesoET.getText().toString()));
            Call<Patient> call = retrofitMethods.PostPatient("Bearer " + token, patient);

            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {

                    if (response.isSuccessful()) {
                        mandarDatosPatient(response.body());
                        goToFichaF(view);
                    } else {
                        try {
                            JSONArray respuesta = new JSONArray(response.errorBody().string());
                            JSONObject respuestaClase = respuesta.getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    //TODO toast
                    Log.d("apionFailure", "FALO API");
                }
            });
        }


    public void mandarDatosPatient(Patient patient ){
        ((HomeActivity)getActivity()).recibirDatosPatient(patient);

    }


    public void goToFichaF (View view){
        ((HomeActivity)getActivity()).backToFicha(view);
    }

    public void token(){
        token = getArguments().getString ("TOKEN");
    }

    private void findView(View view) {
        nombreET = view.findViewById(R.id.nombreET);
        pesoET = view.findViewById(R.id.pesoET);
        notasET = view.findViewById(R.id.notasET);
        crearPatientBT = view.findViewById(R.id.crearPatientBT);
        cancelarPatientBT = view.findViewById(R.id.cancelarPatientBT);
    }

}