package com.pillapp.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pillapp.R;
import com.pillapp.dto.LoginOkDto;
import com.pillapp.models.Login;
import com.pillapp.services.RetrofitMethods;
import com.pillapp.views.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText ETEmail, ETPassword;
    CheckBox CBRecordar;
    TextView TVOlvidadoPass, TVCrearCuenta, TVNoCuenta, TVError;
    Button BTAcceder;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PillApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
    }

    public void toAccess(View view) {
        email = ETEmail.getText().toString();
            if (isValidEmail(email)) {
                if (ETPassword.getText().toString().isEmpty()) {
                    ETPassword.setError("Introduzca Password");
                } else {
                    callApi();
                }
            } else {
                ETEmail.setError("Introduzca email correcto!");
            }
    }

    public void callApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitMethods retrofitMethods = retrofit.create(RetrofitMethods.class);

        Login login = new Login(ETEmail.getText().toString(), ETPassword.getText().toString());

        retrofitMethods.PostLogin(login);
        Call<LoginOkDto> call = retrofitMethods.PostLogin(login);

        call.enqueue(new Callback<LoginOkDto>() {
            @Override
            public void onResponse(Call<LoginOkDto> call, Response<LoginOkDto> response) {
                if (response.isSuccessful()) {
                    LoginOkDto respuestaApi =  response.body();
                    goToHome(respuestaApi);
                } else {
                    ETEmail.setError("Introduzca email correcto!");
                    ETPassword.setError("Introduzca Password correcto!");
                }
            }

            @Override
            public void onFailure(Call<LoginOkDto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo API", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void goToRegister(View view) {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

    public void goToHome(LoginOkDto respuestaApi) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("LOGIN", respuestaApi);
        startActivity(intent);
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void findView() {
        ETEmail = findViewById(R.id.ETEmail);
        ETPassword = findViewById(R.id.ETPassword);
        TVError = findViewById(R.id.TVError);
        CBRecordar = findViewById(R.id.CBRecordar);
        TVOlvidadoPass = findViewById(R.id.TVOlvidadoPass);
        BTAcceder = findViewById(R.id.BTAcceder);
        TVCrearCuenta = findViewById(R.id.TVCrearCuenta);
        TVNoCuenta = findViewById(R.id.TVNoCuenta);
    }

}