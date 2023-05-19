package com.example.shopfood.Fragment;

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

import com.example.shopfood.Adapter.AdapterRCVProduct;
import com.example.shopfood.Modal.Product;
import com.example.shopfood.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    List<Product> list;
    AdapterRCVProduct adapterRCVProduct;
    RecyclerView rcvProduct;

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

        list = new ArrayList<>();
        list.add(new Product(1,"Burger",10));
        list.add(new Product(2,"Burger",12));
        list.add(new Product(3,"Burger",13));
        list.add(new Product(4,"Burger",15));
        list.add(new Product(5,"Burger",17));

        adapterRCVProduct = new AdapterRCVProduct(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setAdapter(adapterRCVProduct);
    }
}