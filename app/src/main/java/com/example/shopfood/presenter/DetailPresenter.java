package com.example.shopfood.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.DetailInterface;
import com.example.shopfood.modal.Cart;
import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.StatusCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    DetailInterface mDetailInterface;
    ProgressDialog mProgressDialog;

    public DetailPresenter(DetailInterface mDetailInterface) {
        this.mDetailInterface = mDetailInterface;
    }

    public void addToCart(String idFood, String quantity, String idUser , Context context){
        mProgressDialog = ProgressDialog.show(context, "", "Add ... ");
        ApiService.API_SERVICE.addToCart(idFood, quantity, idUser).enqueue(new Callback<StatusCart>() {
            @Override
            public void onResponse(Call<StatusCart> call, Response<StatusCart> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    mDetailInterface.onMessage();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<StatusCart> call, Throwable t) {
                mProgressDialog.dismiss();
            }
        });
    }
}
