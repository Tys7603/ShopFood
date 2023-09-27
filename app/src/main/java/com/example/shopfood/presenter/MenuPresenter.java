package com.example.shopfood.presenter;

import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.interfacee.MainInterface;
import com.example.shopfood.interfacee.MenuInterface;
import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.StatusFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuPresenter {

   MenuInterface mMenuInterface;

    public MenuPresenter(MenuInterface mMenuInterface) {
        this.mMenuInterface = mMenuInterface;
    }

    public void getListFood(){
        ApiService.API_SERVICE.getListFood().enqueue(new Callback<StatusFood>() {
            @Override
            public void onResponse(Call<StatusFood> call, Response<StatusFood> response) {
                if (response.body().getStatus().equals(Constant.SUCCESS)){
                    List<Food> foodList = response.body().getFoodList();
                    mMenuInterface.listFood(foodList);
                }
            }

            @Override
            public void onFailure(Call<StatusFood> call, Throwable t) {

            }
        });
    }
}
