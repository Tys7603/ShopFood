package com.example.shopfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopfood.Adapter.ViewPager2Adapter;
import com.example.shopfood.R;

import me.relex.circleindicator.CircleIndicator3;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TextView btnSkip;
    ImageView btnNext;
    ConstraintLayout layout_Controls;
    CircleIndicator3 circleIndicator3;
    ViewPager2Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        hidenStatus();
        init();
        setAdapter();
        onClick();
    }




    // xử lí sự kiển click
    public void onClick(){
        btnSkip.setOnClickListener(view -> viewPager2.setCurrentItem(2));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager2.getCurrentItem() < 2){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else {
                    // thay đổi trạng thái trang đã chọn
                    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        // dc gọi khi trang mới dc gọi
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            if(position == 2 ){
                                layout_Controls.setVisibility(View.GONE);
                            }else {
                                layout_Controls.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        });
    }
    // xét giao dien cho view pager2
    public void setAdapter(){
        adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
    }
    // anh xa view
    public void init(){
        btnSkip = findViewById(R.id.btn_Skip);
        btnNext = findViewById(R.id.btn_next);
        layout_Controls = findViewById(R.id.layout_Controls);
        circleIndicator3 = findViewById(R.id.circleIndicator3);
        viewPager2 = findViewById(R.id.viewPager2);
    }

    // ẩn status
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