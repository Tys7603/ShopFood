package com.example.shopfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopfood.activity.main.MainActivity;
import com.example.shopfood.R;
import com.example.shopfood.interfacee.DetailInterface;
import com.example.shopfood.modal.User;
import com.example.shopfood.presenter.DetailPresenter;
import com.google.gson.Gson;

public class DetailMenuActivity extends AppCompatActivity implements DetailInterface {
    LinearLayout btnBack;
    ImageView foodImg;
    TextView tvName, tvPrice, tvContent;
    Button btnAddToCart;
    DetailPresenter mDetailPresenter;
    Intent intent;
    Gson mGson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        init();
        onClick();
        setValue();
    }
    public void init(){
        mDetailPresenter = new DetailPresenter(this);
        intent = getIntent();
        btnBack = findViewById(R.id.btnBack);
        tvName = findViewById(R.id.nameFoodDetial);
        tvPrice = findViewById(R.id.foodPriceDetail);
        tvContent = findViewById(R.id.contentFoodDetail);
        foodImg = findViewById(R.id.foodImgDetail);
        btnAddToCart = findViewById(R.id.btnAddToChart);
        mGson = new Gson();

    }
    private void onClick() {
        btnBack.setOnClickListener( view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        btnAddToCart.setOnClickListener(view -> {
            String idFood = String.valueOf(intent.getIntExtra("idFood", 1));
            String quantity = "1";
            String idUser = convertObject().getEmail();
            mDetailPresenter.addToCart(idFood, quantity, idUser, this);
        });
    }
    private void setValue() {
        tvName.setText(intent.getStringExtra("foodName"));
        tvPrice.setText(intent.getStringExtra("foodPrice"));
        tvContent.setText(intent.getStringExtra("foodContent"));
        Glide.with(this).load(intent.getStringExtra("foodImg")).into(foodImg);
    }

    private User convertObject() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        String strUser  = sharedPreferences.getString("user","");
        User user = mGson.fromJson(strUser, User.class);
        return user;
    }

    @Override
    public void onMessage() {
        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}