package com.example.shopfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.shopfood.activity.main.MainActivity;
import com.example.shopfood.R;

public class DetailMenuActivity extends AppCompatActivity {
    LinearLayout btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        init();
    }

    public void init(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener( view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }

}