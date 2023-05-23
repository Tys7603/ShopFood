package com.example.shopfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.shopfood.Adapter.ViewPager2Adapter_main;
import com.example.shopfood.R;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ViewPager2Adapter_main adapterMain;
    LinearLayout btnActivityCart, btnActivityProfile, btnActivityChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        hidenStatus();
    }

    public void init(){
        btnActivityCart = findViewById(R.id.btnActivityCart);
        btnActivityProfile = findViewById(R.id.btnActivityProfile);
        btnActivityChat = findViewById(R.id.btnActivityChat);
        viewPager2 = findViewById(R.id.viewPage2Main);
        adapterMain = new ViewPager2Adapter_main(this);
        viewPager2.setAdapter(adapterMain);
        // chuyen man hinh
        btnActivityCart.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OderActivity.class)));
        btnActivityProfile.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        btnActivityChat.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ChatActivity.class)));
    }

    public void hidenStatus(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}