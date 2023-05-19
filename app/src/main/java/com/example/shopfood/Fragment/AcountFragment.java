package com.example.shopfood.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopfood.Adapter.ViewPager2Adapte_Acount;
import com.example.shopfood.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class AcountFragment extends Fragment {

    ViewPager2Adapte_Acount viewPager2Adapte_acount;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    public AcountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = view.findViewById(R.id.viewPager2_acount);
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager2Adapte_acount = new ViewPager2Adapte_Acount(getActivity());

        viewPager2.setAdapter(viewPager2Adapte_acount);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 : tab.setText("Create Account");

                        break;
                    case 1 : tab.setText("Login");
                        break;
                }
            }
        }).attach();


    }



}