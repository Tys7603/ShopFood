package com.example.shopfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfood.Fragment.AcountFragment;
import com.example.shopfood.R;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    String name, email, pass;
    TextView tvName, tvEmail, tvPass;
    Button btnLogOut;
    LinearLayout btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        hidenStatus();
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