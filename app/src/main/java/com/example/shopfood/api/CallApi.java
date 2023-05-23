package com.example.shopfood.api;

import com.example.shopfood.Modal.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface CallApi {
    @GET(ManagerUrl.URL_GET_LIST_USER)
    Call<List<User>> getListUser();
}
