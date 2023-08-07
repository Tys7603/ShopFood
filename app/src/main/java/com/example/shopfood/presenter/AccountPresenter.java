package com.example.shopfood.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.AccountInterface;
import com.example.shopfood.modal.StatusUser;
import com.example.shopfood.modal.User;
import com.google.gson.Gson;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountPresenter {
    AccountInterface mAccountInterface;
    final String regex = "[^@]{2,64}@[^.]{2,253}\\.[0-9a-z-.]{2,63}";
    ProgressDialog mProgressDialog;
    Gson mGson;
    public AccountPresenter(AccountInterface mAccountInterface) {
        this.mAccountInterface = mAccountInterface;
    }

    // check login
    public boolean validate(String email, String pass){
        if (email.isEmpty() || pass.isEmpty() ){
            mAccountInterface.onMessageValidate();
            return false;
        }
        return true;
    }
    // lưu user khi login thanh cong
    public void rememberUser(String user, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", String.valueOf(user));
        editor.apply();
    }
    // login
    public void login(String email, String pass, Context context){
        if (validate(email, pass)){
            mProgressDialog = ProgressDialog.show(context,"","Login ... ");
            ApiService.API_SERVICE.login(email, pass).enqueue(new Callback<StatusUser>() {
                @Override
                public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
                    assert response.body() != null;
                    if (response.body().getStatus().equals(Constant.SUCCESS)){
                        mProgressDialog.dismiss();
                        String user = convertJson(response.body().getUser());
                        rememberUser(user, context);
                        mAccountInterface.starActivity();
                    }else {
                        mProgressDialog.dismiss();
                        mAccountInterface.onMessage(false);
                    }
                }
                @Override
                public void onFailure(Call<StatusUser> call, Throwable t) {

                }
            });
        }
    }

    private String convertJson(User user) {
        mGson = new Gson();
        return mGson.toJson(user);
    }

    // check dang ky
    public boolean validate(String email, String pass, String name){
        if (!checkInfo(name, email, pass)){
            return false;
        } else if (!checkEmail(email)){
            return false;
        }
        return true ;
    }
    // check nhap day du thong tin
    public boolean checkInfo(String name , String email, String pass){
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()){
            mAccountInterface.onMessageValidate();
            return false;
        }
        return true;
    }
    // check email dung dinh dang
    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            mAccountInterface.onMessageValidateEmail();
            return false;
        }
        return true;
    }

    // dang ky
    public void createUser(String email , String pass, String name,Context context ) {
       if (validate(email, pass, name)){
           mProgressDialog = ProgressDialog.show(context, "","Register ... ");
           ApiService.API_SERVICE.createUser(email, pass, name).enqueue(new Callback<StatusUser>() {
               @Override
               public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
                   assert response.body() != null;
                   if (response.body().getStatus().equals(Constant.SUCCESS)){
                       mProgressDialog.dismiss();
                       mAccountInterface.onMessage(true);
                       mAccountInterface.clearText();
                   }else {
                       mProgressDialog.dismiss();
                       mAccountInterface.onMessage(false);
                   }
               }

               @Override
               public void onFailure(Call<StatusUser> call, Throwable t) {

               }
           });
       }
    }

}
