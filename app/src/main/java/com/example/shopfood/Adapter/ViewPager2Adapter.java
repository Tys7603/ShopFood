package com.example.shopfood.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfood.Fragment.AcountFragment;
import com.example.shopfood.Fragment.OnboardingFragment;
import com.example.shopfood.Fragment.OnboardingFragment1;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new OnboardingFragment();
            case 1 : return new OnboardingFragment1();
            case 2 : return new AcountFragment();
            default : return new OnboardingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
