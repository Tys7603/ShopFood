package com.example.shopfood.api;

import com.example.shopfood.modal.StatusUser;
import com.example.shopfood.modal.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl(Constant.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    // user
    @FormUrlEncoded
    @POST(Constant.LOGIN_USER)
    Call<StatusUser> login (@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.CREATE_ACCOUNT)
    Call<StatusUser> createUser(@Field("email") String email, @Field("password") String password,  @Field("name") String fullName);

    @FormUrlEncoded
    @POST(Constant.UPDATE_INFORMATION_ACCOUNT)
    Call<StatusUser> updateInformationUser(@Field("email") String email, @Field("password") String password,
                                           @Field("name") String fullName);
    @FormUrlEncoded
    @POST(Constant.UPDATE_CONTACT_ACCOUNT)
    Call<StatusUser> updateContactUser(@Field("email") String email,
                                       @Field("sdt") String sdt,  @Field("address") String address);
}
