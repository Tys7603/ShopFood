package com.example.shopfood.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopfood.activity.welcome.OnboardingActivity;
import com.example.shopfood.R;
import com.example.shopfood.modal.User;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName, tvEmail, tvPass, tvSDT, tvAddress, tvRole;
    Button btnLogOut;
    LinearLayout btnBack;
    Gson mGson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setText();
        onClick();
    }
    // anh xa view
    public void init(){
        mGson = new Gson();
        tvName = findViewById(R.id.tv_nameProfile);
        tvEmail = findViewById(R.id.tv_emailProfile);
        tvPass = findViewById(R.id.tv_passProfile);
        tvAddress = findViewById(R.id.tv_addressProfile);
        tvSDT = findViewById(R.id.tv_sdtProfile);
        tvRole = findViewById(R.id.tv_roleProfile);
        btnBack = findViewById(R.id.btn_backProfile);
        btnLogOut = findViewById(R.id.btn_logOut);
    }
    public void onClick(){
        btnBack.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, MainActivity.class)));
        btnLogOut.setOnClickListener(view -> logOut());
    }
    public void setText(){
        User user = convertObject();
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        tvPass.setText(user.getPassword());
        tvSDT.setText(user.getSdt());
        tvAddress.setText(user.getAddress());
        tvRole.setText(user.getRole());
    }
    private User convertObject() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        String strUser  = sharedPreferences.getString("user","");
        User user = mGson.fromJson(strUser, User.class);
        return user;
    }
    public void logOut(){
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(ProfileActivity.this, OnboardingActivity.class));
    }

}