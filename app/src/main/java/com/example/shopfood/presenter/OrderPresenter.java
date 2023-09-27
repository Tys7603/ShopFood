package com.example.shopfood.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.OrderInterface;
import com.example.shopfood.modal.Cart;
import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.StatusCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPresenter {
    OrderInterface mOrderInterface;
    Context context;
    ProgressDialog mProgressDialog;
    public OrderPresenter(OrderInterface mOrderInterface, Context context) {
        this.mOrderInterface = mOrderInterface;
        this.context = context;
    }

    public void getListCart(String email){
        ApiService.API_SERVICE.getListCart(email).enqueue(new Callback<StatusCart>() {
            @Override
            public void onResponse(Call<StatusCart> call, Response<StatusCart> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Cart> cartList = response.body().getCartList();
                    mOrderInterface.listCart(cartList);
                }
            }

            @Override
            public void onFailure(Call<StatusCart> call, Throwable t) {

            }
        });
    }
    public void calculateBill(float subTotal){
        float total = 20 + subTotal ;
        mOrderInterface.totalBill(total, subTotal);
    }

    public void updateQuantityToCart(String id, String quantity, float subTotal, String email){
        ApiService.API_SERVICE.updateToCart(id, quantity).enqueue(new Callback<StatusCart>() {
            @Override
            public void onResponse(Call<StatusCart> call, Response<StatusCart> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    getListCart(email);
                    calculateBill(subTotal);
                }
            }

            @Override
            public void onFailure(Call<StatusCart> call, Throwable t) {
                Log.d("T", t.toString());
            }
        });
    }

    public void deleteByEmailCart(String email){
        mProgressDialog = ProgressDialog.show(context,"","Deleting ...");
        ApiService.API_SERVICE.deleteByEmailToCart(email).enqueue(new Callback<StatusCart>() {
            @Override
            public void onResponse(Call<StatusCart> call, Response<StatusCart> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    mOrderInterface.onMessage();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<StatusCart> call, Throwable t) {
                mProgressDialog.dismiss();
            }
        });
    }

    public void deleteByIdCart(String id){
        mProgressDialog = ProgressDialog.show(context,"","Deleting ...");
        ApiService.API_SERVICE.deleteToCart(id).enqueue(new Callback<StatusCart>() {
           @Override
           public void onResponse(Call<StatusCart> call, Response<StatusCart> response) {
               if (response.body().getStatus().equals(Constant.SUCCESS)){
                   mOrderInterface.deleteMessage();
                   mProgressDialog.dismiss();
               }
           }

           @Override
           public void onFailure(Call<StatusCart> call, Throwable t) {

           }
       });
    }
}
