package com.example.shopfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.shopfood.Activity.main.MainActivity;
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