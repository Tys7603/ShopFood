package com.example.shopfood.Activity.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopfood.Adapter.OnBoardingVPAdapter;
import com.example.shopfood.R;

import me.relex.circleindicator.CircleIndicator3;

public class OnboardingActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TextView btnSkip;
    ImageView btnNext;
    ConstraintLayout layout_Controls;
    CircleIndicator3 circleIndicator3;
    OnBoardingVPAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
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
        adapter = new OnBoardingVPAdapter(this);
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

}