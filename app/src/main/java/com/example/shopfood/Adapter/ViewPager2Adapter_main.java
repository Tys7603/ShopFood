package com.example.shopfood.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfood.Fragment.HomeFragment;

public class ViewPager2Adapter_main extends FragmentStateAdapter {
    public ViewPager2Adapter_main(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
