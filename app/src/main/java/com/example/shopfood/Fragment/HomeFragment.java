package com.example.shopfood.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.shopfood.Activity.DetailMenuActivity;
import com.example.shopfood.Adapter.AdapterRCVMenuProduct;
import com.example.shopfood.Adapter.AdapterRCVProduct;
import com.example.shopfood.Modal.Product;
import com.example.shopfood.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    List<Product> list;
    AdapterRCVProduct adapterRCVProduct;
    AdapterRCVMenuProduct adapterRCVMenuProduct;
    RecyclerView rcvProduct;
    RecyclerView rcvMenuProduct;
    LinearLayoutManager layoutManager;
    Intent intent;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // anh xa
       rcvProduct = view.findViewById(R.id.productReycleView);
       rcvMenuProduct = view.findViewById(R.id.productMenuRCV);
       // add list
        addList();
       // set adapter
        setAdapterRCVProduct();
        setAdapterRCVMenuProduct();



    }


    public void addList(){
        list = new ArrayList<>();
        list.add(new Product(1,"Burger",10));
        list.add(new Product(2,"Burger",12));
        list.add(new Product(3,"Burger",13));
        list.add(new Product(4,"Burger",15));
        list.add(new Product(5,"Burger",17));
    }

    public void setAdapterRCVProduct(){
        // rcv product
        layoutManager = new LinearLayoutManager(getContext());
        adapterRCVProduct = new AdapterRCVProduct(getActivity(), list);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(adapterRCVProduct);
    }
    public void setAdapterRCVMenuProduct(){
        // rcv menu prodcut
        layoutManager = new LinearLayoutManager(getContext());
        adapterRCVMenuProduct = new AdapterRCVMenuProduct(getActivity(), list);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMenuProduct.setLayoutManager(layoutManager);
        rcvMenuProduct.setAdapter(adapterRCVMenuProduct);
    }
}