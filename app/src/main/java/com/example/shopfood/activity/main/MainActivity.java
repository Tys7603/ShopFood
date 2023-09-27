package com.example.shopfood.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopfood.activity.MealMenuActivity;
import com.example.shopfood.adapter.MenuRCVAdapter;
import com.example.shopfood.adapter.FoodRCVAdapter;
import com.example.shopfood.adapter.MainVPAdapter;
import com.example.shopfood.DepthPageTransformer;
import com.example.shopfood.interfacee.MainInterface;
import com.example.shopfood.modal.Photo;
import com.example.shopfood.modal.Food;
import com.example.shopfood.R;
import com.example.shopfood.modal.User;
import com.example.shopfood.presenter.MainPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity implements MainInterface {
    List<Photo> listPhoto;
    FoodRCVAdapter FoodRCVAdapter;
    MenuRCVAdapter menuRCVAdapter;
    RecyclerView rcvProduct;
    RecyclerView rcvMenuProduct;
    LinearLayoutManager layoutManager;
    ViewPager2 mViewPager2;
    MainVPAdapter mMainVPAdapter;
    CircleIndicator3 mIndicator3;
    LinearLayout btnActivityCart, btnActivityProfile, btnActivityChat, btnBurger, btnPizza, btnSandwich;
    TextView tvBurger, tvPizza, tvSandwich, tvMealMenu, tvDiaChi;
    ImageView ivAnh;
    MainPresenter mMainPresenter;
    Gson mGson;
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
        setAdapterViewPager2Auto();
        // on click
         onClick();
         //set value
        setValue();
    }

    private void setValue() {
        User user = convertObject();
        if (!user.getAddress().isEmpty()){
            tvDiaChi.setText(user.getAddress());
        }
        if (!user.getAvatar().isEmpty()){
            Glide.with(this).load(user.getAvatar()).into(ivAnh);
        }
    }

    public void init(){
        mMainPresenter = new MainPresenter(this);
        btnActivityCart = findViewById(R.id.btnActivityCart);
        btnActivityProfile = findViewById(R.id.btnActivityProfile);
        btnActivityChat = findViewById(R.id.btnActivityChat);
        rcvProduct = findViewById(R.id.productReycleView);
        rcvMenuProduct = findViewById(R.id.productMenuRCV);
        mViewPager2 = findViewById(R.id.viewPagerAuto);
        mIndicator3 = findViewById(R.id.indicatorHome);
        btnBurger = findViewById(R.id.btnBurger);
        btnPizza = findViewById(R.id.btnPizza);
        btnSandwich = findViewById(R.id.btnSandwich);
        tvBurger = findViewById(R.id.tvBurger);
        tvPizza = findViewById(R.id.tvPizza);
        tvSandwich = findViewById(R.id.tvSandwich);
        tvMealMenu = findViewById(R.id.tvMealMenu);
        tvDiaChi = findViewById(R.id.tvDiachiMain);
        ivAnh = findViewById(R.id.ivAnhMain);
        mGson = new Gson();
    }

    public void onClick(){
        // chuyen man hinh
        tvMealMenu.setOnClickListener(view ->startActivity(new Intent(MainActivity.this, MealMenuActivity.class)));
        btnActivityCart.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OderActivity.class)));
        btnActivityProfile.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        btnActivityChat.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ChatActivity.class)));
        // set list food theo loai
        btnBurger.setOnClickListener(view -> mMainPresenter.getListFoodBurger("1"));
        btnPizza.setOnClickListener(view -> mMainPresenter.getListFoodPizza("2"));
        btnSandwich.setOnClickListener(view -> mMainPresenter.getListFoodSandwich("3"));
    }
    public void addList(){
        mMainPresenter.getListFood();
        mMainPresenter.getListFoodBurger("1");
        // list img
        listPhoto = new ArrayList<>();
        listPhoto.add(new Photo(R.drawable.banner));
        listPhoto.add(new Photo(R.drawable.img_3));
        listPhoto.add(new Photo(R.drawable.img_4));
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
    public void setAdapterRCVProduct(List<Food> list){
        // rcv product
        layoutManager = new LinearLayoutManager(getApplicationContext());
        FoodRCVAdapter = new FoodRCVAdapter(this, list);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(FoodRCVAdapter);
    }
    public void setAdapterRCVMenuProduct(List<Food> list){
        // rcv menu prodcut
        layoutManager = new LinearLayoutManager(getApplicationContext());
        menuRCVAdapter = new MenuRCVAdapter(getApplicationContext(), list);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMenuProduct.setLayoutManager(layoutManager);
        rcvMenuProduct.setAdapter(menuRCVAdapter);
    }


    private User convertObject() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        String strUser  = sharedPreferences.getString("user","");
        User user = mGson.fromJson(strUser, User.class);
        return user;
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

    @Override
    public void listFood(List<Food> foodList) {
        setAdapterRCVMenuProduct(foodList);
    }

    @Override
    public void listFoodBurger(List<Food> foodListBurger) {
        btnBurger.setBackgroundResource(R.drawable.bgr_item_bottom_menu1);
        btnPizza.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        btnSandwich.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        tvBurger.setTextColor(getResources().getColor(R.color.white));
        tvPizza.setTextColor(getResources().getColor(R.color.black));
        tvSandwich.setTextColor(getResources().getColor(R.color.black));
        setAdapterRCVProduct(foodListBurger);

    }

    @Override
    public void listFoodPizza(List<Food> foodListPizza) {
        btnBurger.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        btnPizza.setBackgroundResource(R.drawable.bgr_item_bottom_menu1);
        btnSandwich.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        tvBurger.setTextColor(getResources().getColor(R.color.black));
        tvPizza.setTextColor(getResources().getColor(R.color.white));
        tvSandwich.setTextColor(getResources().getColor(R.color.black));
        setAdapterRCVProduct(foodListPizza);

    }

    @Override
    public void listFoodSandwich(List<Food> foodListSandwich) {
        btnBurger.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        btnPizza.setBackgroundResource(R.drawable.bgr_item_bottom_menu2);
        btnSandwich.setBackgroundResource(R.drawable.bgr_item_bottom_menu1);
        tvBurger.setTextColor(getResources().getColor(R.color.black));
        tvPizza.setTextColor(getResources().getColor(R.color.black));
        tvSandwich.setTextColor(getResources().getColor(R.color.white));
        setAdapterRCVProduct(foodListSandwich);

    }
}