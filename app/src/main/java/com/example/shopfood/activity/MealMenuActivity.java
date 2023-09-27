package com.example.shopfood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.shopfood.R;
import com.example.shopfood.activity.main.MainActivity;
import com.example.shopfood.adapter.MenuRCVAdapter;
import com.example.shopfood.interfacee.MenuInterface;
import com.example.shopfood.modal.Food;
import com.example.shopfood.presenter.MenuPresenter;

import java.util.List;

public class MealMenuActivity extends AppCompatActivity implements MenuInterface {
    RecyclerView rcvMenu;
    LinearLayoutManager layoutManager;
    MenuRCVAdapter menuRCVAdapter;
    MenuPresenter mMenuPresenter;
    LinearLayout btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);
        initUI();
        onClick();
    }

    private void onClick() {
        mMenuPresenter.getListFood();
        btnBack.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
    }

    private void initUI() {
        mMenuPresenter = new MenuPresenter(this);
        rcvMenu = findViewById(R.id.mealMenuRCV);
        btnBack = findViewById(R.id.btn_backMenu);
    }

    public void setAdapterRCVMenuProduct(List<Food> list){
        // rcv menu prodcut
        layoutManager = new LinearLayoutManager(getApplicationContext());
        menuRCVAdapter = new MenuRCVAdapter(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMenu.setLayoutManager(layoutManager);
        rcvMenu.setAdapter(menuRCVAdapter);
    }

    @Override
    public void listFood(List<Food> foodList) {
        setAdapterRCVMenuProduct(foodList);
    }
}