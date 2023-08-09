package com.example.shopfood.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.ProfileInterface;
import com.example.shopfood.modal.StatusUser;
import com.example.shopfood.modal.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    ProfileInterface mProfileInterface;
    Context context;
    ProgressDialog mProgressDialog;
    Gson mGson;
    public ProfilePresenter(ProfileInterface mProfileInterface, Context context) {
        this.mProfileInterface = mProfileInterface;
        this.context = context;
    }

    public boolean updateInformationUser(String email, String pass, String name, User user){
        if (validateInformation(pass,name)){
            mProgressDialog = ProgressDialog.show(context, "","Updating ... ");
            ApiService.API_SERVICE.updateInformationUser(email, pass, name).enqueue(new Callback<StatusUser>() {
                @Override
                public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
                    mProgressDialog.dismiss();
                    if (response.body().getStatus().equals(Constant.SUCCESS)){
                        rememberUser(convertJson(user), context);;
                        mProfileInterface.notificationSuccess(true);

                    }else {
                        mProfileInterface.notificationSuccess(false);
                    }
                }

                @Override
                public void onFailure(Call<StatusUser> call, Throwable t) {
                    mProgressDialog.dismiss();
                }
            });
            return true;
        }
        return false;
    }

    public void updateContactUser(String email, String sdt, String address, User user){
        mProgressDialog = ProgressDialog.show(context, "","Updating ... ");
        ApiService.API_SERVICE.updateContactUser(email, sdt, address).enqueue(new Callback<StatusUser>() {
            @Override
            public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
                mProgressDialog.dismiss();
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    rememberUser(convertJson(user), context);
                    mProfileInterface.notificationSuccess(true);
                }else {
                    mProfileInterface.notificationSuccess(false);
                }
            }

            @Override
            public void onFailure(Call<StatusUser> call, Throwable t) {
                mProgressDialog.dismiss();
            }
        });
    }

    public void rememberUser(String user, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", String.valueOf(user));
        editor.apply();
    }
    private String convertJson(User user) {
        mGson = new Gson();
        return mGson.toJson(user);
    }
    public boolean validateInformation(String pass, String name){
        if (pass.isEmpty() || name.isEmpty()){
            mProfileInterface.onValidate();
            return false;
        }
        return true;
    }
}
