package com.example.shopfood.interfacee;

import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.StatusFood;

import java.util.List;

public interface MainInterface {
    void listFood(List<Food> foodList);
    void listFoodBurger(List<Food> foodListBurger);
    void listFoodPizza(List<Food> foodListPizza);
    void listFoodSandwich(List<Food> foodListSandwich);
}
