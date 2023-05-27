package com.example.shopfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.shopfood.Adapter.AdapterRCVMenuProduct;
import com.example.shopfood.Adapter.AdapterRCVProduct;
import com.example.shopfood.Adapter.AdapterViewPager2Auto;
import com.example.shopfood.DepthPageTransformer;
import com.example.shopfood.Modal.Photo;
import com.example.shopfood.Modal.Product;
import com.example.shopfood.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    List<Product> list;
    List<Photo> listPhoto;
    AdapterRCVProduct adapterRCVProduct;
    AdapterRCVMenuProduct adapterRCVMenuProduct;
    RecyclerView rcvProduct;
    RecyclerView rcvMenuProduct;
    LinearLayoutManager layoutManager;
    ViewPager2 mViewPager2;
    AdapterViewPager2Auto mAdapterViewPager2Auto;
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
        hidenStatus();
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
        mAdapterViewPager2Auto = new AdapterViewPager2Auto(listPhoto, getApplicationContext());
        mViewPager2.setAdapter(mAdapterViewPager2Auto);
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
        adapterRCVProduct = new AdapterRCVProduct(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(adapterRCVProduct);
    }
    public void setAdapterRCVMenuProduct(){
        // rcv menu prodcut
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapterRCVMenuProduct = new AdapterRCVMenuProduct(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMenuProduct.setLayoutManager(layoutManager);
        rcvMenuProduct.setAdapter(adapterRCVMenuProduct);
    }
    public void onClick(){
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