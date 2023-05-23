package com.example.shopfood.api;

import com.example.shopfood.Modal.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface CallApi {

    CallApi CallApi = new Retrofit.Builder()
            .baseUrl(ManagerUrl.ApiLogin)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CallApi.class);

    @GET(ManagerUrl.ApiLogin+"/list-user")
    Call<List<User>> getListUser ();
}
