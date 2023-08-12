package com.example.shopfood.presenter;

import android.util.Log;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.MainInterface;
import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.StatusFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainInterface mMainInterface;

    public MainPresenter(MainInterface mMainInterface) {
        this.mMainInterface = mMainInterface;
    }

    public void getListFood(){
        ApiService.API_SERVICE.getListFood().enqueue(new Callback<StatusFood>() {
            @Override
            public void onResponse(Call<StatusFood> call, Response<StatusFood> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Food> foodList = response.body().getFoodList();
                    mMainInterface.listFood(foodList);
                }
            }

            @Override
            public void onFailure(Call<StatusFood> call, Throwable t) {

            }
        });
    }

    public void getListFoodBurger(String id){
        ApiService.API_SERVICE.getListFoodBurger(id).enqueue(new Callback<StatusFood>() {
            @Override
            public void onResponse(Call<StatusFood> call, Response<StatusFood> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Food> foodList = response.body().getFoodList();
                    mMainInterface.listFoodBurger(foodList);
                }
            }

            @Override
            public void onFailure(Call<StatusFood> call, Throwable t) {

            }
        });
    }

    public void getListFoodPizza(String id){
        ApiService.API_SERVICE.getListFoodPizza(id).enqueue(new Callback<StatusFood>() {
            @Override
            public void onResponse(Call<StatusFood> call, Response<StatusFood> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Food> foodList = response.body().getFoodList();
                    mMainInterface.listFoodPizza(foodList);
                }
            }

            @Override
            public void onFailure(Call<StatusFood> call, Throwable t) {

            }
        });
    }

    public void getListFoodSandwich(String id){
        ApiService.API_SERVICE.getListFoodSandwich(id).enqueue(new Callback<StatusFood>() {
            @Override
            public void onResponse(Call<StatusFood> call, Response<StatusFood> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Food> foodList = response.body().getFoodList();
                    mMainInterface.listFoodSandwich(foodList);
                }
            }

            @Override
            public void onFailure(Call<StatusFood> call, Throwable t) {

            }
        });
    }

}
