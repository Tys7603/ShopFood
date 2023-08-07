package com.example.shopfood.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.example.shopfood.adapter.MenuRCVAdapter;
import com.example.shopfood.adapter.FoodRCVAdapter;
import com.example.shopfood.adapter.MainVPAdapter;
import com.example.shopfood.DepthPageTransformer;
import com.example.shopfood.modal.Photo;
import com.example.shopfood.modal.Product;
import com.example.shopfood.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    List<Product> list;
    List<Photo> listPhoto;
    FoodRCVAdapter FoodRCVAdapter;
    MenuRCVAdapter menuRCVAdapter;
    RecyclerView rcvProduct;
    RecyclerView rcvMenuProduct;
    LinearLayoutManager layoutManager;
    ViewPager2 mViewPager2;
    MainVPAdapter mMainVPAdapter;
    CircleIndicator3 mIndicator3;
    LinearLayout btnActivityCart, btnActivityProfile, btnActivityChat;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mViewPager2.getCurrentItem() == listPhoto.size() - 1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(mViewPager2.getCurrentItem() + 1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // add list
        addList();
        // set adapter
        setAdapterRCVProduct();
        setAdapterRCVMenuProduct();
        setAdapterViewPager2Auto();
        // on click
         onClick();
    }

    public void init(){
        btnActivityCart = findViewById(R.id.btnActivityCart);
        btnActivityProfile = findViewById(R.id.btnActivityProfile);
        btnActivityChat = findViewById(R.id.btnActivityChat);
        rcvProduct = findViewById(R.id.productReycleView);
        rcvMenuProduct = findViewById(R.id.productMenuRCV);
        mViewPager2 = findViewById(R.id.viewPagerAuto);
        mIndicator3 = findViewById(R.id.indicatorHome);
    }
    public void addList(){
        list = new ArrayList<>();
        list.add(new Product(1,"Burger",10));
        list.add(new Product(2,"Burger",12));
        list.add(new Product(3,"Burger",13));
        list.add(new Product(4,"Burger",15));
        list.add(new Product(5,"Burger",17));
        // list img
        listPhoto = new ArrayList<>();
        listPhoto.add(new Photo(R.drawable.banner));
        listPhoto.add(new Photo(R.drawable.banner));
        listPhoto.add(new Photo(R.drawable.banner));
    }
    public void setAdapterViewPager2Auto(){
        mMainVPAdapter = new MainVPAdapter(listPhoto, getApplicationContext());
        mViewPager2.setAdapter(mMainVPAdapter);
        mIndicator3.setViewPager(mViewPager2);
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        mViewPager2.setPageTransformer(new DepthPageTransformer());
    }
    public void setAdapterRCVProduct(){
        // rcv product
        layoutManager = new LinearLayoutManager(getApplicationContext());
        FoodRCVAdapter = new FoodRCVAdapter(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(FoodRCVAdapter);
    }
    public void setAdapterRCVMenuProduct(){
        // rcv menu prodcut
        layoutManager = new LinearLayoutManager(getApplicationContext());
        menuRCVAdapter = new MenuRCVAdapter(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMenuProduct.setLayoutManager(layoutManager);
        rcvMenuProduct.setAdapter(menuRCVAdapter);
    }
    public void onClick(){
        // chuyen man hinh
        btnActivityCart.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OderActivity.class)));
        btnActivityProfile.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        btnActivityChat.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ChatActivity.class)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}