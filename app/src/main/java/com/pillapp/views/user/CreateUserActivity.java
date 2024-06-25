package com.pillapp.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pillapp.R;
import com.pillapp.models.User;
import com.pillapp.services.RetrofitMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateUserActivity extends AppCompatActivity {

    String email;
    String name;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        EditText ETemail = findViewById(R.id.ETmail);
        EditText ETPassword = findViewById(R.id.ETpassword);
        EditText ETRepeatPasword = findViewById(R.id.ETRepeatPassword);
        EditText ETUser = findViewById(R.id.ETUserName);
        Button buttonRegister = findViewById(R.id.button);
        CheckBox checkBoxAcept = findViewById(R.id.checkBoxAcept);

        checkBoxListener();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO refactorizar todo en métodos
                if (ETemail.getText().toString().isEmpty() ) {
                    ETemail.setError("introduzca un email");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(ETemail.getText().toString()).matches()){
                    ETemail.setError("introduzca un email válido");
                }
                 else if (ETPassword.getText().toString().isEmpty()) {
                     ETPassword.setError("introduzca contraseña");
                } else if (ETRepeatPasword.getText().toString().isEmpty()) {
                     ETRepeatPasword.setError("repita la contraseña");
                } else if (!ETPassword.getText().toString().equals(ETRepeatPasword.getText().toString())) {
                     ETPassword.setError("las contraseñas no coinciden");
                     ETRepeatPasword.setError("las contraseñas no coinciden");
                } else if (ETUser.getText().toString().isEmpty()) {
                     ETUser.setError("introduzca nombre de usuario");
                } else if (!checkBoxAcept.isChecked()) {
                   checkBoxAcept.setError("acepte terminos y condiciones");
                } else {

                    email = ETemail.getText().toString();
                    pass = ETPassword.getText().toString();
                    name = ETUser.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8080")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitMethods retrofitMethods = retrofit.create(RetrofitMethods.class);

                    User user = new User(ETemail.getText().toString(), ETUser.getText().toString(), ETPassword.getText().toString());

                    retrofitMethods.PostData(user);
                    Call<ResponseBody> call = retrofitMethods.PostData(user);

                    call.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.isSuccessful()) {
                                Log.d("api successful", String.valueOf(response.code()));
                                goToLogin(view);
                            } else {
                                Log.d("api NOT successful", String.valueOf(response.code()));
                                try {
                                    JSONArray respuesta = new JSONArray(response.errorBody().string());
                                    JSONObject respuestaClase = respuesta.getJSONObject(0);
                                    Log.d("api NOT successful else ", String.valueOf(respuestaClase.get("field")));
                                    if (respuestaClase.get("field").equals("password") && respuestaClase.get("code").equals("Length")) {
                                        Log.d("api fallo contraseña", "fallo contraseña");
                                        ETPassword.setError("la contraseña tiene que tener entre 8 y 20 caracteres");
                                    } else if(respuestaClase.get("field").equals("password") && respuestaClase.get("code").equals("Pattern")){
                                        ETPassword.setError("la contraseña tiene que tener una mayuscula, un número y un caracter especial");
                                    } else if (respuestaClase.get("code").equals("UniqueUserName")) {
                                        ETUser.setError("Usuario ya existente");
                                    } else if (respuestaClase.get("code").equals("UniqueEmail")) {
                                        ETemail.setError("Email ya existente");
                                    } else {

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }
    public void goToLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkBoxListener(){
        CheckBox checkBoxAcept=(CheckBox)findViewById(R.id.checkBoxAcept);
        checkBoxAcept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkBoxAcept.isChecked())
                {
                   checkBoxAcept.setError(null);
                }
            }
        });
    }
}