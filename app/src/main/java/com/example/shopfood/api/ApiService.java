package com.example.shopfood.api;

import com.example.shopfood.modal.Cart;
import com.example.shopfood.modal.StatusCart;
import com.example.shopfood.modal.StatusFood;
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

    @FormUrlEncoded
    @POST(Constant.UPDATE_AVATAR_USER)
    Call<StatusUser> updateAvatarUser(@Field("email") String email, @Field("avatar") String avatar);

    //food
    @POST(Constant.GET_LIST_FOOD)
    Call<StatusFood> getListFood ();
    @FormUrlEncoded
    @POST(Constant.GET_LIST_FOOD_BY_ID)
    Call<StatusFood> getListFoodBurger (@Field("id") String id);
    @FormUrlEncoded
    @POST(Constant.GET_LIST_FOOD_BY_ID)
    Call<StatusFood> getListFoodPizza (@Field("id") String id);
    @FormUrlEncoded
    @POST(Constant.GET_LIST_FOOD_BY_ID)
    Call<StatusFood> getListFoodSandwich (@Field("id") String id);

    //cart
    @FormUrlEncoded
    @POST(Constant.ADD_TO_CART)
    Call<StatusCart> addToCart (@Field("idFood") String idFood, @Field("quantity") String quantity, @Field("idUser") String idUser);
    @FormUrlEncoded
    @POST(Constant.GET_TO_CART)
    Call<StatusCart> getListCart (@Field("email") String email);
    @FormUrlEncoded
    @POST(Constant.UPDATE_TO_CART)
    Call<StatusCart> updateToCart (@Field("id") String id, @Field("quantity") String quantity);
    @FormUrlEncoded
    @POST(Constant.DELETE_TO_CART)
    Call<StatusCart> deleteToCart (@Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.DELETE_BY_EMAIL_TO_CART)
    Call<StatusCart> deleteByEmailToCart (@Field("email") String email);
}
