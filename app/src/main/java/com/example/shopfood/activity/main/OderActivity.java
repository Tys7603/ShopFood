package com.example.shopfood.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfood.R;
import com.example.shopfood.adapter.MenuRCVAdapter;
import com.example.shopfood.adapter.OrderRCVAdapter;
import com.example.shopfood.interfacee.OrderInterface;
import com.example.shopfood.modal.Cart;
import com.example.shopfood.modal.Food;
import com.example.shopfood.modal.User;
import com.example.shopfood.presenter.OrderPresenter;
import com.google.gson.Gson;

import java.util.List;

public class OderActivity extends AppCompatActivity implements OrderInterface {
    RecyclerView rcvOrder;
    OrderPresenter mOrderPresenter;
    Gson mGson;
    TextView tvDelivery, tvSub, tvTotal;
    LinearLayout btnBack;
    Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);
        initUI();
        onClick();
    }

    private void initUI() {

        btnBack = findViewById(R.id.btnBackOrder);
        btnOrder = findViewById(R.id.btnPlaceOder);
        rcvOrder = findViewById(R.id.orderRCV);
        tvDelivery = findViewById(R.id.tvDelivery);
        tvSub = findViewById(R.id.tvSubTotal);
        tvTotal = findViewById(R.id.tvTotal);
        mOrderPresenter = new OrderPresenter(this, this);
        mGson = new Gson();
    }

    private void onClick() {
        mOrderPresenter.getListCart(convertObject().getEmail());
        btnBack.setOnClickListener(view -> startActivity(new Intent(this,MainActivity.class)));
        btnOrder.setOnClickListener(view -> mOrderPresenter.deleteByEmailCart(convertObject().getEmail()));
    }

    public void setAdapterRCVOrder(List<Cart> list){
        // rcv
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        OrderRCVAdapter adapter = new OrderRCVAdapter(this , list);
        rcvOrder.setLayoutManager(layoutManager);
        rcvOrder.setAdapter(adapter);
    }

    private User convertObject() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        String strUser  = sharedPreferences.getString("user","");
        User user = mGson.fromJson(strUser, User.class);
        return user;
    }

    @Override
    public void listCart(List<Cart> cartList) {
        setAdapterRCVOrder(cartList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void totalBill(float total, float sub) {
        String o = "00";
        tvDelivery.setText("20.000");
        tvSub.setText(sub+o);
        tvTotal.setText(total+o);
    }

    @Override
    public void onMessage() {
        tvDelivery.setText("");
        tvSub.setText("");
        tvTotal.setText("");
        recreate();
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMessage() {
        recreate();
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }
}