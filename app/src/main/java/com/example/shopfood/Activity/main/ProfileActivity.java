package com.example.shopfood.Activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopfood.Activity.welcome.OnboardingActivity;
import com.example.shopfood.R;

public class ProfileActivity extends AppCompatActivity {
    String name, email, pass;
    TextView tvName, tvEmail, tvPass;
    Button btnLogOut;
    LinearLayout btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setText();
        onClick();
    }

    public void init(){
        tvName = findViewById(R.id.tv_nameProfile);
        tvEmail = findViewById(R.id.tv_emailProfile);
        tvPass = findViewById(R.id.tv_passProfile);
        btnBack = findViewById(R.id.btn_backProfile);
        btnLogOut = findViewById(R.id.btn_logOut);
    }

    public void setText(){
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        name  = sharedPreferences.getString("name","");
        email =  sharedPreferences.getString("email","");
        pass  =  sharedPreferences.getString("pass","");
        tvName.setText(name);
        tvEmail.setText(email);
        tvPass.setText(pass);
    }

    public void onClick(){
        btnBack.setOnClickListener(view -> startActivity(new Intent(ProfileActivity.this, MainActivity.class)));
        btnLogOut.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(ProfileActivity.this, OnboardingActivity.class));
        });
    }


}