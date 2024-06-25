package com.pillapp.services;

import com.pillapp.dto.LoginOkDto;
import com.pillapp.dto.RespuestaGetDto;
import com.pillapp.models.Login;
import com.pillapp.models.Patient;
import com.pillapp.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitMethods {

    @POST("login")
    Call<LoginOkDto> PostLogin(@Body Login login);

    @POST("user")
    Call<ResponseBody> PostData(@Body User user);

    @GET("/")
    Call<RespuestaGetDto> GetData(@Header("Authorization") String auth);

    @POST("patient")
    Call<Patient> PostPatient(@Header("Authorization") String auth, @Body Patient patient);

}
