package com.example.shopfood.api;

import com.example.shopfood.Modal.StatusUser;
import com.example.shopfood.Modal.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CallApi {
    CallApi callApi = new Retrofit.Builder()
            .baseUrl(ManagerUrl.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CallApi.class);

    @GET(ManagerUrl.URL_GET_LIST_USER)
    Call<List<User>> getListUser ();

    @FormUrlEncoded
    @POST(ManagerUrl.URL_CREATE_ACCOUNT)
    Call<StatusUser> createUser(@Field("email") String email, @Field("fullname") String fullName, @Field("password") String password);
}
